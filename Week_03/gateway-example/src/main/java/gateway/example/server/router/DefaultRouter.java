package gateway.example.server.router;

import cn.hutool.core.util.StrUtil;
import com.sun.deploy.util.StringUtils;
import gateway.example.server.proxy.HttpChannels;
import io.netty.channel.Channel;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ？是否可以将Channel add到list，然后根据具体规则get相应的channel writeAndFlush
 *
 */
public class DefaultRouter implements HttpRule{

    @Override
    public  Channel router(HttpChannels channels){
            Channel outboundChannel=null;
            while (outboundChannel==null){
                List<Channel> list = channels.getChannels();
                int size=list.size();
                if(size==0){
                    return null;
                }
                Random random = new Random();
                int n = random.nextInt(list.size());
                outboundChannel=list.get(n);

                if (outboundChannel == null) {
                    Thread.yield();
                    continue;
                }
                if (outboundChannel.isActive()) {
                    System.out.println("DefaultRouter:outboundChannel.isActive()-->"+outboundChannel.localAddress());

                    return outboundChannel;
                }

                outboundChannel = null;
                Thread.yield();
            }
            System.out.println("DefaultRouter-->"+outboundChannel.localAddress());
            return outboundChannel;
    }
//    @Override
//    public  Channel routerRound(HttpChannels channels){
//        Channel outboundChannel=null;
//        return outboundChannel;
//    }

//    public static void covertEndpoint(String endpoint){
//        remoteHost=endpoint.split(":")[0];
//        remotePort=Integer.parseInt(endpoint.split(":")[1]);
//    }

}
