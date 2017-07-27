package com.ss.editor.extension.loader;

import static com.ss.rlib.util.ObjectUtils.notNull;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.post.FilterPostProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * The implementation of jME Importer to load scenes.
 *
 * @author JavaSaBr
 */
public class SceneLoader implements JmeImporter {

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
        assetManager.unregisterLoader(BinaryImporter.class);
        assetManager.registerLoader(SceneLoader.class, "j3o", "j3f", "j3s");
        SceneLoader.application = application;
        SceneLoader.processor = processor;
    }

    @NotNull
    public static AssetManager tryToGetAssetManager() {
        return notNull(application).getAssetManager();
    }

    @NotNull
    public static AppStateManager tryToGetStateManager() {
        return notNull(application).getStateManager();
    }

    @Nullable
    public static FilterPostProcessor tryToGetPostProcessor() {
        return processor;
    }

    /**
     * The importer.
     */
    @NotNull
    private final BinaryImporter importer;

    public SceneLoader() {
        importer = new BinaryImporter();
    }

    @Override
    public InputCapsule getCapsule(final Savable id) {
        return importer.getCapsule(id);
    }

    @Override
    public AssetManager getAssetManager() {
        return importer.getAssetManager();
    }

    @Override
    public int getFormatVersion() {
        return importer.getFormatVersion();
    }

    @Override
    public Object load(@NotNull final AssetInfo assetInfo) throws IOException {
        return importer.load(assetInfo);
    }
}
