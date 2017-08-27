package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of {@link EditableProperty} to separate other properties.
 *
 * @author JavaSaBr
 */
public final class SeparatorProperty implements EditableProperty<Object, Object> {

    @Override
    public @NotNull String getName() {
        return "Separator";
    }

    @Override
    public @Nullable Object getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull EditablePropertyType getType() {
        return EditablePropertyType.SEPARATOR;
    }

    @Override
    public float getScrollPower() {
        return 0;
    }

    @Override
    public float getMinValue() {
        return 0;
    }

    @Override
    public float getMaxValue() {
        return 0;
    }

    @Override
    public @NotNull Object getObject() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(@Nullable final Object value) {
        throw new UnsupportedOperationException();
    }
}
