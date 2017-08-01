package com.ss.editor.extension.scene.control.impl;

import static com.ss.editor.extension.property.EditablePropertyType.ENUM;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.scene.control.BillboardControl;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.control.EditableControl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(1);

        result.add(new SimpleProperty<>(ENUM, "Alignment", this,
                makeGetter(this, Alignment.class, "getAlignment"),
                makeSetter(this, Alignment.class, "setAlignment")));

        return result;
    }
}
