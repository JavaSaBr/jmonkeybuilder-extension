package com.ss.editor.extension.action;

import com.ss.editor.extension.integration.EditorEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Base implementation of the {@link ModifyingAction}.
 *
 * @param <S> the state's type.
 * @param <O> the object's type.
 */
public abstract class AbstractModifyingAction<S, O> implements ModifyingAction {

    protected static final Object NO_STATE = new Object();

    @Override
    public @NotNull Object redo(@NotNull EditorEnvironment env, @NotNull Object object) {
        return redoImpl(env, (O) object);
    }

    protected abstract @NotNull S redoImpl(@NotNull EditorEnvironment env, @NotNull O object);

    @Override
    public void undo(@NotNull EditorEnvironment env, @NotNull Object object, @NotNull Object prevState) {
        undoImpl(env, (O) object, (S) prevState);
    }

    protected void undoImpl(@NotNull EditorEnvironment env, @NotNull O object, @NotNull S prevState) {
    }
}
