package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.math.ColorRGBA;
import com.simsilica.fx.shadow.DropShadowFilter;
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
 * The editable implementation of the {@link DropShadowFilter}.
 *
 * @author JavaSaBr
 */
public class EditableDropShadowFilter extends DropShadowFilter implements EditableSceneFilter {

    public EditableDropShadowFilter() {
        super();
    }

    @NotNull
    @Override
    public String getName() {
        return "Drop shadow filter";
    }

    @Override
    public DropShadowFilter get() {
        return this;
    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(3);

        result.add(new SimpleProperty<>(COLOR, "Shadow color", this,
                makeGetter(this, ColorRGBA.class, "getShadowColor"),
                makeSetter(this, ColorRGBA.class, "setShadowColor")));
        result.add(new SimpleProperty<>(INTEGER, "Max shadows", this,
                makeGetter(this, int.class, "getMaxShadows"),
                makeSetter(this, int.class, "setMaxShadows")));
        result.add(new SimpleProperty<>(FLOAT, "Shadow intensity", 0.005F, 0F, 1F, this,
                makeGetter(this, float.class, "getShadowIntensity"),
                makeSetter(this, float.class, "setShadowIntensity")));

        return result;
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
