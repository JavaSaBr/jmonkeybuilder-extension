package com.ss.editor.extension;

import org.jetbrains.annotations.NotNull;

/**
 * The interface to mark a class that objects have its own name.
 *
 * @author JavaSaBr
 */
public interface Named {

    /**
     * Get the object's name.
     *
     * @return the object's name.
     */
    @NotNull String getName();
}
