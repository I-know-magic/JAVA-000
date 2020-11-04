
package gateway.example.server.inbound;

import gateway.example.server.filter.HttpRequestExampleFilter;
import gateway.example.server.router.HttpRouterExampleFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    public HttpInboundInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void initChannel(SocketChannel ch) {
//        ch.pipeline().addLast(new HttpRequestDecoder());
//
//        ch.pipeline().addLast(new HttpResponseEncoder());
//
        ch.pipeline().addLast(new HttpServerCodec());/*HTTP 服务的解码器*/

        ch.pipeline().addLast(new HttpObjectAggregator(65535));/*HTTP 消息的合并处理*/
        ch.pipeline().addLast(new HttpContentDecompressor());

        ch.pipeline().addLast(new HttpRequestExampleFilter());
        ch.pipeline().addLast(new HttpRouterExampleFilter());
        ch.pipeline().addLast(new HttpInboundHandler());
        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
    }
}
