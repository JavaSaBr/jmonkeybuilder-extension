package com.ss.editor.extension.scene.app.state.impl;

import com.jme3.environment.EnvironmentCamera;
import com.jme3.environment.LightProbeFactory;
import com.jme3.environment.generation.JobProgressAdapter;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.light.LightProbe;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SafeArrayList;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * The implementation of static PBR app state.
 *
 * @author JavaSaBr
 */
public class StaticPBRSceneAppState extends EnvironmentCamera implements EditableSceneAppState {

    @NotNull
    private final JobProgressAdapter<LightProbe> probeHandler = new JobProgressAdapter<LightProbe>() {

        @Override
        public void done(@NotNull final LightProbe result) {
            if (!isInitialized()) return;
            notifyProbeComplete();
        }
    };

    /**
     * The list of hide nodes.
     */
    @NotNull
    private SafeArrayList<Spatial> hideNodes;

    /**
     * The PBR Light probe.
     */
    @NotNull
    private LightProbe lightProbe;

    /**
     * The PBR scene
     */
    @Nullable
    private Node pbrScene;

    /**
     * The current frame.
     */
    private int frame;

    public StaticPBRSceneAppState() {
        this.lightProbe = new LightProbe();
        this.hideNodes = new SafeArrayList<>(Spatial.class);
    }

    public void setPbrScene(@NotNull final Node pbrScene) {
        this.pbrScene = pbrScene;
        this.frame = 0;
    }

    public Node getPbrScene() {
        return pbrScene;
    }

    @Override
    public void update(final float tpf) {
        super.update(tpf);

        if (pbrScene == null) {
            return;
        }

        if (frame == 2) {
            prepareToMakeProbe();
            LightProbeFactory.updateProbe(lightProbe, this, pbrScene, probeHandler);
        } else if (frame < 2) {
            frame++;
        }
    }

    @NotNull
    protected SafeArrayList<Spatial> getHideNodes() {
        return hideNodes;
    }

    private void prepareToMakeProbe() {

        final Node pbrScene = getPbrScene();
        if (pbrScene == null) {
            throw new RuntimeException("The PBR scene shouldn't be not null.");
        }

        final SafeArrayList<Spatial> hideNodes = getHideNodes();
        hideNodes.clear();


    }

    private void notifyProbeComplete() {
    }

    @NotNull
    @Override
    public String getName() {
        return "PBR Scene App State";
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

    @Override
    public Object jmeClone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {

    }

    @Override
    public void write(@NotNull final JmeExporter ex) throws IOException {

    }

    @Override
    public void read(@NotNull final JmeImporter im) throws IOException {

    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {
        return null;
    }

    @Nullable
    @Override
    public String checkStates(@NotNull final List<SceneAppState> exists) {
        return null;
    }

    @Nullable
    @Override
    public String checkFilters(@NotNull final List<SceneFilter> exists) {
        return null;
    }
}
