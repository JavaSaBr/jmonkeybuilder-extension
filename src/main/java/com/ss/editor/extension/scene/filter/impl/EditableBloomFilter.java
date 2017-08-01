package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.post.filters.BloomFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable implementation of bloom filter.
 *
 * @author JavaSaBr
 */
public class EditableBloomFilter extends BloomFilter implements EditableSceneFilter {

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
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(5);

        result.add(new SimpleProperty<>(FLOAT, "Blur scale", 0.1F, 0F, 10F, this,
                makeGetter(this, float.class, "getBlurScale"),
                makeSetter(this, float.class, "setBlurScale")));
        result.add(new SimpleProperty<>(FLOAT, "Bloom intensity", 0.1F, 0F, 10F, this,
                makeGetter(this, float.class, "getBloomIntensity"),
                makeSetter(this, float.class, "setBloomIntensity")));
        result.add(new SimpleProperty<>(FLOAT, "Exposure cut off", 0.01F, 0F, 100F, this,
                makeGetter(this, float.class, "getExposureCutOff"),
                makeSetter(this, float.class, "setExposureCutOff")));
        result.add(new SimpleProperty<>(FLOAT, "Exposure power", 0.1F, 0F, 100F, this,
                makeGetter(this, float.class, "getExposurePower"),
                makeSetter(this, float.class, "setExposurePower")));
        result.add(new SimpleProperty<>(FLOAT, "Down sampling factor", 0.1F, 0F, 100F, this,
                makeGetter(this, float.class, "getDownSamplingFactor"),
                makeSetter(this, float.class, "setDownSamplingFactor")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }

    @Nullable
    @Override
    public String checkStates(final @NotNull List<SceneAppState> exists) {
        return null;
    }

    @Nullable
    @Override
    public String checkFilters(final @NotNull List<SceneFilter> exists) {
        return null;
    }
}
