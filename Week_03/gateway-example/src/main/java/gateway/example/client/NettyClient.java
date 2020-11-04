package gateway.example.client;

import gateway.example.server.NettyGateWayApplication;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NettyClient {
    private static Logger logger = LoggerFactory.getLogger(NettyGateWayApplication.class);

    public static void start(String host,int port){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<Channel>() {

                        @Override
                        protected void initChannel(Channel channel)
                                throws Exception {
//                            channel.pipeline().addLast(new HttpResponseDecoder());
////
//                            channel.pipeline().addLast(new HttpRequestEncoder());
                            channel.pipeline().addLast(new HttpClientCodec());
                            channel.pipeline().addLast(new HttpObjectAggregator(65535));
                            channel.pipeline().addLast(new HttpContentDecompressor());
                            channel.pipeline().addLast(new NettyClientHandler());
                            channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));

                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
            logger.info("client connect-->127.0.0.1:8888");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        //start("127.0.0.1", 8000);
//        start("127.0.0.1", 8088);
        start("127.0.0.1", 8888);
//        start("127.0.0.1", 8443);
    }
}