package com.example.multimegafon2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

public class ThirdFragment extends Fragment {

    boolean isRadioButtonSelected = false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_third, container, false);
        RadioGroup radioGroup = (RadioGroup) myView .findViewById(R.id.radioGroup);

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

        return myView;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.przycisk_zmiany_trybu_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.actionThirdFragmentToFirstFragment);
            }
        });

        view.findViewById(R.id.przycisk_potwierdzenia_odtwarzania).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRadioButtonSelected) {
                    NavHostFragment.findNavController(ThirdFragment.this).navigate(R.id.actionThirdFragmentToFourthFragment);
                }
                else
                {
                    Snackbar wybierzNadawceSnackbar = Snackbar.make(view, R.string.wybierz_nadawce_snackbar_text, 1000);
                    wybierzNadawceSnackbar.show();
                }
            }
        });
    }
}