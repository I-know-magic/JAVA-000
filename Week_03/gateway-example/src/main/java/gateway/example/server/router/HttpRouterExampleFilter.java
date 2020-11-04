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
 * 后续将不同的规则定义不同的类
 */
public class HttpRouterExampleFilter extends ChannelInboundHandlerAdapter implements HttpEndpointRouter{
    private static Logger logger = LoggerFactory.getLogger(HttpRouterExampleFilter.class);
//    定义一个集合，存放代理服务器连接信息，可用其他方式进行添加。
    public  List<String> endpoints= Arrays.asList("127.0.0.1:8088","127.0.0.1:8087","127.0.0.1:8089");
    static final String REMOTE_ADDR = System.getProperty("remote_addr", "127.0.0.1:8088");
    static int index=0;
//    默认 random
    String rule= System.getProperty("rule", "random");

    public HttpRouterExampleFilter() {
        super();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("router start ->msg"+msg);
        if(msg instanceof FullHttpRequest){
            String endpoint=routerRandom(endpoints);
            switch (rule){
                case "random":
                    endpoint=routerRandom(endpoints);
                    break;
                case "round":
                    endpoint=routerRound(endpoints);
                    break;
            }
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

    @Override
    public String route(List<String> endpoints) {
        return "";
    }

    @Override
    public String routerRandom(List<String> endpoints) {
        if(endpoints.size()==0){
            return REMOTE_ADDR;
        }
        Random random=new Random();
        int n=random.nextInt(endpoints.size());
        return endpoints.get(n);
    }

    @Override
    public String routerRound(List<String> endpoints) {
        String endpoint=endpoints.get(index);
        index=(index+1) % endpoints.size();
        logger.info("index-->"+index+",endpoint--->"+endpoint);
        return endpoint;
    }
//    public static String routerRound1(List<String> endpoints) {
//        String endpoint=endpoints.get(index);
//        index=(index+1) % endpoints.size();
//        logger.info("index-->"+index+",endpoint--->"+endpoint);
//        return endpoint;
//    }

    public static void main(String[] args) {
//        routerRound1(HttpRouterExampleFilter.endpoints);
//        routerRound1(HttpRouterExampleFilter.endpoints);
//        routerRound1(HttpRouterExampleFilter.endpoints);
//        routerRound1(HttpRouterExampleFilter.endpoints);
//        routerRound1(HttpRouterExampleFilter.endpoints);
//        routerRound1(HttpRouterExampleFilter.endpoints);
//        routerRound1(HttpRouterExampleFilter.endpoints);
    }

}
