package ru.alepar.forza.android.ui.core;

import android.view.View;

public interface ViewFactory {

    <V extends View> V inflate(Class<V> clazz, int resourceId);

}
