package com.beny.sudokusolver;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {
    EditText email;
    EditText name;
    EditText opinion;
    Float rate;
    RatingBar rb;
    Button sub;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_feedback);
        this.name = (EditText) findViewById(R.id.input_name);
        this.email = (EditText) findViewById(R.id.email);
        this.opinion = (EditText) findViewById(R.id.opinion);
        this.rb = (RatingBar) findViewById(R.id.feedbackRb);
        this.sub = (Button) findViewById(R.id.submitBtn);
        this.rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Feedback.this.rate = Float.valueOf(rating);
            }
        });
        this.sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name_input = Feedback.this.name.getText().toString();
                String obj = Feedback.this.email.getText().toString();
                String opinion_input = Feedback.this.opinion.getText().toString();
                Intent email = new Intent("android.intent.action.SEND");
                email.putExtra("android.intent.extra.EMAIL", new String[]{"benyaminramezany@yahoo.com"});
                email.putExtra("android.intent.extra.SUBJECT", "SudokuSolver");
                email.putExtra("android.intent.extra.TEXT", "name : " + name_input + "\nRating : " + Feedback.this.rate + "\n" + opinion_input);
                email.setType("message/rfc822");
                try {
                    Feedback.this.startActivity(Intent.createChooser(email, "Send mail..."));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Feedback.this, "There are no email clients installed.", 0).show();
                }
            }
        });
    }
}
