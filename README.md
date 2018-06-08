# About this library #
This library provides some interfaces to get integration with jMonkeyBuilder.

# License #
Please see the file called LICENSE.

## How to use

#### Gradle

```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/javasabr/maven" 
    }
}

dependencies {
    compile 'com.spaceshift:jmonkeybuilder-extension:2.4.0'
}
```

#### Maven

```!xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-javasabr-maven</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/javasabr/maven</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.spaceshift</groupId>
    <artifactId>jmonkeybuilder-extension</artifactId>
    <version>2.4.0</version>
</dependency>
```