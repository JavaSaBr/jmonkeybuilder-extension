package com.ss.editor.extension.util;

import com.jme3.bullet.control.PhysicsControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import org.jetbrains.annotations.NotNull;

/**
 * The utility methods from jMB extension library.
 *
 * @author JavaSaBr
 */
public class JmbExtUtils {

    @NotNull
    public static final SceneGraphVisitor RESET_PHYSICS_VISITOR = new SceneGraphVisitor() {

        @Override
        public void visit(@NotNull Spatial spatial) {
            int numControls = spatial.getNumControls();
            for (int i = 0; i < numControls; i++) {

                Control control = spatial.getControl(i);
                if (!(control instanceof PhysicsControl) || !((PhysicsControl) control).isEnabled()) {
                    continue;
                }

                if (control instanceof RigidBodyControl) {
                    RigidBodyControl bodyControl = (RigidBodyControl) control;
                    boolean kinematic = bodyControl.isKinematic();
                    boolean kinematicSpatial = bodyControl.isKinematicSpatial();
                    bodyControl.setKinematic(true);
                    bodyControl.setKinematicSpatial(true);
                    bodyControl.clearForces();
                    bodyControl.update(0);
                    bodyControl.setKinematic(kinematic);
                    bodyControl.setKinematicSpatial(kinematicSpatial);
                }
            }
        }
    };

    /**
     * Reset physics control's positions in the spatial.
     *
     * @param spatial the spatial.
     */
    public static void resetPhysicsControlPositions(@NotNull Spatial spatial) {
        spatial.depthFirstTraversal(RESET_PHYSICS_VISITOR);
    }
}
