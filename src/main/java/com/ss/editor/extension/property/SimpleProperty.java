package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The simple implementation of editable property to a generic object.
 *
 * @param <T> the property's type.
 * @param <O> the edited object's type.
 * @author JavaSabr
 */
public class SimpleProperty<T, O> implements EditableProperty<T, O> {

    /**
     * The type of this property.
     */
    @NotNull
    private final EditablePropertyType type;

    /**
     * The name of this property.
     */
    @NotNull
    private final String name;

    /**
     * The getter of this property.
     */
    @NotNull
    private final Getter<O, T> getter;

    /**
     * The setter of this property.
     */
    @Nullable
    private final Setter<O, T> setter;

    /**
     * The edited object.
     */
    @NotNull
    private final O object;

    /**
     * The extension to filter resources/files.
     */
    @Nullable
    private final String extension;

    /**
     * The scroll power.
     */
    private float scrollPower;

    /**
     * The min value.
     */
    private float minValue;

    /**
     * The max value.
     */
    private float maxValue;

    public SimpleProperty(
            @NotNull EditablePropertyType type,
            @NotNull String name,
            @NotNull O object,
            @NotNull Getter<O, T> getter
    ) {
        this(type, name, 1F, Integer.MIN_VALUE, Integer.MAX_VALUE, object, null, getter);
    }

    public SimpleProperty(
            @NotNull EditablePropertyType type,
            @NotNull String name,
            @NotNull O object,
            @NotNull Getter<O, T> getter,
            @NotNull Setter<O, T> setter
    ) {
        this(type, name, 1F, Integer.MIN_VALUE, Integer.MAX_VALUE, object, getter, setter);
    }

    public SimpleProperty(
            @NotNull EditablePropertyType type,
            @NotNull String name,
            float scrollPower,
            @NotNull O object,
            @NotNull Getter<O, T> getter,
            @NotNull Setter<O, T> setter
    ) {
        this(type, name, scrollPower, Integer.MIN_VALUE, Integer.MAX_VALUE, object, getter, setter);
    }

    public SimpleProperty(
            @NotNull EditablePropertyType type,
            @NotNull String name,
            float scrollPower,
            float minValue,
            float maxValue,
            @NotNull O object,
            @NotNull Getter<O, T> getter,
            @NotNull Setter<O, T> setter
    ) {
        this(type, name, scrollPower, minValue, maxValue, object, null, getter, setter);
    }

    public SimpleProperty(
            @NotNull EditablePropertyType type,
            @NotNull String name,
            float scrollPower,
            float minValue,
            float maxValue,
            @NotNull O object,
            @Nullable String extension,
            @NotNull Getter<O, T> getter
    ) {
        this(type, name, scrollPower, minValue, maxValue, object, extension, getter, null);
    }

    public SimpleProperty(
            @NotNull EditablePropertyType type,
            @NotNull String name,
            float scrollPower,
            float minValue,
            float maxValue,
            @NotNull O object,
            @Nullable String extension,
            @NotNull Getter<O, T> getter,
            @Nullable Setter<O, T> setter
    ) {
        this.type = type;
        this.name = name;
        this.object = object;
        this.extension = extension;
        this.getter = getter;
        this.setter = setter;
        this.scrollPower = scrollPower;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public float getScrollPower() {
        return scrollPower;
    }

    @Override
    public float getMaxValue() {
        return maxValue;
    }

    @Override
    public float getMinValue() {
        return minValue;
    }

    @Override
    public @NotNull EditablePropertyType getType() {
        return type;
    }

    @Override
    public @NotNull O getObject() {
        return object;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @Nullable T getValue() {
        return getter.get(object);
    }

    @Override
    public boolean isReadOnly() {
        return setter == null;
    }

    @Override
    public void setValue(@Nullable final T value) {
        if (setter == null) {
            throw new IllegalStateException("This property " + this + " is read only.");
        }
        setter.set(object, value);
    }

    @Override
    public @Nullable String getFileExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "SimpleProperty{" + "type=" + type + ", name='" + name + '\'' + ", getter=" + getter + ", setter=" +
                setter + ", object=" + object + ", scrollPower=" + scrollPower + ", minValue=" + minValue +
                ", maxValue=" + maxValue + '}';
    }
}
