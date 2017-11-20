# About this library #
This library provides some interfaces to get integration with jMonkeyBuilder.

# License #
Please see the file called LICENSE.

## How to use

#### Gradle

```
#!groovy

allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.JavaSaBr:jmonkeybuilder-extension:1.9.4'
}
```

#### Maven

```
#!xml

<repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependency>
        <groupId>com.github.JavaSaBr</groupId>
        <artifactId>jmonkeybuilder-extension</artifactId>
        <version>1.9.4</version>
    </dependency>
```