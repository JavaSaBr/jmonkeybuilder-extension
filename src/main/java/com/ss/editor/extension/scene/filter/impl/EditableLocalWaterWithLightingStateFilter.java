package com.ss.editor.extension.scene.filter.impl;

import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.simsilica.fx.LightingState;
import com.ss.editor.extension.loader.SceneLoader;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The implementation of local water filter which uses the light direction from {@link LightingState}.
 *
 * @author JavaSaBr
 */
public class EditableLocalWaterWithLightingStateFilter extends EditableLocalWaterFilter {

    public EditableLocalWaterWithLightingStateFilter() {
    }

    @Override
    public @NotNull String getName() {
        return "Local water with lighting state";
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

        if (lightingState == null) {
            return "The Shadows from Lighting State requires the Lighting State";
        }

        return null;
    }

    @Override
    protected boolean needLightColor() {
        return false;
    }

    @Override
    protected boolean needLightDirection() {
        return false;
    }

    @Override
    protected void initFilter(@NotNull final AssetManager manager, @NotNull final RenderManager renderManager,
                              @NotNull final ViewPort viewPort, final int width, final int height) {

        final AppStateManager stateManager = SceneLoader.tryToGetStateManager();
        final LightingState state = stateManager.getState(LightingState.class);
        final DirectionalLight sunLight = state.getSunLight();

        setLightDirection(sunLight.getDirection());
        setLightColor(sunLight.getColor());

        super.initFilter(manager, renderManager, viewPort, width, height);
    }
}
