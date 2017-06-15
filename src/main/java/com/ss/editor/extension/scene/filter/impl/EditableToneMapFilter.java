package com.ss.editor.extension.scene.filter.impl;

import com.jme3.post.filters.ToneMapFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditablePropertyType;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import org.jetbrains.annotations.NotNull;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;

/**
 * The editable implementation of Tone-mapping filter.
 *
 * @author JavaSaBr
 */
public class EditableToneMapFilter extends ToneMapFilter implements EditableSceneFilter<ToneMapFilter> {

    @Override
    public ToneMapFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
        return "Tone-mapping filter";
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

        result.add(new SimpleProperty<>(EditablePropertyType.VECTOR_3F, "White point", this,
                                        ToneMapFilter::getWhitePoint,
                                        ToneMapFilter::setWhitePoint));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }
}
