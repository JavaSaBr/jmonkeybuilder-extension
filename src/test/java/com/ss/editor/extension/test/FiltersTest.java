package com.ss.editor.extension.test;

import com.ss.editor.extension.scene.filter.impl.*;
import org.junit.jupiter.api.Test;

/**
 * The test to test all editable filters.
 *
 * @author JavaSaBr
 */
class FiltersTest extends PropertyTest {

    @Test
    void cartoonEdgeFilterTest() {
        final EditableCartoonEdgeFilter filter = new EditableCartoonEdgeFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void colorOverlayFilterTest() {
        final EditableColorOverlayFilter filter = new EditableColorOverlayFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void depthOfFieldFilterTest() {
        final EditableDepthOfFieldFilter filter = new EditableDepthOfFieldFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void fogFilterTest() {
        final EditableFogFilter filter = new EditableFogFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void fXAAFilterTest() {
        final EditableFXAAFilter filter = new EditableFXAAFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void objectsBloomFilterTest() {
        final EditableObjectsBloomFilter filter = new EditableObjectsBloomFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void posterizationFilterTest() {
        final EditablePosterizationFilter filter = new EditablePosterizationFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void radialBlurFilterTest() {
        final EditableRadialBlurFilter filter = new EditableRadialBlurFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void sceneAndObjectsBloomFilterTest() {
        final EditableSceneAndObjectsBloomFilter filter = new EditableSceneAndObjectsBloomFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void sceneBloomFilterTest() {
        final EditableSceneBloomFilter filter = new EditableSceneBloomFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void toneMapFilterTest() {
        final EditableToneMapFilter filter = new EditableToneMapFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void waterFilterTest() {
        final EditableWaterFilter filter = new EditableWaterFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void localWaterFilterTest() {
        final EditableLocalWaterFilter filter = new EditableLocalWaterFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void directionalLightShadowFilterTest() {
        final EditableDirectionalLightShadowFilter filter = new EditableDirectionalLightShadowFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void directionLightFromSceneShadowFilterTest() {
        final EditableDirectionLightFromSceneShadowFilter filter = new EditableDirectionLightFromSceneShadowFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void pointLightShadowFilterTest() {
        final EditablePointLightShadowFilter filter = new EditablePointLightShadowFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void pointLightFromSceneShadowFilterTest() {
        final EditablePointLightFromSceneShadowFilter filter = new EditablePointLightFromSceneShadowFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void localWaterWithDirectionLightFilterTest() {
        final EditableLocalWaterWithDirectionLightFilter filter = new EditableLocalWaterWithDirectionLightFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void localWaterWithLightingStateFilterTest() {
        final EditableLocalWaterWithLightingStateFilter filter = new EditableLocalWaterWithLightingStateFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void waterWithDirectionLightFilterTest() {
        final EditableWaterWithDirectionLightFilter filter = new EditableWaterWithDirectionLightFilter();
        testProperties(filter.getEditableProperties());
    }

    @Test
    void waterWithLightingStateFilterTest() {
        final EditableWaterWithLightingStateFilter filter = new EditableWaterWithLightingStateFilter();
        testProperties(filter.getEditableProperties());
    }
}
