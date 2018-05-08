package com.ss.editor.extension.scene.app.state.impl.bullet;

import static com.ss.editor.extension.property.EditablePropertyType.*;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState.ThreadingType;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.PhysicsSpace.BroadphaseType;
import com.jme3.bullet.PhysicsTickListener;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.SceneNode;
import com.ss.editor.extension.scene.app.state.EditableSceneAppState;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.app.state.impl.bullet.debug.BulletDebugAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * The implementation of an editable bullet state.
 *
 * @author JavaSaBr
 */
public class EditableBulletSceneAppState extends AbstractAppState implements EditableSceneAppState,
        PhysicsTickListener {

    /**
     * The Physics update task.
     */
    protected final Callable<Boolean> physicsUpdateTask = new Callable<Boolean>() {

        @Override
        public Boolean call() throws Exception {
            PhysicsSpace physicsSpace = getPhysicsSpace();
            if (physicsSpace == null) return false;
            physicsSpace.update(getTpf() * getSpeed());
            return true;
        }
    };

    /***
     * The physics space.
     */
    @Nullable
    protected volatile PhysicsSpace physicsSpace;

    /**
     * The time per frame.
     */
    protected volatile float tpf;

    /**
     * The executor.
     */
    @Nullable
    protected ScheduledExecutorService executor;

    /**
     * The state manager.
     */
    @Nullable
    protected AppStateManager stateManager;

    /**
     * The application.
     */
    @Nullable
    protected Application application;

    /**
     * The debug state.
     */
    @Nullable
    protected BulletDebugAppState debugAppState;

    /**
     * The scene node.
     */
    @Nullable
    protected SceneNode sceneNode;

    /**
     * The threading type.
     */
    @NotNull
    protected ThreadingType threadingType;

    /**
     * The prev threading type.
     */
    @Nullable
    protected ThreadingType prevThreadingType;

    /**
     * The broadphase type.
     */
    @NotNull
    protected BroadphaseType broadphaseType;

    /**
     * The world min.
     */
    @NotNull
    protected Vector3f worldMin;

    /**
     * The world max.
     */
    @NotNull
    protected Vector3f worldMax;

    /**
     * The gravity.
     */
    @NotNull
    protected Vector3f gravity;

    /**
     * The reference to background physics updating.
     */
    protected Future<?> physicsFuture;

    /**
     * The speed.
     */
    protected float speed;

    /**
     * The flag to enable debug.
     */
    protected boolean debugEnabled;

    public EditableBulletSceneAppState() {
        this.threadingType = ThreadingType.SEQUENTIAL;
        this.broadphaseType = BroadphaseType.AXIS_SWEEP_3;
        this.worldMin = new Vector3f(-10000f, -10000f, -10000f);
        this.worldMax = new Vector3f(10000f, 10000f, 10000f);
        this.gravity = new Vector3f(0,-9.81f,0);
        this.debugEnabled = false;
    }

    @Override
    public void setSceneNode(@Nullable SceneNode sceneNode) {
        this.sceneNode = sceneNode;
    }

    /**
     * Get the scene node.
     *
     * @return the scene node.
     */
    protected @Nullable SceneNode getSceneNode() {
        return sceneNode;
    }

    /**
     * Set the debug enabled.
     *
     * @param debugEnabled the flag to enable debug.
     */
    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        rebuildState();
    }

    /**
     * Return true if debug is enabled.
     *
     * @return true if debug is enabled.
     */
    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /**
     * Get the physics space.
     *
     * @return the physics space.
     */
    public @Nullable PhysicsSpace getPhysicsSpace() {
        return physicsSpace;
    }

    /**
     * Get the speed.
     *
     * @return the speed.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set the speed.
     *
     * @param speed the speed.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Get the tpf.
     *
     * @return the time per frame.
     */
    public float getTpf() {
        return tpf;
    }

    @Override
    public @NotNull String getName() {
        return "Bullet state";
    }

    /**
     * Set the threading type.
     *
     * @param threadingType the threading type
     */
    public void setThreadingType(@NotNull ThreadingType threadingType) {
        this.prevThreadingType = getPhysicsSpace() != null ? getThreadingType() : null;
        this.threadingType = threadingType;
        rebuildState();
    }

    /**
     * Get the threading type.
     *
     * @return the threading type
     */
    public @NotNull ThreadingType getThreadingType() {
        return threadingType;
    }

    /**
     * Set the broadphase type.
     *
     * @param broadphaseType the broadphase type
     */
    public void setBroadphaseType(@NotNull BroadphaseType broadphaseType) {
        this.broadphaseType = broadphaseType;
        rebuildState();
    }

    /**
     * Get the broadphase type.
     *
     * @return the broadphase type
     */
    public @NotNull BroadphaseType getBroadphaseType() {
        return broadphaseType;
    }

    /**
     * Get the world max.
     *
     * @return the world max
     */
    public @NotNull Vector3f getWorldMax() {
        return worldMax;
    }

    /**
     * Set the world max.
     *
     * @param worldMax the world max
     */
    public void setWorldMax(@NotNull Vector3f worldMax) {
        this.worldMax.set(worldMax);
        rebuildState();
    }

    /**
     * Get the world min.
     *
     * @return the world min
     */
    public @NotNull Vector3f getWorldMin() {
        return worldMin;
    }

    /**
     * Set the world min.
     *
     * @param worldMin the world min
     */
    public void setWorldMin(@NotNull Vector3f worldMin) {
        this.worldMin.set(worldMin);
        rebuildState();
    }

    /**
     * Get the gravity.
     *
     * @return the gravity.
     */
    public @NotNull Vector3f getGravity() {
        return gravity;
    }

    /**
     * Set the gravity.
     *
     * @param gravity the gravity.
     */
    public void setGravity(@NotNull Vector3f gravity) {
        this.gravity.set(gravity);

        PhysicsSpace physicsSpace = getPhysicsSpace();

        if (physicsSpace != null) {
            physicsSpace.setGravity(gravity);
        }
    }

    @Override
    public void initialize(@NotNull AppStateManager stateManager, @NotNull Application app) {
        super.initialize(stateManager, app);
        this.stateManager = stateManager;
        this.application = app;

        startPhysics();

        if (isDebugEnabled()) {
            debugAppState = new BulletDebugAppState(physicsSpace);
            stateManager.attach(debugAppState);
        }

        SceneNode sceneNode = getSceneNode();
        if (sceneNode != null) {
            updateNode(sceneNode, physicsSpace);
        }
    }

    /**
     * Update the spatial.
     *
     * @param spatial      the spatial.
     * @param physicsSpace the new physical space or null.
     */
    private void updateNode(@NotNull Spatial spatial, @Nullable final PhysicsSpace physicsSpace) {
        spatial.depthFirstTraversal(new SceneGraphVisitor() {

            @Override
            public void visit(final Spatial spatial) {

                int numControls = spatial.getNumControls();

                for (int i = 0; i < numControls; i++) {
                    Control control = spatial.getControl(i);
                    if (control instanceof PhysicsControl) {
                        ((PhysicsControl) control).setPhysicsSpace(physicsSpace);
                    }
                }
            }
        });
    }

    @Override
    public void cleanup() {
        super.cleanup();

        if (debugAppState != null) {
            stateManager.detach(debugAppState);
            debugAppState = null;
        }

        SceneNode sceneNode = getSceneNode();
        if (sceneNode != null) {
            updateNode(sceneNode, null);
        }

        stopPhysics();

        this.stateManager = null;
        this.application = null;
    }

    /**
     * Start physics.
     */
    public void startPhysics() {

        if (physicsSpace != null) {
            return;
        }

        if (threadingType == ThreadingType.PARALLEL) {
            startBackgroundPhysics();
        } else {
            physicsSpace = new PhysicsSpace(worldMin, worldMax, broadphaseType);
            physicsSpace.addTickListener(this);
            physicsSpace.setGravity(getGravity());
        }

        if (threadingType == ThreadingType.PARALLEL) {
            PhysicsSpace.setLocalThreadPhysicsSpace(physicsSpace);
        }
    }

    /**
     * Start background physics.
     *
     * @return true of the physics was started.
     */
    protected boolean startBackgroundPhysics() {

        if (executor == null) {
            executor = Executors.newSingleThreadScheduledExecutor();
        }

        try {

            return executor.submit(new Callable<Boolean>() {

                @Override
                public Boolean call() throws Exception {
                    physicsSpace = new PhysicsSpace(worldMin, worldMax, broadphaseType);
                    physicsSpace.addTickListener(EditableBulletSceneAppState.this);
                    physicsSpace.setGravity(getGravity());
                    return true;
                }

            }).get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Rebuild this state.
     */
    protected void rebuildState() {

        if (!isInitialized()) {
            return;
        }

        SceneNode sceneNode = getSceneNode();

        if (debugAppState != null) {
            stateManager.detach(debugAppState);
            debugAppState = null;
        }

        if (sceneNode != null) {
            updateNode(sceneNode, null);
        }

        stopPhysics();
        startPhysics();

        if (isDebugEnabled()) {
            debugAppState = new BulletDebugAppState(physicsSpace);
            stateManager.attach(debugAppState);
        }

        if (sceneNode != null) {
            updateNode(sceneNode, physicsSpace);
        }
    }

    /**
     * Stop physics.
     */
    public void stopPhysics() {

        if (physicsSpace == null) {
            return;
        }

        if (executor != null) {
            executor.shutdown();
            executor = null;
        }

        ThreadingType threadingType = prevThreadingType != null ? prevThreadingType : getThreadingType();

        if (threadingType == ThreadingType.PARALLEL) {
            PhysicsSpace.setLocalThreadPhysicsSpace(null);
            prevThreadingType = null;
        }

        physicsSpace.removeTickListener(this);
        physicsSpace.destroy();
        physicsSpace = null;
    }

    @Override
    public void render(@NotNull RenderManager renderManager) {

        if (!isEnabled()) {
            return;
        }

        switch (threadingType) {
            case PARALLEL: {
                physicsFuture = executor.submit(physicsUpdateTask);
                break;
            }
            case SEQUENTIAL: {
                physicsSpace.update(tpf * speed);
                break;
            }
        }
    }

    @Override
    public void postRender() {

        if (physicsFuture == null) {
            return;
        }

        try {
            physicsFuture.get();
            physicsFuture = null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prePhysicsTick(@NotNull PhysicsSpace space, float tpf) {

    }

    @Override
    public void physicsTick(@NotNull PhysicsSpace space, float tpf) {

    }

    @Override
    public void update(float tpf) {
        super.update(tpf);

        PhysicsSpace physicsSpace = getPhysicsSpace();
        if (physicsSpace != null) {
            physicsSpace.distributeEvents();
        }

        this.tpf = tpf;
    }

    @Override
    public void notifyAdded(@NotNull Object object) {
        if (object instanceof PhysicsControl) {
            ((PhysicsControl) object).setPhysicsSpace(getPhysicsSpace());
        } else if (object instanceof Spatial) {
            updateNode((Spatial) object, getPhysicsSpace());
        }
    }

    @Override
    public void notifyRemoved(@NotNull Object object) {
        if (object instanceof PhysicsControl) {
            ((PhysicsControl) object).setPhysicsSpace(null);
        } else if (object instanceof Spatial) {
            updateNode((Spatial) object, null);
        }
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        List<EditableProperty<?, ?>> result = new ArrayList<>(7);

        result.add(new SimpleProperty<>(BOOLEAN, "Debug enabled", this,
                makeGetter(this, boolean.class, "isDebugEnabled"),
                makeSetter(this, boolean.class, "setDebugEnabled")));
        result.add(new SimpleProperty<>(FLOAT, "Speed", this,
                makeGetter(this, float.class, "getSpeed"),
                makeSetter(this, float.class, "setSpeed")));
        result.add(new SimpleProperty<>(ENUM, "Broadphase type", this,
                makeGetter(this, BroadphaseType.class, "getBroadphaseType"),
                makeSetter(this, BroadphaseType.class, "setBroadphaseType")));
        result.add(new SimpleProperty<>(ENUM, "Threading type", this,
                makeGetter(this, ThreadingType.class, "getThreadingType"),
                makeSetter(this, ThreadingType.class, "setThreadingType")));
        result.add(new SimpleProperty<>(VECTOR_3F, "World max", this,
                makeGetter(this, Vector3f.class, "getWorldMax"),
                makeSetter(this, Vector3f.class, "setWorldMax")));
        result.add(new SimpleProperty<>(VECTOR_3F, "World min", this,
                makeGetter(this, Vector3f.class, "getWorldMin"),
                makeSetter(this, Vector3f.class, "setWorldMin")));
        result.add(new SimpleProperty<>(VECTOR_3F, "Gravity", this,
                makeGetter(this, Vector3f.class, "getGravity"),
                makeSetter(this, Vector3f.class, "setGravity")));

        return result;
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
    public Object jmeClone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cloneFields(@NotNull Cloner cloner, @NotNull Object original) {
        sceneNode = cloner.clone(sceneNode);
        worldMin = cloner.clone(worldMin);
        worldMax = cloner.clone(worldMax);
    }

    @Override
    public void write(@NotNull JmeExporter exporter) throws IOException {
        OutputCapsule capsule = exporter.getCapsule(this);
        capsule.write(sceneNode, "sceneNode", null);
        capsule.write(threadingType, "threadingType", ThreadingType.SEQUENTIAL);
        capsule.write(broadphaseType, "broadphaseType", BroadphaseType.DBVT);
        capsule.write(worldMin, "worldMin", null);
        capsule.write(worldMax, "worldMax", null);
        capsule.write(gravity, "gravity", null);
        capsule.write(speed, "speed", 0);
        capsule.write(debugEnabled, "debugEnabled", false);
    }

    @Override
    public void read(@NotNull JmeImporter importer) throws IOException {
        InputCapsule capsule = importer.getCapsule(this);
        sceneNode = (SceneNode) capsule.readSavable("sceneNode", null);
        threadingType = capsule.readEnum("threadingType", ThreadingType.class, ThreadingType.SEQUENTIAL);
        broadphaseType = capsule.readEnum("broadphaseType", BroadphaseType.class, BroadphaseType.DBVT);
        worldMin = (Vector3f) capsule.readSavable("worldMin", null);
        worldMax = (Vector3f) capsule.readSavable("worldMax", null);
        gravity = (Vector3f) capsule.readSavable("worldMax", new Vector3f(0, -9.81f, 0));
        speed = capsule.readFloat("speed", 0);
        debugEnabled = capsule.readBoolean("debugEnabled", false);
    }
}
