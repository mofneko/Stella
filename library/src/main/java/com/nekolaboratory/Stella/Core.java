package com.nekolaboratory.Stella;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

import static android.os.AsyncTask.Status.RUNNING;

/**
 * @author Yusuke Arakawa
 */

public class Core {

    static {
        System.loadLibrary("Stella");
    }

    private StellaCallback callback;
    private boolean detectionEnable;
    private int loopDeley;
    private SpeedHackDetectionTask speedHackDetectionTask;

    private Core() {
    }

    public Core(int loopDeley, StellaCallback callback) {
        setLoopDeley(loopDeley);
        setCallback(callback);
    }

    public native boolean checkSpeedHack();

    public int getLoopDeley() {
        return loopDeley;
    }

    public void setLoopDeley(int loopDeley) {
        this.loopDeley = loopDeley;
    }

    public boolean isDetectionEnable() {
        return detectionEnable;
    }

    public StellaCallback getCallback() {
        return callback;
    }

    public void setCallback(StellaCallback callback) {
        this.callback = callback;
    }

    public void start() {
        if (!isDetectionEnable()) {
            detectionEnable = true;
            startDetection();
        }
    }

    public void stop() {
        if (isDetectionEnable()) {
            detectionEnable = false;
            speedHackDetectionTask.cancel(true);
        }
    }

    private void startDetection() {
        if (speedHackDetectionTask != null && speedHackDetectionTask.getStatus() == RUNNING) {
            return;
        }
        speedHackDetectionTask = new SpeedHackDetectionTask(new SpeedHackDetectionCallBack() {
            @Override
            public void complete() {
                if (isDetectionEnable()) {
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            startDetection();
                        }
                    }, getLoopDeley());
                }
            }
        });
        speedHackDetectionTask.execute();
    }

    interface SpeedHackDetectionCallBack {
        void complete();
    }

    class SpeedHackDetectionTask extends AsyncTask<Void, Void, Boolean> {

        private SpeedHackDetectionCallBack speedHackDetectionCallBack;

        public SpeedHackDetectionTask(SpeedHackDetectionCallBack speedHackDetectionCallBack) {
            this.speedHackDetectionCallBack = speedHackDetectionCallBack;
        }

        @Override
        protected void onPreExecute() {
            if (!isDetectionEnable()) {
                this.cancel(true);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return checkSpeedHack();
        }

        @Override
        protected void onPostExecute(Boolean isDetect) {
            if (isDetect) {
                getCallback().onDetect();
            }
            this.speedHackDetectionCallBack.complete();
        }
    }
}
