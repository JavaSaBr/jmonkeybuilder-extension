package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture2D;
import com.jme3.water.WaterFilter;
import com.ss.editor.extension.property.*;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable implementation of water filter.
 *
 * @author JavaSaBr
 */
public class EditableWaterFilter extends WaterFilter implements EditableSceneFilter {

    public EditableWaterFilter() {
    }

    @Override
    public String getName() {
        return "Global water filter";
    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(35);
        result.add(new SimpleProperty<>(BOOLEAN, "Use foam", this,
                makeGetter(this, boolean.class, "isUseFoam"),
                makeSetter(this, boolean.class, "setUseFoam")));
        result.add(new SimpleProperty<>(BOOLEAN, "Use caustics", this,
                makeGetter(this, boolean.class, "isUseCaustics"),
                makeSetter(this, boolean.class, "setUseCaustics")));
        result.add(new SimpleProperty<>(BOOLEAN, "Use HQ shoreline", this,
                makeGetter(this, boolean.class, "isUseHQShoreline"),
                makeSetter(this, boolean.class, "setUseHQShoreline")));
        result.add(new SimpleProperty<>(BOOLEAN, "Use refraction", this,
                makeGetter(this, boolean.class, "isUseRefraction"),
                makeSetter(this, boolean.class, "setUseRefraction")));
        result.add(new SimpleProperty<>(BOOLEAN, "Use ripples", this,
                makeGetter(this, boolean.class, "isUseRipples"),
                makeSetter(this, boolean.class, "setUseRipples")));
        result.add(new SimpleProperty<>(BOOLEAN, "Use specular", this,
                makeGetter(this, boolean.class, "isUseSpecular"),
                makeSetter(this, boolean.class, "setUseSpecular")));
        result.add(new SimpleProperty<>(FLOAT, "Water height", this,
                makeGetter(this, float.class, "getWaterHeight"),
                makeSetter(this, float.class, "setWaterHeight")));
        result.add(new SimpleProperty<>(FLOAT, "Water transparency", 0.01F, 0.001F, 1F, this,
                makeGetter(this, float.class, "getWaterTransparency"),
                makeSetter(this, float.class, "setWaterTransparency")));
        result.add(new SimpleProperty<>(FLOAT, "Under water fog distance", this,
                makeGetter(this, float.class, "getUnderWaterFogDistance"),
                makeSetter(this, float.class, "setUnderWaterFogDistance")));
        result.add(new SimpleProperty<>(FLOAT, "Refraction strength", 0.01F, -2F, 2F, this,
                makeGetter(this, float.class, "getRefractionStrength"),
                makeSetter(this, float.class, "setRefractionStrength")));
        result.add(new SimpleProperty<>(FLOAT, "Refraction constant", 0.01F, 0F, 1F, this,
                makeGetter(this, float.class, "getRefractionConstant"),
                makeSetter(this, float.class, "setRefractionConstant")));
        result.add(new SimpleProperty<>(FLOAT, "Reflection displace", 10F, -10000F, 10000F, this,
                makeGetter(this, float.class, "getReflectionDisplace"),
                makeSetter(this, float.class, "setReflectionDisplace")));
        result.add(new SimpleProperty<>(FLOAT, "Max aplitude", 0.1F, 0F, 10F, this,
                makeGetter(this, float.class, "getMaxAmplitude"),
                makeSetter(this, float.class, "setMaxAmplitude")));
        result.add(new SimpleProperty<>(FLOAT, "Wave scale", 0.003F, 0.001F,    1F, this,
                makeGetter(this, float.class, "getWaveScale"),
                makeSetter(this, float.class, "setWaveScale")));
        result.add(new SimpleProperty<>(FLOAT, "Caustics intensity", 0.01F, 0F, 1F, this,
                makeGetter(this, float.class, "getCausticsIntensity"),
                makeSetter(this, float.class, "setCausticsIntensity")));
        result.add(new SimpleProperty<>(FLOAT, "Foam intensity", 0.01F, -100F, 100F, this,
                makeGetter(this, float.class, "getFoamIntensity"),
                makeSetter(this, float.class, "setFoamIntensity")));
        result.add(new SimpleProperty<>(FLOAT, "Foam hardness", 0.01F, -100F, 100F, this,
                makeGetter(this, float.class, "getFoamHardness"),
                makeSetter(this, float.class, "setFoamHardness")));
        result.add(new SimpleProperty<>(COLOR, "Water color", this,
                makeGetter(this, ColorRGBA.class, "getWaterColor"),
                makeSetter(this, ColorRGBA.class, "setWaterColor")));
        result.add(new SimpleProperty<>(COLOR, "Deep water color", this,
                makeGetter(this, ColorRGBA.class, "getDeepWaterColor"),
                makeSetter(this, ColorRGBA.class, "setDeepWaterColor")));
        result.add(new SimpleProperty<>(COLOR, "Light color", this,
                makeGetter(this, ColorRGBA.class, "getLightColor"),
                makeSetter(this, ColorRGBA.class, "setLightColor")));
        result.add(new SimpleProperty<>(ENUM, "Shape type", this,
                makeGetter(this, AreaShape.class, "getShapeType"),
                makeSetter(this, AreaShape.class, "setShapeType")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Color extinction", this,
                makeGetter(this, Vector3f.class, "getColorExtinction"),
                makeSetter(this, Vector3f.class, "setColorExtinction")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Foam existence", this,
                makeGetter(this, Vector3f.class, "getFoamExistence"),
                makeSetter(this, Vector3f.class, "setFoamExistence")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Light direction", this,
                makeGetter(this, Vector3f.class, "getLightDirection"),
                makeSetter(this, Vector3f.class, "setLightDirection")));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Foam texture", this,
                makeGetter(this, Texture2D.class, "getFoamTexture"),
                makeSetter(this, Texture2D.class, "setFoamTexture")));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Caustics texture", this,
                makeGetter(this, Texture2D.class, "getCausticsTexture"),
                makeSetter(this, Texture2D.class, "setCausticsTexture")));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Height texture", this,
                makeGetter(this, Texture2D.class, "getHeightTexture"),
                makeSetter(this, Texture2D.class, "setHeightTexture")));
        result.add(new SimpleProperty<>(TEXTURE_2D, "Normal texture", this,
                makeGetter(this, Texture2D.class, "getNormalTexture"),
                makeSetter(this, Texture2D.class, "setNormalTexture")));
        result.add(new SimpleProperty<>(SPATIAL_FROM_SCENE, "Reflection node", this,
                makeReflectionSceneGetter(),
                makeReflectionSceneSetter()));

        return result;
    }

    @NotNull
    private Setter<EditableWaterFilter, Spatial> makeReflectionSceneSetter() {
        return new Setter<EditableWaterFilter, Spatial>() {

            @Override
            public void set(@NotNull final EditableWaterFilter filter, @Nullable final Spatial spatial) {
                filter.setReflectionScene(spatial);
                filter.setNeedSaveReflectionScene(spatial != null);
            }
        };
    }

    @NotNull
    private Getter<EditableWaterFilter, Spatial> makeReflectionSceneGetter() {
        return new Getter<EditableWaterFilter, Spatial>() {

            @Nullable
            @Override
            public Spatial get(@NotNull final EditableWaterFilter filter) {
                if (filter.isNeedSaveReflectionScene()) {
                    return filter.getReflectionScene();
                } else {
                    return null;
                }
            }
        };
    }

    @Override
    public WaterFilter get() {
        return this;
    }

    @Nullable
    @Override
    public String checkStates(final @NotNull List<SceneAppState> exists) {
        return null;
    }

    @Nullable
    @Override
    public String checkFilters(final @NotNull List<SceneFilter> exists) {
        return null;
    }
}
