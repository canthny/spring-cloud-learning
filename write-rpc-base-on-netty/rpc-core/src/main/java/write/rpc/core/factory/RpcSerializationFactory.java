package write.rpc.core.factory;

import write.rpc.core.ProtocolConstants;
import write.rpc.core.serialize.IRpcSerialization;
import write.rpc.core.serialize.ProtobufSerialization;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化工厂类
 */
public class RpcSerializationFactory {

    private static Map<Integer, IRpcSerialization> serializationMap = new HashMap<>();

    static {
        serializationMap.put((int)ProtocolConstants.DEFAULT_SERIALIZATION_PROTOBUF,new ProtobufSerialization());
    }

    public static IRpcSerialization getBySerialization(Integer serialization){
        return serializationMap.get(serialization);
    }
}
