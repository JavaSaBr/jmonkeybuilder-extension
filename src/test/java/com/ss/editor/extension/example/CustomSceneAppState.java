package com.ss.editor.extension.example;

import com.jme3.app.Application;
import com.jme3.renderer.RenderManager;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.impl.BaseEditableSceneAppState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author JavaSaBr
 */
public class CustomSceneAppState extends BaseEditableSceneAppState implements EditableSceneAppState {

    @Override
    protected void initialize(@NotNull Application application) {
        super.initialize(application);
    }

    @Override
    protected void cleanup(@NotNull Application application) {
        super.cleanup(application);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
    }

    @Override
    public void render(RenderManager renderManager) {
        super.render(renderManager);
    }

    @Override
    public void setSceneNode(@Nullable SceneNode sceneNode) {
        super.setSceneNode(sceneNode);
    }

    @Override
    public void notifyAdded(@NotNull Object object) {
        super.notifyAdded(object);
    }

    @Override
    public void notifyRemoved(@NotNull Object object) {
        super.notifyRemoved(object);
    }

    @Override
    public @NotNull String getName() {
        return "MySceneState";
    }
}
