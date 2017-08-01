package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface to implement a getter of an editable property.
 *
 * @param <O> the type of an object.
 * @param <P> the type of a property.
 * @author JavaSaBr
 */
public interface Getter<O, P> {

    /**
     * Get a current property.
     *
     * @param object the object.
     */
    @Nullable
    P get(@NotNull O object);
}
