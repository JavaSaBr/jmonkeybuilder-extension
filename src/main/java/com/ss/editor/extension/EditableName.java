package com.ss.editor.extension;

import org.jetbrains.annotations.NotNull;

/**
 * Mark a class that objects have editable its own name.
 *
 * @author JavaSaBr
 */
public interface EditableName extends Named {

    /**
     * Set the new name.
     *
     * @param name the new name.
     */
    void setName(@NotNull String name);
}
