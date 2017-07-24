package com.ss.editor.extension.scene.control.impl;

import static com.ss.editor.extension.property.EditablePropertyType.ENUM;
import com.jme3.scene.control.BillboardControl;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.control.EditableControl;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of the {@link BillboardControl}.
 *
 * @author JavaSaBr
 */
public class EditableBillboardControl extends BillboardControl implements EditableControl {

    @NotNull
    @Override
    public String getName() {
        return "Billboard";
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(ENUM, "Alignment", this,
                                        BillboardControl::getAlignment,
                                        BillboardControl::setAlignment));

        return result;
    }
}
