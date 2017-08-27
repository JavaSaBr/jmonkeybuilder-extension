package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.post.filters.RadialBlurFilter;
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
 * The editable implementation of radial blur filter.
 *
 * @author JavaSaBr
 */
public class EditableRadialBlurFilter extends RadialBlurFilter implements EditableSceneFilter {

    @Override
    public @NotNull RadialBlurFilter get() {
        return this;
    }

    @Override
    public @NotNull String getName() {
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

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(2);
        result.add(new SimpleProperty<>(FLOAT, "Sample distance", 0.1F, 0F, 100F, this,
                makeGetter(this, float.class, "getSampleDistance"),
                makeSetter(this, float.class, "setSampleDistance")));
        result.add(new SimpleProperty<>(FLOAT, "Sample strength", 0.1F, 0F, 100F, this,
                makeGetter(this, float.class, "getSampleStrength"),
                makeSetter(this, float.class, "setSampleStrength")));

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
