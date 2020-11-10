package com.example.multimegafon2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //To jest przypisywanie widoku do fragmentu podczas tworzenia:
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.przycisk_trybu_nadawania).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).przejdzDoNadawania(view);
            }
        });
        view.findViewById(R.id.przycisk_trybu_odbierania).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ((MainActivity)getActivity()).przejdzDoOdbierania(view);
                }
        });
        view.findViewById(R.id.testbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).przejdzDoEkranuTestowego(view);
            }
        });
        view.findViewById(R.id.serverbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).przejdzServera(view);
            }
        });

        view.findViewById(R.id.multiserverbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).przejdzServeraWieloclientowego(view);
            }
        });

        view.findViewById(R.id.clientbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).przejdzClienta(view);
            }
        });
    }

}