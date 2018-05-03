package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.POINT_LIGHT_FROM_SCENE;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.light.PointLight;
import com.jme3.shadow.AbstractShadowRenderer;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The editable implementation of a {@link EditablePointLightShadowFilter} which uses the light from a scene.
 *
 * @author JavaSaBr
 */
public class EditablePointLightFromSceneShadowFilter extends EditablePointLightShadowFilter {

    static {
        Logger.getLogger(AbstractShadowRenderer.class.getName())
                .setLevel(Level.OFF);
    }

    public EditablePointLightFromSceneShadowFilter() {
    }

    @Override
    public @NotNull String getName() {
        return "Shadows from point light";
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        List<EditableProperty<?, ?>> result = super.getEditableProperties();
        result.add(new SimpleProperty<>(POINT_LIGHT_FROM_SCENE, "Point light", this,
                makeGetter(this, PointLight.class, "getLight"),
                makeSetter(this, PointLight.class, "setLight")));

        return result;
    }
}
