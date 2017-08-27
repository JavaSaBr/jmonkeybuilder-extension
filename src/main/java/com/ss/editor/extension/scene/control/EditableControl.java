package com.ss.editor.extension.scene.control;

import com.jme3.scene.control.Control;
import com.ss.editor.extension.Named;
import com.ss.editor.extension.property.EditableProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * The interface to implement an editable control.
 *
 * @author JavaSaBr
 */
public interface EditableControl extends Control, Named {

    @NotNull List<EditableProperty<?, ?>> EMPTY_PROPERTIES = Collections.emptyList();

    /**
     * @return the control's name.
     */
    @NotNull String getName() ;

    /**
     * Get list of editable properties.
     *
     * @return the list of editable properties.
     */
    @NotNull List<EditableProperty<?, ?>> getEditableProperties();
}
