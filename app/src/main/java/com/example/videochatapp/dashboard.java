package com.example.videochatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.URL;

public class dashboard extends AppCompatActivity {
    EditText secretcode;
    Button sharebtn,joinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        secretcode = (EditText)findViewById(R.id.editTextTextPersonName2);
       joinbtn = (Button)findViewById(R.id.button3);
        sharebtn = (Button)findViewById(R.id.button4);
        URL serverURL;
      try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder().setServerURL(serverURL).setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        }
        catch (Exception e)
        {

        }
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = secretcode.getText().toString();
                if (code.isEmpty()) {
                    Toast.makeText(dashboard.this,"Enter the secret code",Toast.LENGTH_SHORT).show();
                } else {
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setRoom(secretcode.getText().toString()).setWelcomePageEnabled(false)
                            .build();
                    JitsiMeetActivity.launch(dashboard.this, options);
                }

            }        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = secretcode.getText().toString();

                if (code.isEmpty()) {
                    Toast.makeText(dashboard.this, "Enter the secret code", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, secretcode.getText().toString());
                    startActivity(Intent.createChooser(intent, "hi1"));
                }
            }
        });

    }
}