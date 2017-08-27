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
     * Get a current property value.
     *
     * @param object the object.
     * @return the current property value.
     */
    @Nullable P get(@NotNull O object);
}
