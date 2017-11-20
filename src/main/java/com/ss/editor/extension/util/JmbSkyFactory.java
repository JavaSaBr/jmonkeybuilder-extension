package com.ss.editor.extension.util;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingSphere;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Image;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.TextureCubeMap;
import com.jme3.util.SkyFactory;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * <code>SkyFactory</code> is used to create jME {@link Spatial}s that can
 * be attached to the scene to display a sky image in the background.
 *
 * @author Kirill Vainer, JavaSaBr
 */
public class JmbSkyFactory {

    /**
     * Create a sky with radius=10 using the given cubemap or spheremap texture.
     * <p>
     * For the sky to be visible, its radius must fall between the near and far
     * planes of the camera's frustrum.
     *
     * @param assetManager from which to load materials
     * @param texture      to use
     * @param normalScale  The normal scale is multiplied by the 3D normal to get
     *                     a texture coordinate. Use Vector3f.UNIT_XYZ to not apply and
     *                     transformation to the normal.
     * @param envMapType   see {@link SkyFactory.EnvMapType}
     * @return a new spatial representing the sky, ready to be attached to the
     * scene graph
     */
    public static @NotNull Spatial createSky(@NotNull final AssetManager assetManager, @NotNull final Texture texture,
                                             @NotNull final Vector3f normalScale,
                                             @NotNull final SkyFactory.EnvMapType envMapType) {
        return createSky(assetManager, texture, normalScale, envMapType, 10);
    }

    /**
     * Create a sky using the given cubemap or spheremap texture.
     *
     * @param assetManager from which to load materials
     * @param texture      to use
     * @param normalScale  The normal scale is multiplied by the 3D normal to get
     *                     a texture coordinate. Use Vector3f.UNIT_XYZ to not apply and
     *                     transformation to the normal.
     * @param envMapType   see {@link SkyFactory.EnvMapType}
     * @param sphereRadius the sky sphere's radius: for the sky to be visible,
     *                     its radius must fall between the near and far planes of the camera's
     *                     frustrum
     * @return a new spatial representing the sky, ready to be attached to the
     * scene graph
     */
    public static @NotNull Spatial createSky(@NotNull final AssetManager assetManager, @NotNull Texture texture,
                                             @NotNull final Vector3f normalScale,
                                             @NotNull final SkyFactory.EnvMapType envMapType, float sphereRadius) {

        final Sphere sphereMesh = new Sphere(10, 10, sphereRadius, false, true);

        final Geometry sky = new Geometry("Sky", sphereMesh);
        sky.setQueueBucket(Bucket.Sky);
        sky.setCullHint(Spatial.CullHint.Never);
        sky.setModelBound(new BoundingSphere(Float.POSITIVE_INFINITY, Vector3f.ZERO));

        final Material skyMat = new Material(assetManager, "com/ss/editor/extension/shader/sky/Sky.j3md");
        skyMat.setVector3("NormalScale", normalScale);

        switch (envMapType) {
            case CubeMap:
                // make sure its a cubemap
                if (!(texture instanceof TextureCubeMap)) {
                    Image img = texture.getImage();
                    texture = new TextureCubeMap();
                    texture.setImage(img);
                }
                break;
            case SphereMap:
                skyMat.setBoolean("SphereMap", true);
                break;
            case EquirectMap:
                skyMat.setBoolean("EquirectMap", true);
                break;
        }

        texture.setMagFilter(Texture.MagFilter.Bilinear);
        texture.setMinFilter(Texture.MinFilter.BilinearNoMipMaps);
        texture.setAnisotropicFilter(0);
        texture.setWrap(Texture.WrapMode.EdgeClamp);

        if (texture instanceof TextureCubeMap) {
            skyMat.setTexture("Texture", texture);
        } else {
            skyMat.setTexture("SimpleTexture", texture);
        }

        sky.setMaterial(skyMat);

        return sky;
    }

    private static void checkImage(@NotNull final Image image) {

        if (image.getWidth() != image.getHeight()) {
            throw new IllegalArgumentException("Image width and height must be the same");
        }

        if (image.getMultiSamples() != 1) {
            throw new IllegalArgumentException("Multisample textures not allowed");
        }
    }

    private static void checkImagesForCubeMap(@NotNull final Image... images) {

        if (images.length == 1) {
            return;
        }

        Format fmt = images[0].getFormat();
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        ByteBuffer data = images[0].getData(0);
        int size = data != null ? data.capacity() : 0;

        checkImage(images[0]);

        for (int i = 1; i < images.length; i++) {
            Image image = images[i];
            checkImage(images[i]);
            if (image.getFormat() != fmt) {
                throw new IllegalArgumentException("Images must have same format");
            }
            if (image.getWidth() != width || image.getHeight() != height) {
                throw new IllegalArgumentException("Images must have same resolution");
            }
            ByteBuffer data2 = image.getData(0);
            if (data2 != null) {
                if (data2.capacity() != size) {
                    throw new IllegalArgumentException("Images must have same size");
                }
            }
        }
    }

    /**
     * Create a cube-mapped sky with radius=10 using six textures.
     * <p>
     * For the sky to be visible, its radius must fall between the near and far
     * planes of the camera's frustrum.
     *
     * @param assetManager from which to load materials
     * @param west         texture for the western face of the cube
     * @param east         texture for the eastern face of the cube
     * @param north        texture for the northern face of the cube
     * @param south        texture for the southern face of the cube
     * @param up           texture for the top face of the cube
     * @param down         texture for the bottom face of the cube
     * @param normalScale  The normal scale is multiplied by the 3D normal to get
     *                     a texture coordinate. Use Vector3f.UNIT_XYZ to not apply and
     *                     transformation to the normal.
     * @return a new spatial representing the sky, ready to be attached to the
     * scene graph
     */
    public static @NotNull Spatial createSky(@NotNull final AssetManager assetManager, @NotNull final Texture west,
                                             @NotNull final Texture east, @NotNull final Texture north,
                                             @NotNull final Texture south, @NotNull final Texture up,
                                             @NotNull final Texture down, @NotNull final Vector3f normalScale) {
        return createSky(assetManager, west, east, north, south, up, down, normalScale, 10);
    }

    /**
     * Create a cube-mapped sky using six textures.
     *
     * @param assetManager from which to load materials
     * @param west         texture for the western face of the cube
     * @param east         texture for the eastern face of the cube
     * @param north        texture for the northern face of the cube
     * @param south        texture for the southern face of the cube
     * @param up           texture for the top face of the cube
     * @param down         texture for the bottom face of the cube
     * @param normalScale  The normal scale is multiplied by the 3D normal to get
     *                     a texture coordinate. Use Vector3f.UNIT_XYZ to not apply and
     *                     transformation to the normal.
     * @param sphereRadius the sky sphere's radius: for the sky to be visible,
     *                     its radius must fall between the near and far planes of the camera's
     *                     frustrum
     * @return a new spatial representing the sky, ready to be attached to the
     * scene graph
     */
    public static @NotNull Spatial createSky(@NotNull final AssetManager assetManager, @NotNull final Texture west,
                                             @NotNull final Texture east, @NotNull final Texture north,
                                             @NotNull final Texture south, @NotNull final Texture up,
                                             @NotNull final Texture down, @NotNull final Vector3f normalScale,
                                             float sphereRadius) {

        Image westImg = west.getImage();
        Image eastImg = east.getImage();
        Image northImg = north.getImage();
        Image southImg = south.getImage();
        Image upImg = up.getImage();
        Image downImg = down.getImage();

        checkImagesForCubeMap(westImg, eastImg, northImg, southImg, upImg, downImg);

        Image cubeImage = new Image(westImg.getFormat(), westImg.getWidth(), westImg.getHeight(), null, westImg.getColorSpace());
        cubeImage.addData(westImg.getData(0));
        cubeImage.addData(eastImg.getData(0));
        cubeImage.addData(downImg.getData(0));
        cubeImage.addData(upImg.getData(0));
        cubeImage.addData(southImg.getData(0));
        cubeImage.addData(northImg.getData(0));

        TextureCubeMap cubeMap = new TextureCubeMap(cubeImage);
        return createSky(assetManager, cubeMap, normalScale, SkyFactory.EnvMapType.CubeMap, sphereRadius);
    }
}
