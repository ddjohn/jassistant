package se.avelon.jassistant;

import android.Manifest;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;

public class MainActivity extends MyActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.check(Manifest.permission.RECORD_AUDIO);
        this.request(Manifest.permission.RECORD_AUDIO);

        this.check(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        this.request(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        FloatingActionButton fab = (FloatingActionButton)this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                /*
                Gson gson = new Gson();
                Log.e(TAG, "gson=" + gson);

                String url = "https://accounts.google.com/o/oauth2/v2/auth?" +
                        "scope=" + "https://www.googleapis.com/auth/assistant-sdk-prototype" + "&" +
                        "response_type=code&" +
                        "redirect_uri=" + "urn:ietf:wg:oauth:2.0:oob" + "&" +
                        "client_id=" + "secret";
                Log.e(TAG, "url=" + url);
                */

                AudioRecorder recorder = new AudioRecorder();
                //recorder.detect();
                recorder.test();
   //             while(true) {
    //                byte[] data = recorder.record(2000);
     //            }

                //play(data);

                /*
                4/AADh3UqheojNQ8aNIuS5XDMjIia9jyxMm1kMQUP5XddD-KlWNT20g7E
                 */

               // ManagedChannel channel = ManagedChannelBuilder.forAddress("https://embeddedassistant.googleapis.com/", 443).build();
               // EmbeddedAssistantGrpc.EmbeddedAssistantStub stub = EmbeddedAssistantGrpc.newStub(channel);
            }
        });
    }

    void play(short[] data) {
        int size = AudioTrack.getMinBufferSize(16000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
        Log.e(TAG, "size=" + size);

        AudioTrack player = new AudioTrack(AudioManager.STREAM_MUSIC, 16000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, size, AudioTrack.MODE_STREAM);
        player.play();
        player.write(data, 0, data.length);
        player.stop();
    }
}

