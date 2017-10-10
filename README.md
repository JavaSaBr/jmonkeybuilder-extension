# About this library #
This library provides some interface to get integration with jMonkey Builder.

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
    compile 'com.github.JavaSaBr:jmonkey-builder-extension:1.8.0'
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
        <artifactId>jmonkey-builder-extension</artifactId>
        <version>1.8.0</version>
    </dependency>
```