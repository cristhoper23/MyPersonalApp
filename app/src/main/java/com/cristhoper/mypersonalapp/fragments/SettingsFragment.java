package com.cristhoper.mypersonalapp.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cristhoper.mypersonalapp.R;
import com.cristhoper.mypersonalapp.models.User;
import com.cristhoper.mypersonalapp.repositories.UserRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    EditText edName;
    SharedPreferences sharedPreferences;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_settings, container, false);

        //Set a title activity
        getActivity().setTitle("Configuración");

        edName = (EditText) vista.findViewById(R.id.edName);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        String username = sharedPreferences.getString("username", null);

        User user = UserRepository.getUser(username);

        edName.setText(user.getFullname());
        // Inflate the layout for this fragment
        return vista;
    }

}
