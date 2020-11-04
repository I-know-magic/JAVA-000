package gateway.example.server.inbound;

import gateway.example.server.proxy.HttpProxyHandler;
import gateway.example.server.proxy.NettyProxyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyServiceHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpProxyServiceHandler.class);

    private Channel outboundChannel;
    private NettyProxyClient nettyProxyClient;
    private ChannelHandlerContext ctx;

    public HttpProxyServiceHandler(String remoteHost, int remotePort) {
//        nettyProxyClient=new NettyProxyClient(remoteHost,remotePort);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        nettyProxyClient.connect(ctx);
//        outboundChannel=nettyProxyClient.getOutboundChannel();
        this.ctx=ctx;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) {
        logger.info("HttpInboundHandler-msg-->"+msg);
        logger.info("outboundChannel-->"+outboundChannel.localAddress());
        logger.info("outboundChannel.isActive()-->"+outboundChannel.isActive());
        if (outboundChannel.isActive()) {
            logger.info("outboundChannel.writeAndFlush()-->"+msg);

            outboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    if (future.isSuccess()) {
                        ctx.channel().read();
                    } else {
                        future.channel().close();
                    }
                }
            });
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (outboundChannel != null) {
            closeOnFlush(outboundChannel);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        closeOnFlush(ctx.channel());
    }


    public static void closeOnFlush(Channel ch) {
        if (ch.isActive()) {
            ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
    public void  connect(ChannelHandlerContext ctx){
        final Channel inboundChannel = ctx.channel();
        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
                .channel(ctx.channel().getClass())
                .handler(new HttpProxyHandler(inboundChannel))
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect("", 0);
        outboundChannel = f.channel();
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    inboundChannel.read();
                } else {
                    inboundChannel.close();
                }
            }
        });
    }
}
