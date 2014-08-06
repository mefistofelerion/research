package com.mefistofelerion.justrun;


public class DistanceNotifier implements StepListener{

    public interface Listener {
        public void valueChanged(float value);
        public void passValue();
    }

    private Listener listener;
    
    float distance = 0;

    float stepLength;

    public DistanceNotifier(Listener listener) {
        this.listener = listener;
    }
    public void setDistance(float distance) {
        this.distance = distance;
        notifyListener();
    }
    
    public void onStep() {
        //convert cm to km
        distance += (float)(stepLength / 100000.0);
        notifyListener();
    }
    
    private void notifyListener() {
        listener.valueChanged(distance);
    }
    
    public void passValue() {
        //just for in case
    }
}

