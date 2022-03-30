package sh.insane.elementproxy.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import sh.insane.elementproxy.method.MethodInterceptor;
import sh.insane.elementproxy.ElementProxyException;
import sh.insane.elementproxy.method.GetterFunction;
import sh.insane.elementproxy.method.MethodDescriptor;

import java.lang.reflect.InvocationTargetException;

public class SubclassMethodProxy<T> extends MethodProxy<T> {
    private final MethodInterceptor methodInterceptor;
    private final T proxyInstance;

    public SubclassMethodProxy(Class<T> proxiedClass) {
        super(proxiedClass);

        methodInterceptor = new MethodInterceptor();
        proxyInstance = createProxyInstance();
    }

    private T createProxyInstance() {
        Class<? extends T> clazz = new ByteBuddy()
                .subclass(getProxiedClass())
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(methodInterceptor))
                .make()
                .load(SubclassMethodProxy.class.getClassLoader())
                .getLoaded();

        T instance;

        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ElementProxyException("instantiation of proxied class failed", e);
        }

        return instance;
    }

    @Override
    public MethodDescriptor invoke(GetterFunction<T, ?> getterFunction) {
        getterFunction.apply(proxyInstance);
        return new MethodDescriptor(methodInterceptor.getMethod());
    }
}
