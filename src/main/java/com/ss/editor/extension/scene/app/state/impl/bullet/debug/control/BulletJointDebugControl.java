/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ss.editor.extension.scene.app.state.impl.bullet.debug.control;

import com.jme3.bullet.joints.PhysicsJoint;
import com.jme3.math.Quaternion;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.ss.editor.extension.scene.app.state.impl.bullet.debug.BulletDebugAppState;

/**
 * @author normenhansen
 */
public class BulletJointDebugControl extends AbstractPhysicsDebugControl {

    protected final PhysicsJoint body;
    protected final Geometry geomA;
    protected final Arrow arrowA;
    protected final Geometry geomB;
    protected final Arrow arrowB;
    protected final Transform a = new Transform(new Vector3f(), new Quaternion());
    protected final Transform b = new Transform(new Vector3f(), new Quaternion());
    protected final Vector3f offA = new Vector3f();
    protected final Vector3f offB = new Vector3f();

    public BulletJointDebugControl(BulletDebugAppState debugAppState, PhysicsJoint body) {
        super(debugAppState);
        this.body = body;
        this.geomA = new Geometry(body.toString());
        arrowA = new Arrow(Vector3f.ZERO);
        geomA.setMesh(arrowA);
        //  geomA.setMaterial(debugAppState.DEBUG_GREEN);
        this.geomB = new Geometry(body.toString());
        arrowB = new Arrow(Vector3f.ZERO);
        geomB.setMesh(arrowB);
        // geomB.setMaterial(debugAppState.DEBUG_GREEN);
    }

    @Override
    public void setSpatial(Spatial spatial) {
        if (spatial != null && spatial instanceof Node) {
            Node node = (Node) spatial;
            node.attachChild(geomA);
            node.attachChild(geomB);
        } else if (spatial == null && this.spatial != null) {
            Node node = (Node) this.spatial;
            node.detachChild(geomA);
            node.detachChild(geomB);
        }
        super.setSpatial(spatial);
    }

    @Override
    protected void controlUpdate(float tpf) {
        body.getBodyA()
            .getPhysicsLocation(a.getTranslation());
        body.getBodyA()
            .getPhysicsRotation(a.getRotation());

        body.getBodyB()
            .getPhysicsLocation(b.getTranslation());
        body.getBodyB()
            .getPhysicsRotation(b.getRotation());

        geomA.setLocalTransform(a);
        geomB.setLocalTransform(b);

        arrowA.setArrowExtent(body.getPivotA());
        arrowB.setArrowExtent(body.getPivotB());
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
