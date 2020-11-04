package gateway.example.server.router;

import gateway.example.server.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class HttpRouterExampleFilter extends ChannelInboundHandlerAdapter implements HttpRouterFilter{
    private static Logger logger = LoggerFactory.getLogger(HttpRouterExampleFilter.class);

    public HttpRouterExampleFilter() {
        super();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("router start ->msg"+msg);
        if(msg instanceof FullHttpRequest){
            HttpEndpointRouter httpRouter=new RandomRule();
            String rule=RouterConstant.rule;
            switch (rule){
                case "random":
                    httpRouter=new RandomRule();
                    break;
                case "round":
                    httpRouter=new RoundRule();;
                    break;
            }
            String endpoint=httpRouter.route(RouterConstant.endpoints);
            logger.info("HttpRouterExampleFilter:channelRead-endpoint-->"+endpoint+",rule-->"+rule);
            filter((FullHttpRequest) msg,ctx,endpoint);
            logger.info("router end ->msg"+(FullHttpRequest)msg);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void filter(FullHttpRequest req, ChannelHandlerContext ctx,String endpoint) {
        HttpHeaders headers = req.headers();
        headers.add("remote_endpoint",endpoint);
    }
}
