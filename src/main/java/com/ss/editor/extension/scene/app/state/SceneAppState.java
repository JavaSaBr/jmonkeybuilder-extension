package com.ss.editor.extension.scene.app.state;

import com.jme3.app.state.AppState;
import com.jme3.export.Savable;
import com.jme3.util.clone.JmeCloneable;
import com.ss.editor.extension.Named;
import com.ss.editor.extension.scene.SceneNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface to implement a scene app state.
 *
 * @author JavaSaBr
 */
public interface SceneAppState extends AppState, Savable, Named, Cloneable, JmeCloneable {

    /**
     * Set a scene node which is owner of this app state.
     *
     * @param sceneNode the scene node or null.
     */
    void setSceneNode(@Nullable SceneNode sceneNode);

    /**
     * Notify this scene app state about the added object.
     *
     * @param object the added object.
     */
    void notifyAdded(@NotNull Object object);

    /**
     * Notify this scene app state about the removed object.
     *
     * @param object the removed object.
     */
    void notifyRemoved(@NotNull Object object);
}

