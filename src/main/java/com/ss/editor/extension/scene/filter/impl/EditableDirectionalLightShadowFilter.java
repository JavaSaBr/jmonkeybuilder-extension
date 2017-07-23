package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.loader.SceneLoader.tryToGetAssetManager;
import static com.ss.editor.extension.property.EditablePropertyType.*;
import com.jme3.shadow.AbstractShadowFilter;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of a {@link DirectionalLightShadowFilter}.
 *
 * @author JavaSaBr
 */
@SuppressWarnings("WeakerAccess")
public class EditableDirectionalLightShadowFilter extends DirectionalLightShadowFilter implements
        EditableSceneFilter<AbstractShadowFilter<?>> {

    public static final int SHADOW_MAP_SIZE = 1024;

    public EditableDirectionalLightShadowFilter() {
        super(tryToGetAssetManager(), SHADOW_MAP_SIZE, 3);
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(ENUM, "Edge filtering mode", this,
                EditableDirectionalLightShadowFilter::getEdgeFilteringMode,
                EditableDirectionalLightShadowFilter::setEdgeFilteringMode));

        result.add(new SimpleProperty<>(ENUM, "Shadow compare mode", this,
                EditableDirectionalLightShadowFilter::getShadowCompareMode,
                EditableDirectionalLightShadowFilter::setShadowCompareMode));

        result.add(new SimpleProperty<>(FLOAT, "Shadow z extend", this,
                EditableDirectionalLightShadowFilter::getShadowZExtend,
                EditableDirectionalLightShadowFilter::setShadowZExtend));

        result.add(new SimpleProperty<>(FLOAT, "Shadow z fade length", this,
                EditableDirectionalLightShadowFilter::getShadowZFadeLength,
                EditableDirectionalLightShadowFilter::setShadowZFadeLength));

        result.add(new SimpleProperty<>(FLOAT, "Lambda", this,
                EditableDirectionalLightShadowFilter::getLambda,
                EditableDirectionalLightShadowFilter::setLambda));

        result.add(new SimpleProperty<>(FLOAT, "Shadow intensity", 0.1F, 0F, 1F, this,
                EditableDirectionalLightShadowFilter::getShadowIntensity,
                EditableDirectionalLightShadowFilter::setShadowIntensity));

        result.add(new SimpleProperty<>(INTEGER, "Edges thickness", 1F, 1, 10, this,
                EditableDirectionalLightShadowFilter::getEdgesThickness,
                EditableDirectionalLightShadowFilter::setEdgesThickness));

        result.add(new SimpleProperty<>(BOOLEAN, "Back faces shadows", this,
                EditableDirectionalLightShadowFilter::isRenderBackFacesShadows,
                EditableDirectionalLightShadowFilter::setRenderBackFacesShadows));

        result.add(new SimpleProperty<>(BOOLEAN, "Stabilization", this,
                EditableDirectionalLightShadowFilter::isEnabledStabilization,
                EditableDirectionalLightShadowFilter::setEnabledStabilization));

        return result;
    }

    public void setLambda(final float lambda) {
        shadowRenderer.setLambda(lambda);
    }

    public float getLambda() {
        return shadowRenderer.getLambda();
    }

    public void setEnabledStabilization(final boolean stabilize) {
        shadowRenderer.setEnabledStabilization(stabilize);
    }

    public boolean isEnabledStabilization() {
        return shadowRenderer.isEnabledStabilization();
    }

    @Override
    public AbstractShadowFilter<?> get() {
        return this;
    }
}
