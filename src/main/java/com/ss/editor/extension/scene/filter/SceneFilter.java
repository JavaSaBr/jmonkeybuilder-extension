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
     * Get a filter. It is usually return this.
     *
     * @return the filter.
     */
    @NotNull Filter get();

    /**
     * Enable or disable this filter
     *
     * @param enabled true to enable
     */
    void setEnabled(boolean enabled);

    /**
     * returns true if the filter is enabled
     *
     * @return enabled
     */
    boolean isEnabled();

    /**
     * Check state dependencies.
     *
     * @param exists the current exists states.
     * @return null of can create or message with description.
     */
    @Nullable String checkStates(@NotNull final List<SceneAppState> exists);

    /**
     * Check filter dependencies.
     *
     * @param exists the current exists filters.
     * @return null of can create or message with description.
     */
    @Nullable String checkFilters(@NotNull final List<SceneFilter> exists);
}
