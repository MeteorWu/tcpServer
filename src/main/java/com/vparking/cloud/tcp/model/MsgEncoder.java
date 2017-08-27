package com.vparking.cloud.tcp.model;

import com.vparking.cloud.common.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Talen on 8/16/17.
 */
public class MsgEncoder extends MessageToByteEncoder<BaseMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BaseMsg msg, ByteBuf out) throws Exception {
        byte[] datas = ByteObjConverter.objectToByte(msg);
        out.writeBytes(datas);
        ctx.flush();
    }
}
