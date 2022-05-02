package com.johnpier.lab35observer.models;

public interface PictureSubject {
    void subscribe(PictureObserver observer);

    void unsubscribe();

    void next();
}
