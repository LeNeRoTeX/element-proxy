package sh.insane.elementproxy.method;

import java.util.function.Function;

@FunctionalInterface
public interface GetterFunction<T, R> extends Function<T, R> {
}
