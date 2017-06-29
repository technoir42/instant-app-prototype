package com.sch.instantapp.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.instantapps.InstantApps;
import com.sch.instantapp.R;

public class FirstActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_INSTALL = 0;
    private static final int REQUEST_CODE_DETAILS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final TextView textView = (TextView) findViewById(R.id.text);
        if (InstantApps.isInstantApp(this)) {
            textView.setText(R.string.first_type_instant_app);
        } else {
            textView.setText(R.string.first_type_full_app);
        }

        final Button detailsButton = (Button) findViewById(R.id.button_details);
        detailsButton.setOnClickListener(v -> {
            final Intent intent = createSecondIntent();
            startActivityForResult(intent, REQUEST_CODE_DETAILS);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first, menu);

        final MenuItem installItem = menu.findItem(R.id.action_install);
        installItem.setVisible(InstantApps.isInstantApp(this));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_install) {
            InstantApps.showInstallPrompt(this, REQUEST_CODE_INSTALL, null);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DETAILS) {
            Toast.makeText(this, "Result from SecondActivity: " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    private Intent createSecondIntent() {
        if (InstantApps.isInstantApp(this)) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://sch.com/second"));
        } else {
            return new Intent().setComponent(new ComponentName(this, "com.sch.instantapp.ui.SecondActivity"));
        }
    }
}
