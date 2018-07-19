package se.avelon.jassistant;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AudioRecorder {
    private String TAG = AudioRecorder.class.getSimpleName();
    private final static int SAMPLE_RATE = 11025;

    public AudioRecorder() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
    }

    public void detect() {
        for(int rate : new int[]{8000, 11025, 16000, 22050, 44100}) {
            for(short format : new short[]{AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT}) {
                for(short channel : new short[]{AudioFormat.CHANNEL_IN_MONO, AudioFormat.CHANNEL_IN_STEREO}) {
                    int size = AudioRecord.getMinBufferSize(rate, channel, format);
                    if (size != AudioRecord.ERROR_BAD_VALUE) {
                        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, rate, channel, format, size);
                        if (recorder.getState() == AudioRecord.STATE_INITIALIZED) {
                            Log.e(TAG, "" + rate + "," + format + "," + channel + " - ok");
                        }
                        else {
                            Log.e(TAG, "" + rate + "," + format + "," + channel + " - nok");
                        }
                    }
                    else {
                        Log.e(TAG, "" + rate + "," + format + "," + channel + " - nok");
                    }
                }
            }
        }
    }

    private int buffertSize(int rate) {
        int size = AudioRecord.getMinBufferSize(rate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        Log.e(TAG, "size=" + size);
        return size;
    }

    public void test() {
        MediaRecorder record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.MIC);
        record.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        record.setAudioEncoder(AudioFormat.ENCODING_PCM_16BIT);

        //String s = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM + File.separator + "FILE_NAME";
        String s = "" + Environment.getExternalStoragePublicDirectory("data") + File.separator + "FILE_NAME";
       // record.setAudioChannels(AudioFormat.CHANNEL_IN_MONO);
        //record.setAudioSamplingRate(SAMPLE_RATE);
        record.setOutputFile(new File(s));
        try {
            record.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        record.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        record.stop();
    }
    public byte[] record(int time) {
        int size = buffertSize(SAMPLE_RATE);
        byte[] data = new byte[size];

        AudioRecord record = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, size);
        record.startRecording();

        int no = record.read(data, 0, data.length);
        Log.e(TAG, "Reading " + no + " bytes");
        record.stop();
        record.release();

        print(data);

        return Arrays.copyOf(data, no);
    }

    public void print(byte[] data) {
        Log.e(TAG, "print:");
        int i = 0;
        for(byte b : data) {
            if(b > 0)
                Log.e(TAG, "" + i++ + ": " + b);
        }
    }

    public String toString() {
        /*
        Log.e(TAG, "" + record.getState());
        Log.e(TAG, "" + record.getRecordingState());
        Log.e(TAG, "" + AudioRecord.RECORDSTATE_RECORDING);
        Log.e(TAG, "" + AudioRecord.RECORDSTATE_STOPPED);
        Log.e(TAG, "" + AudioRecord.STATE_INITIALIZED);
        Log.e(TAG, "" + AudioRecord.STATE_UNINITIALIZED);
        */
        return "";
    }
}
