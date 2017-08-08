package com.ss.editor.extension.test;

import com.ss.editor.extension.property.EditableProperty;
import com.ss.rlib.util.ClassUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The test to test cases with editable properties.
 *
 * @author JavaSaBr
 */
public class PropertyTest extends SetUpTest {

    protected void testProperties(@NotNull final List<EditableProperty<?, ?>> properties) {
        properties.forEach(editableProperty -> {

            switch (editableProperty.getType()) {
                case TEXTURE_2D: {
                    return;
                }
            }

            try {
                final EditableProperty<Object, ?> property = ClassUtils.unsafeCast(editableProperty);
                property.setValue(property.getValue());
            } catch (final Throwable e) {
                throw new RuntimeException("Problem with property " + editableProperty.getName() + " in the " +
                        editableProperty.getObject(), e);
            }
        });
    }
}
