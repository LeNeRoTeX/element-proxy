package sh.insane.elementproxy.method;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

public class MethodInterceptor {
    private ThreadLocal<Method> threadLocalMethod;

    public MethodInterceptor() {
        threadLocalMethod = new ThreadLocal<>();
    }

    @RuntimeType
    public Object intercept(@Origin Method origin) {
        threadLocalMethod.set(origin);

        Class<?> returnType = origin.getReturnType();

        if(returnType.isPrimitive()) {
            return getDefaultPrimitiveValue(returnType);
        }

        return null;
    }

    private Object getDefaultPrimitiveValue(Class<?> clazz) {
        if(clazz.equals(boolean.class)) {
            return false;
        } else if(clazz.equals(char.class)) {
            return '\0';
        } else if(clazz.equals(byte.class) || clazz.equals(short.class) || clazz.equals(int.class)) {
            return 0;
        } else if(clazz.equals(long.class)) {
            return 0L;
        } else if(clazz.equals(float.class) || clazz.equals(double.class)) {
            return 0.0f;
        }

        return null;
    }

    public Method getMethod() {
        return threadLocalMethod.get();
    }
}
