package com.ss.editor.extension.scene.filter.impl;

import org.jetbrains.annotations.NotNull;

/**
 * The HQ version {@link EditableDirectionLightFromSceneShadowFilter}.
 *
 * @author JavaSaBr
 */
public class EditableHqDirectionalLightFromSceneShadowFilter extends EditableDirectionLightFromSceneShadowFilter {

    public EditableHqDirectionalLightFromSceneShadowFilter() {
        super(4096, 4);
    }

    @Override
    public @NotNull String getName() {
        return "HQ Shadows from direction light";
    }
}
