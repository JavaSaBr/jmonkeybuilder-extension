package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.COLOR;
import static com.ss.editor.extension.property.EditablePropertyType.FLOAT;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.ColorRGBA;
import com.jme3.post.filters.CartoonEdgeFilter;
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
 * The editable implementation of cartoon edge filter.
 *
 * @author JavaSaBr
 */
public class EditableCartoonEdgeFilter extends CartoonEdgeFilter implements EditableSceneFilter {

    @Override
    public @NotNull CartoonEdgeFilter get() {
        return this;
    }

    @Override
    public @NotNull String getName() {
        return "Cartoon edge filter";
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
    public void cloneFields(@NotNull Cloner cloner, @NotNull Object original) {
        setEdgeColor(cloner.clone(getEdgeColor()));
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

        List<EditableProperty<?, ?>> result = new ArrayList<>(7);

        result.add(new SimpleProperty<>(COLOR, "Edge color", this,
                makeGetter(this, ColorRGBA.class, "getEdgeColor"),
                makeSetter(this, ColorRGBA.class, "setEdgeColor")));
        result.add(new SimpleProperty<>(FLOAT, "Edge width", this,
                makeGetter(this, float.class, "getEdgeWidth"),
                makeSetter(this, float.class, "setEdgeWidth")));
        result.add(new SimpleProperty<>(FLOAT, "Edge intensity", this,
                makeGetter(this, float.class, "getEdgeIntensity"),
                makeSetter(this, float.class, "setEdgeIntensity")));
        result.add(new SimpleProperty<>(FLOAT, "Normal threshold", this,
                makeGetter(this, float.class, "getNormalThreshold"),
                makeSetter(this, float.class, "setNormalThreshold")));
        result.add(new SimpleProperty<>(FLOAT, "Depth threshold", this,
                makeGetter(this, float.class, "getDepthThreshold"),
                makeSetter(this, float.class, "setDepthThreshold")));
        result.add(new SimpleProperty<>(FLOAT, "Normal sensitivity", this,
                makeGetter(this, float.class, "getNormalSensitivity"),
                makeSetter(this, float.class, "setNormalSensitivity")));
        result.add(new SimpleProperty<>(FLOAT, "Depth sensitivity", this,
                makeGetter(this, float.class, "getDepthSensitivity"),
                makeSetter(this, float.class, "setDepthSensitivity")));

        return result;
    }

    @Override
    public void read(@NotNull JmeImporter importer) throws IOException {
        super.read(importer);
        InputCapsule capsule = importer.getCapsule(this);
        setEdgeWidth(capsule.readFloat("edgeWidth", 1.0f));
        setEdgeIntensity(capsule.readFloat("edgeIntensity", 1.0f));
        setNormalThreshold(capsule.readFloat("normalThreshold", 0.5f));
        setDepthThreshold(capsule.readFloat("depthThreshold", 0.1f));
        setNormalSensitivity(capsule.readFloat("normalSensitivity", 1.0f));
        setDepthSensitivity(capsule.readFloat("depthSensitivity", 10.0f));
        setEdgeColor((ColorRGBA) capsule.readSavable("edgeColor", new ColorRGBA(0, 0, 0, 1)));
    }

    @Override
    public void write(@NotNull JmeExporter exporter) throws IOException {
        super.write(exporter);
        OutputCapsule capsule = exporter.getCapsule(this);
        capsule.write(getEdgeWidth(), "edgeWidth", 1.0f);
        capsule.write(getEdgeIntensity(), "edgeIntensity", 1.0f);
        capsule.write(getNormalThreshold(), "normalThreshold", 0.5f);
        capsule.write(getDepthThreshold(), "depthThreshold", 0.1f);
        capsule.write(getNormalSensitivity(), "normalSensitivity", 1.0f);
        capsule.write(getDepthSensitivity(), "depthSensitivity", 10.0f);
        capsule.write(getEdgeColor(), "edgeColor", new ColorRGBA(0, 0, 0, 1));
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
