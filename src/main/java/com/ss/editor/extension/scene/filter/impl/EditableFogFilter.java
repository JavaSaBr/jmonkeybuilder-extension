package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.COLOR;
import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import com.jme3.post.filters.FogFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of fog filter.
 *
 * @author JavaSaBr
 */
public class EditableFogFilter extends FogFilter implements EditableSceneFilter<FogFilter> {

    @Override
    public FogFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
        return "Fog filter";
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

        result.add(new SimpleProperty<>(COLOR, "Fog color", this,
                                        EditableFogFilter::getFogColor,
                                        EditableFogFilter::setFogColor));
        result.add(new SimpleProperty<>(FLOAT, "Fog density", 0.01F, 0F, 100F, this,
                                        EditableFogFilter::getFogDensity,
                                        EditableFogFilter::setFogDensity));
        result.add(new SimpleProperty<>(FLOAT, "Fog distance", 1F, 0F, Integer.MAX_VALUE, this,
                                        EditableFogFilter::getFogDistance,
                                        EditableFogFilter::setFogDistance));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
        setFogColor(cloner.clone(getFogColor()));
    }
}
