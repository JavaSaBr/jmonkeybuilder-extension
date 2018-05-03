package com.ss.editor.extension.scene;

import static com.ss.editor.extension.loader.SceneLoader.tryToGetPostProcessor;
import static com.ss.editor.extension.loader.SceneLoader.tryToGetStateManager;
import com.jme3.app.state.AppStateManager;
import com.jme3.export.*;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.util.SafeArrayList;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * The implementation of a scene node.
 *
 * @author JavaSaBr
 */
@SuppressWarnings("WeakerAccess")
public class SceneNode extends Node {

    public static final SceneLayer[] EMPTY_LAYERS = new SceneLayer[0];
    public static final SceneAppState[] EMPTY_STATES = new SceneAppState[0];
    public static final SceneFilter[] EMPTY_FILTERS = new SceneFilter[0];

    /**
     * The override the app state manager from {@link com.ss.editor.extension.loader.SceneLoader}.
     */
    @Nullable
    private AppStateManager stateManager;

    /**
     * The override the post filter processor from {@link com.ss.editor.extension.loader.SceneLoader}.
     */
    @Nullable
    private FilterPostProcessor postProcessor;

    /**
     * The scene layers.
     */
    @NotNull
    private SafeArrayList<SceneLayer> layers;

    /**
     * The scene app states.
     */
    @NotNull
    private SafeArrayList<SceneAppState> appStates;

    /**
     * The scene filters.
     */
    @NotNull
    private SafeArrayList<SceneFilter> filters;

    public SceneNode() {
        super("Empty scene");
        this.layers = new SafeArrayList<>(SceneLayer.class);
        this.appStates = new SafeArrayList<>(SceneAppState.class);
        this.filters = new SafeArrayList<>(SceneFilter.class);
    }

    /**
     * Set the override reference to a state manager.
     *
     * @param stateManager the override state manager.
     */
    private void setStateManager(@Nullable AppStateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Set the override reference to a post processor.
     *
     * @param postProcessor the override post processor.
     */
    private void setPostProcessor(@Nullable FilterPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    private @Nullable AppStateManager getStateManager() {
        return stateManager;
    }

    private @Nullable FilterPostProcessor getPostProcessor() {
        return postProcessor;
    }

    @Override
    protected void setParent(@Nullable Node parent) {
        super.setParent(parent);

        AppStateManager stateManager = getStateManager() == null ? tryToGetStateManager() : getStateManager();
        FilterPostProcessor postProcessor = getPostProcessor() == null ? tryToGetPostProcessor() : getPostProcessor();

        SafeArrayList<SceneAppState> appStates = getAppStates();
        SafeArrayList<SceneFilter> filters = getFilters();

        if (!filters.isEmpty() && postProcessor == null) {
            throw new IllegalArgumentException("Not found an instance of FilterPostProcessor in your installed SceneLoader, " +
                    "so this scene can't load scene filters. You need to install SceneLoader using the method SceneLoader.install(application, postFilterProcessor).");
        }

        if (parent == null) {

            for (SceneAppState appState : appStates) {
                stateManager.detach(appState);
            }

            if (postProcessor != null) {
                for (SceneFilter filter : filters) {
                    postProcessor.removeFilter(filter.get());
                }
            }

        } else {

            for (SceneAppState appState : appStates) {
                stateManager.attach(appState);
            }

            if (postProcessor != null) {
                for (SceneFilter filter : filters) {
                    postProcessor.addFilter(filter.get());
                }
            }
        }
    }

    /**
     * Add the new layer.
     *
     * @param layer the layer.
     */
    public void addLayer(@NotNull SceneLayer layer) {
        layers.add(layer);
    }

    /**
     * Remove the layer.
     *
     * @param layer the layer.
     */
    public void removeLayer(@NotNull SceneLayer layer) {
        layers.remove(layer);
    }

    /**
     * Get the scene layers.
     *
     * @return the scene layers.
     */
    public @NotNull SafeArrayList<SceneLayer> getLayers() {
        return layers;
    }

    /**
     * Add the new scene app state.
     *
     * @param appState the scene app state.
     */
    public void addAppState(@NotNull SceneAppState appState) {
        appStates.add(appState);
        appState.setSceneNode(this);
    }

    /**
     * Remove the scene app state.
     *
     * @param appState the scene app state.
     */
    public void removeAppState(@NotNull SceneAppState appState) {
        appStates.remove(appState);
        appState.setSceneNode(null);
    }

    /**
     * Get the scene app states.
     *
     * @return the scene app states.
     */
    public @NotNull SafeArrayList<SceneAppState> getAppStates() {
        return appStates;
    }

    /**
     * Add the new scene filter.
     *
     * @param filter the scene filter.
     */
    public void addFilter(@NotNull SceneFilter filter) {
        filters.add(filter);
    }

    /**
     * Remove the scene filter.
     *
     * @param filter the scene filter.
     */
    public void removeFilter(@NotNull SceneFilter filter) {
        filters.remove(filter);
    }

    /**
     * Get a list of filters.
     *
     * @return the list of filters.
     */
    public @NotNull SafeArrayList<SceneFilter> getFilters() {
        return filters;
    }

    @Override
    public void write(@NotNull JmeExporter exporter) throws IOException {

        OutputCapsule capsule = exporter.getCapsule(this);
        SceneLayer[] layers = getLayers().getArray();
        SceneAppState[] appStates = getAppStates().getArray();
        SceneFilter[] filters = getFilters().getArray();

        capsule.write(layers, "layers", EMPTY_LAYERS);
        capsule.write(appStates, "appStates", EMPTY_STATES);
        capsule.write(filters, "filters", EMPTY_FILTERS);

        super.write(exporter);
    }

    @Override
    public void read(@NotNull JmeImporter importer) throws IOException {

        final InputCapsule capsule = importer.getCapsule(this);

        Savable[] importedLayers = capsule.readSavableArray("layers", EMPTY_LAYERS);
        Savable[] importedAppStates = capsule.readSavableArray("appStates", EMPTY_STATES);
        Savable[] importedFilters = capsule.readSavableArray("filters", EMPTY_FILTERS);

        for (Savable savable : importedLayers) {
            layers.add((SceneLayer) savable);
        }

        for (Savable savable : importedAppStates) {
            appStates.add((SceneAppState) savable);
        }

        for (Savable savable : importedFilters) {
            filters.add((SceneFilter) savable);
        }

        super.read(importer);
    }

    @Override
    public void cloneFields(Cloner cloner, Object original) {
        super.cloneFields(cloner, original);

        layers = cloner.clone(layers);
        appStates = cloner.clone(appStates);
        filters = cloner.clone(filters);

        for (SceneAppState appState : appStates.getArray()) {
            appState.setSceneNode(this);
        }
    }

    /**
     * Notify this scene node about the added object.
     *
     * @param object the added object.
     */
    public void notifyAdded(@NotNull Object object) {
        for (final SceneAppState appState : getAppStates().getArray()) {
            appState.notifyAdded(object);
        }
    }

    /**
     * Notify this scene node about the removed object.
     *
     * @param object the removed object.
     */
    public void notifyRemoved(@NotNull Object object) {
        for (final SceneAppState appState : getAppStates().getArray()) {
            appState.notifyRemoved(object);
        }
    }
}
