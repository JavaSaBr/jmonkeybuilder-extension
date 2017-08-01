package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface to implement a setter of an editable property.
 *
 * @param <O> the type of an object.
 * @param <P> the type of a property.
 * @author JavaSaBr
 */
public interface Setter<O, P> {

    /**
     * Set a new property.
     *
     * @param object   the object.
     * @param property the property.
     */
    void set(@NotNull O object, @Nullable P property);
}
