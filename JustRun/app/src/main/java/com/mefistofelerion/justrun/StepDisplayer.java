package com.mefistofelerion.justrun;

import java.util.ArrayList;

/**
 * Created by ivan on 29/06/14.
 */
public class StepDisplayer implements StepListener {

    private int count = 0;
    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    public StepDisplayer() {

    }

    public void setSteps(int steps) {
        this.count = steps;
        notifyListener();
    }

    public void reloadSettings() {
        notifyListener();
    }

    @Override
    public void onStep() {
        this.count++;
        notifyListener();
    }

    @Override
    public void passValue() {

    }

    public void addListener(Listener l) {
        listeners.add(l);
    }

    private void notifyListener() {
        for (Listener listener : listeners) {
            listener.stepsChanged((int) count);
        }
    }

    public interface Listener {
        public void stepsChanged(int value);

        public void passValue();
    }
}
