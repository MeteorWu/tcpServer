package com.vparking.cloud.tcp.model;

import com.vparking.cloud.common.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Talen on 8/16/17.
 */
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);

        Object obj = ByteObjConverter.byteToObject(req);
        out.add(obj);
    }

}
