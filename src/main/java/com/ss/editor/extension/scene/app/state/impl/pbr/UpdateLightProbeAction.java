package com.ss.editor.extension.scene.app.state.impl.pbr;

import com.jme3.environment.LightProbeFactory;
import com.jme3.environment.generation.JobProgressAdapter;
import com.jme3.environment.util.EnvMapUtils;
import com.jme3.light.LightProbe;
import com.jme3.scene.Node;
import com.ss.editor.extension.action.AbstractModifyingAction;
import com.ss.editor.extension.integration.EditorEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * The implementation of {@link com.ss.editor.extension.action.ModifyingAction} to update a light probe.
 *
 * @author JavaSaBr
 */
public class UpdateLightProbeAction extends AbstractModifyingAction<Object, StaticLightProbeSceneAppState> {

    private static final UpdateLightProbeAction INSTANCE = new UpdateLightProbeAction();

    protected static @NotNull UpdateLightProbeAction getInstance() {
        return INSTANCE;
    }

    @Override
    public @NotNull String getName() {
        return "Update light probe";
    }


    @Override
    protected @NotNull Object redoImpl(
            @NotNull final EditorEnvironment env,
            @NotNull final StaticLightProbeSceneAppState appState
    ) {

        Node envScene = appState.getEnvironmentScene();

        if (envScene == null) {
            envScene = StaticLightProbeSceneAppState.EMPTY_SCENE;
        }

        EnvMapUtils.GenerationType genType = appState.getGenerationType();
        LightProbe lightProbe = appState.getLightProbe();

        env.notifyStartLoading();
        appState.prepareToMakeProbe();

        LightProbeFactory.updateProbe(lightProbe, appState, envScene, genType, new JobProgressAdapter<LightProbe>() {

            @Override
            public void done(@NotNull LightProbe result) {
                env.notifyEndLoading();
                appState.notifyProbeComplete();
            }
        });

        return NO_STATE;
    }
}
