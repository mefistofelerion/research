package com.mefistofelerion.justrun;

public class CaloriesNotifier implements StepListener {

    public interface Listener {
        public void valueChanged(float value);

        public void passValue();
    }

    private Listener listener;

    private static double METRIC_RUNNING_FACTOR = 1.02784823;
    private static double METRIC_WALKING_FACTOR = 0.708;

    private double calories = 0;

    boolean isRunning;
    float stepLength;
    float bodyWeight;

    public CaloriesNotifier(Listener listener) {
        listener = listener;
    }

    public void setCalories(float calories) {
        this.calories = calories;
        notifyListener();
    }

    public void resetValues() {
        calories = 0;
    }

    public void setStepLength(float stepLength) {
        this.stepLength = stepLength;
    }

    public void onStep() {


        calories +=
                (bodyWeight * (isRunning ? METRIC_RUNNING_FACTOR : METRIC_WALKING_FACTOR)) * stepLength / 100000.0; // centimeters/kilometer

        notifyListener();
    }

    private void notifyListener() {
        listener.valueChanged((float) calories);
    }

    public void passValue() {

    }

}

