package com.ss.editor.extension;

import org.jetbrains.annotations.NotNull;

/**
 * The interface to mark a class that it has a name.
 *
 * @author JavaSaBr
 */
public interface Named {

    /**
     * @return the name.
     */
    @NotNull String getName();
}
