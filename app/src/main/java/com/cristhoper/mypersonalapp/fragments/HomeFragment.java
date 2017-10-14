package com.cristhoper.mypersonalapp.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristhoper.mypersonalapp.R;
import com.cristhoper.mypersonalapp.models.User;
import com.cristhoper.mypersonalapp.repositories.UserRepository;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    SharedPreferences sharedPreferences;
    TextView usernameText;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_home, container, false);

        //Set a title activity
        getActivity().setTitle("Inicio");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        usernameText = vista.findViewById(R.id.fullname_text);

        String username = sharedPreferences.getString("username", null);

        User user = UserRepository.getUser(username);
        Log.d(TAG, "username: " + username);

        usernameText.setText(user.getFullname());
        return vista;
    }

}
