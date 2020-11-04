package gateway.example.server.router;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    String routerRandom(List<String> endpoints);
    String routerRound(List<String> endpoints);
    void filter(FullHttpRequest req, ChannelHandlerContext ctx, String endpoint);
    
}
