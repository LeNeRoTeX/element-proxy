package sh.insane.elementproxy.proxy;

import sh.insane.elementproxy.method.GetterFunction;
import sh.insane.elementproxy.method.MethodDescriptor;

public abstract class MethodProxy<T> {
    private final Class<T> proxiedClass;

    protected MethodProxy(Class<T> proxiedClass) {
        this.proxiedClass = proxiedClass;
    }

    public Class<T> getProxiedClass() {
        return proxiedClass;
    }

    public abstract MethodDescriptor invoke(GetterFunction<T, ?> getterFunction);
}
