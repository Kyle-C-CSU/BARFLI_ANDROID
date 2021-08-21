package com.barfli.barfli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity {
/**
 * Fire Base Database use
 * conect analytics with a variable and track it in the log to make sure its connected...
 * https://console.firebase.google.com/project/barfli-222cf/overview
 * private FirebaseAnalytics analytics;
 */

    //TAG
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //hookup editText fields
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        //hookup buttons
        Button login = findViewById(R.id.button_login);
        Button signup = findViewById(R.id.button_signup);

        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //launch login or signup page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginScreen = new Intent("com.barfli.barfli.HomeActivity");
                startActivity(loginScreen);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupScreen = new Intent("com.barfli.barfli.SignupActivity");
                startActivity(signupScreen);
            }
        });

    }
    @Override
    public void onPause() {
        super.onPause();
    }
}