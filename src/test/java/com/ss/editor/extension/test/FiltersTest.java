package com.ss.editor.extension.test;

import com.ss.editor.extension.scene.filter.impl.*;
import org.junit.jupiter.api.Test;

/**
 * @author JavaSaBr
 */
class FiltersTest extends PropertyTest {

    @Test
    void cartoonEdgeFilterTest() {
        final EditableCartoonEdgeFilter cartoonEdgeFilter = new EditableCartoonEdgeFilter();
        testProperties(cartoonEdgeFilter.getEditableProperties());
    }

    @Test
    void colorOverlayFilterTest() {
        final EditableColorOverlayFilter colorOverlayFilter = new EditableColorOverlayFilter();
        testProperties(colorOverlayFilter.getEditableProperties());
    }

    @Test
    void depthOfFieldFilterTest() {
        final EditableDepthOfFieldFilter depthOfFieldFilter = new EditableDepthOfFieldFilter();
        testProperties(depthOfFieldFilter.getEditableProperties());
    }

    @Test
    void fogFilterTest() {
        final EditableFogFilter fogFilter = new EditableFogFilter();
        testProperties(fogFilter.getEditableProperties());
    }

    @Test
    void fXAAFilterTest() {
        final EditableFXAAFilter fxaaFilter = new EditableFXAAFilter();
        testProperties(fxaaFilter.getEditableProperties());
    }

    @Test
    void objectsBloomFilterTest() {
        final EditableObjectsBloomFilter objectsBloomFilter = new EditableObjectsBloomFilter();
        testProperties(objectsBloomFilter.getEditableProperties());
    }

    @Test
    void posterizationFilterTest() {
        final EditablePosterizationFilter posterizationFilter = new EditablePosterizationFilter();
        testProperties(posterizationFilter.getEditableProperties());
    }

    @Test
    void radialBlurFilterTest() {
        final EditableRadialBlurFilter radialBlurFilter = new EditableRadialBlurFilter();
        testProperties(radialBlurFilter.getEditableProperties());
    }

    @Test
    void sceneAndObjectsBloomFilterTest() {
        final EditableSceneAndObjectsBloomFilter sceneAndObjectsBloomFilter = new EditableSceneAndObjectsBloomFilter();
        testProperties(sceneAndObjectsBloomFilter.getEditableProperties());
    }

    @Test
    void sceneBloomFilterTest() {
        final EditableSceneBloomFilter sceneBloomFilter = new EditableSceneBloomFilter();
        testProperties(sceneBloomFilter.getEditableProperties());
    }

    @Test
    void toneMapFilterTest() {
        final EditableToneMapFilter toneMapFilter = new EditableToneMapFilter();
        testProperties(toneMapFilter.getEditableProperties());
    }

    @Test
    void waterFilterTest() {
        final EditableWaterFilter waterFilter = new EditableWaterFilter();
        testProperties(waterFilter.getEditableProperties());
    }

    @Test
    void localWaterFilterTest() {
        final EditableLocalWaterFilter localWaterFilter = new EditableLocalWaterFilter();
        testProperties(localWaterFilter.getEditableProperties());
    }
}
