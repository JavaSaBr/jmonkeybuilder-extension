package com.ss.editor.extension.scene.filter.impl;

import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of objects bloom filter.
 *
 * @author JavaSaBr
 */
public class EditableObjectsBloomFilter extends EditableBloomFilter {

    public EditableObjectsBloomFilter() {
        super(GlowMode.Objects);
    }

    @Override
    public @NotNull String getName() {
        return "Objects bloom filter";
    }
}
