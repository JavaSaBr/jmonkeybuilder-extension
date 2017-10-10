package com.ss.editor.extension.test;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import com.ss.editor.extension.scene.SceneNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test ot test all editable controls.
 *
 * @author JavaSaBr
 */
class LoaderTest extends SetUpTest {

    @Test
    void testLoadJ3o() {
        final AssetManager assetManager = getApplication().getAssetManager();
        final Spatial model = assetManager.loadModel("com/ss/editor/extension/testCube.j3o");
        Assertions.assertNotNull(model);
    }

    @Test
    void testLoadJ3s() {
        final AssetManager assetManager = getApplication().getAssetManager();
        final SceneNode model = (SceneNode) assetManager.loadModel("com/ss/editor/extension/testScene.j3s");
        Assertions.assertNotNull(model);
    }
}
