package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of {@link EditableProperty} to separate other properties.
 *
 * @author JavaSaBr
 */
public final class SeparatorProperty implements EditableProperty<Object, Object> {

    private static final SeparatorProperty INSTANCE = new SeparatorProperty();

    /**
     * Get the instance of this separator.
     *
     * @return the instance of this separator.
     */
    public static @NotNull SeparatorProperty getInstance() {
        return INSTANCE;
    }

    @Override
    public @NotNull String getName() {
        return "Separator";
    }

    @Override
    public @NotNull Object getValue() {
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
    public void setValue(@Nullable Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public @Nullable String getFileExtension() {
        return null;
    }
}
