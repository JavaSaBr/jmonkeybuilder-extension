package com.ss.editor.extension.scene.app.state.impl;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import com.simsilica.fx.LightingState;
import com.simsilica.fx.sky.SkyState;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The editable version of sky state.
 *
 * @author JavaSaBr
 */
public class EditableSkySceneAppState extends SkyState implements EditableSceneAppState {

    public EditableSkySceneAppState() {
        super();
    }

    @NotNull
    @Override
    public String getName() {
        return "Sky State";
    }

    @Nullable
    @Override
    public String checkStates(@NotNull final Array<SceneAppState> exists) {
        final SceneAppState state = exists.search(LightingState.class::isInstance);
        return state == null ? "The Sky State requires the Lighting State" : null;
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(BOOLEAN, "Flat shaded", this,
                                        SkyState::isFlatShaded,
                                        SkyState::setFlatShaded));

        result.add(new SimpleProperty<>(BOOLEAN, "Show ground", this,
                                        SkyState::isShowGroundGeometry,
                                        SkyState::setShowGroundGeometry));

        result.add(new SimpleProperty<>(COLOR, "Ground color", this,
                                        SkyState::getGroundColor,
                                        SkyState::setGroundColor));

        result.add(new SimpleProperty<>(COLOR, "Flat color", this,
                                        SkyState::getFlatColor,
                                        SkyState::setFlatColor));

        result.add(new SimpleProperty<>(FLOAT, "Rayleigh constant", 0.005F, 0, 1, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getRayleighConstant(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setRayleighConstant(value)));

        result.add(new SimpleProperty<>(FLOAT, "Mie constant", 0.001F, 0.0001F, 1, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getMieConstant(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setMieConstant(value)));

        result.add(new SimpleProperty<>(FLOAT, "Blue wave length", 0.01F, 0, 1, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getBlueWaveLength(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setBlueWaveLength(value)));

        result.add(new SimpleProperty<>(FLOAT, "Red wave length", 0.01F, 0, 1, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getRedWaveLength(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setRedWaveLength(value)));

        result.add(new SimpleProperty<>(FLOAT, "Green wave length", 0.005F, 0, 1, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getGreenWaveLength(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setGreenWaveLength(value)));

        result.add(new SimpleProperty<>(FLOAT, "Average density scale", 0.01F, -1, 8, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getAverageDensityScale(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setAverageDensityScale(value)));

        result.add(new SimpleProperty<>(FLOAT, "Ground exposure", this,
                                        state -> state.getAtmosphericParameters()
                                                      .getGroundExposure(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setGroundExposure(value)));

        result.add(new SimpleProperty<>(FLOAT, "Light intensity", this,
                                        state -> state.getAtmosphericParameters()
                                                      .getLightIntensity(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setLightIntensity(value)));

        result.add(new SimpleProperty<>(FLOAT, "MPA factor", 0.01F, -1.5F, 0.0F, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getMiePhaseAsymmetryFactor(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setMiePhaseAsymmetryFactor(value)));

        result.add(new SimpleProperty<>(FLOAT, "Sky exposure", 0.03F, 0, 10, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getSkyExposure(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setSkyExposure(value)));

        result.add(new SimpleProperty<>(FLOAT, "Planet radius", this,
                                        state -> state.getAtmosphericParameters()
                                                      .getPlanetRadius(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setPlanetRadius(value)));

        result.add(new SimpleProperty<>(FLOAT, "Sky flattening", 0.01F, 0, 1, this,
                                        state -> state.getAtmosphericParameters()
                                                      .getSkyFlattening(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setSkyFlattening(value)));

        result.add(new SimpleProperty<>(FLOAT, "Sky dome radius", this,
                                        state -> state.getAtmosphericParameters()
                                                      .getSkyDomeRadius(),
                                        (state, value) -> state.getAtmosphericParameters()
                                                               .setSkyDomeRadius(value)));

        return result;
    }
}
