package com.example.multimegafon2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //To jest przypisywanie widoku do fragmentu podczas tworzenia:
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.przycisk_trybu_nadawania).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.action_FirstFragment_to_secondFragment);
            }
        });
        view.findViewById(R.id.przycisk_trybu_odbierania).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    NavHostFragment.findNavController(MenuFragment.this)
                            .navigate(R.id.actionFirstFragmentToThirdFragment);
                }
        });
    }
}