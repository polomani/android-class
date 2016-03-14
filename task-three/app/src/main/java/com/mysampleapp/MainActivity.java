package com.mysampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.cognito.Dataset;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_USER_DATA = "main_user_data";
    private static final String USER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());
        loadFromDataset();

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveToDataset();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void loadFromDataset() {
        Dataset dataset = getDataset();
        final String userName = dataset.get(USER_NAME);
        if (userName != null) {
            ((EditText) findViewById(R.id.editText)).setText(userName);
        }
        Toast.makeText(this, "Loaded: " + userName , Toast.LENGTH_LONG).show();
    }

    public void saveToDataset() {
        Dataset dataset = getDataset();
        dataset.put(USER_NAME,  ((EditText) findViewById(R.id.editText)).getText().toString());
    }

    public Dataset getDataset() {
        return AWSMobileClient.defaultMobileClient()
                .getSyncManager()
                .openOrCreateDataset(MAIN_USER_DATA);
    }
}
