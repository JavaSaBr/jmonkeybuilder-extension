package com.ss.editor.extension.scene.app.state.impl.pbr;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import static java.lang.Math.max;
import com.jme3.app.Application;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bounding.BoundingVolume;
import com.jme3.environment.EnvironmentCamera;
import com.jme3.environment.LightProbeFactory;
import com.jme3.environment.generation.JobProgressAdapter;
import com.jme3.environment.util.EnvMapUtils.GenerationType;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.light.LightList;
import com.jme3.light.LightProbe;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
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
 * The implementation of {@link SceneAppState} to make static {@link LightProbe}.
 *
 * @author JavaSaBr
 */
public class StaticLightProbeSceneAppState extends EnvironmentCamera implements EditableSceneAppState, ScenePresentable {

    @NotNull
    private JobProgressAdapter<LightProbe> probeHandler = makeProbeHandler();

    @NotNull
    private static final Node EMPTY_SCENE = new Node("Empty scene");

    /**
     * The PBR Light probe.
     */
    @NotNull
    private LightProbe lightProbe;

    /**
     * The scene to put a light probe.
     */
    @Nullable
    private Node pbrScene;

    /**
     * The scene to make a light probe.
     */
    @Nullable
    private Node environmentScene;

    /**
     * The generation type.
     */
    @NotNull
    private GenerationType generationType;

    /**
     * The current frame.
     */
    private int frame;

    /**
     * The next quality size.
     */
    private int nextSize;

    /**
     * The flag of this state is preparing now.
     */
    private boolean preparing;

    public StaticLightProbeSceneAppState() {
        this.lightProbe = new InvisibleLightProbe();
        this.generationType = GenerationType.Fast;
    }

    /**
     * Set the scene to put a light probe.
     *
     * @param scene the scene to put a light probe.
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

    /**
     * Sets the generation type.
     *
     * @return the generation type.
     */
    public @NotNull GenerationType getGenerationType() {
        return generationType;
    }

    /**
     * Gets the generation type.
     *
     * @param generationType the generation type.
     */
    public void setGenerationType(@NotNull final GenerationType generationType) {
        this.generationType = generationType;
    }

    /**
     * Set the scene to make a light probe.
     *
     * @param environmentScene the scene to make a light probe.
     */
    public void setEnvironmentScene(final Node environmentScene) {
        this.environmentScene = environmentScene;
        this.frame = 0;
    }

    @Override
    protected void onEnable() {
        super.onEnable();

        final Node pbrScene = getPbrScene();
        if (pbrScene == null) {
            return;
        }

        final LightList lightList = pbrScene.getLocalLightList();

        for (int i = 0; i < lightList.size(); i++) {
            if (lightList.get(i) == lightProbe) {
                return;
            }
        }

        pbrScene.addLight(lightProbe);
    }

    @Override
    protected void onDisable() {
        super.onDisable();

        final Node pbrScene = getPbrScene();

        if (pbrScene != null) {
            pbrScene.removeLight(lightProbe);
        }
    }

    public @Nullable Node getPbrScene() {
        return pbrScene;
    }

    public @Nullable Node getEnvironmentScene() {
        return environmentScene;
    }

    @Override
    protected void initialize(final Application app) {
        this.nextSize = size;
        super.initialize(app);
    }

    @Override
    public void render(final RenderManager renderManager) {
        try {

            if (pbrScene == null) {
                return;
            }

            if (frame == 2 && !preparing) {
                prepareToMakeProbe();

                Node environmentScene = getEnvironmentScene();

                if (environmentScene == null) {
                    environmentScene = EMPTY_SCENE;
                }

                environmentScene.updateGeometricState();

                LightProbeFactory.updateProbe(lightProbe, this, environmentScene, getGenerationType(), probeHandler);

                frame++;
            } else if (frame < 2) {
                frame++;
            }

        } finally {
            super.render(renderManager);
        }
    }

    protected @NotNull JobProgressAdapter<LightProbe> makeProbeHandler() {
        return new JobProgressAdapter<LightProbe>() {

            @Override
            public void done(@NotNull final LightProbe result) {
                if (!isInitialized()) return;
                notifyProbeComplete();
            }
        };
    }

    /**
     * Prepare the PBR scene to make a light probe.
     */
    protected void prepareToMakeProbe() {
        preparing = true;
    }

    /**
     * Handle finishing making a light probe.
     */
    protected void notifyProbeComplete() {
        preparing = false;

        if (getSize() == nextSize) {
            return;
        }

        getApplication().enqueue(new Runnable() {
            @Override
            public void run() {
                setSize(nextSize);
            }
        });
    }

    @Override
    public @NotNull String getName() {
        return "Static Light probe";
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
        out.write(environmentScene, "environmentScene", this);
        out.write(frame, "frame", 0);
        out.write(generationType, "generationType", GenerationType.Fast);
    }

    @Override
    public void read(@NotNull final JmeImporter im) throws IOException {
        final InputCapsule in = im.getCapsule(this);
        lightProbe = (LightProbe) in.readSavable("lightProbe", null);
        pbrScene = (Node) in.readSavable("pbrScene", null);
        environmentScene = (Node) in.readSavable("environmentScene", null);
        generationType = in.readEnum("generationType", GenerationType.class, GenerationType.Fast);
        frame = in.readInt("frame", 0);
    }

    @Override
    public @Nullable String checkStates(@NotNull final List<SceneAppState> exists) {
        return null;
    }

    @Override
    public @Nullable String checkFilters(@NotNull final List<SceneFilter> exists) {
        return null;
    }

    @Override
    public void setSize(final int size) {
        nextSize = size;

        if (!preparing) {
            super.setSize(size);
        }

        frame = 0;
    }

    @Override
    public @NotNull Vector3f getLocation() {
        return lightProbe.getPosition();
    }

    @Override
    public @NotNull Quaternion getRotation() {
        return Quaternion.IDENTITY;
    }

    @Override
    public @NotNull Vector3f getScale() {
        final float radius = getRadius();
        return new Vector3f(radius, radius, radius);
    }

    @Override
    public void setLocation(@NotNull final Vector3f location) {

        if (!location.equals(lightProbe.getPosition())) {
            frame = 0;
        }

        lightProbe.setPosition(location);
    }

    @Override
    public void setScale(@NotNull final Vector3f scale) {

        final float radius = max(max(scale.getX(), scale.getY()), scale.getZ());

        if (radius != getRadius()) {
            frame = 0;
        }

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

    @Override
    public @NotNull ScenePresentable.PresentationType getPresentationType() {
        return PresentationType.SPHERE;
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>();
        result.add(new SimpleProperty<>(INTEGER, "Quality size", this,
                makeGetter(this, int.class, "getSize"),
                makeSetter(this, int.class, "setSize")));
        result.add(new SimpleProperty<>(FLOAT, "Radius", this,
                makeGetter(this, float.class, "getRadius"),
                makeSetter(this, float.class, "setRadius")));
        result.add(new SimpleProperty<>(NODE_FROM_SCENE, "Env scene", this,
                makeGetter(this, Node.class, "getEnvironmentScene"),
                makeSetter(this, Node.class, "setEnvironmentScene")));
        result.add(new SimpleProperty<>(NODE_FROM_SCENE, "PBR Scene", this,
                makeGetter(this, Node.class, "getPbrScene"),
                makeSetter(this, Node.class, "setPbrScene")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Position", this,
                makeGetter(this, Vector3f.class, "getLocation"),
                makeSetter(this, Vector3f.class, "setLocation")));
        result.add(new SimpleProperty<>(ENUM, "Generation type", this,
                makeGetter(this, GenerationType.class, "getGenerationType"),
                makeSetter(this, GenerationType.class, "setGenerationType")));

        return result;
    }
}
