# envwrapper

Wrapper around environment variables for Java.

This library helps reducing boilerplate code for parsing and checking for existence of environment variables.

**NOTE** This is a proof of concept. Classes or annotation names will probably change.

## Usage

Define an interface with the appropriate annotations. The only mandatory one is `VarName` because it defines the name of the environment variable to be read. 

```java
public interface TestConfig {
    @OptionalVar
    @VarName("LANG")
    String getLang();
}
```

Then get an automatically generated instance of the interface:

```java
TestConfig config = EnvWrapper.getConfig(TestConfig.class);
String lang = config.getLang();
}
```

That's all

## Details

A Proxy class implementing the configuration interface is generated. All methods are routed to a handler which does all the parsing and type checking.