package ru.alepar.forza.android;

import android.view.View;
import android.view.ViewGroup;

public interface ViewFactory {

    <V extends View> V inflate(Class<V> clazz, int resourceId);

}
