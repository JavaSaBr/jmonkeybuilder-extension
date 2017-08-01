package com.ss.editor.extension.test;

import com.jme3.app.SimpleApplication;
import com.simsilica.lemur.demo.BasicDemo;
import com.ss.editor.extension.loader.SceneLoader;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;

/**
 * @author JavaSaBr
 */
public class SetUpTest {

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private static SimpleApplication application = null;

    private static synchronized SimpleApplication getApplication() {


        if (application == null) {

            application = new BasicDemo() {
                @Override
                public void simpleInitApp() {
                    super.simpleInitApp();
                    COUNT_DOWN_LATCH.countDown();
                }
            };

            application.setShowSettings(false);

            new Thread(() -> application.start()).start();

            try {
                COUNT_DOWN_LATCH.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SceneLoader.install(application);
        }

        return application;
    }


    @BeforeAll
    public static void setUpLoaderTest() {
        getApplication();
    }
}
