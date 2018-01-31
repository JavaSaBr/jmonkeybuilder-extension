package com.ss.editor.extension.scene.filter.impl;

import org.jetbrains.annotations.NotNull;

/**
 * The HQ version {@link EditableDirectionLightFromSceneShadowFilter}.
 *
 * @author JavaSaBr
 */
public class EditableHqDirectionalLightShadowFilter extends EditableDirectionLightFromSceneShadowFilter {

    public EditableHqDirectionalLightShadowFilter() {
        super(4096, 4);
    }

    @Override
    public @NotNull String getName() {
        return "HQ Shadows from direction light";
    }
}
