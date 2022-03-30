# Getting Started

ElementProxy is a library to intercept and fetch methods.
You can retrieve the name of the method, the return type and more.

Always try to use single `ElementProxyContext` and not multiple instances.

## How to use
### Objects

```
ElementProxyContext ctx = new ElementProxyContext();

MethodDescriptor descriptor = ctx.getMethod(Account.class, Account::getName);

descriptor.getName(); // -> "getName"
descriptor.getReturnType(); // -> String.class
```

### Interfaces
```
ElementProxyContext ctx = new ElementProxyContext();

MethodDescriptor descriptor = ctx.getMethod(List.class, List::size);

descriptor.getName(); // -> "size"
descriptor.getReturnType(); // -> int.class
```