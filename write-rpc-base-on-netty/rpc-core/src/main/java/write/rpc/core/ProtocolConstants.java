package write.rpc.core;

public class ProtocolConstants {

    public static final Integer HEADER_LENGTH = 22;
    public static final short MAGIC_DATA = 01011;
    public static final byte VERSION = 0x01;
    public static final byte DEFAULT_SERIALIZATION_PROTOBUF = 0x01;
    public static final byte REQUEST_TYPE_GET = 0x01;
    public static final byte REQUEST_TYPE_RETURN = 0x11;
    public static final byte STATUS = 0x1E;
    public static final long DEFAULT_TIMEOUT = 100000;
}
