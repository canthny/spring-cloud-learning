package write.rpc.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import write.rpc.core.factory.RpcSerializationFactory;
import write.rpc.core.serialize.IRpcSerialization;

public class THProtocolEncoder extends MessageToByteEncoder<THProtocolMsg<Object>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, THProtocolMsg<Object> msg, ByteBuf out) throws Exception {
        if(!(msg instanceof THProtocolMsg)){
            System.out.println("unknow msg type");
            return ;
        }
        THMsgHeader header = msg.getHeader();
        out.writeShort(header.getMagicData());
        out.writeByte(header.getVersion());
        out.writeByte(header.getSerialization());
        out.writeByte(header.getRequestType());
        out.writeByte(header.getStatus());
        out.writeBytes(header.getExtension());
        out.writeLong(header.getRequestId());
        IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toUnsignedInt(header.getSerialization()));
        Object bodyObj = msg.getBody();
        byte[] data;
        if(bodyObj instanceof THRpcRequest){
            data = rpcSerialization.serialize((THRpcRequest)msg.getBody(),THRpcRequest.class);
        }else if(bodyObj instanceof THRpcResponse){
            data = rpcSerialization.serialize((THRpcResponse)msg.getBody(),THRpcResponse.class);
        }else{
            data = null;
        }
        out.writeInt(data==null?0:data.length);
        out.writeBytes(data);
    }
}
