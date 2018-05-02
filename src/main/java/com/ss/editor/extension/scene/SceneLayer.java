package com.ss.editor.extension.scene;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * The implementation of a scene layer.
 *
 * @author JavaSaBr
 */
@SuppressWarnings("WeakerAccess")
public class SceneLayer extends Node {

    public static final String KEY = SceneLayer.class.getName();
    public static final SceneLayer NO_LAYER = new SceneLayer();

    /**
     * Get a layer of a spatial.
     *
     * @param spatial the spatial.
     * @return the layer or null.
     */
    public static @Nullable SceneLayer getLayer(@NotNull Spatial spatial) {
        return spatial.getUserData(KEY);
    }

    /**
     * Set a layer to a spatial.
     *
     * @param layer   the layer.
     * @param spatial the spatial.
     */
    public static void setLayer(@Nullable SceneLayer layer, @NotNull Spatial spatial) {
        spatial.setUserData(KEY, layer == NO_LAYER ? null : layer);
    }

    /**
     * True if this layer is built in.
     */
    private boolean builtIn;

    /**
     * True if this layer is visible.
     */
    private boolean showed;

    public SceneLayer() {
        super("Empty layer");
    }

    public SceneLayer(@NotNull String name, boolean builtIn) {
        super(name);
        this.builtIn = builtIn;
    }

    /**
     * Return true if this layer is built in.
     *
     * @return true if this layer is built in.
     */
    public boolean isBuiltIn() {
        return builtIn;
    }

    /**
     * Set true if this layer is built in.
     *
     * @param builtIn true if this layer is built in.
     */
    public void setBuiltIn(boolean builtIn) {
        this.builtIn = builtIn;
    }

    /**
     * Set true if this layer is visible.
     *
     * @param showed true if this layer is visible.
     */
    protected void setShowed(boolean showed) {
        this.showed = showed;
    }

    /**
     * Return true if this layer is visible.
     *
     * @return true if this layer is showed.
     */
    public boolean isShowed() {
        return showed;
    }

    /**
     * Hide this layer.
     */
    public void hide() {
        if (!isShowed()) return;
        setShowed(false);
    }

    /**
     * Show this layer.
     */
    public void show() {
        if (isShowed()) return;
        setShowed(true);
    }

    @Override
    public void write(@NotNull JmeExporter exporter) throws IOException {
        super.write(exporter);

        OutputCapsule capsule = exporter.getCapsule(this);
        capsule.write(isShowed(), "showed", false);
        capsule.write(isBuiltIn(), "builtIn", false);
    }

    @Override
    public void read(@NotNull final JmeImporter importer) throws IOException {
        super.read(importer);

        InputCapsule capsule = importer.getCapsule(this);
        setShowed(capsule.readBoolean("showed", false));
        setBuiltIn(capsule.readBoolean("builtIn", false));
    }
}
