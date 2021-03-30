package write.rpc.core.factory;

import write.rpc.core.serialize.IRpcSerialization;
import write.rpc.core.serialize.ProtobufSerialization;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化工厂类
 */
public class RpcSerializationFactory {

    private static Map<String, IRpcSerialization> serializationMap = new HashMap<>();

    static {
        serializationMap.put("protobuf",new ProtobufSerialization());
    }

    public static IRpcSerialization getBySerialization(String serialization){
        return serializationMap.get(serialization);
    }
}
