package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import com.jme3.post.filters.BloomFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.rlib.util.array.Array;
import com.ss.rlib.util.array.ArrayFactory;
import org.jetbrains.annotations.NotNull;

/**
 * The editable implementation of bloom filter.
 *
 * @author JavaSaBr
 */
public class EditableBloomFilter extends BloomFilter implements EditableSceneFilter<BloomFilter> {

    EditableBloomFilter(@NotNull final GlowMode glowMode) {
        super(glowMode);
    }

    @Override
    public BloomFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
        return "Bloom filter";
    }

    @Override
    public Object jmeClone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Array<EditableProperty<?, ?>> getEditableProperties() {

        final Array<EditableProperty<?, ?>> result = ArrayFactory.newArray(EditableProperty.class);

        result.add(new SimpleProperty<>(FLOAT, "Blur scale", 0.1F, 0F, 10F, this,
                EditableBloomFilter::getBlurScale,
                EditableBloomFilter::setBlurScale));
        result.add(new SimpleProperty<>(FLOAT, "Bloom intensity", 0.1F, 0F, 10F, this,
                EditableBloomFilter::getBloomIntensity,
                EditableBloomFilter::setBloomIntensity));
        result.add(new SimpleProperty<>(FLOAT, "Exposure cut off", 0.01F, 0F, 100F, this,
                EditableBloomFilter::getExposureCutOff,
                EditableBloomFilter::setExposureCutOff));
        result.add(new SimpleProperty<>(FLOAT, "Exposure power", 0.1F, 0F, 100F, this,
                EditableBloomFilter::getExposurePower,
                EditableBloomFilter::setExposurePower));
        result.add(new SimpleProperty<>(FLOAT, "Down sampling factor", 0.1F, 0F, 100F, this,
                EditableBloomFilter::getDownSamplingFactor,
                EditableBloomFilter::setDownSamplingFactor));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }
}
