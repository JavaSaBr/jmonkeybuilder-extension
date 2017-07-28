package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import com.jme3.scene.Spatial;
import com.jme3.water.WaterFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The editable implementation of water filter.
 *
 * @author JavaSaBr
 */
public class EditableWaterFilter extends WaterFilter implements EditableSceneFilter<WaterFilter> {

    public EditableWaterFilter() {
    }

    @Override
    public String getName() {
        return "Global water filter";
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);
        result.add(new SimpleProperty<>(BOOLEAN, "Use foam", this,
                WaterFilter::isUseFoam,
                WaterFilter::setUseFoam));
        result.add(new SimpleProperty<>(BOOLEAN, "Use caustics", this,
                WaterFilter::isUseCaustics,
                WaterFilter::setUseCaustics));
        result.add(new SimpleProperty<>(BOOLEAN, "Use HQ shoreline", this,
                WaterFilter::isUseHQShoreline,
                WaterFilter::setUseHQShoreline));
        result.add(new SimpleProperty<>(BOOLEAN, "Use refraction", this,
                WaterFilter::isUseRefraction,
                WaterFilter::setUseRefraction));
        result.add(new SimpleProperty<>(BOOLEAN, "Use ripples", this,
                WaterFilter::isUseRipples,
                WaterFilter::setUseRipples));
        result.add(new SimpleProperty<>(BOOLEAN, "Use specular", this,
                WaterFilter::isUseSpecular,
                WaterFilter::setUseSpecular));
        result.add(new SimpleProperty<>(FLOAT, "Water height", this,
                WaterFilter::getWaterHeight,
                WaterFilter::setWaterHeight));
        result.add(new SimpleProperty<>(FLOAT, "Water transparency", 0.05F, 0.05F, 1F, this,
                WaterFilter::getWaterTransparency,
                WaterFilter::setWaterTransparency));
        result.add(new SimpleProperty<>(FLOAT, "Under water fog distance", this,
                WaterFilter::getUnderWaterFogDistance,
                WaterFilter::setUnderWaterFogDistance));
        result.add(new SimpleProperty<>(FLOAT, "Refraction strength", this,
                WaterFilter::getRefractionStrength,
                WaterFilter::setRefractionStrength));
        result.add(new SimpleProperty<>(FLOAT, "Refraction constant", 0.01F, 0F, 1F, this,
                WaterFilter::getRefractionConstant,
                WaterFilter::setRefractionConstant));
        result.add(new SimpleProperty<>(FLOAT, "Reflection displace", this,
                WaterFilter::getReflectionDisplace,
                WaterFilter::setReflectionDisplace));
        result.add(new SimpleProperty<>(FLOAT, "Max aplitude", this,
                WaterFilter::getMaxAmplitude,
                WaterFilter::setMaxAmplitude));
        result.add(new SimpleProperty<>(FLOAT, "Wave scale", 0.01F, 0F, 1F, this,
                WaterFilter::getWaveScale,
                WaterFilter::setWaveScale));
        result.add(new SimpleProperty<>(FLOAT, "Caustics intensity", 0.01F, 0F, 1F, this,
                WaterFilter::getCausticsIntensity,
                WaterFilter::setCausticsIntensity));
        result.add(new SimpleProperty<>(FLOAT, "Foam intensity", 0.01F, -100F, 100F, this,
                WaterFilter::getFoamIntensity,
                WaterFilter::setFoamIntensity));
        result.add(new SimpleProperty<>(FLOAT, "Foam hardness", 0.01F, -100F, 100F, this,
                WaterFilter::getFoamHardness,
                WaterFilter::setFoamHardness));
        result.add(new SimpleProperty<>(COLOR, "Water color", this,
                WaterFilter::getWaterColor,
                WaterFilter::setWaterColor));
        result.add(new SimpleProperty<>(COLOR, "Deep water color", this,
                WaterFilter::getDeepWaterColor,
                WaterFilter::setDeepWaterColor));
        result.add(new SimpleProperty<>(COLOR, "Light color", this,
                WaterFilter::getLightColor,
                WaterFilter::setLightColor));
        result.add(new SimpleProperty<>(ENUM, "Shape type", this,
                WaterFilter::getShapeType,
                WaterFilter::setShapeType));
        result.add(new SimpleProperty<>(VECTOR_3F, "Color extinction", this,
                WaterFilter::getColorExtinction,
                WaterFilter::setColorExtinction));
        result.add(new SimpleProperty<>(VECTOR_3F, "Foam existence", this,
                WaterFilter::getFoamExistence,
                WaterFilter::setFoamExistence));
        result.add(new SimpleProperty<>(VECTOR_3F, "Light direction", this,
                WaterFilter::getLightDirection,
                WaterFilter::setLightDirection));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Foam texture", this,
                WaterFilter::getFoamTexture,
                WaterFilter::setFoamTexture));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Caustics texture", this,
                WaterFilter::getCausticsTexture,
                WaterFilter::setCausticsTexture));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Height texture", this,
                WaterFilter::getHeightTexture,
                WaterFilter::setHeightTexture));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Normal texture", this,
                WaterFilter::getNormalTexture,
                WaterFilter::setNormalTexture));
        result.add(new SimpleProperty<>(SPATIAL_FROM_SCENE, "Reflection node", this,
                makeReflectionSceneGetter(),
                makeReflectionSceneSetter()));

        return result;
    }

    @NotNull
    private BiConsumer<EditableWaterFilter, Spatial> makeReflectionSceneSetter() {
        return (filter, spatial) -> {
            filter.setReflectionScene(spatial);
            filter.setNeedSaveReflectionScene(spatial != null);
        };
    }

    @NotNull
    private Function<EditableWaterFilter, Spatial> makeReflectionSceneGetter() {
        return filter -> {
            if (filter.isNeedSaveReflectionScene()) {
                return filter.getReflectionScene();
            } else {
                return null;
            }
        };
    }

    @Override
    public WaterFilter get() {
        return this;
    }
}
