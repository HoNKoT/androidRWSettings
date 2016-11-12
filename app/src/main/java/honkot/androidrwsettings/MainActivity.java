package honkot.androidrwsettings;

import android.os.Build;
import android.provider.Settings;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.read).setOnClickListener(this);
        findViewById(R.id.write).setOnClickListener(this);

        TextView tv = (TextView)findViewById(R.id.result);
        tv.setText("API level:" + Build.VERSION.SDK_INT);
    }

    @Override
    public void onClick(View view) {
        TextView tv = (TextView)findViewById(R.id.result);
        StringBuffer buf = new StringBuffer();
        switch (view.getId()) {
            case R.id.read:
                buf.append("Get Auto Date&Time:");
                buf.append(getAutoState());
                break;
            case R.id.write:
                buf.append("Set Auto Date&Time: true");
                setAutoState(true);
                break;
        }
        tv.setText(buf.toString());
    }

    private boolean getAutoState() {
        try {
            if (Build.VERSION.SDK_INT < 17) {
                return Settings.System.getInt(getContentResolver(),
                        Settings.System.AUTO_TIME) > 0;
            } else {
                return android.provider.Settings.Global.getInt(
                        getContentResolver(), android.provider.Settings.Global.AUTO_TIME, 0) > 0;
            }
        } catch (Settings.SettingNotFoundException snfe) {
            return true;
        }
    }

    private void setAutoState(boolean value) {
        if (Build.VERSION.SDK_INT < 17) {
            Settings.System.putInt(getContentResolver(),
                    Settings.System.AUTO_TIME, value ? 1 : 0);
        } else {
            android.provider.Settings.Global.putInt(
                    getContentResolver(), android.provider.Settings.Global.AUTO_TIME, value ? 1 : 0);
        }
    }
}
