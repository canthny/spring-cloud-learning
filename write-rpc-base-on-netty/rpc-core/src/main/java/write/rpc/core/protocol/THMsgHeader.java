package write.rpc.core.protocol;

/**
 * +---------------------------------------------------------------+
 * | 魔数 2byte | 协议版本号 1byte | 序列化算法 1byte | 报文类型 1byte |
 * +---------------------------------------------------------------+
 * | 状态 1byte | 保留字段 4byte |  请求id 8byte | 数据长度 4byte | 数据内容 （长度不定）|
 * +---------------------------------------------------------------+
 */
public class THMsgHeader {

    private short magicData;

    private byte version;

    private byte serialization;

    private byte requestType;

    private byte status;

    private byte[] extension = new byte[4];

    private long requestId;

    private int dataLength;

    public short getMagicData() {
        return magicData;
    }

    public void setMagicData(short magicData) {
        this.magicData = magicData;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getSerialization() {
        return serialization;
    }

    public void setSerialization(byte serialization) {
        this.serialization = serialization;
    }

    public byte getRequestType() {
        return requestType;
    }

    public void setRequestType(byte requestType) {
        this.requestType = requestType;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte[] getExtension() {
        return extension;
    }

    public void setExtension(byte[] extension) {
        this.extension = extension;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }



}
