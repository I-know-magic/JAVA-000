package gateway.example.demoapi;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * request bean
 *
 */
public class RequestBean  extends DefaultFullHttpRequest{

    public RequestBean(HttpVersion httpVersion, HttpMethod method, String uri) {
        super(httpVersion, method, uri);
    }
}
