package write.rpc.core;

import java.io.Serializable;
import java.util.Arrays;

public class THRpcRequest implements Serializable {

    private String serviceId;

    private String alias;

    private String interfaceName;

    private Object[] params;

    private Class<?>[] parameterTypes;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public String toString() {
        return "THRpcRequest{" +
                "serviceId='" + serviceId + '\'' +
                ", alias='" + alias + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", params=" + Arrays.toString(params) +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}
