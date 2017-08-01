package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.VECTOR_3F;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.math.Vector3f;
import com.jme3.post.filters.ToneMapFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable implementation of Tone-mapping filter.
 *
 * @author JavaSaBr
 */
public class EditableToneMapFilter extends ToneMapFilter implements EditableSceneFilter {

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
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(1);
        result.add(new SimpleProperty<>(VECTOR_3F, "White point", this,
                makeGetter(this, Vector3f.class, "getWhitePoint"),
                makeSetter(this, Vector3f.class, "setWhitePoint")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }

    @Nullable
    @Override
    public String checkStates(final @NotNull List<SceneAppState> exists) {
        return null;
    }

    @Nullable
    @Override
    public String checkFilters(final @NotNull List<SceneFilter> exists) {
        return null;
    }
}
