package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import com.jme3.post.filters.RadialBlurFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of radial blur filter.
 *
 * @author JavaSaBr
 */
public class EditableRadialBlurFilter extends RadialBlurFilter implements EditableSceneFilter<RadialBlurFilter> {

    @Override
    public RadialBlurFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
        return "Radial blur filter";
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

        result.add(new SimpleProperty<>(FLOAT, "Sample distance", 0.1F, 0F, 100F, this,
                                        EditableRadialBlurFilter::getSampleDistance,
                                        EditableRadialBlurFilter::setSampleDistance));
        result.add(new SimpleProperty<>(FLOAT, "Sample strength", 0.1F, 0F, 100F, this,
                                        EditableRadialBlurFilter::getSampleStrength,
                                        EditableRadialBlurFilter::setSampleStrength));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }
}
