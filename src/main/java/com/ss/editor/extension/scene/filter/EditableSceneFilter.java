package com.ss.editor.extension.scene.filter;

import com.jme3.post.Filter;
import com.ss.editor.extension.property.EditableProperty;
import org.jetbrains.annotations.NotNull;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;

/**
 * The interface to implement editing support.
 *
 * @author JavaSaBr
 */
public interface EditableSceneFilter<T extends Filter> extends SceneFilter<T> {

    @NotNull
    Array<EditableProperty<?, ?>> EMPTY_PROPERTIES = ArrayFactory.newArray(EditableProperty.class);

    /**
     * Get list of editable properties.
     *
     * @return the list of editable properties.
     */
    @NotNull
    default Array<EditableProperty<?, ?>> getEditableProperties() {
        return EMPTY_PROPERTIES;
    }
}
