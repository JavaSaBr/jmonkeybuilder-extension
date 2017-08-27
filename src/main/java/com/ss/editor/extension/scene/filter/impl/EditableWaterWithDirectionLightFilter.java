package com.ss.editor.extension.scene.filter.impl;

import static com.ss.editor.extension.property.EditablePropertyType.DIRECTION_LIGHT_FROM_SCENE;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeGetter;
import static com.ss.editor.extension.property.ReflectionGetterSetterFactory.makeSetter;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.util.clone.Cloner;
import com.ss.editor.extension.property.EditableProperty;
import com.ss.editor.extension.property.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * The implementation of global water filter which uses the light direction from set {@link DirectionalLight}.
 *
 * @author JavaSaBr
 */
public class EditableWaterWithDirectionLightFilter extends EditableWaterFilter {

    /**
     * The direction light to follow.
     */
    @Nullable
    private DirectionalLight directionalLight;

    public EditableWaterWithDirectionLightFilter() {
    }

    @Override
    public @NotNull String getName() {
        return "Global water with direction light";
    }

    @Override
    public @NotNull List<EditableProperty<?, ?>> getEditableProperties() {

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
            setLightColor(directionalLight.getColor());
        } else {
            setLightDirection(Vector3f.UNIT_XYZ.clone());
            setLightColor(ColorRGBA.White.clone());
        }
    }

    @Override
    protected boolean needLightColor() {
        return false;
    }

    @Override
    protected boolean needLightDirection() {
        return false;
    }

    /**
     * @return the direction light to follow.
     */
    public @Nullable DirectionalLight getDirectionalLight() {
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
