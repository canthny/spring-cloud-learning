package write.rpc.core;

public interface IRpcSerialization {

    <T> T deserialize(byte[] data, Class<T> clazz);

    <T> byte[] serialize(T t,Class<T> clazz);
}
