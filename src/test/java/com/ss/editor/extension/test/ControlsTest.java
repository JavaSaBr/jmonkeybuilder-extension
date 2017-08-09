package com.ss.editor.extension.test;

import com.ss.editor.extension.scene.control.impl.EditableBillboardControl;
import org.junit.jupiter.api.Test;

/**
 * The test ot test all editable controls.
 *
 * @author JavaSaBr
 */
class ControlsTest extends PropertyTest {

    @Test
    void billboardControlTest() {
        final EditableBillboardControl control = new EditableBillboardControl();
        testProperties(control.getEditableProperties());
    }
}
