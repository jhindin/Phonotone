package com.jhindin.phonotone;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import org.billthefarmer.mididriver.GeneralMidiConstants;
import org.billthefarmer.mididriver.MidiDriver;
import org.billthefarmer.mididriver.MidiConstants;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PhonotoneApp extends Application  implements Application.ActivityLifecycleCallbacks {
    MidiDriver midiDriver;
    Handler handler;
    boolean midiStarted = false;

    byte currentInstrument = (byte)6;

    int nActivities = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        midiDriver = new MidiDriver();
        handler = new Handler();
        registerActivityLifecycleCallbacks(this);
    }

    public void sendMidi(byte...args)
    {
        if (midiStarted)
            midiDriver.queueEvent(args);
    }

    public void scheduleEvent(long delay, byte...eventBytes) {
        ScheduledMidiEvent futureEvent = new ScheduledMidiEvent(eventBytes);
        handler.postAtTime(futureEvent, midiDriver, SystemClock.uptimeMillis() + delay);

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public synchronized void  onActivityResumed(Activity activity) {
        if (nActivities == 0) {
            midiDriver.start();
            midiStarted = true;
        }
        nActivities++;
    }

    @Override
    public synchronized  void onActivityPaused(Activity activity) {
        nActivities--;
        if (nActivities == 0) {
            handler.removeCallbacksAndMessages(midiDriver);
            for (byte ch = 0; ch < 15; ch++) {
                // All notes off
                sendMidi((byte) (MidiConstants.CONTROL_CHANGE | ch), (byte) 123, (byte) 0);
            }

            midiDriver.stop();
            midiStarted = false;
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    class ScheduledMidiEvent implements Runnable {
        byte event[];

        ScheduledMidiEvent(byte...eventBytes) {
            event = eventBytes;
        }


        @Override
        public void run() {
            sendMidi(event);
        }
    }



}
