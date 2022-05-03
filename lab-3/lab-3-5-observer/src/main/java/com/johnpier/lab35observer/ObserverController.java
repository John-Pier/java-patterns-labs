package com.johnpier.lab35observer;

import com.johnpier.lab35observer.models.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class ObserverController {

    @FXML
    private Node eyeOne;

    @FXML
    private Node eyeTwo;

    @FXML
    private Shape nose;

    @FXML
    private Node lips;

    private final ArrayList<PictureSubscription> subscriptions = new ArrayList<>(4);

    private final PictureSubject subject = new ConcretePictureSubject();

    public void initObservers() {
        subscriptions.add(subject.subscribe(new NosePictureObserver(nose)));
        subscriptions.add(subject.subscribe(new LipsPictureObserver(lips)));
        subscriptions.add(subject.subscribe(new EyePictureObserver(eyeOne, PictureAction.EYE_ONE)));
        subscriptions.add(subject.subscribe(new EyePictureObserver(eyeTwo, PictureAction.EYE_TWO)));
    }

    @FXML
    protected void onEyeOneClick() {
        subject.next(PictureAction.EYE_ONE);
    }

    @FXML
    protected void onEyeTwoClick() {
        subject.next(PictureAction.EYE_TWO);
    }

    @FXML
    protected void onNoseClick() {
        subject.next(PictureAction.NOSE);
    }

    @FXML
    protected void onLipsClick() {
        subject.next(PictureAction.LIPS);
    }

    @FXML
    protected void onUnsubscribeClick() {
        for (PictureSubscription subscription : subscriptions) {
            subscription.unsubscribe();
        }
        subscriptions.clear();
    }
}