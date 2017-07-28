package com.ss.editor.extension.property;

import org.jetbrains.annotations.NotNull;

/**
 * The enum with list editable property types.
 *
 * @author JavaSaBr
 */
public enum EditablePropertyType {
    BOOLEAN,
    INTEGER,
    FLOAT,
    VECTOR_2F,
    VECTOR_3F,
    QUATERNION,
    COLOR,
    ENUM,
    STRING,
    TEXTURE_2D,
    DIRECTION_LIGHT_FROM_SCENE,
    POINT_LIGHT_FROM_SCENE,
    SPATIAL_FROM_ASSET_FOLDER,
    SPATIAL_FROM_SCENE,
    NODE_FROM_ASSET_FOLDER,
    NODE_FROM_SCENE,
    GEOMETRY_FROM_ASSET_FOLDER,
    GEOMETRY_FROM_SCENE;

    @NotNull
    private static final EditablePropertyType[] TYPES = values();

    @NotNull
    public static EditablePropertyType valueOf(final int index) {
        return TYPES[index];
    }
}
