package com.ss.editor.extension.example;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.EditablePropertyType;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.control.EditableControl;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The example of a editable custom control.
 *
 * @author JavaSaBr
 */
public class CustomControlExample extends AbstractControl implements EditableControl {

    private String stringProperty;

    private Vector3f vectorProperty;

    private float floatProperty;

    @Override
    public @NotNull Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(EditablePropertyType.STRING, "Property #1", this,
                CustomControlExample::getStringProperty,
                CustomControlExample::setStringProperty));

        result.add(new SimpleProperty<>(EditablePropertyType.VECTOR_3F, "Property #2", this,
                CustomControlExample::getVectorProperty,
                (control, vector3f) -> {
                // you can handle this setter
                control.setVectorProperty(vector3f);
        }));

        result.add(new SimpleProperty<>(EditablePropertyType.FLOAT, "Property #3", this,
                CustomControlExample::getFloatProperty,
                CustomControlExample::setFloatProperty));

        return result;
    }

    /**
     * Gets string property.
     *
     * @return the string property
     */
    public String getStringProperty() {
        return stringProperty;
    }

    /**
     * Sets string property.
     *
     * @param stringProperty the string property
     */
    public void setStringProperty(final String stringProperty) {
        this.stringProperty = stringProperty;
    }

    /**
     * Gets vector property.
     *
     * @return the vector property
     */
    public Vector3f getVectorProperty() {
        return vectorProperty;
    }

    /**
     * Sets vector property.
     *
     * @param vectorProperty the vector property
     */
    public void setVectorProperty(final Vector3f vectorProperty) {
        this.vectorProperty.set(vectorProperty);
    }

    /**
     * Gets float property.
     *
     * @return the float property
     */
    public float getFloatProperty() {
        return floatProperty;
    }

    /**
     * Sets float property.
     *
     * @param floatProperty the float property
     */
    public void setFloatProperty(final float floatProperty) {
        this.floatProperty = floatProperty;
    }

    @Override
    protected void controlUpdate(final float tpf) {

    }

    @Override
    protected void controlRender(final RenderManager rm, final ViewPort vp) {

    }
}
