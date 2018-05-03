package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.COLOR;
import com.jme3.math.ColorRGBA;
import com.jme3.post.filters.ColorOverlayFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.ReflectionGetterSetterFactory;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable implementation of color overlay filter.
 *
 * @author JavaSaBr
 */
public class EditableColorOverlayFilter extends ColorOverlayFilter implements EditableSceneFilter {

    @Override
    public @NotNull ColorOverlayFilter get() {
        return this;
    }

    @Override
    public @NotNull String getName() {
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

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        List<EditableProperty<?, ?>> result = new ArrayList<>(1);

        result.add(new SimpleProperty<>(COLOR, "Color", this,
                ReflectionGetterSetterFactory.makeGetter(this, ColorRGBA.class, "getColor"),
                ReflectionGetterSetterFactory.makeSetter(this, ColorRGBA.class, "setColor")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull Cloner cloner, @NotNull Object original) {
        setColor(cloner.clone(getColor()));
    }

    @Override
    public @Nullable String checkStates(@NotNull List<SceneAppState> exists) {
        return null;
    }

    @Override
    public @Nullable String checkFilters(@NotNull List<SceneFilter> exists) {
        return null;
    }
}
