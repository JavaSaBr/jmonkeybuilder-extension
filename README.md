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

## Most interesting parts:
### Editable Control Interface

```java

public class CustomControlExample extends AbstractControl implements EditableControl {

    private String string;
    private Vector3f position;
    private float power;

    @Override
    public @NotNull String getName() {
        return "MyControl";
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        var result = new ArrayList<EditableProperty<?, ?>>(3);
        result.add(new SimpleProperty<>(EditablePropertyType.FLOAT, "Power", this,
                CustomControlExample::getPower, CustomControlExample::setPower));
        result.add(new SimpleProperty<>(EditablePropertyType.STRING, "String", this,
                CustomControlExample::getString, CustomControlExample::setString));
        result.add(new SimpleProperty<>(EditablePropertyType.VECTOR_3F, "Position", this,
                CustomControlExample::getPosition, CustomControlExample::setPosition));

        return result;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position.set(position);
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
```

![MyControl](https://i.imgur.com/D6vl1bD.png)