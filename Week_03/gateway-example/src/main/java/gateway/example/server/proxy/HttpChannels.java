package gateway.example.server.proxy;

import io.netty.channel.Channel;
import java.util.ArrayList;
import java.util.List;

public class HttpChannels {
    private static List<Channel> channels=new ArrayList<Channel>();

    public static List<Channel> getChannels() {
        return channels;
    }

    public static void setChannels(Channel channel) {
        channels.add(channel);
    }
}
