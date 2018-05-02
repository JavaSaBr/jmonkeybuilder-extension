package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.DIRECTION_LIGHT_FROM_SCENE;
import com.jme3.light.DirectionalLight;
import com.jme3.shadow.AbstractShadowRenderer;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.ReflectionGetterSetterFactory;
import com.ss.editor.extension.property.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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

    protected EditableDirectionLightFromSceneShadowFilter(int shadowMapSize, int nbSplits) {
        super(shadowMapSize, nbSplits);
    }

    @Override
    public @NotNull String getName() {
        return "Shadows from direction light";
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        List<EditableProperty<?, ?>> result = super.getEditableProperties();
        result.add(new SimpleProperty<>(DIRECTION_LIGHT_FROM_SCENE, "Direction light", this,
                ReflectionGetterSetterFactory.makeGetter(this, DirectionalLight.class, "getLight"),
                ReflectionGetterSetterFactory.makeSetter(this, DirectionalLight.class, "setLight")));

        return result;
    }
}
