package write.rpc.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class THProtocolCodec extends ByteToMessageCodec {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        if(in.readableBytes()<ProtocolConstants.HEADER_LENGTH){
            return;
        }
        short magicData = in.readShort();
        if(magicData != ProtocolConstants.MAGIC_DATA){
            throw new IllegalArgumentException("magicData is error,L="+magicData);
        }
        byte version = in.readByte();
        byte serialization = in.readByte();
        byte requestType = in.readByte();
        in.skipBytes(1);//调过状态字段
        in.skipBytes(4);//调过扩展字段
        Long requestId = in.readLong();
        Integer dataLength = in.readInt();
        if(in.readableBytes()<dataLength){
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        THMsgHeader header = new THMsgHeader();
        header.setMagicData(magicData);
        header.setVersion(version);
        header.setSerialization(serialization);
        header.setRequestType(requestType);
        header.setDataLength(dataLength);
        header.setRequestId(requestId);
        IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toString(serialization));
        THRpcRequest thRpcRequestBody = rpcSerialization.deserialize(data,THRpcRequest.class);
        THProtocolMsg<THRpcRequest> request = new THProtocolMsg<>();
        request.setHeader(header);
        request.setBody(thRpcRequestBody);
        out.add(request);
    }
}
