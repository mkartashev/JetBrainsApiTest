[![JetBrains incubator project](https://jb.gg/badges/incubator.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

# JBR API

**_JBR API_** is an interface for
[JBR](https://github.com/JetBrains/JetBrainsRuntime)-specific functionality.
It provides a standalone jar with a bunch of API classes and interfaces,
allowing  client code to be compiled using any JDK, while enjoying JBR-specific
features at runtime without worrying about compatibility and runtime errors.

JBR API automatically translates calls to itself into
[JBR](https://github.com/JetBrains/JetBrainsRuntime).
When needed functionality is unavailable (e.g. when running on another JRE,
or incompatible JBR version), this is reported in a safe manner and no
linkage errors occur.


## Quickstart

Any feature exposed via JBR API begins with a **_service_**, which is a basic
unit of JBR API. Each service has three related methods in `JBR` class:
* `JBR.get<NAME>()` - returns service instance if it's supported, or null.
* `JBR.get<NAME>(Extensions...)` - returns service instance with set of enabled extensions.
* `JBR.is<NAME>Supported()` - convenience method, equivalent of `JBR.get<NAME>() != null`.

```java
if (JBR.isSomeServiceSupported()) {
    JBR.getSomeService().doSomething();
}
// or
SomeService service = JBR.getSomeService();
if (service != null) {
    service.doSomething();
}
```
> <picture>
>   <source media="(prefers-color-scheme: light)" srcset="https://raw.githubusercontent.com/Mqxx/GitHub-Markdown/f167aefa480e8d37e9941a25f0b40981b74a47be/blockquotes/badge/light-theme/tip.svg">
>   <img alt="Tip" src="https://raw.githubusercontent.com/Mqxx/GitHub-Markdown/f167aefa480e8d37e9941a25f0b40981b74a47be/blockquotes/badge/dark-theme/tip.svg">
> </picture><br>
>
> More details with a list of available services can be found in the
> [javadoc](https://jetbrains.github.io/JetBrainsApiTest).

### Extensions

API methods marked with `@Extension` are *optional*, meaning that service would still be
supported even if some of its extension methods are not.
To use extension methods, those extensions must be explicitly enabled when retrieving the service.
Extension methods may appear not only in services, but in regular interfaces too,
in such cases the set of enabled extensions is implicitly propagated to objects retrieved from that service.

> <picture>
>   <source media="(prefers-color-scheme: light)" srcset="https://raw.githubusercontent.com/Mqxx/GitHub-Markdown/f167aefa480e8d37e9941a25f0b40981b74a47be/blockquotes/badge/light-theme/example.svg">
>   <img alt="Example" src="https://raw.githubusercontent.com/Mqxx/GitHub-Markdown/f167aefa480e8d37e9941a25f0b40981b74a47be/blockquotes/badge/dark-theme/example.svg">
> </picture><br>
>
> ```java
> SomeService service;
> Foo foo;
> 
> service = JBR.getSomeService();
> foo = service.getFoo();
> foo.bar(); // UnsupportedOperationException: Foo.bar - extension BAR is disabled
> 
> service = JBR.getSomeService(Extensions.BAR);
> foo = service.getFoo();
> foo.bar(); // OK
> ```


## Versioning

JBR API releases follow [semantic versioning](https://semver.org).
API and implementation versions can be retrieved from `JBR` class too:
* `JBR.getApiVersion()` - version of the `jbr-api.jar` currently used.
* `JBR.getImplVersion()` - version of JBR API implemented by the current runtime.

> <picture>
>   <source media="(prefers-color-scheme: light)" srcset="https://raw.githubusercontent.com/Mqxx/GitHub-Markdown/f167aefa480e8d37e9941a25f0b40981b74a47be/blockquotes/badge/light-theme/info.svg">
>   <img alt="Info" src="https://raw.githubusercontent.com/Mqxx/GitHub-Markdown/f167aefa480e8d37e9941a25f0b40981b74a47be/blockquotes/badge/dark-theme/info.svg">
> </picture><br>
>
> Although JBR API version does reflect compatibility of API
> changes, it is not used for any kind of compatibility checks, including
> determining service availability. That means that in practice some services
> will continue to work across multiple major releases.
> 
> However, just for the completeness*, when
> *impl.major == api.major && impl.minor >= api.minor*,
> all services currently present are **guaranteed** to be supported.
> 
> *_Versions should not be used for any purpose other than logging._

---
* [JBR API documentation](https://jetbrains.github.io/JetBrainsApiTest)
* [JBR API development guide](CONTRIBUTING.md)
* [JetBrainsRuntime (JBR)](https://github.com/JetBrains/JetBrainsRuntime)