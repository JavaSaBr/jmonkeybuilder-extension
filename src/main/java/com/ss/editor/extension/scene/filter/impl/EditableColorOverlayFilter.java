package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.COLOR;
import com.jme3.post.filters.ColorOverlayFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of color overlay filter.
 *
 * @author JavaSaBr
 */
public class EditableColorOverlayFilter extends ColorOverlayFilter implements EditableSceneFilter<ColorOverlayFilter> {

    @Override
    public ColorOverlayFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
        return "Color overlay filter";
    }

    @Override
    public Object jmeClone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(COLOR, "Color", this,
                                        ColorOverlayFilter::getColor,
                                        ColorOverlayFilter::setColor));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
        setColor(cloner.clone(getColor()));
    }
}
