package com.ss.editor.extension.scene.filter;

import com.jme3.export.Savable;
import com.jme3.post.Filter;
import com.jme3.util.clone.JmeCloneable;
import com.ss.editor.extension.Named;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The interface to implement a scene filter.
 *
 * @author JavaSaBr
 */
@SuppressWarnings("NullableProblems")
public interface SceneFilter extends Savable, Named, JmeCloneable, Cloneable {

    /**
     * Get a filter. This method usually return itself.
     *
     * @return the filter.
     */
    @NotNull Filter get();

    /**
     * Enable or disable this filter
     *
     * @param enabled true to enable.
     */
    void setEnabled(boolean enabled);

    /**
     * Returns true if the filter is enabled.
     *
     * @return true if the filter is enabled.
     */
    boolean isEnabled();

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
