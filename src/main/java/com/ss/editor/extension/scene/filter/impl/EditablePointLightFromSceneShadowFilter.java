package com.ss.editor.extension.scene.filter.impl;

import com.jme3.shadow.AbstractShadowRenderer;
import com.ss.editor.extension.property.EditablePropertyType;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import com.ss.rlib.util.array.Array;

import java.util.logging.Level;

/**
 * The editable implementation of a {@link EditablePointLightShadowFilter} which uses the light from a scene.
 *
 * @author JavaSaBr
 */
public class EditablePointLightFromSceneShadowFilter extends EditablePointLightShadowFilter {

    static {
        java.util.logging.Logger.getLogger(AbstractShadowRenderer.class.getName())
                                .setLevel(Level.OFF);
    }

    public EditablePointLightFromSceneShadowFilter() {
    }

    @NotNull
    @Override
    public String getName() {
        return "Shadows from point light";
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = super.getEditableProperties();
        result.add(new SimpleProperty<>(EditablePropertyType.POINT_LIGHT_FROM_SCENE, "Point light", this,
                                        EditablePointLightShadowFilter::getLight,
                                        EditablePointLightShadowFilter::setLight));

        return result;
    }
}
