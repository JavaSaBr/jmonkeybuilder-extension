package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.shadow.AbstractShadowFilter;
import com.jme3.shadow.CompareMode;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.shadow.PointLightShadowFilter;
import com.ss.editor.extension.loader.SceneLoader;
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
 * The editable implementation of a {@link PointLightShadowFilter}.
 *
 * @author JavaSaBr
 */
public class EditablePointLightShadowFilter extends PointLightShadowFilter implements EditableSceneFilter {

    public static final int SHADOW_MAP_SIZE = 1024;

    public EditablePointLightShadowFilter() {
        super(SceneLoader.tryToGetAssetManager(), SHADOW_MAP_SIZE);
    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(8);
        result.add(new SimpleProperty<>(ENUM, "Edge filtering mode", this,
                makeGetter(this, EdgeFilteringMode.class, "getEdgeFilteringMode"),
                makeSetter(this, EdgeFilteringMode.class, "setEdgeFilteringMode")));
        result.add(new SimpleProperty<>(ENUM, "Shadow compare mode", this,
                makeGetter(this, CompareMode.class, "getShadowCompareMode"),
                makeSetter(this, CompareMode.class, "setShadowCompareMode")));
        result.add(new SimpleProperty<>(FLOAT, "Shadow z extend", this,
                makeGetter(this, float.class, "getShadowZExtend"),
                makeSetter(this, float.class, "setShadowZExtend")));
        result.add(new SimpleProperty<>(FLOAT, "Shadow z fade length", this,
                makeGetter(this, float.class, "getShadowZFadeLength"),
                makeSetter(this, float.class, "setShadowZFadeLength")));
        result.add(new SimpleProperty<>(FLOAT, "Shadow intensity", 0.1F, 0F, 1F, this,
                makeGetter(this, float.class, "getShadowIntensity"),
                makeSetter(this, float.class, "setShadowIntensity")));
        result.add(new SimpleProperty<>(INTEGER, "Edges thickness", 1F, 1, 10, this,
                makeGetter(this, int.class, "getEdgesThickness"),
                makeSetter(this, int.class, "setEdgesThickness")));
        result.add(new SimpleProperty<>(BOOLEAN, "Back faces shadows", this,
                makeGetter(this, boolean.class, "isRenderBackFacesShadows"),
                makeSetter(this, boolean.class, "setRenderBackFacesShadows")));

        return result;
    }

    @Override
    public AbstractShadowFilter<?> get() {
        return this;
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
