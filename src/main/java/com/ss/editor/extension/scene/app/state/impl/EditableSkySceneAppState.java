package com.ss.editor.extension.scene.app.state.impl;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.math.ColorRGBA;
import com.simsilica.fx.LightingState;
import com.simsilica.fx.sky.SkyState;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.Getter;
import com.ss.editor.extension.property.Setter;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable version of sky state.
 *
 * @author JavaSaBr
 */
public class EditableSkySceneAppState extends SkyState implements EditableSceneAppState {

    public EditableSkySceneAppState() {
        super();
    }

    @Override
    public @NotNull String getName() {
        return "Sky State";
    }

    @Override
    public @Nullable String checkStates(@NotNull final List<SceneAppState> exists) {

        SceneAppState lightingState = null;

        for (final SceneAppState state : exists) {
            if (state instanceof LightingState) {
                lightingState = state;
                break;
            }
        }

        return lightingState == null ? "The Sky State requires the Lighting State" : null;
    }

    @Override
    public @Nullable String checkFilters(@NotNull final List<SceneFilter> exists) {
        return null;
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(18);

        result.add(new SimpleProperty<>(BOOLEAN, "Flat shaded", this,
                makeGetter(this, boolean.class, "isFlatShaded"),
                makeSetter(this, boolean.class, "setFlatShaded")));

        result.add(new SimpleProperty<>(BOOLEAN, "Show ground", this,
                makeGetter(this, boolean.class, "isShowGroundGeometry"),
                makeSetter(this, boolean.class, "setShowGroundGeometry")));

        result.add(new SimpleProperty<>(COLOR, "Ground color", this,
                makeGetter(this, ColorRGBA.class, "getGroundColor"),
                makeSetter(this, ColorRGBA.class, "setGroundColor")));

        result.add(new SimpleProperty<>(COLOR, "Flat color", this,
                makeGetter(this, ColorRGBA.class, "getFlatColor"),
                makeSetter(this, ColorRGBA.class, "setFlatColor")));

        result.add(new SimpleProperty<>(FLOAT, "Rayleigh constant", 0.005F, 0, 1, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getRayleighConstant();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setRayleighConstant(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Mie constant", 0.001F, 0.0001F, 1, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getMieConstant();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setMieConstant(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Blue wave length", 0.01F, 0, 1, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getBlueWaveLength();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setBlueWaveLength(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Red wave length", 0.01F, 0, 1, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getRedWaveLength();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setRedWaveLength(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Green wave length", 0.005F, 0, 1, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getGreenWaveLength();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setGreenWaveLength(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Average density scale", 0.01F, -1, 8, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getAverageDensityScale();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setAverageDensityScale(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Ground exposure", this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getGroundExposure();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setGroundExposure(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Light intensity", this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getLightIntensity();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setLightIntensity(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "MPA factor", 0.01F, -1.5F, 0.0F, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getMiePhaseAsymmetryFactor();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setMiePhaseAsymmetryFactor(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Sky exposure", 0.03F, 0, 10, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getSkyExposure();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setSkyExposure(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Planet radius", this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getPlanetRadius();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setPlanetRadius(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Sky flattening", 0.01F, 0, 1, this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getSkyFlattening();
            }
        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setSkyFlattening(property);
            }
        }));

        result.add(new SimpleProperty<>(FLOAT, "Sky dome radius", this, new Getter<EditableSkySceneAppState, Float>() {

            @Nullable
            @Override
            public Float get(@NotNull final EditableSkySceneAppState object) {
                return object.getAtmosphericParameters().getSkyDomeRadius();
            }

        }, new Setter<EditableSkySceneAppState, Float>() {

            @Override
            public void set(@NotNull final EditableSkySceneAppState object, @Nullable final Float property) {
                object.getAtmosphericParameters().setSkyDomeRadius(property);
            }
        }));

        return result;
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
