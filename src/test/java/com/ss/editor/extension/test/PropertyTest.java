package com.ss.editor.extension.test;

import com.ss.editor.extension.property.EditableProperty;
import com.ss.rlib.util.ClassUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PropertyTest extends SetUpTest {

    protected void testProperties(@NotNull final List<EditableProperty<?, ?>> properties) {
        properties.forEach(editableProperty -> {

            switch (editableProperty.getType()) {
                case TEXTURE_2D: {
                    return;
                }
            }

            try {

                EditableProperty<Object, ?> property = ClassUtils.unsafeCast(editableProperty);
                property.setValue(property.getValue());

            } catch (final Throwable e) {
                throw new RuntimeException("Problem with property " + editableProperty.getName() + " in the " +
                        editableProperty.getObject(), e);
            }
        });
    }
}
