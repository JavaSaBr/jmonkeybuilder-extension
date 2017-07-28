package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.EditablePropertyType.VECTOR_3F;
import static java.lang.Math.max;
import com.jme3.math.Vector3f;
import com.jme3.water.WaterFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.ScenePresentable;
import com.ss.rlib.util.array.Array;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of local water filter.
 *
 * @author JavaSaBr
 */
public class EditableLocalWaterFilter extends EditableWaterFilter implements ScenePresentable {

    public EditableLocalWaterFilter() {
        setCenter(new Vector3f());
        setShapeType(AreaShape.Circular);
    }

    @NotNull
    @Override
    public Vector3f getLocation() {
        return getCenter();
    }

    @NotNull
    @Override
    public Vector3f getScale() {
        return new Vector3f(getRadius(), getRadius(), getRadius());
    }

    @Override
    public void setLocation(@NotNull final Vector3f location) {
        setCenter(location.clone());
        setWaterHeight(location.getY());
    }

    @Override
    public void setScale(@NotNull final Vector3f scale) {
        setRadius(max(max(scale.getX(), scale.getY()), scale.getZ()));
    }

    @Override
    public String getName() {
        return "Local water filter";
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> properties = super.getEditableProperties();
        properties.add(new SimpleProperty<>(VECTOR_3F, "Center", this,
                WaterFilter::getCenter,
                WaterFilter::setCenter));
        properties.add(new SimpleProperty<>(FLOAT, "Radius", this,
                WaterFilter::getRadius,
                WaterFilter::setRadius));

        return properties;
    }
}
