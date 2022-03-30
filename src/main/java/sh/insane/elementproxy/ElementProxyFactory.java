package sh.insane.elementproxy;

import sh.insane.elementproxy.proxy.InterfaceMethodProxy;
import sh.insane.elementproxy.proxy.SubclassMethodProxy;
import sh.insane.elementproxy.proxy.MethodProxy;

public class ElementProxyFactory {

    public <T> MethodProxy<T> createProxy(Class<T> proxiedClass) {
        if(proxiedClass.isInterface()) {
            return createInterfaceProxy(proxiedClass);
        } else return createSubclassProxy(proxiedClass);
    }

    protected  <T> InterfaceMethodProxy<T> createInterfaceProxy(Class<T> proxiedClass) {
        return new InterfaceMethodProxy<>(proxiedClass);
    }

    protected <T> SubclassMethodProxy<T> createSubclassProxy(Class<T> proxiedClass) {
        return new SubclassMethodProxy<>(proxiedClass);
    }
}