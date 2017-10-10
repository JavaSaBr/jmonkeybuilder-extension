package com.ss.editor.extension.test;

import com.jme3.app.SimpleApplication;
import com.ss.editor.extension.loader.SceneLoader;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;

/**
 * The base test.
 *
 * @author JavaSaBr
 */
public class SetUpTest {

    @NotNull
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private static SimpleApplication application = null;

    @NotNull
    protected static synchronized SimpleApplication getApplication() {

        if (application == null) {

            application = new SimpleApplication() {
                @Override
                public void simpleInitApp() {
                    COUNT_DOWN_LATCH.countDown();
                }
            };
            application.setShowSettings(false);

            final Thread appThread = new Thread(() -> application.start());
            appThread.setDaemon(true);
            appThread.start();

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
