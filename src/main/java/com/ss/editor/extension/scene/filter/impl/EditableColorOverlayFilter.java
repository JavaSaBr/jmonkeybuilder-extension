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
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(1);

        result.add(new SimpleProperty<>(COLOR, "Color", this,
                ReflectionGetterSetterFactory.makeGetter(this, ColorRGBA.class, "getColor"),
                ReflectionGetterSetterFactory.makeSetter(this, ColorRGBA.class, "setColor")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
        setColor(cloner.clone(getColor()));
    }

    @Nullable
    @Override
    public String checkStates(@NotNull final List<SceneAppState> exists) {
        return null;
    }

    @Nullable
    @Override
    public String checkFilters(@NotNull final List<SceneFilter> exists) {
        return null;
    }
}
