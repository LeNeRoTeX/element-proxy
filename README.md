![example workflow](https://github.com/LeNeRoTeX/element-proxy/actions/workflows/maven.yml/badge.svg)

# Getting Started

ElementProxy is a library to intercept and fetch methods.
You can retrieve the name of the method, the return type and more.

Always try to use single `ElementProxyContext` and not multiple instances.

## How to use
### Objects

```
ElementProxyContext ctx = new ElementProxyContext();

MethodDescriptor descriptor = ctx.getDescriptor(Account.class, Account::getName);

descriptor.getName(); // -> "getName"
descriptor.getReturnType(); // -> String.class


// OR

Method method = ctx.getMethod(Account.class, Account::getName);
```

### Interfaces
```
ElementProxyContext ctx = new ElementProxyContext();

MethodDescriptor descriptor = ctx.getDescriptor(List.class, List::size);

descriptor.getName(); // -> "size"
descriptor.getReturnType(); // -> int.class

// OR

Method method = ctx.getMethod(List.class, List::size);
```
