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
        THProtocolMsg protocolMsg = (THProtocolMsg)msg;
        THMsgHeader header = protocolMsg.getHeader();
        out.writeShort(header.getMagicData());
        out.writeByte(header.getVersion());
        out.writeByte(header.getSerialization());
        out.writeByte(header.getRequestType());
        out.writeByte(header.getStatus());
        out.writeBytes(header.getExtension());
        out.writeLong(header.getRequestId());
        IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toString(header.getSerialization()));
        Object bodyObj = protocolMsg.getBody();
        byte[] body;
        if(bodyObj instanceof THRpcRequest){
            body = rpcSerialization.serialize((THRpcRequest)protocolMsg.getBody(),THRpcRequest.class);
        }else if(bodyObj instanceof THRpcResponse){
            body = rpcSerialization.serialize((THRpcResponse)protocolMsg.getBody(),THRpcResponse.class);
        }else{
            body = null;
        }
        out.writeInt(body==null?0:body.length);
        out.writeBytes(body);
    }
}
