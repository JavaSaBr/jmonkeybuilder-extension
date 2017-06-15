package com.ss.editor.extension.scene.filter.impl;

import com.simsilica.fx.shadow.DropShadowFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.EditablePropertyType;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.editor.extension.property.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;

/**
 * The editable implementation of the {@link DropShadowFilter}.
 *
 * @author JavaSaBr
 */
public class EditableDropShadowFilter extends DropShadowFilter implements EditableSceneFilter<DropShadowFilter> {

    public EditableDropShadowFilter() {
        super();
    }

    @NotNull
    @Override
    public String getName() {
        return "Drop shadow filter";
    }

    @Override
    public DropShadowFilter get() {
        return this;
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(EditablePropertyType.COLOR, "Shadow color", this,
                                        EditableDropShadowFilter::getShadowColor,
                                        EditableDropShadowFilter::setShadowColor));
        result.add(new SimpleProperty<>(EditablePropertyType.INTEGER, "Max shadows", this,
                                        EditableDropShadowFilter::getMaxShadows,
                                        EditableDropShadowFilter::setMaxShadows));
        result.add(new SimpleProperty<>(EditablePropertyType.FLOAT, "Shadow intensity", 0.005F, 0F, 1F, this,
                                        EditableDropShadowFilter::getShadowIntensity,
                                        EditableDropShadowFilter::setShadowIntensity));

        return result;
    }
}
