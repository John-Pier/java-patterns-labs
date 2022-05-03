package com.johnpier.lab35observer.models;

public interface PictureSubject {
    PictureSubscription subscribe(PictureObserver observer);

    void next(PictureAction action);
}
