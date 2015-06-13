package com.jhindin.phonotone;

import android.app.Application;
import android.os.Handler;

import org.billthefarmer.mididriver.GeneralMidiConstants;
import org.billthefarmer.mididriver.MidiDriver;
import org.billthefarmer.mididriver.MidiConstants;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PhonotoneApp extends Application implements MidiDriver.OnMidiStartListener  {
    MidiDriver midiDriver;
    Handler handler;
    int nActivities;
    boolean midiStarted = false;
    boolean midiStartAcknowledged = false;

    PriorityQueue<ScheduledMidiEvent> eventQueue = new PriorityQueue<>();

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

    public void scheduleEvent(long delay, byte...eventBytes) {
        ScheduledMidiEvent futureEvent = new ScheduledMidiEvent(delay, eventBytes);

    }


    class ScheduledMidiEvent implements Comparable<ScheduledMidiEvent> {
        ScheduledMidiEvent(long delay, byte...eventBytes) {
            time = System.currentTimeMillis() + delay;
            event = eventBytes;
        }

        long time;

        byte event[];

        @Override
        public int compareTo(ScheduledMidiEvent another) {
            return this.time < another.time ? -1 :
                    (this.time >another.time ? 1 : 0);

        }
    }



}
