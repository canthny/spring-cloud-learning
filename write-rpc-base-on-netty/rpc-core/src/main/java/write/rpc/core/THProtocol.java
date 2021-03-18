package write.rpc.core;

import java.io.Serializable;

/**
 * TH协议
 */
public class THProtocol<T> implements Serializable {

    THMsgHeader header;

    T body;

    public THMsgHeader getHeader() {
        return header;
    }

    public void setHeader(THMsgHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
