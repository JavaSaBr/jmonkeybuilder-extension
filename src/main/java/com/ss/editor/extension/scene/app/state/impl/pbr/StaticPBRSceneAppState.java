package com.ss.editor.extension.scene.app.state.impl.pbr;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import static java.lang.Math.max;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bounding.BoundingVolume;
import com.jme3.environment.EnvironmentCamera;
import com.jme3.environment.LightProbeFactory;
import com.jme3.environment.generation.JobProgressAdapter;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.light.LightProbe;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.ScenePresentable;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of static PBR app state.
 *
 * @author JavaSaBr
 */
public class StaticPBRSceneAppState extends EnvironmentCamera implements EditableSceneAppState, ScenePresentable {

    @NotNull
    private JobProgressAdapter<LightProbe> probeHandler = makeProbeHandler();

    /**
     * The list of hide nodes.
     */
    @NotNull
    private List<Spatial> hideNodes;

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
        this.lightProbe = new InvisibleLightProbe();
        this.hideNodes = new ArrayList<>();
    }

    /**
     * Set the PBR scene.
     *
     * @param scene the PBR scene.
     */
    public void setPbrScene(@Nullable final Node scene) {

        final Node prevScene = getPbrScene();

        if (prevScene != null) {
            prevScene.removeLight(lightProbe);
        }

        this.pbrScene = scene;

        if (scene != null) {
            scene.addLight(lightProbe);
        }

        this.frame = 0;
    }

    @Nullable
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
            frame++;
        } else if (frame < 2) {
            frame++;
        }
    }

    @NotNull
    protected JobProgressAdapter<LightProbe> makeProbeHandler() {
        return new JobProgressAdapter<LightProbe>() {

            @Override
            public void done(@NotNull final LightProbe result) {
                if (!isInitialized()) return;
                notifyProbeComplete();
            }
        };
    }

    @NotNull
    protected List<Spatial> getHideNodes() {
        return hideNodes;
    }

    /**
     * Prepare the PBR scene to make a light probe.
     */
    protected void prepareToMakeProbe() {

        final Node pbrScene = getPbrScene();
        if (pbrScene == null) {
            throw new RuntimeException("The PBR scene shouldn't be not null.");
        }

        final List<Spatial> hideNodes = getHideNodes();
        hideNodes.clear();
        hideNodes.addAll(pbrScene.getChildren());

        pbrScene.detachAllChildren();
    }

    /**
     * Handle finishing making a light probe.
     */
    protected void notifyProbeComplete() {

        final Node pbrScene = getPbrScene();
        if (pbrScene == null) {
            throw new RuntimeException("The PBR scene shouldn't be not null.");
        }

        final List<Spatial> hideNodes = getHideNodes();
        for (final Spatial spatial : hideNodes) {
            pbrScene.attachChild(spatial);
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "PBR static scene";
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
        this.lightProbe = cloner.clone(lightProbe);
        this.pbrScene = cloner.clone(pbrScene);
        this.probeHandler = makeProbeHandler();
    }

    @Override
    public void write(@NotNull final JmeExporter ex) throws IOException {
        final OutputCapsule out = ex.getCapsule(this);
        out.write(lightProbe, "lightProbe", null);
        out.write(pbrScene, "pbrScene", this);
        out.write(frame, "frame", 0);
    }

    @Override
    public void read(@NotNull final JmeImporter im) throws IOException {
        final InputCapsule in = im.getCapsule(this);
        lightProbe = (LightProbe) in.readSavable("lightProbe", null);
        pbrScene = (Node) in.readSavable("pbrScene", null);
        frame = in.readInt("frame", 0);
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

    @NotNull
    @Override
    public Vector3f getLocation() {
        return lightProbe.getPosition();
    }

    @NotNull
    @Override
    public Quaternion getRotation() {
        return Quaternion.IDENTITY;
    }

    @NotNull
    @Override
    public Vector3f getScale() {
        final float radius = getRadius();
        return new Vector3f(radius, radius, radius);
    }

    @Override
    public void setLocation(@NotNull final Vector3f location) {
        lightProbe.setPosition(location);
    }

    @Override
    public void setScale(@NotNull final Vector3f scale) {
        final float radius = max(max(scale.getX(), scale.getY()), scale.getZ());
        setRadius(radius);
    }

    /**
     * Set the radius of the light probe.
     *
     * @param radius the radius.
     */
    public void setRadius(final float radius) {

        final BoundingVolume bounds = lightProbe.getBounds();

        if (bounds instanceof BoundingSphere) {
            ((BoundingSphere) bounds).setRadius(radius);
        }
    }

    /**
     * Get the radius of the light probe.
     *
     * @return the radius.
     */
    public float getRadius() {

        final BoundingVolume bounds = lightProbe.getBounds();

        if (bounds instanceof BoundingSphere) {
            return ((BoundingSphere) bounds).getRadius();
        }

        return 1F;
    }

    @Override
    public void setRotation(@NotNull final Quaternion rotation) {
    }

    @NotNull
    @Override
    public ScenePresentable.PresentationType getPresentationType() {
        return PresentationType.SPHERE;
    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>();
        result.add(new SimpleProperty<>(FLOAT, "Radius", this,
                makeGetter(this, float.class, "getRadius"),
                makeSetter(this, float.class, "setRadius")));
        result.add(new SimpleProperty<>(NODE_FROM_SCENE, "PBR Scene", this,
                makeGetter(this, Node.class, "getPbrScene"),
                makeSetter(this, Node.class, "setPbrScene")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Position", this,
                makeGetter(this, Vector3f.class, "getPosition"),
                makeSetter(this, Vector3f.class, "setPosition")));

        return result;
    }

}
