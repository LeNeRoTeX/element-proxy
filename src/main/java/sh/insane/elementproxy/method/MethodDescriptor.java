package sh.insane.elementproxy.method;

import java.lang.reflect.Method;

public class MethodDescriptor {
    private final Class<?> clazz;
    private final Class<?> returnType;
    private final String name;

    public MethodDescriptor(Method method) {
        clazz = method.getDeclaringClass();
        returnType = method.getReturnType();
        name = method.getName();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }
}
