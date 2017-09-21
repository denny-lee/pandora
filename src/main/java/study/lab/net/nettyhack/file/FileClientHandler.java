package study.lab.net.nettyhack.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * fail 2017-9-20
 */
public class FileClientHandler extends SimpleChannelInboundHandler<String> {
    /*@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("/Users/liwei/cat_log_cmd.txt");
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("---");
        String s = null;
        if (msg instanceof String) {
            s = (String) msg;
        }
        System.out.println(s);
        if (null != s && s.startsWith("HELLO")) {
            ctx.writeAndFlush("/Users/liwei/cat_log_cmd.txt");
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(s);
        if (s.startsWith("HELLO")) {
            ctx.writeAndFlush("/Users/liwei/cat_log_cmd.txt");
        }
    }
}
