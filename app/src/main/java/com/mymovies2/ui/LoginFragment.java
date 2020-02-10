package com.mymovies2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mymovies2.R;
import com.mymovies2.data.DAO;

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
    private TextView email;
    private TextView password;

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

        email = view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.login_password);

        view.findViewById(R.id.login_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSignupClicked();
            }
        });

        view.findViewById(R.id.login_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidData()){
                    if(DAO.getInstance(getContext()).isUserExist(email.getText().toString(), password.getText().toString())){
                        DAO.getInstance(getContext()).findCurrentUser(email.getText().toString());
                        mListener.onLoginSuccess();
                    }
                }
            }
        });

        return view;
    }

    private boolean checkValidData() {
        boolean b = false;
        if(email.getText().toString().equals("") || ! email.getText().toString().contains("@")){
            Toast.makeText(getContext(), "please insert valid email", Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().equals("")) {
            Toast.makeText(getContext(), "please insert password", Toast.LENGTH_LONG).show();
        } else {
            b = true;
        }
        return b;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupClicked();
        void onLoginSuccess();
    }
}
