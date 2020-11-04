package gateway.example.server.router;

import gateway.example.server.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HttpRouterExampleFilter extends ChannelInboundHandlerAdapter implements HttpEndpointRouter{
    private static Logger logger = LoggerFactory.getLogger(HttpRouterExampleFilter.class);

    public HttpRouterExampleFilter() {
        super();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("router start ->msg"+msg);
//        if(msg instanceof FullHttpRequest){
//            String endpoint=route(RouterUtils.endpoints);
//            filter((FullHttpRequest) msg,ctx,endpoint);
//            logger.info("router end ->msg"+(FullHttpRequest)msg);
//        }
        super.channelRead(ctx, msg);
    }

    public void filter(FullHttpRequest req, ChannelHandlerContext ctx,String endpoint) {
        HttpHeaders headers = req.headers();
        headers.add("endpoint",endpoint);
    }

    @Override
    public String route(List<String> endpoints) {
        return "";
    }
}
