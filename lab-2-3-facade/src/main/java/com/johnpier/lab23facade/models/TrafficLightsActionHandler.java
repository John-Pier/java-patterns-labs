package com.johnpier.lab23facade.models;

@FunctionalInterface
public interface TrafficLightsActionHandler {
    void doAction(TrafficLightsActions action);
}
