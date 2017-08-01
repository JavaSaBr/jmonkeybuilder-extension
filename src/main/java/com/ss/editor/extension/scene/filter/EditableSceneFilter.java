package com.ss.editor.extension.scene.filter;

import com.ss.editor.extension.property.EditableProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * The interface to implement editing support.
 *
 * @author JavaSaBr
 */
public interface EditableSceneFilter extends SceneFilter {

    @NotNull
    List<EditableProperty<?, ?>> EMPTY_PROPERTIES = Collections.emptyList();

    /**
     * Get list of editable properties.
     *
     * @return the list of editable properties.
     */
    @NotNull
    List<EditableProperty<?, ?>> getEditableProperties();
}
