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
    public PosterizationFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
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

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

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
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }

    @Override
    public void read(@NotNull final JmeImporter importer) throws IOException {
        super.read(importer);
        final InputCapsule capsule = importer.getCapsule(this);
        setGamma(capsule.readFloat("gamma", 0.6F));
        setNumColors(capsule.readInt("numColors", 8));
        setStrength(capsule.readFloat("strength", 1.0f));
    }

    @Override
    public void write(@NotNull final JmeExporter exporter) throws IOException {
        super.write(exporter);
        final OutputCapsule capsule = exporter.getCapsule(this);
        capsule.write(getGamma(), "gamma", 0.6F);
        capsule.write(getNumColors(), "numColors", 8);
        capsule.write(getStrength(), "strength", 1.0f);
    }

    @Nullable
    @Override
    public String checkStates(@NotNull final List<SceneAppState> exists) {
        return null;
    }

    @Nullable
    @Override
    public String checkFilters(@NotNull final List<SceneFilter> exists) {
        return null;
    }
}
