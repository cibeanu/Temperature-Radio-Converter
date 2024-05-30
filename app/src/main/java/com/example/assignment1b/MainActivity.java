/*Assignment 1B
Assignment 1B.zip
Chinedu Ibeanu
Kasumi Yang

 */

package com.example.assignment1b;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    //Creating a variable of type radioGroup and setting it to the radiogroup in our xml
    RadioGroup radioGroup;

    public static final String TAG = "demo";

    EditText userTemp;

    TextView finalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        radioGroup= findViewById(R.id.radioGroup);



        userTemp = findViewById(R.id.userTemp);
        finalValue = findViewById(R.id.finalValue);

        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a variable to check and see which radio button is clicked
                //this method returns the identifier of the SELECTED radio button as an int hence
                //the reason we are setting it to an int.
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if (checkedId == R.id.cToFarRButton){
                    cToFar();

                } else if(checkedId == R.id.ftoCelRButton){
                    fToCel();
                }
                //Edge case to deal with no radio button selected at all
                else if(checkedId != R.id.ftoCelRButton || checkedId != R.id.cToFarRButton){
                    Toast.makeText(MainActivity.this, "No Button Selected. Please select a button.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTemp.setText("");
                finalValue.setText("");
            }
        });

    }
    private void cToFar(){
        String userInput = userTemp.getText().toString();
        //We then need to convert this to a double as temperature values usually involve decimals

        //We need to use exception handling to make sure that we account for the user not inputting in the text field as trying to convert an empty string into a double will throw an error

        try{
            double temp = Double.valueOf(userInput);
            Log.d(TAG, "onClick: " + temp);

            //Need to set the finalValue = (temp - 32) * 5/9

            double convertedTemp = (temp * 9/5) + 32;
            double roundedTemp = (Math.round(convertedTemp * 100.0)/ 100.0);

            finalValue.setText(String.valueOf(roundedTemp) + " F");

        }catch (NumberFormatException exception){
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }
    private void fToCel(){
        //Need to get the userTemperature and convert to a string
        String userInput = userTemp.getText().toString();
        //We then need to convert this to a double as temperature values usually involve decimals

        //We need to use exception handling to make sure that we account for the user not inputting in the text field as trying to convert an empty string into a double will throw an error

        try{
            double temp = Double.valueOf(userInput);
            Log.d(TAG, "onClick: " + temp);

            //Need to set the finalValue = (temp - 32) * 5/9

            double convertedTemp = (temp - 32) * 5/9;
            double roundedTemp = (Math.round(convertedTemp * 100.0)/ 100.0);

            finalValue.setText(String.valueOf(roundedTemp) + " C");
        }catch (NumberFormatException exception){
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }



    }
}