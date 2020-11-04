package gateway.example.server.router;

import gateway.example.server.proxy.HttpChannels;
import io.netty.channel.Channel;

import java.util.List;

public interface HttpRule {
    Channel routerRandom(HttpChannels channels);
    Channel routerRound(HttpChannels channels);
}
