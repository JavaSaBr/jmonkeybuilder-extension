package com.ss.editor.extension.scene.app.state.impl;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.action.ModifyingAction;
import com.ss.editor.extension.integration.EditorEnvironment;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * The base implementation of {@link EditableSceneAppState}.
 *
 * @author JavaSaBr
 */
public abstract class BaseEditableSceneAppState extends BaseAppState implements EditableSceneAppState {

    /**
     * The current scene node.
     */
    @Nullable
    private SceneNode sceneNode;

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {
        return Collections.emptyList();
    }

    @Override
    public @NotNull List<ModifyingAction> getModifyingActions(@NotNull EditorEnvironment env) {
        return Collections.emptyList();
    }

    @Override
    public @Nullable String checkStates(@NotNull List<SceneAppState> exists) {
        return null;
    }

    @Override
    public @Nullable String checkFilters(@NotNull List<SceneFilter> exists) {
        return null;
    }

    @Override
    protected void initialize(@NotNull Application app) {
    }

    @Override
    protected void cleanup(@NotNull Application app) {
    }

    @Override
    public Object jmeClone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cloneFields(@NotNull Cloner cloner, @NotNull Object original) {
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void setSceneNode(@Nullable SceneNode sceneNode) {
        this.sceneNode = sceneNode;
    }

    /**
     * Get the current scene node.
     *
     * @return the current scene node.
     */
    protected @Nullable SceneNode getSceneNode() {
        return sceneNode;
    }

    @Override
    public void notifyAdded(@NotNull Object object) {
    }

    @Override
    public void notifyRemoved(@NotNull Object object) {
    }

    @Override
    public void write(@NotNull JmeExporter exporter) throws IOException {
    }

    @Override
    public void read(@NotNull JmeImporter importer) throws IOException {
    }
}
