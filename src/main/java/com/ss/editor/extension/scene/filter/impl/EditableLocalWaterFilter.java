package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.EditablePropertyType.VECTOR_3F;
import com.jme3.math.Vector3f;
import com.jme3.water.WaterFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.rlib.util.array.Array;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of local water filter.
 *
 * @author JavaSaBr
 */
public class EditableLocalWaterFilter extends EditableWaterFilter {

    public EditableLocalWaterFilter() {
        setCenter(new Vector3f());
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
