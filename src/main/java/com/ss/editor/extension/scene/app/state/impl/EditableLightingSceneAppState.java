package com.ss.editor.extension.scene.app.state.impl;

import static com.ss.editor.extension.property.EditablePropertyType.COLOR;
import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.math.ColorRGBA;
import com.simsilica.fx.LightingState;
import com.ss.editor.extension.property.*;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable version of lighting state.
 *
 * @author JavaSaBr
 */
public class EditableLightingSceneAppState extends LightingState implements EditableSceneAppState {

    public EditableLightingSceneAppState() {
        super();
    }

    @NotNull
    @Override
    public String getName() {
        return "Lighting State";
    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(4);

        result.add(new SimpleProperty<>(COLOR, "Ambient color", this,
                makeGetter(this, ColorRGBA.class, "getAmbientColor"),
                makeSetter(this, ColorRGBA.class, "setAmbientColor")));

        result.add(new SimpleProperty<>(COLOR, "Sun color", this,
                makeGetter(this, ColorRGBA.class, "getSunColor"),
                makeSetter(this, ColorRGBA.class, "setSunColor")));

        result.add(new SimpleProperty<>(FLOAT, "Time of day", 0.03F, -0.300F, 1.300F, this,
                makeGetter(this, float.class, "getTimeOfDay"),
                makeSetter(this, float.class, "setTimeOfDay")));

        result.add(new SimpleProperty<>(FLOAT, "Orientation", 0.1F, 0F, 6.283F, this,
                makeGetter(this, float.class, "getOrientation"),
                makeSetter(this, float.class, "setOrientation")));

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

    @Override
    public void setSceneNode(@Nullable final SceneNode sceneNode) {
    }

    @Override
    public void notifyAdded(@NotNull final Object object) {
    }

    @Override
    public void notifyRemoved(@NotNull final Object object) {
    }
}
