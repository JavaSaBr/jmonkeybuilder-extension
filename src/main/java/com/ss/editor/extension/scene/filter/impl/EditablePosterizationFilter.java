package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.EditablePropertyType.INTEGER;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.post.filters.PosterizationFilter;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import com.ss.editor.extension.scene.app.state.SceneAppState;
import com.ss.editor.extension.scene.filter.EditableSceneFilter;
import com.ss.editor.extension.scene.filter.SceneFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The editable implementation of posterization filter.
 *
 * @author JavaSaBr
 */
public class EditablePosterizationFilter extends PosterizationFilter implements EditableSceneFilter {

    @Override
    public @NotNull PosterizationFilter get() {
        return this;
    }

    @Override
    public @NotNull String getName() {
        return "Posterization filter";
    }

    @Override
    public Object jmeClone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = new ArrayList<>(3);
        result.add(new SimpleProperty<>(FLOAT, "Gamma", 0.005F, 0F, 10F, this,
                makeGetter(this, float.class, "getGamma"),
                makeSetter(this, float.class, "setGamma")));
        result.add(new SimpleProperty<>(INTEGER, "Num colors", 1F, 0F, 100F, this,
                makeGetter(this, int.class, "getNumColors"),
                makeSetter(this, int.class, "setNumColors")));
        result.add(new SimpleProperty<>(FLOAT, "Strength", 0.1F, 0F, 100F, this,
                makeGetter(this, float.class, "getStrength"),
                makeSetter(this, float.class, "setStrength")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull Cloner cloner, @NotNull Object original) {
    }

    @Override
    public void read(@NotNull JmeImporter importer) throws IOException {
        super.read(importer);
        InputCapsule capsule = importer.getCapsule(this);
        setGamma(capsule.readFloat("gamma", 0.6F));
        setNumColors(capsule.readInt("numColors", 8));
        setStrength(capsule.readFloat("strength", 1.0f));
    }

    @Override
    public void write(@NotNull JmeExporter exporter) throws IOException {
        super.write(exporter);
        OutputCapsule capsule = exporter.getCapsule(this);
        capsule.write(getGamma(), "gamma", 0.6F);
        capsule.write(getNumColors(), "numColors", 8);
        capsule.write(getStrength(), "strength", 1.0f);
    }

    @Override
    public @Nullable String checkStates(@NotNull List<SceneAppState> exists) {
        return null;
    }

    @Override
    public @Nullable String checkFilters(@NotNull List<SceneFilter> exists) {
        return null;
    }
}
