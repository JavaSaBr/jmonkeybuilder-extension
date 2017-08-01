package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * The factory of getters/setter using reflection.
 *
 * @author JavaSabr
 */
public class ReflectionGetterSetterFactory {

    @NotNull
    private static final Map<String, Object> GETTERS = new HashMap<>();

    @NotNull
    private static final Map<String, Object> SETTERS = new HashMap<>();

    @NotNull
    public static synchronized <T, O> Getter<T, O> makeGetter(@NotNull final T object,
                                                              @NotNull final Class<O> propertyType,
                                                              @NotNull final String methodName) {

        final Class<?> objectType = object.getClass();
        final String key = objectType.getName() + "." + methodName;

        Getter<T, O> getter = (Getter<T, O>) GETTERS.get(key);

        if (getter != null) {
            return getter;
        }

        final Method targetMethod;
        try {
            targetMethod = objectType.getMethod(methodName);
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        getter = new Getter<T, O>() {

            @Nullable
            @Override
            public O get(@NotNull final T object) {
                try {
                    return (O) targetMethod.invoke(object);
                } catch (final IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        GETTERS.put(key, getter);

        return getter;
    }

    public static synchronized <T, O> Setter<T, O> makeSetter(@NotNull final T object,
                                                              @NotNull final Class<O> propertyType,
                                                              @NotNull final String methodName) {

        final Class<?> objectType = object.getClass();
        final String key = objectType.getName() + "." + methodName;

        Setter<T, O> setter = (Setter<T, O>) SETTERS.get(key);

        if (setter != null) {
            return setter;
        }

        final Method targetMethod;
        try {
            targetMethod = objectType.getMethod(methodName, propertyType);
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        setter = new Setter<T, O>() {

            @Override
            public void set(@NotNull final T object, @Nullable final O property) {
                try {
                    targetMethod.invoke(object, property);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        SETTERS.put(key, setter);

        return setter;
    }
}
