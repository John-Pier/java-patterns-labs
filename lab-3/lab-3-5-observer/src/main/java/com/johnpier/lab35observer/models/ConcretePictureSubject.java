package com.johnpier.lab35observer.models;

import java.util.ArrayList;

public class ConcretePictureSubject implements PictureSubject {

    private final ArrayList<PictureObserver> pictureObservers;

    public ConcretePictureSubject() {
        pictureObservers = new ArrayList<>();
    }

    @Override
    public PictureSubscription subscribe(PictureObserver observer) {
        pictureObservers.add(observer);
        return () -> this.pictureObservers.remove(observer);
    }

    @Override
    public void next(PictureAction action) {
        for (PictureObserver observer : pictureObservers) {
            observer.update(action);
        }
    }
}
