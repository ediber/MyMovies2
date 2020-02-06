package com.mymovies2.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovies2.R;
import com.mymovies2.data.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment(OnFragmentInteractionListener listener) {
        this.mListener = listener;
    }


    public static LoginFragment newInstance(OnFragmentInteractionListener mListener) {
        LoginFragment fragment = new LoginFragment(mListener);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        view.findViewById(R.id.login_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSignupClicked();
            }
        });


        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupClicked();
        void onLoginSucess(User user);
    }
}
