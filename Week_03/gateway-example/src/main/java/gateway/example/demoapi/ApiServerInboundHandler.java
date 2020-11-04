package gateway.example.demoapi;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiServerInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ApiServerInboundHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = (FullHttpRequest)msg;
        logger.info("客户端请求数据内容：msg -->"+ msg);
        String ret = "";
        try {
            String uri = httpRequest.uri();
            String data = httpRequest.content().toString(CharsetUtil.UTF_8);
            HttpMethod method = httpRequest.method();
            HttpHeaders headers=httpRequest.headers();
//            获取header信息进行处理
            String paramStr=headers.get("nio");
            String remoteEndpoint=headers.get("remote_endpoint");
//          处理参数等数据

//            if("/error".equalsIgnoreCase(uri)){
//                ret = "非法请求路径：" + uri;
//                response(ctx,ret, HttpResponseStatus.BAD_REQUEST);
//                return;
//            }

            if(HttpMethod.GET.equals(method)){
                logger.info("客户端请求数据内容：" +uri+"-->data:"+ data+"-->header:"+paramStr+",remoteEndpoint-->"+remoteEndpoint);
                ret = "服务端接受到数据，接收到数据为："  +uri+"-->data:"+ data+"-->header:"+paramStr+",remoteEndpoint-->"+remoteEndpoint;
                response( ctx,ret, HttpResponseStatus.OK);
            }
            if (HttpMethod.POST.equals(method)){
                //...
            }
            if (HttpMethod.PUT.equals(method)){
                //..
            }
            //..
        } catch (Exception e) {
            System.out.println("服务器处理失败...");
        } finally {
            httpRequest.release();
        }
    }
//    后续改为outbound
    public void response(ChannelHandlerContext ctx,Object data,HttpResponseStatus  status){
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer(data.toString(), CharsetUtil.UTF_8));
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }
}
