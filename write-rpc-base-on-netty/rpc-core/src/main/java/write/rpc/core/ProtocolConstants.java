package write.rpc.core;

public class ProtocolConstants {

    public static final Integer HEADER_LENGTH = 22;
    public static final short MAGIC_DATA = 01011;
    public static final byte VERSION = "1.0".getBytes()[0];
    public static final byte DEFAULT_SERIALIZATION = "protobuf".getBytes()[0];
    public static final byte REQUEST_TYPE_GET = "get".getBytes()[0];
    public static final byte REQUEST_TYPE_RETURN = "return".getBytes()[0];
    public static final long DEFAULT_TIMEOUT = 100000;
}
