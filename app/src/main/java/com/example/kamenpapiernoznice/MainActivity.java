package com.example.kamenpapiernoznice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView playerAImage;
    ImageView playerBImage;
    TextView playerAScore;
    TextView playerBScore;
    SharedPreferences preferences;

    int scoreA =0;
    int scoreB =0;

    Random rand = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppData.getInstance().getUserName();

        playerAImage = findViewById(R.id.a_image);
        playerBImage = findViewById(R.id.b_image);
        playerAScore = findViewById(R.id.a_score);
        playerBScore = findViewById(R.id.b_score);
        Button playButton = findViewById(R.id.play);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(preferences.getLong("LastButtonPress", 0L) > 0L) {
            Toast.makeText(this, "Tlacidlo uz bolo stlacene", Toast.LENGTH_LONG).show();
        }
        playButton.setOnClickListener(view -> {
            preferences.edit().putLong("LastButtonPress", System.currentTimeMillis()).apply();
            Intent intent = new Intent(this, ChildActivity.class);
            intent.putExtra("kluc", "bytc50");
            startActivity(intent);
            //compute();
        });

    }

    private void compute() {
        int playerAToss = rand.nextInt(3);
        int playerBToss = rand.nextInt(3);

        switch(playerAToss) {
            case 0: playerAImage.setImageResource(R.drawable._paper); break;
            case 1: playerAImage.setImageResource(R.drawable._rock); break;
            case 2: playerAImage.setImageResource(R.drawable._scissors); break;
        }

        switch(playerBToss) {
            case 0: playerBImage.setImageResource(R.drawable._paper); break;
            case 1: playerBImage.setImageResource(R.drawable._rock); break;
            case 2: playerBImage.setImageResource(R.drawable._scissors); break;
        }

        if (playerAToss == playerBToss) return;
        if (playerAToss == 0 && playerBToss ==2) scoreB++;
        if (playerAToss == 2 && playerBToss == 0) scoreA++;
        if (playerAToss == 1 && playerBToss == 0) scoreB++;
        if (playerAToss == 0 && playerBToss == 1) scoreA++;
        if (playerAToss == 1 && playerBToss == 2) scoreB++;
        if (playerAToss == 2 && playerBToss == 1) scoreA++;

        playerAScore.setText(Integer.toString(scoreA));
        playerBScore.setText(Integer.toString(scoreB));
    }
}