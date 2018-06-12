package com.ss.editor.extension.action;

import com.ss.editor.extension.integration.EditorEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * The interface to implement some modifying action.
 *
 * @author JavaSaBr
 */
public interface ModifyingAction {

    /**
     * Get the name of this action.
     *
     * @return the name of this action.
     */
    @NotNull String getName();

    /**
     * Execute this action for the object.
     *
     * @param env    the editor's environment.
     * @param object the object.
     * @return the prev. state of the object.
     */
    @NotNull Object redo(@NotNull EditorEnvironment env, @NotNull Object object);

    /**
     * Revert this action for the object.
     *
     * @param env       the editor's environment.
     * @param object    the object.
     * @param prevState the previous state.
     */
    void undo(@NotNull EditorEnvironment env, @NotNull Object object, @NotNull Object prevState);
}
