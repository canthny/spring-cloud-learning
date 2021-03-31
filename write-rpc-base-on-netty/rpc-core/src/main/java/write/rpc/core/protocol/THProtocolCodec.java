package write.rpc.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import write.rpc.core.ProtocolConstants;
import write.rpc.core.factory.RpcSerializationFactory;
import write.rpc.core.serialize.IRpcSerialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class THProtocolCodec extends ByteToMessageCodec {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
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

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
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
        IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toString(serialization));
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

//    private static int sizeof(Object obj) throws IOException {
//        try (ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()) {
//
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
//
//            objectOutputStream.writeObject(obj);
//
//            objectOutputStream.flush();
//
//            objectOutputStream.close();
//
//            return byteOutputStream.toByteArray().length;
//        }
//
//    }
}
