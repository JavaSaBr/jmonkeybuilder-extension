package com.ss.editor.extension.scene;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import org.jetbrains.annotations.NotNull;

/**
 * The interface to implement presentation of some object in a scene.
 *
 * @author JavaSaBr
 */
public interface ScenePresentable {

    enum PresentationType {
        SPHERE,
        BOX
    }

    /**
     * Get the current location of this object.
     *
     * @return the current location.
     */
    @NotNull Vector3f getLocation();

    /**
     * Get the current rotation of this object.
     *
     * @return the current rotation.
     */
    @NotNull Quaternion getRotation();

    /**
     * Get the current scale of this object.
     *
     * @return the current scale.
     */
    @NotNull Vector3f getScale();

    /**
     * Set the new location of this object.
     *
     * @param location the new location.
     */
    void setLocation(@NotNull Vector3f location);

    /**
     * Set the new scale of this object.
     *
     * @param scale the new scale.
     */
    void setScale(@NotNull Vector3f scale);

    /**
     * Set the new rotation of this object.
     *
     * @param rotation the new rotation.
     */
    void setRotation(@NotNull Quaternion rotation);

    /**
     * Get the type of presentation of this object.
     *
     * @return the type of presentation.
     */
    @NotNull PresentationType getPresentationType();
}
