package com.ss.editor.extension.example;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.EditablePropertyType;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.control.EditableControl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The example of an editable custom control.
 *
 * @author JavaSaBr
 */
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
