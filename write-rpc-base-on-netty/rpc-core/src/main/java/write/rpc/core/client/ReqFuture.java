package write.rpc.core.client;

import io.netty.util.concurrent.Promise;

public class ReqFuture<T> {

    private Promise<T> promise;

    private long timeout;

    public ReqFuture(Promise<T> promise,long timeout){
        this.promise = promise;
        this.timeout = timeout;
    }

    public Promise<T> getPromise() {
        return promise;
    }

    public void setPromise(Promise<T> promise) {
        this.promise = promise;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
