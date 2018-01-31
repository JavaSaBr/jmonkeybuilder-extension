package com.ss.editor.extension.test;

import com.ss.editor.extension.scene.app.state.impl.bullet.EditableBulletSceneAppState;
import com.ss.editor.extension.scene.app.state.impl.pbr.StaticLightProbeSceneAppState;
import org.junit.jupiter.api.Test;

/**
 * The test ot test all editable app states.
 *
 * @author JavaSaBr
 */
class AppStatesTest extends PropertyTest {

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
