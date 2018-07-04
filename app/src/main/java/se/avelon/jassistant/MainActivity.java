package se.avelon.jassistant;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            Gson gson = new Gson();
            Log.e(TAG, "gson=" + gson);

                String url = "https://accounts.google.com/o/oauth2/v2/auth?" +
                        "scope=" + "https://www.googleapis.com/auth/assistant-sdk-prototype" + "&" +
                        "response_type=code&" +
                        "redirect_uri=" + "urn:ietf:wg:oauth:2.0:oob" + "&" +
                        "client_id=" + "secret";
                Log.e(TAG, "url=" + url);


                byte[] bytes = record();
                play(bytes);
                /*
                4/AADh3UqheojNQ8aNIuS5XDMjIia9jyxMm1kMQUP5XddD-KlWNT20g7E
                 */

               // ManagedChannel channel = ManagedChannelBuilder.forAddress("https://embeddedassistant.googleapis.com/", 443).build();
               // EmbeddedAssistantGrpc.EmbeddedAssistantStub stub = EmbeddedAssistantGrpc.newStub(channel);


                /*

                final AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, 1600, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, 1024 * 2);
                recorder.startRecording();

                boolean isRecording = true;
                Thread recordingThread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            br.readLine();
                            Log.e(TAG, "End of the capture");
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        //recorder.stop();
                        //recorder.close();
                    }
                }, "AudioRecorder Thread");
                recordingThread.start();

*/
            }
        });
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

    byte[] record() {
        int size = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        Log.e(TAG, "size=" + size);

        AudioRecord record = new AudioRecord(MediaRecorder.AudioSource.MIC, 16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, 1024 * 2);
        byte[] bytes = new byte[10240];
        record.read(bytes, 0,  bytes.length);
for(byte b : bytes) {
    Log.e(TAG, "" + b);
}
        return bytes;
    }

    void play(byte[] bytes) {
        int size = AudioTrack.getMinBufferSize(16000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
        Log.e(TAG, "size=" + size);

        AudioTrack player = new AudioTrack(AudioManager.STREAM_MUSIC, 16000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, size, AudioTrack.MODE_STREAM);
        player.play();
        player.write(bytes, 0, bytes.length);
        player.stop();
    }
}
