package com.ss.editor.extension.scene.filter.impl;

import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of scene and objects bloom filter.
 *
 * @author JavaSaBr
 */
public class EditableSceneAndObjectsBloomFilter extends EditableBloomFilter {

    public EditableSceneAndObjectsBloomFilter() {
        super(GlowMode.SceneAndObjects);
    }

    @Override
    public @NotNull String getName() {
        return "Scene and objects bloom filter";
    }
}
