package se.avelon.jassistant;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MyActivity extends AppCompatActivity {
    private static final String TAG = MyActivity.class.getSimpleName();
    private int no = 0;

    void request(String permission) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, ++no);
    }

    void check(String permission) {
        int res = ActivityCompat.checkSelfPermission(this, permission);
        Log.e(TAG, "res=" + res);

    }

    @Override
    public void onRequestPermissionsResult(int code, String permissions[], int[] result) {
        if(permissions.length > 0)
        Log.e(TAG, "Request=" + code + ", permission=" + permissions[0] + ", result=" + result[0]);
    }
}
