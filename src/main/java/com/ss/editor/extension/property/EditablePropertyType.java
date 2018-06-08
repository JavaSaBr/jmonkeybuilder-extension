package com.ss.editor.extension.property;

import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture2D;
import com.ss.editor.extension.scene.SceneLayer;
import javafx.scene.AmbientLight;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

/**
 * The enum with list of editable property types.
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
    GEOMETRY_FROM_SCENE(Geometry.class),
    AWT_FONT(java.awt.Font.class),
    FX_FONT(Font.class),
    STRING_FROM_LIST(String.class),
    OBJECT_FROM_LIST(Object.class),
    SEPARATOR(Void.class),
    FILE_FROM_ASSET_FOLDER(Path.class),
    FOLDER_FROM_ASSET_FOLDER(Path.class),
    RESOURCE_FROM_CLASSPATH(String.class),
    READ_ONLY_STRING(String.class),
    EXTERNAL_FILE(Path.class),
    LIGHT_FROM_SCENE(Light.class),
    AMBIENT_LIGHT_FROM_SCENE(AmbientLight.class),
    FILTER_FROM_SCENE(Filter.class),
    SCENE_LAYER(SceneLayer.class),
    MIN_MAX_2F(Vector2f.class),;

    private static final EditablePropertyType[] TYPES = values();

    /**
     * Get the the property type by the index.
     *
     * @param index the index.
     * @return the property type.
     */
    public static @NotNull EditablePropertyType valueOf(int index) {
        return TYPES[index];
    }

    /**
     * The list of available classes for this type.
     */
    @NotNull
    private final Class<?>[] types;

    EditablePropertyType(@NotNull Class<?>... types) {
        this.types = types;
    }

    /**
     * Get the list of available classes for this type.
     *
     * @return the list of available classes for this type.
     */
    public @NotNull Class<?>[] getTypes() {
        return types;
    }
}
