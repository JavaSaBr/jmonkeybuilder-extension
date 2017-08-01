package com.ss.editor.extension.scene.filter.impl;

import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.simsilica.fx.LightingState;
import com.ss.editor.extension.loader.SceneLoader;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The editable implementation of a {@link DirectionalLightShadowFilter} which uses the light from {@link
 * LightingState}.
 *
 * @author JavaSaBr
 */
public class EditableLightingStateShadowFilter extends EditableDirectionalLightShadowFilter {

    public EditableLightingStateShadowFilter() {
    }

    @NotNull
    @Override
    public String getName() {
        return "Shadows from Lighting State";
    }

    @Nullable
    @Override
    public String checkStates(@NotNull final List<SceneAppState> exists) {

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
    protected void initFilter(@NotNull final AssetManager manager, @NotNull final RenderManager renderManager,
                              @NotNull final ViewPort viewPort, final int width, final int height) {

        final AppStateManager stateManager = SceneLoader.tryToGetStateManager();
        final LightingState state = stateManager.getState(LightingState.class);
        final DirectionalLight sunLight = state.getSunLight();

        shadowRenderer.setLight(sunLight);

        super.initFilter(manager, renderManager, viewPort, width, height);
    }
}
