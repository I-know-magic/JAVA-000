package gateway.example.server.proxy;

import gateway.example.client.NettyClientHandler;
import gateway.example.server.proxy.HttpProxyHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyProxyClient {
    private static Logger logger = LoggerFactory.getLogger(HttpProxyHandler.class);

    private Channel outboundChannel;

    public Channel getOutboundChannel() {
        return outboundChannel;
    }

    public void setOutboundChannel(Channel outboundChannel) {
        this.outboundChannel = outboundChannel;
    }

    private final String remoteHost;
    private final int remotePort;
    public NettyProxyClient(String remoteHost,int remotePort){
        this.remoteHost=remoteHost;
        this.remotePort=remotePort;
    }

//    public  void connect(ChannelHandlerContext ctx,String rHost,int rPort){
    public  void connect(ChannelHandlerContext ctx,Object msg){
        final Channel inboundChannel = ctx.channel();
        logger.info("NettyProxyClient-->"+inboundChannel.localAddress());
        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
                .channel(ctx.channel().getClass())
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(EpollChannelOption.SO_REUSEPORT, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel channel)
                            throws Exception {
                        channel.pipeline().addLast(new HttpClientCodec());
                        channel.pipeline().addLast(new HttpObjectAggregator(65535));
                        channel.pipeline().addLast(new HttpContentDecompressor());
                        channel.pipeline().addLast(new HttpProxyHandler(inboundChannel));
                        channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));


                    }
                });

        ChannelFuture f = b.connect(remoteHost, remotePort);
//        ChannelFuture f = b.connect(rHost, rPort);
        outboundChannel = f.channel();
        logger.info("NettyProxyClient:outboundChannel-->"+outboundChannel.localAddress());
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    logger.info("outboundChannel.isActive()-->"+outboundChannel.isActive());
                    logger.info("future.isSuccess()");
                    future.channel().writeAndFlush(msg);
                } else {
//                    future.channel().close();
                    inboundChannel.close();
                }
            }
        });
    }

}
