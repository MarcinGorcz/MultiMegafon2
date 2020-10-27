package com.example.multimegafon2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;

public class OdbieranieWyborActivity extends AppCompatActivity {
    boolean isRadioButtonSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odbieranie_wybor);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton1:
                        isRadioButtonSelected = true;
                        break;
                    case R.id.radioButton2:
                        isRadioButtonSelected = true;
                        break;
                    case R.id.radioButton3:
                        isRadioButtonSelected = true;
                        break;
                }
            }
        });
    }



    public void przejdzDoOdbioru(View view) {
        if(isRadioButtonSelected) {
            Intent intent = new Intent(this, OdbieranieActivity.class);
            startActivity(intent);
        }
        else
        {
            Snackbar wybierzNadawceSnackbar = Snackbar.make(view, R.string.wybierz_nadawce_snackbar_text, 1000);
            wybierzNadawceSnackbar.show();
        }
    }


}