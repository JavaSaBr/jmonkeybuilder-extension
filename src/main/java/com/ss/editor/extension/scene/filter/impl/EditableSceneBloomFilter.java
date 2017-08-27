package com.ss.editor.extension.scene.filter.impl;

import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of scene bloom filter.
 *
 * @author JavaSaBr
 */
public class EditableSceneBloomFilter extends EditableBloomFilter {

    public EditableSceneBloomFilter() {
        super(GlowMode.Scene);
    }

    @Override
    public @NotNull String getName() {
        return "Scene bloom filter";
    }
}
