package com.ss.editor.extension.scene.app.state.impl.pbr;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import static java.lang.Math.max;
import com.jme3.app.Application;
import com.jme3.environment.EnvironmentCamera;
import com.jme3.environment.util.EnvMapUtils.GenerationType;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.light.LightProbe;
import com.jme3.light.LightProbe.AreaType;
import com.jme3.light.OrientedBoxProbeArea;
import com.jme3.light.ProbeArea;
import com.jme3.light.SphereProbeArea;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.EditableName;
import com.ss.editor.extension.action.ModifyingAction;
import com.ss.editor.extension.integration.EditorEnvironment;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.ScenePresentable;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import com.ss.editor.extension.util.JmbExtUtils;
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
public class StaticLightProbeSceneAppState extends EnvironmentCamera implements
        EditableSceneAppState, ScenePresentable, EditableName {

    protected static final Node EMPTY_SCENE = new Node("Empty scene");

    private static final String DEFAULT_NAME = "Static light probe";

    /**
     * The PBR light probe.
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
     * The name.
     */
    @NotNull
    private String name;

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
        this.name = DEFAULT_NAME;
    }

    /**
     * Set the scene to put a light probe.
     *
     * @param scene the scene to put a light probe.
     */
    public void setPbrScene(@Nullable Node scene) {

        Node prevScene = getPbrScene();

        if (prevScene != null) {
            prevScene.removeLight(lightProbe);
        }

        this.pbrScene = scene;

        if (scene != null && !JmbExtUtils.contains(scene, lightProbe)) {
            scene.addLight(lightProbe);
        }
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
    public void setGenerationType(@NotNull GenerationType generationType) {
        this.generationType = generationType;
    }

    /**
     * Sets the area type.
     *
     * @return the area type.
     */
    public @NotNull AreaType getAreaType() {
        return lightProbe.getAreaType();
    }

    /**
     * Gets the area type.
     *
     * @param areaType the area type.
     */
    public void setAreaType(@NotNull AreaType areaType) {
        Vector3f scale = getScale();
        this.lightProbe.setAreaType(areaType);
        setScale(scale);
    }

    /**
     * Set the scene to make a light probe.
     *
     * @param environmentScene the scene to make a light probe.
     */
    public void setEnvironmentScene(@Nullable Node environmentScene) {
        this.environmentScene = environmentScene;
    }

    @Override
    protected void onEnable() {
        super.onEnable();

        Node pbrScene = getPbrScene();
        if (pbrScene == null || JmbExtUtils.contains(pbrScene, lightProbe)) {
            return;
        }

        pbrScene.addLight(lightProbe);
    }

    @Override
    protected void onDisable() {
        super.onDisable();

        Node pbrScene = getPbrScene();

        if (pbrScene != null) {
            pbrScene.removeLight(lightProbe);
        }
    }

    /**
     * Get the current PBR scene.
     *
     * @return the current PBR scene.
     */
    public @Nullable Node getPbrScene() {
        return pbrScene;
    }

    /**
     * Get the current env scene.
     *
     * @return the current env scene.
     */
    public @Nullable Node getEnvironmentScene() {
        return environmentScene;
    }

    @Override
    protected void initialize(@NotNull Application application) {
        this.nextSize = size;
        super.initialize(application);
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
    public void setName(@NotNull String name) {
        this.name = name;
    }


    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void setSceneNode(@Nullable SceneNode sceneNode) {
    }

    @Override
    public void notifyAdded(@NotNull Object object) {
    }

    @Override
    public void notifyRemoved(@NotNull Object object) {
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
        this.lightProbe = cloner.clone(lightProbe);
        this.pbrScene = cloner.clone(pbrScene);
        this.environmentScene = cloner.clone(environmentScene);
    }

    @Override
    public void write(@NotNull JmeExporter ex) throws IOException {
        OutputCapsule out = ex.getCapsule(this);
        out.write(name, "name", null);
        out.write(lightProbe, "lightProbe", null);
        out.write(pbrScene, "pbrScene", null);
        out.write(environmentScene, "environmentScene", null);
        out.write(generationType, "generationType", GenerationType.Fast);
    }

    @Override
    public void read(@NotNull JmeImporter im) throws IOException {
        InputCapsule in = im.getCapsule(this);
        name = in.readString("name", DEFAULT_NAME);
        lightProbe = (LightProbe) in.readSavable("lightProbe", null);
        pbrScene = (Node) in.readSavable("pbrScene", null);
        environmentScene = (Node) in.readSavable("environmentScene", null);
        generationType = in.readEnum("generationType", GenerationType.class, GenerationType.Fast);
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
    public void setSize(int size) {
        nextSize = size;

        if (!preparing) {
            super.setSize(size);
        }
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

        ProbeArea area = lightProbe.getArea();

        if (area instanceof SphereProbeArea) {
            float radius = area.getRadius();
            return new Vector3f(radius, radius, radius);
        } else if (area instanceof OrientedBoxProbeArea) {
            Vector3f extent = ((OrientedBoxProbeArea) area).getExtent();
            return extent.clone();
        }

        return new Vector3f();
    }

    @Override
    public void setLocation(@NotNull Vector3f location) {
        lightProbe.setPosition(location);
    }

    @Override
    public void setScale(@NotNull Vector3f scale) {

        ProbeArea area = lightProbe.getArea();

        if (area instanceof SphereProbeArea) {
            area.setRadius(max(max(scale.getX(), scale.getY()), scale.getZ()));
        } else if (area instanceof OrientedBoxProbeArea) {
            ((OrientedBoxProbeArea) area).setExtent(scale.clone());
        }
    }

    @Override
    public void setRotation(@NotNull Quaternion rotation) {
    }

    @Override
    public @NotNull ScenePresentable.PresentationType getPresentationType() {

        switch (getAreaType()) {
            case OrientedBox: return PresentationType.BOX;
            case Spherical: return PresentationType.SPHERE;
        }

        return PresentationType.SPHERE;
    }

    protected @NotNull LightProbe getLightProbe() {
        return lightProbe;
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        List<EditableProperty<?, ?>> result = new ArrayList<>(6);
        result.add(new SimpleProperty<>(INTEGER, "Quality size", this,
                makeGetter(this, int.class, "getSize"),
                makeSetter(this, int.class, "setSize")));
        result.add(new SimpleProperty<>(ENUM, "Generation type", this,
                makeGetter(this, GenerationType.class, "getGenerationType"),
                makeSetter(this, GenerationType.class, "setGenerationType")));
        result.add(new SimpleProperty<>(ENUM, "Area type", this,
                makeGetter(this, AreaType.class, "getAreaType"),
                makeSetter(this, AreaType.class, "setAreaType")));
        result.add(new SimpleProperty<>(NODE_FROM_SCENE, "Env scene", this,
                makeGetter(this, Node.class, "getEnvironmentScene"),
                makeSetter(this, Node.class, "setEnvironmentScene")));
        result.add(new SimpleProperty<>(NODE_FROM_SCENE, "PBR Scene", this,
                makeGetter(this, Node.class, "getPbrScene"),
                makeSetter(this, Node.class, "setPbrScene")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Scale", this,
                makeGetter(this, Vector3f.class, "getScale"),
                makeSetter(this, Vector3f.class, "setScale")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Position", this,
                makeGetter(this, Vector3f.class, "getLocation"),
                makeSetter(this, Vector3f.class, "setLocation")));

        return result;
    }

    @Override
    public @NotNull List<ModifyingAction> getModifyingActions(@NotNull EditorEnvironment env) {

        List<ModifyingAction> result = new ArrayList<>(1);

        if (!preparing) {
            result.add(UpdateLightProbeAction.getInstance());
        }

        return result;
    }
}
