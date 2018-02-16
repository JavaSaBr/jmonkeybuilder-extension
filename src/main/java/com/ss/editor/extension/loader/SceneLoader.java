package com.ss.editor.extension.loader;

import static java.util.Objects.requireNonNull;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The implementation of asset loader to load scenes.
 *
 * @author JavaSaBr
 */
public class SceneLoader implements AssetLoader {

    /**
     * The application.
     */
    @Nullable
    private static Application application;

    /**
     * The filter post processor.
     */
    @Nullable
    private static FilterPostProcessor processor;

    /**
     * Install a scene loader to the asset manager.
     *
     * @param application the application.
     */
    public static void install(@NotNull final Application application) {
        install(application, null);
    }

    /**
     * Install a scene loader to the asset manager.
     *
     * @param application the application.
     * @param processor   the processor.
     */
    public static void install(@NotNull final Application application, @Nullable final FilterPostProcessor processor) {
        final AssetManager assetManager = application.getAssetManager();
        assetManager.registerLoader(SceneLoader.class, "j3o", "j3f", "j3s");
        SceneLoader.application = application;
        SceneLoader.processor = processor;
    }

    /**
     * Try to get an asset manager.
     *
     * @return the asset manager.
     * @throws NullPointerException if we don't have an asset manager.
     */
    public static @NotNull AssetManager tryToGetAssetManager() {
        return requireNonNull(application).getAssetManager();
    }

    /**
     * Try to get a state manager.
     *
     * @return the state manager.
     * @throws NullPointerException if we don't have a state manager.
     */
    public static @NotNull AppStateManager tryToGetStateManager() {
        return requireNonNull(application).getStateManager();
    }

    /**
     * Try to get a filter post processor.
     *
     * @return the filter post processor.
     * @throws NullPointerException if we don't have a filter post processor.
     */
    public static @Nullable FilterPostProcessor tryToGetPostProcessor() {
        return processor;
    }

    /**
     * The queue importers.
     */
    @NotNull
    private final Deque<BinaryImporter> importers;

    public SceneLoader() {
        importers = new ArrayDeque<>();
    }

    @Override
    public Object load(@NotNull final AssetInfo assetInfo) throws IOException {

        BinaryImporter importer = importers.pollLast();

        if (importer == null) {
            importer = new BinaryImporter();
        }

        try {

            Object load = importer.load(assetInfo);

            if (load instanceof Spatial) {
                resetPhysics((Spatial) load);
            }

            return load;

        } finally {
            importers.addLast(importer);
        }
    }

    /**
     * Reset physics controls on loaded spatial.
     *
     * @param spatial the loaded spatial.
     */
    private void resetPhysics(@NotNull final Spatial spatial) {

        final int numControls = spatial.getNumControls();
        for (int i = 0; i < numControls; i++) {
            final Control control = spatial.getControl(i);
            if (control instanceof RigidBodyControl) {
                final RigidBodyControl bodyControl = (RigidBodyControl) control;
                final boolean kinematic = bodyControl.isKinematic();
                final boolean kinematicSpatial = bodyControl.isKinematicSpatial();
                bodyControl.setKinematic(true);
                bodyControl.setKinematicSpatial(true);
                bodyControl.clearForces();
                bodyControl.update(0);
                bodyControl.setKinematic(kinematic);
                bodyControl.setKinematicSpatial(kinematicSpatial);
            }
        }

        if (spatial instanceof Node) {
            for (final Spatial child : ((Node) spatial).getChildren()) {
                resetPhysics(child);
            }
        }
    }
}
