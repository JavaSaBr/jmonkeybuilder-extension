package com.ss.editor.extension.scene.app.state;

import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * The interface to implement am editable scene app state.
 *
 * @author JavaSaBr
 */
public interface EditableSceneAppState extends SceneAppState {

    @NotNull List<EditableProperty<?, ?>> EMPTY_PROPERTIES = Collections.emptyList();

    /**
     * Get a list of editable properties.
     *
     * @return the list of editable properties.
     */
    @NotNull List<EditableProperty<?, ?>> getEditableProperties();

    /**
     * Check state dependencies.
     *
     * @param exists the current exists states.
     * @return null if can be created or message with description of missed dependencies.
     */
    @Nullable String checkStates(@NotNull List<SceneAppState> exists);

    /**
     * Check filter dependencies.
     *
     * @param exists the current exists filters.
     * @return null if can be created or message with description of missed dependencies.
     */
    @Nullable String checkFilters(@NotNull List<SceneFilter> exists);
}

