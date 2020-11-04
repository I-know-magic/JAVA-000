/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package gateway.example.demoapi;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public  class ApiServerInitializer extends ChannelInitializer<SocketChannel> {


    public ApiServerInitializer() {

    }

    @Override
    public void initChannel(SocketChannel ch) {
//        ch.pipeline().addLast(new HttpRequestDecoder());
//
//        ch.pipeline().addLast(new HttpResponseEncoder());
//
        ch.pipeline().addLast(new HttpServerCodec());/*HTTP 服务的解码器*/
        ch.pipeline().addLast(new HttpObjectAggregator(65535));/*HTTP 消息的合并处理*/
        ch.pipeline().addLast(new HttpContentDecompressor());
//        处理req
        ch.pipeline().addLast(new ApiServerInboundHandler());
        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
    }
}

