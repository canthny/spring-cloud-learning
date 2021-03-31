package write.rpc.core.client;

import write.rpc.core.protocol.THRpcResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RequestHolder {

    private final static AtomicLong REQUEST_ID_GEN = new AtomicLong(0);

    public static final Map<Long,ReqFuture<THRpcResponse>> REQ_FUTURE_MAP = new ConcurrentHashMap<>();

    public static Long getRequestId(){
        long curTime = System.currentTimeMillis();
        return curTime*10000000+REQUEST_ID_GEN.decrementAndGet();
    }
}
