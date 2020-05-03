package albert.rafels.yourrelatedwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //public EditText word;
    public Button random_button, check_button;
    public TextView word_english, word_spanish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //word_english=findViewById(R.id.editText);
        random_button=findViewById(R.id.button_random);
        check_button=findViewById(R.id.button_check);
        word_spanish=findViewById(R.id.textViewSpanish);
        word_english=findViewById(R.id.textViewEnglish);

        //when click random button
        random_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //instance of database access class and open database connection
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();

                    //getting string value from edit text
                    String random_english=databaseAccess.getRandomEnglish();
                    word_english.setTextColor(Color.RED);
                    word_english.setText(random_english); //write random_english in word english text view.
                    final String spanish = databaseAccess.getSpanish(random_english); //getSpanish method used to get the spanish word from the english word
                    //word_spanish.setText(spanish);//print on spanish textView
                    word_spanish.setTextColor(Color.GREEN);
                    word_spanish.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            word_spanish.setText(spanish); //print on spanish textView with delay
                        }
                    }, 3000);

                    //String n=word.getText().toString();
                    //String spanish = databaseAccess.getSpanish(n); //getSpanish method used to get the spanish word from the english word
                    //setting text to result_spanish text view
                    //word_spanish.setText(spanish);
                    databaseAccess.close();//database connection closed
                    //Intent i = new Intent(MainActivity.this, MainActivity.class);
//                    i.putExtra("pass_random_english",random_english);
//                    startActivity(i);

                }


       });

        //when click check button
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = getIntent();
                //String randEng=i.getStringExtra("pass_random_english");
                //instance of database access class and open database connection
                DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String random_spanish=databaseAccess.getRandomSpanish();
                word_spanish.setTextColor(Color.RED);
                word_spanish.setText(random_spanish); //write random_english in word english text view.
                final String english = databaseAccess.getEnglish(random_spanish); //getSpanish method used to get the spanish word from the english word
                //word_spanish.setText(spanish);//print on spanish textView
                word_english.setTextColor(Color.GREEN);
                word_english.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        word_english.setText(english); //print on spanish textView with delay
                    }
                }, 3000);

                //String n=word.getText().toString();
                //String spanish = databaseAccess.getSpanish(n); //getSpanish method used to get the spanish word from the english word
                //setting text to result_spanish text view
                //String randEng=databaseAccess.getRandomEnglish();
                //word_spanish.setText(randEng);//print on spanish textView
                databaseAccess.close();//database connection closed

            }


        });
    }
}
