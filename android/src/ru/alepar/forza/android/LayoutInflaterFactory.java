package ru.alepar.forza.android;

import android.view.LayoutInflater;
import android.view.View;

public class LayoutInflaterFactory implements ViewFactory {

    private final LayoutInflater inflater;

    public LayoutInflaterFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public <V extends View> V inflate(Class<V> clazz, int resourceId) {
        return clazz.cast(inflater.inflate(resourceId, null));
    }

}
