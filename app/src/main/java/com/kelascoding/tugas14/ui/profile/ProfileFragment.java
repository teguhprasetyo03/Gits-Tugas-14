package com.kelascoding.tugas14.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kelascoding.tugas14.R;
import com.kelascoding.tugas14.ui.login.activity.Login;
import com.kelascoding.tugas14.ui.login.sharedprefrences.SharedPref;

public class ProfileFragment extends Fragment {
    TextView tvUsername;
    Button btnLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        tvUsername = root.findViewById(R.id.tv_username);
        btnLogout = root.findViewById(R.id.btn_logout);

        if (!SharedPref.getInstance(getActivity()).isLoggedUsername()){
            startActivity(new Intent(getActivity(), Login.class));
            getActivity().finish();
        }


//        getting logged in user name & email
        String username = SharedPref.getInstance(getActivity()).LoggedInUsername();
//        String email = SharedPref.getInstance(getActivity()).LoggedEmail();
        tvUsername.setText("Username : " + username);
//        tvEmail.setText("Email "+ email);

        //logging out
        btnLogout.setOnClickListener(view -> {
            getActivity().finish();
            SharedPref.getInstance(getActivity()).logout();

        });
        return root;
    }
}
