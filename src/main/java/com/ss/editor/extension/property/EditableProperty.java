package com.ss.editor.extension.property;

import com.ss.editor.extension.Named;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface for implementing editable property of generic object.
 *
 * @author JavaSabr
 */
public interface EditableProperty<T, O> extends Named {

    /**
     * Get the current value.
     *
     * @return the current value.
     */
    @Nullable
    T getValue();

    /**
     * Get a type of this property.
     *
     * @return the property type.
     */
    @NotNull
    EditablePropertyType getType();

    /**
     * @return scroll power.
     */
    float getScrollPower();

    /**
     * @return the min value.
     */
    float getMinValue();

    /**
     * @return the max value.
     */
    float getMaxValue();

    /**
     * Get an edited object.
     *
     * @return the edited object.
     */
    @NotNull
    O getObject();

    /**
     * Set a new value.
     *
     * @param value the new value.
     */
    void setValue(@Nullable final T value);
}
