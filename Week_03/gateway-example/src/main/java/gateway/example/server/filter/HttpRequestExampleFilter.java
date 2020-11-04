package gateway.example.server.filter;

import gateway.example.server.inbound.HttpInboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestExampleFilter extends ChannelInboundHandlerAdapter implements HttpRequestFilter{
    private static Logger logger = LoggerFactory.getLogger(HttpRequestExampleFilter.class);

    public HttpRequestExampleFilter() {
        super();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("filter start ->msg"+msg);
        if(msg instanceof FullHttpRequest){
            filter((FullHttpRequest) msg,ctx);
            logger.info("filter end ->msg"+(FullHttpRequest)msg);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void filter(FullHttpRequest req, ChannelHandlerContext ctx) {
        HttpHeaders headers = req.headers();
        headers.add("nio","lvpeng-test");
    }
}
