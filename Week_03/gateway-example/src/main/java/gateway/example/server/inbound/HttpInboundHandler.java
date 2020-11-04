package gateway.example.server.inbound;

import gateway.example.client.NettyClient;
import gateway.example.server.proxy.HttpProxyHandler;
import gateway.example.server.proxy.NettyProxyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    private final String remoteHost="127.0.0.1";
    private final int remotePort=8088;
    private Channel outboundChannel;
    private NettyProxyClient nettyProxyClient;
//    private ChannelHandlerContext ctx;

    public HttpInboundHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        nettyProxyClient.connect(ctx);
//        outboundChannel=nettyProxyClient.getOutboundChannel();
//        this.ctx=ctx;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) {
        logger.info("HttpInboundHandler-msg-->"+msg);
//        解析request,获取remote_endpoint
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String host = request.headers().get("remote_endpoint");
            String[] temp = host.split(":");
            nettyProxyClient=new NettyProxyClient(temp[0],Integer.parseInt(temp[1]));
            nettyProxyClient.connect(ctx,msg);
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
        ChannelFuture f = b.connect(remoteHost, remotePort);
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
