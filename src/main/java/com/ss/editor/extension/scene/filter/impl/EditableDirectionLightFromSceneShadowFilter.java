package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.DIRECTION_LIGHT_FROM_SCENE;
import com.jme3.shadow.AbstractShadowRenderer;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.rlib.util.array.Array;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

/**
 * The editable implementation of a {@link DirectionalLightShadowFilter} which uses the light from a scene.
 *
 * @author JavaSaBr
 */
public class EditableDirectionLightFromSceneShadowFilter extends EditableDirectionalLightShadowFilter {

    static {
        java.util.logging.Logger.getLogger(AbstractShadowRenderer.class.getName())
                                .setLevel(Level.OFF);
    }

    public EditableDirectionLightFromSceneShadowFilter() {
    }

    @NotNull
    @Override
    public String getName() {
        return "Shadows from direction light";
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = super.getEditableProperties();
        result.add(new SimpleProperty<>(DIRECTION_LIGHT_FROM_SCENE, "Direction light", this,
                EditableDirectionalLightShadowFilter::getLight,
                EditableDirectionalLightShadowFilter::setLight));

        return result;
    }
}
