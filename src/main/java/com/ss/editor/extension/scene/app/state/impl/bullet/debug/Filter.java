package com.ss.editor.extension.scene.app.state.impl.bullet.debug;

import org.jetbrains.annotations.NotNull;

public interface Filter {

    boolean test(@NotNull final Object object);
}
