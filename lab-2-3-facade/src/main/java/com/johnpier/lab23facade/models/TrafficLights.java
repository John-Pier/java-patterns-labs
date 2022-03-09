package com.johnpier.lab23facade.models;

public class TrafficLights {
    public long timeout = 1000;

    private TrafficLightsActionHandler handler;

    public boolean isStarted() {
        return isStarted;
    }

    private boolean isStarted = false;

    public void setHandler(TrafficLightsActionHandler handler) {
        this.handler = handler;
    }

    public void stop() {
        isStarted = false;
    }

    public void start() {
        if (this.handler == null) {
            return;
        }
        new Thread(() -> {
            this.isStarted = true;
            var thread = Thread.currentThread();
            try {
                while (isStarted && !thread.isInterrupted()) {
                    synchronized (this) {
                        this.handler.doAction(TrafficLightsActions.TO_GREEN);
                        wait(this.timeout);
                        this.handler.doAction(TrafficLightsActions.TO_YELLOW);
                        wait(this.timeout);
                        this.handler.doAction(TrafficLightsActions.TO_RED);
                        wait(this.timeout);
                        this.handler.doAction(TrafficLightsActions.TO_YELLOW);
                        wait(this.timeout);
                    }
                }
            } catch (InterruptedException e) {
                this.isStarted = false;
                e.printStackTrace();
            }
        }).start();
    }

}
