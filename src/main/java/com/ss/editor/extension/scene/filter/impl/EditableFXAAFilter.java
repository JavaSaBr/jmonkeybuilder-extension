package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.post.filters.FXAAFilter;
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
 * The editable implementation of FXAA filter.
 *
 * @author JavaSaBr
 */
public class EditableFXAAFilter extends FXAAFilter implements EditableSceneFilter {

    @Override
    public FXAAFilter get() {
        return this;
    }

    @NotNull
    @Override
    public String getName() {
        return "FXAA filter";
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

        final List<EditableProperty<?, ?>> result = new ArrayList<>(4);

        result.add(new SimpleProperty<>(FLOAT, "Sub pixel shift", 0.005F, 0F, 10F, this,
                makeGetter(this, float.class, "getSubPixelShift"),
                makeSetter(this, float.class, "setSubPixelShift")));
        result.add(new SimpleProperty<>(FLOAT, "VX offset", 0.005F, 0F, 10F, this,
                makeGetter(this, float.class, "getVxOffset"),
                makeSetter(this, float.class, "setVxOffset")));
        result.add(new SimpleProperty<>(FLOAT, "Span max", 1F, 0F, 100F, this,
                makeGetter(this, float.class, "getSpanMax"),
                makeSetter(this, float.class, "setSpanMax")));
        result.add(new SimpleProperty<>(FLOAT, "Reduce mul", 0.1F, 0F, 100F, this,
                makeGetter(this, float.class, "getReduceMul"),
                makeSetter(this, float.class, "setReduceMul")));

        return result;
    }

    @Override
    public void cloneFields(@NotNull final Cloner cloner, @NotNull final Object original) {
    }

    @Override
    public void read(@NotNull final JmeImporter importer) throws IOException {
        super.read(importer);
        final InputCapsule capsule = importer.getCapsule(this);
        setSubPixelShift(capsule.readFloat("subPixelShift", 1.0f / 4.0f));
        setVxOffset(capsule.readFloat("vxOffset", 0.0f));
        setSpanMax(capsule.readFloat("spanMax", 8.0f));
        setReduceMul(capsule.readFloat("reduceMul", 1.0f / 8.0f));
    }

    @Override
    public void write(@NotNull final JmeExporter exporter) throws IOException {
        super.write(exporter);
        final OutputCapsule capsule = exporter.getCapsule(this);
        capsule.write(getSubPixelShift(), "subPixelShift", 1.0f / 4.0f);
        capsule.write(getVxOffset(), "vxOffset", 0.0f);
        capsule.write(getSpanMax(), "spanMax", 8.0f);
        capsule.write(getReduceMul(), "reduceMul", 1.0f / 8.0f);
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
