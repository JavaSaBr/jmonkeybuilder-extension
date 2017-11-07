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

    @NotNull
    public static final SceneLayer[] EMPTY_LAYERS = new SceneLayer[0];

    @NotNull
    public static final SceneAppState[] EMPTY_STATES = new SceneAppState[0];

    @NotNull
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
    private void setStateManager(@Nullable final AppStateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Set the override reference to a post processor.
     *
     * @param postProcessor the override post processor.
     */
    private void setPostProcessor(@Nullable final FilterPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    private @Nullable AppStateManager getStateManager() {
        return stateManager;
    }

    private @Nullable FilterPostProcessor getPostProcessor() {
        return postProcessor;
    }

    @Override
    protected void setParent(@Nullable final Node parent) {
        super.setParent(parent);

        final AppStateManager stateManager = getStateManager() == null ? tryToGetStateManager() : getStateManager();
        final FilterPostProcessor postProcessor = getPostProcessor() == null ? tryToGetPostProcessor() : getPostProcessor();

        final SafeArrayList<SceneAppState> appStates = getAppStates();
        final SafeArrayList<SceneFilter> filters = getFilters();

        if (!filters.isEmpty() && postProcessor == null) {
            throw new IllegalArgumentException("Not found an instance of FilterPostProcessor in your installed SceneLoader, " +
                    "so this scene can't load scene filters. You need to install SceneLoader using the method SceneLoader.install(application, postFilterProcessor).");
        }

        if (parent == null) {

            for (final SceneAppState appState : appStates) {
                stateManager.detach(appState);
            }

            if (postProcessor != null) {
                for (final SceneFilter filter : filters) {
                    postProcessor.removeFilter(filter.get());
                }
            }

        } else {

            for (final SceneAppState appState : appStates) {
                stateManager.attach(appState);
            }

            if (postProcessor != null) {
                for (final SceneFilter filter : filters) {
                    postProcessor.addFilter(filter.get());
                }
            }
        }
    }

    /**
     * Add a new layer.
     *
     * @param layer the layer.
     */
    public void addLayer(@NotNull final SceneLayer layer) {
        layers.add(layer);
    }

    /**
     * Remove an old layer.
     *
     * @param layer the layer.
     */
    public void removeLayer(@NotNull final SceneLayer layer) {
        layers.remove(layer);
    }

    /**
     * @return the scene layers.
     */
    public @NotNull SafeArrayList<SceneLayer> getLayers() {
        return layers;
    }

    /**
     * Add a new scene app state.
     *
     * @param appState the scene app state.
     */
    public void addAppState(@NotNull final SceneAppState appState) {
        appStates.add(appState);
        appState.setSceneNode(this);
    }

    /**
     * Remove an old scene app state.
     *
     * @param appState the scene app state.
     */
    public void removeAppState(@NotNull final SceneAppState appState) {
        appStates.remove(appState);
        appState.setSceneNode(null);
    }

    /**
     * @return the scene app states.
     */
    public @NotNull SafeArrayList<SceneAppState> getAppStates() {
        return appStates;
    }

    /**
     * Add a new filter.
     *
     * @param filter the scene filter.
     */
    public void addFilter(@NotNull final SceneFilter filter) {
        filters.add(filter);
    }

    /**
     * Remove an old filter.
     *
     * @param filter the scene filter.
     */
    public void removeFilter(@NotNull final SceneFilter filter) {
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
    public void write(@NotNull final JmeExporter exporter) throws IOException {

        final OutputCapsule capsule = exporter.getCapsule(this);
        final SceneLayer[] layers = getLayers().getArray();
        final SceneAppState[] appStates = getAppStates().getArray();
        final SceneFilter[] filters = getFilters().getArray();

        capsule.write(layers, "layers", EMPTY_LAYERS);
        capsule.write(appStates, "appStates", EMPTY_STATES);
        capsule.write(filters, "filters", EMPTY_FILTERS);

        super.write(exporter);
    }

    @Override
    public void read(@NotNull final JmeImporter importer) throws IOException {

        final InputCapsule capsule = importer.getCapsule(this);

        final Savable[] importedLayers = capsule.readSavableArray("layers", EMPTY_LAYERS);
        final Savable[] importedAppStates = capsule.readSavableArray("appStates", EMPTY_STATES);
        final Savable[] importedFilters = capsule.readSavableArray("filters", EMPTY_FILTERS);

        for (final Savable savable : importedLayers) {
            layers.add((SceneLayer) savable);
        }

        for (final Savable savable : importedAppStates) {
            appStates.add((SceneAppState) savable);
        }

        for (final Savable savable : importedFilters) {
            filters.add((SceneFilter) savable);
        }

        super.read(importer);
    }

    @Override
    public void cloneFields(final Cloner cloner, final Object original) {
        super.cloneFields(cloner, original);

        layers = cloner.clone(layers);
        appStates = cloner.clone(appStates);
        filters = cloner.clone(filters);

        for (final SceneAppState appState : appStates.getArray()) {
            appState.setSceneNode(this);
        }
    }

    /**
     * Notify a scene node about added an object to this scene.
     *
     * @param object the added object.
     */
    public void notifyAdded(@NotNull final Object object) {
        for (final SceneAppState appState : getAppStates().getArray()) {
            appState.notifyAdded(object);
        }
    }

    /**
     * Notify a scene node about removed an object from this scene.
     *
     * @param object the removed object.
     */
    public void notifyRemoved(@NotNull final Object object) {
        for (final SceneAppState appState : getAppStates().getArray()) {
            appState.notifyRemoved(object);
        }
    }
}
