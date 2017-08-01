package com.ss.editor.extension.property;

import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture2D;
import org.jetbrains.annotations.NotNull;

/**
 * The enum with list editable property types.
 *
 * @author JavaSaBr
 */
public enum EditablePropertyType {
    BOOLEAN(Boolean.class, boolean.class),
    INTEGER(Integer.class, int.class),
    FLOAT(Float.class, float.class),
    VECTOR_2F(Vector2f.class),
    VECTOR_3F(Vector3f.class),
    QUATERNION(Quaternion.class),
    COLOR(ColorRGBA.class),
    ENUM(Enum.class),
    STRING(String.class),
    TEXTURE_2D(Texture2D.class),
    DIRECTION_LIGHT_FROM_SCENE(DirectionalLight.class),
    POINT_LIGHT_FROM_SCENE(PointLight.class),
    SPATIAL_FROM_ASSET_FOLDER(Spatial.class),
    SPATIAL_FROM_SCENE(Spatial.class),
    NODE_FROM_ASSET_FOLDER(Node.class),
    NODE_FROM_SCENE(Node.class),
    GEOMETRY_FROM_ASSET_FOLDER(Geometry.class),
    GEOMETRY_FROM_SCENE(Geometry.class);

    @NotNull
    private static final EditablePropertyType[] TYPES = values();

    @NotNull
    public static EditablePropertyType valueOf(final int index) {
        return TYPES[index];
    }

    /**
     * The list of available classes for this type.
     */
    @NotNull
    private final Class<?>[] types;

    EditablePropertyType(@NotNull final Class<?>... types) {
        this.types = types;
    }

    /**
     * Get the list of available classes for this type.
     *
     * @return the list of available classes for this type.
     */
    @NotNull
    public Class<?>[] getTypes() {
        return types;
    }
}
