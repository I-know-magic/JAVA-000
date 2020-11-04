package gateway.example.server.proxy;

import io.netty.channel.Channel;
import java.util.ArrayList;
import java.util.List;

public class HttpChannels {
    private List<Channel> channels=new ArrayList<Channel>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Channel channel) {
        this.channels.add(channel);
    }
}
