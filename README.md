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
    compile 'com.github.JavaSaBr:jme3-spaceshift-extension:1.1.4'
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
        <artifactId>jme3-spaceshift-extension</artifactId>
        <version>1.1.4</version>
    </dependency>
```