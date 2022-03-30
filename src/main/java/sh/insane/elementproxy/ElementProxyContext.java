package sh.insane.elementproxy;

import sh.insane.elementproxy.method.GetterFunction;
import sh.insane.elementproxy.method.MethodDescriptor;
import sh.insane.elementproxy.proxy.MethodProxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ElementProxyContext {
    private final Map<Class<?>, MethodProxy<?>> proxiedClasses;
    private final ElementProxyFactory proxyFactory;

    public ElementProxyContext() {
        proxiedClasses = new ConcurrentHashMap<>();
        proxyFactory = getProxyFactory();
    }

    public boolean isClassProxied(Class<?> clazz) {
        return proxiedClasses.containsKey(clazz);
    }

    public <T, R> MethodDescriptor getMethod(Class<T> clazz, GetterFunction<T, R> getterFunction) {
        MethodDescriptor methodDescriptor;

        if(isClassProxied(clazz)) {
            methodDescriptor = invokeExisting(clazz, getterFunction);
        } else {
            synchronized (this)
            {
                if(isClassProxied(clazz)) {
                    methodDescriptor = invokeExisting(clazz, getterFunction);
                } else {
                    MethodProxy<T> methodProxy = proxyFactory.createProxy(clazz);
                    proxiedClasses.put(clazz, methodProxy);

                    methodDescriptor = invokeExisting(clazz, getterFunction);
                }
            }
        }

        return methodDescriptor;
    }

    private <T, R> MethodDescriptor invokeExisting(Class<T> clazz, GetterFunction<T, R> getterFunction) {
        MethodProxy<T> methodProxy = (MethodProxy<T>) proxiedClasses.get(clazz);
        return methodProxy.invoke(getterFunction);
    }

    protected ElementProxyFactory getProxyFactory() {
        return new ElementProxyFactory();
    }
}
