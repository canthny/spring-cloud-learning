package write.rpc.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import write.rpc.core.ProtocolConstants;
import write.rpc.core.factory.RpcSerializationFactory;
import write.rpc.core.serialize.IRpcSerialization;

import java.util.List;

public class THProtocolDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()< ProtocolConstants.HEADER_LENGTH){
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
        header.setStatus((byte)0x1);
        IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toUnsignedInt(serialization));
        THProtocolMsg msg = new THProtocolMsg();
        if(ProtocolConstants.REQUEST_TYPE_GET==requestType){
            THRpcRequest thRpcRequest = rpcSerialization.deserialize(data,THRpcRequest.class);
            msg.setBody(thRpcRequest);
        }else if(ProtocolConstants.REQUEST_TYPE_RETURN==requestType){
            THRpcResponse thRpcResponse = rpcSerialization.deserialize(data,THRpcResponse.class);
            msg.setBody(thRpcResponse);
        }
        msg.setHeader(header);
        out.add(msg);
    }
}
