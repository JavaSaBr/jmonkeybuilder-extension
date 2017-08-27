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
    public @NotNull ToneMapFilter get() {
        return this;
    }

    @Override
    public @NotNull String getName() {
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

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(1);
        result.add(new SimpleProperty<>(VECTOR_3F, "White point", this,
                makeGetter(this, Vector3f.class, "getWhitePoint"),
                makeSetter(this, Vector3f.class, "setWhitePoint")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }

    @Override
    public @Nullable String checkStates(@NotNull final List<SceneAppState> exists) {
        return null;
    }

    @Override
    public @Nullable String checkFilters(@NotNull final List<SceneFilter> exists) {
        return null;
    }
}
