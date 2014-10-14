package com.mefistofelerion.justrun;

import java.util.ArrayList;

public class PaceNotifier implements StepListener {

    public interface Listener {
        public void paceChanged(int value);
        public void passValue();
    }
    private ArrayList<Listener> mListeners = new ArrayList<Listener>();
    
    int mCounter = 0;
    
    private long mLastStepTime = 0;
    private long[] mLastStepDeltas = {-1, -1, -1, -1};//
    private int mLastStepDeltasIndex = 0;
    private long mPace = 0;


    public PaceNotifier() {
    }

	//calculate the paces / minute, useful to check other features like distances and speed
    public void setPace(int pace) {
        mPace = pace;
        int avg = (int)(60*1000.0 / mPace);
        for (int i = 0; i < mLastStepDeltas.length; i++) {
            mLastStepDeltas[i] = avg;
        }
        notifyListener();
    }

    public void addListener(Listener l) {
        mListeners.add(l);
    }

    public void onStep() {
        long thisStepTime = System.currentTimeMillis();
        mCounter ++;
        
        // Calculate pace based on last x steps
        if (mLastStepTime > 0) {
            long delta = thisStepTime - mLastStepTime;
            
            mLastStepDeltas[mLastStepDeltasIndex] = delta;
            mLastStepDeltasIndex = (mLastStepDeltasIndex + 1) % mLastStepDeltas.length;
            
            long sum = 0;
            boolean isMeaningful = true;
            for (long mLastStepDelta : mLastStepDeltas) {
                if (mLastStepDelta < 0) {
                    isMeaningful = false;
                    break;
                }
                sum += mLastStepDelta;
            }
            if (isMeaningful && sum > 0) {
                long avg = sum / mLastStepDeltas.length;
                mPace = 60*1000 / avg;
            }
            else {
                mPace = -1;
            }
        }
        mLastStepTime = thisStepTime;
        notifyListener();
    }
    
    private void notifyListener() {
        for (Listener listener : mListeners) {
            listener.paceChanged((int)mPace);
        }
    }
    
    public void passValue() {
       
    }
}

