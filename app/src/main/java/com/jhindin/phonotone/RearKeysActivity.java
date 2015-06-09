package com.jhindin.phonotone;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jhindin.phonotone.views.RearKeyView;
import com.jhindin.phonotone.views.ToneListener;

import org.billthefarmer.mididriver.MidiConstants;

public class RearKeysActivity extends AppCompatActivity implements ToneListener {

    PhonotoneApp app;
    boolean jazzHandSupported = true;
    RearKeyView keyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (PhonotoneApp)getApplication();

        PackageManager pm = getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND))
        {
            jazzHandSupported = false;

            AlertDialog.Builder ab =  new AlertDialog.Builder(this);
            ab.setTitle("No multitouch");

            ab.show();
            return;
        }

        keyView = (RearKeyView) findViewById(R.id.rear_key);
        keyView.addToneListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        app.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        app.activityPaused();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void keyPressed(View source, int musicKey, float pressure) {
        int iPressure = (int)(pressure * 128);
        if (iPressure > 128)
            iPressure = 128;

        app.sendMidi(MidiConstants.NOTE_ON, (byte)(musicKey + 32), (byte)(iPressure - 1));
    }

    @Override
    public void keyReleased(View source, int musicKey) {
        app.sendMidi(MidiConstants.NOTE_OFF, (byte)(musicKey + 32), (byte)63);

    }

    @Override
    public void pressureChanged(View source, int musicKey, float pressure) {
        //0app.sendMidi(MidiConstants.NOTE_ON, (byte)(musicKey + 32), (byte)(pressure * 128));
    }

}
