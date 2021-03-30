package write.rpc.core;

public class ProtocolConstants {

    public static final Integer HEADER_LENGTH = 22;

    public static final short MAGIC_DATA = 01011;

    public static final String VERSION = "1.0";

    public static final String DEFAULT_SERIALIZATION = "protobuf";

    public static final String REQUEST_TYPE_GET = "get";

    public static final String REQUEST_TYPE_RETURN = "return";
}
