package ru.alepar.forza.mock;

import org.jboss.netty.channel.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class PlayStarter extends SimpleChannelUpstreamHandler {

    private final String file;

    public PlayStarter(String file) {
        this.file = file;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent event) throws Exception {
        final Channel channel = ctx.getChannel();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChannelFuture future = null;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String s;
                    try {
                        while ((s = reader.readLine()) != null) {
                            Thread.sleep(500l);
                            future = channel.write(s + '\n');
                        }
                    } finally {
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (future != null) {
                        future.addListener(ChannelFutureListener.CLOSE);
                    }
                }
            }
        }).start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }
}
