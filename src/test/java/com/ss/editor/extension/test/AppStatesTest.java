package com.ss.editor.extension.test;

import com.ss.editor.extension.scene.app.state.impl.EditableLightingSceneAppState;
import com.ss.editor.extension.scene.app.state.impl.EditableSkySceneAppState;
import com.ss.editor.extension.scene.app.state.impl.bullet.EditableBulletSceneAppState;
import com.ss.editor.extension.scene.app.state.impl.pbr.StaticLightProbeSceneAppState;
import org.junit.jupiter.api.Test;

/**
 * @author JavaSaBr
 */
class AppStatesTest extends PropertyTest {

    @Test
    void skySceneAppStateTest() {
        final EditableSkySceneAppState appState = new EditableSkySceneAppState();
        testProperties(appState.getEditableProperties());
    }

    @Test
    void lightingSceneAppStateTest() {
        final EditableLightingSceneAppState appState = new EditableLightingSceneAppState();
        testProperties(appState.getEditableProperties());
    }

    @Test
    void bulletSceneAppStateTest() {
        final EditableBulletSceneAppState appState = new EditableBulletSceneAppState();
        testProperties(appState.getEditableProperties());
    }

    @Test
    void staticPBRSceneAppStateTest() {
        final StaticLightProbeSceneAppState appState = new StaticLightProbeSceneAppState();
        testProperties(appState.getEditableProperties());
    }
}
