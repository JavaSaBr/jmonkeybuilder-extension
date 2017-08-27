package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.post.filters.DepthOfFieldFilter;
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
 * The editable implementation of depth of field filter.
 *
 * @author JavaSaBr
 */
public class EditableDepthOfFieldFilter extends DepthOfFieldFilter implements EditableSceneFilter {

    @Override
    public @NotNull DepthOfFieldFilter get() {
        return this;
    }

    @Override
    public @NotNull String getName() {
        return "Depth of field filter";
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

        final List<EditableProperty<?, ?>> result = new ArrayList<>(3);

        result.add(new SimpleProperty<>(FLOAT, "Blur scale", 0.01F, 0F, 100F, this,
                makeGetter(this, float.class, "getBlurScale"),
                makeSetter(this, float.class, "setBlurScale")));
        result.add(new SimpleProperty<>(FLOAT, "Focus distance", 1F, 0F, Integer.MAX_VALUE, this,
                makeGetter(this, float.class, "getFocusDistance"),
                makeSetter(this, float.class, "setFocusDistance")));
        result.add(new SimpleProperty<>(FLOAT, "Focus range", 1F, 0F, Integer.MAX_VALUE, this,
                makeGetter(this, float.class, "getFocusRange"),
                makeSetter(this, float.class, "setFocusRange")));

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
