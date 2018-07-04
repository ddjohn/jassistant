package se.avelon.jassistant;

import android.media.AudioFormat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AudioRecorder {
/*
    private TargetDataLine microphone;

    private boolean stopped = false;

    private AudioConf audioConf;

    public AudioRecorder() {}

    public byte[] getRecord() throws AudioException {
        try {
            // Reset the flag
            stopped = false;

            // Start a new thread to wait during listening
            Thread stopper = new Thread(() -> {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    br.readLine();
                    stopped = true;
                    Log.e("EEE", "End of the capture");
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                stop();
            });

            // Start the thread that can stop the record
            stopper.start();

            return record();
        } catch (Exception e) {
            throw new AudioException("Unable to record your voice", e);
        }
    }

    private byte[] record() throws LineUnavailableException {
        AudioFormat format = AudioUtil.getAudioFormat(audioConf);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        // Checks if system supports the data line
        if (!AudioSystem.isLineSupported(info)) {
            Log.e("EEE", "Line not supported");
            System.exit(0);
        }

        microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();

        Log.e("EEE", "Listening, tap enter to stop ...");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int numBytesRead;
        byte[] data = new byte[microphone.getBufferSize() / 5];

        // Begin audio capture.
        microphone.start();

        // Here, stopped is a global boolean set by another thread.
        while (!stopped) {
            // Read the next chunk of data from the TargetDataLine.
            numBytesRead = microphone.read(data, 0, data.length);
            // Save this chunk of data.
            byteArrayOutputStream.write(data, 0, numBytesRead);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void stop() {
        microphone.stop();
        microphone.close();
    }
    */
}