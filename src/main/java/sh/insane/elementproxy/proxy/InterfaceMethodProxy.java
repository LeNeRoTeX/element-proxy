package sh.insane.elementproxy.proxy;

import sh.insane.elementproxy.method.GetterFunction;
import sh.insane.elementproxy.method.MethodDescriptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterfaceMethodProxy<T> extends MethodProxy<T> {
    private final T proxyInstance;
    private ThreadLocal<Method> methodThreadLocal;

    public InterfaceMethodProxy(Class<T> proxiedClass) {
        super(proxiedClass);

        methodThreadLocal = new ThreadLocal<>();
        proxyInstance = createProxyInstance();
    }

    private T createProxyInstance() {
        Class<?>[] interfaces = new Class[1];
        interfaces[0] = getProxiedClass();

        return (T) Proxy.newProxyInstance(InterfaceMethodProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                methodThreadLocal.set(method);

                Class<?> returnType = method.getReturnType();

                if (returnType.isPrimitive()) {
                    return getDefaultPrimitiveValue(returnType);
                }

                return null;
            }
        });
    }

    @Override
    public MethodDescriptor invoke(GetterFunction<T, ?> getterFunction) {
        getterFunction.apply(proxyInstance);
        return new MethodDescriptor(methodThreadLocal.get());
    }

    private Object getDefaultPrimitiveValue(Class<?> clazz) {
        if (clazz.equals(boolean.class)) {
            return false;
        } else if (clazz.equals(char.class)) {
            return '\0';
        } else if (clazz.equals(byte.class) || clazz.equals(short.class) || clazz.equals(int.class)) {
            return 0;
        } else if (clazz.equals(long.class)) {
            return 0L;
        } else if (clazz.equals(float.class) || clazz.equals(double.class)) {
            return 0.0f;
        }

        return null;
    }
}