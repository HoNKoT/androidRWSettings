package honkot.androidrwsettings;

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
    }

    @Override
    public void onClick(View view) {
        TextView tv = (TextView)findViewById(R.id.result);
        StringBuffer buf = new StringBuffer();
        switch (view.getId()) {
            case R.id.read:
                buf.append("Auto Date&Time:");
                buf.append(getAutoState());
                break;
            case R.id.write: break;
        }
        tv.setText(buf.toString());
    }

    private boolean getAutoState() {
        try {
            return Settings.System.getInt(getContentResolver(),
                    Settings.System.AUTO_TIME) > 0;
        } catch (Settings.SettingNotFoundException snfe) {
            return true;
        }
    }
}
