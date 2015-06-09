package com.jhindin.phonotone;

import android.app.Application;
import android.os.Handler;

import org.billthefarmer.mididriver.GeneralMidiConstants;
import org.billthefarmer.mididriver.MidiDriver;
import org.billthefarmer.mididriver.MidiConstants;

public class PhonotoneApp extends Application implements MidiDriver.OnMidiStartListener  {
    MidiDriver midiDriver;
    Handler handler;
    int nActivities;
    boolean midiStarted = false;
    boolean midiStartAcknowledged = false;

    byte currentInstrument = (byte)6;

    @Override
    public void onCreate() {
        super.onCreate();

        midiDriver = new MidiDriver();
        midiDriver.setOnMidiStartListener(this);
        handler = new Handler();
    }

    public synchronized void activityPaused()
    {
        nActivities--;
        if (nActivities == 0 && midiStarted) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (nActivities == 0 && midiStarted) {
                        synchronized (PhonotoneApp.this) {
                            midiDriver.stop();
                            midiStarted = false;
                            midiStartAcknowledged = false;
                        }
                    }
                }
            }, 500);

        }
    }

    public synchronized void activityResumed() {
        nActivities++;
        if (!midiStarted) {
            midiDriver.start();
            midiStarted = true;
        }
    }

    public void onMidiStart() {
        midiStartAcknowledged = true;
        sendMidi(MidiConstants.PROGRAM_CHANGE, GeneralMidiConstants.ACOUSTIC_GRAND_PIANO);
    }

    public void sendMidi(byte...args)
    {
        if (midiStartAcknowledged)
            midiDriver.queueEvent(args);
    }






}
