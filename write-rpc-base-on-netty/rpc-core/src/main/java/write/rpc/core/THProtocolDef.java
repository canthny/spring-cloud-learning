package write.rpc.core;

/**
 * TH协议
 * +---------------------------------------------------------------+
 * | 魔数 2byte | 协议版本号 1byte | 序列化算法 1byte | 报文类型 1byte |
 * +---------------------------------------------------------------+
 * | 状态 1byte | 保留字段 4byte | 数据长度 4byte | 数据内容 （长度不定）|
 * +---------------------------------------------------------------+
 */
public class THProtocolDef {

    private static final Integer magicData = 0xCACA;//11001010 11001010

    public static void main(String[] args) {

    }
}
