package write.rpc.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import write.rpc.core.ProtocolConstants;
import write.rpc.core.factory.RpcSerializationFactory;
import write.rpc.core.protocol.THMsgHeader;
import write.rpc.core.protocol.THProtocolMsg;
import write.rpc.core.protocol.THRpcRequest;
import write.rpc.core.protocol.THRpcResponse;
import write.rpc.core.serialize.IRpcSerialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class THProtocolCodec extends ByteToMessageCodec {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if(!(msg instanceof THProtocolMsg)){
            System.out.println("unknow msg type");
            return ;
        }
        THProtocolMsg protocolMsg = (THProtocolMsg)msg;
        if(protocolMsg.getBody() instanceof THRpcRequest){
            THMsgHeader header = protocolMsg.getHeader();
            out.writeShort(header.getMagicData());
            out.writeByte(header.getVersion());
            out.writeByte(header.getSerialization());
            out.writeByte(header.getRequestType());
            out.writeByte(header.getStatus());
            out.writeBytes(header.getExtension());
            out.writeLong(header.getRequestId());
            IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toString(header.getSerialization()));
            byte[] body = rpcSerialization.serialize((THRpcRequest)protocolMsg.getBody(),THRpcRequest.class);
            out.writeInt(body.length);
            out.writeBytes(body);
        }else if(protocolMsg.getBody() instanceof THRpcResponse){
            THMsgHeader header = protocolMsg.getHeader();
            out.writeShort(header.getMagicData());
            out.writeByte(header.getVersion());
            out.writeByte(header.getSerialization());
            out.writeByte(header.getRequestType());
            out.writeByte(header.getStatus());
            out.writeBytes(header.getExtension());
            out.writeLong(header.getRequestId());
            IRpcSerialization rpcSerialization = RpcSerializationFactory.getBySerialization(Byte.toString(header.getSerialization()));
            byte[] body = rpcSerialization.serialize((THRpcResponse)protocolMsg.getBody(),THRpcResponse.class);
            out.writeInt(body.length);
            out.writeBytes(body);
        }else{
            System.out.println("unknow encode branch");
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
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
        THRpcRequest thRpcRequestBody = rpcSerialization.deserialize(data,THRpcRequest.class);
        THProtocolMsg<THRpcRequest> request = new THProtocolMsg<>();
        request.setHeader(header);
        request.setBody(thRpcRequestBody);
        out.add(request);
    }

    private static int sizeof(Object obj) throws IOException {
        try (ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()) {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);

            objectOutputStream.writeObject(obj);

            objectOutputStream.flush();

            objectOutputStream.close();

            return byteOutputStream.toByteArray().length;
        }

    }
}
