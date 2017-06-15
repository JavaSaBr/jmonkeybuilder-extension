package com.ss.editor.extension.scene.filter.impl;

import com.jme3.shadow.AbstractShadowFilter;
import com.jme3.shadow.PointLightShadowFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.loader.SceneLoader;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.EditablePropertyType;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import org.jetbrains.annotations.NotNull;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;

/**
 * The editable implementation of a {@link PointLightShadowFilter}.
 *
 * @author JavaSaBr
 */
@SuppressWarnings("WeakerAccess")
public class EditablePointLightShadowFilter extends PointLightShadowFilter implements
        EditableSceneFilter<AbstractShadowFilter<?>> {

    public static final int SHADOW_MAP_SIZE = 1024;

    public EditablePointLightShadowFilter() {
        super(SceneLoader.tryToGetAssetManager(), SHADOW_MAP_SIZE);
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(EditablePropertyType.ENUM, "Edge filtering mode", this,
                                        EditablePointLightShadowFilter::getEdgeFilteringMode,
                                        EditablePointLightShadowFilter::setEdgeFilteringMode));
        result.add(new SimpleProperty<>(EditablePropertyType.ENUM, "Shadow compare mode", this,
                                        EditablePointLightShadowFilter::getShadowCompareMode,
                                        EditablePointLightShadowFilter::setShadowCompareMode));
        result.add(new SimpleProperty<>(EditablePropertyType.FLOAT, "Shadow z extend", this,
                                        EditablePointLightShadowFilter::getShadowZExtend,
                                        EditablePointLightShadowFilter::setShadowZExtend));
        result.add(new SimpleProperty<>(EditablePropertyType.FLOAT, "Shadow z fade length", this,
                                        EditablePointLightShadowFilter::getShadowZFadeLength,
                                        EditablePointLightShadowFilter::setShadowZFadeLength));
        result.add(new SimpleProperty<>(EditablePropertyType.FLOAT, "Shadow intensity", this,
                                        EditablePointLightShadowFilter::getShadowIntensity,
                                        EditablePointLightShadowFilter::setShadowIntensity));
        result.add(new SimpleProperty<>(EditablePropertyType.INTEGER, "Edges thickness", this,
                                        EditablePointLightShadowFilter::getEdgesThickness,
                                        EditablePointLightShadowFilter::setEdgesThickness));
        result.add(new SimpleProperty<>(EditablePropertyType.BOOLEAN, "Back faces shadows", this,
                                        EditablePointLightShadowFilter::isRenderBackFacesShadows,
                                        EditablePointLightShadowFilter::setRenderBackFacesShadows));

        return result;
    }

    @Override
    public AbstractShadowFilter<?> get() {
        return this;
    }
}
