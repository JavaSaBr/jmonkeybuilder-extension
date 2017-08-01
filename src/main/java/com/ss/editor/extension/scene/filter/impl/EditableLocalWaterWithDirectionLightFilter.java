package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.DIRECTION_LIGHT_FROM_SCENE;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * The implementation of local water filter which uses the light direction from set {@link DirectionalLight}.
 *
 * @author JavaSaBr
 */
public class EditableLocalWaterWithDirectionLightFilter extends EditableLocalWaterFilter {

    /**
     * The direction light to follow.
     */
    @Nullable
    private DirectionalLight directionalLight;

    public EditableLocalWaterWithDirectionLightFilter() {
    }

    @Override
    public String getName() {
        return "Local water with direction light";
    }

    @NotNull
    @Override
    public List<EditableProperty<?, ?>> getEditableProperties() {

        final List<EditableProperty<?, ?>> result = super.getEditableProperties();
        result.add(new SimpleProperty<>(DIRECTION_LIGHT_FROM_SCENE, "Direction light", this,
                makeGetter(this, DirectionalLight.class, "getDirectionalLight"),
                makeSetter(this, DirectionalLight.class, "setDirectionalLight")));

        return result;
    }

    /**
     * @param directionalLight the direction light to follow.
     */
    public void setDirectionalLight(@Nullable final DirectionalLight directionalLight) {
        this.directionalLight = directionalLight;
        if (directionalLight != null) {
            setLightDirection(directionalLight.getDirection());
        } else {
            setLightDirection(Vector3f.UNIT_XYZ.clone());
        }
    }

    /**
     * @return the direction light to follow.
     */
    @Nullable
    public DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    @Override
    public void cloneFields(final Cloner cloner, final Object original) {
        super.cloneFields(cloner, original);
        this.directionalLight = cloner.clone(directionalLight);
    }

    @Override
    public void write(@NotNull final JmeExporter ex) throws IOException {
        super.write(ex);

        final OutputCapsule out = ex.getCapsule(this);
        out.write(directionalLight, "directionLight", null);
    }

    @Override
    public void read(@NotNull final JmeImporter im) throws IOException {
        super.read(im);

        final InputCapsule in = im.getCapsule(this);
        setDirectionalLight((DirectionalLight) in.readSavable("directionLight", null));
    }
}
