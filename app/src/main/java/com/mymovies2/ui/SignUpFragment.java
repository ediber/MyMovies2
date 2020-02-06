package com.mymovies2.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mymovies2.R;


public class SignUpFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private EditText email;
    private EditText firstName;
    private EditText lastName;
    private EditText password1;
    private EditText password2;
    private View button;

    public SignUpFragment() {
        // Required empty public constructor
    }


    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        email = view.findViewById(R.id.signup_email);
        firstName = view.findViewById(R.id.signup_first_name);
        lastName = view.findViewById(R.id.signup_last_name);
        password1 = view.findViewById(R.id.signup_password1);
        password2 = view.findViewById(R.id.signup_password2);
        button = view.findViewById(R.id.signup_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") || ! email.getText().toString().contains("@")){
                    Toast.makeText(getContext(), "please insert valid email", Toast.LENGTH_LONG).show();
                } else if(firstName.getText().toString().equals("")){
                    Toast.makeText(getContext(), "please insert first name", Toast.LENGTH_LONG).show();
                } else if(lastName.getText().toString().equals("")){
                    Toast.makeText(getContext(), "please insert last name", Toast.LENGTH_LONG).show();

                } else if(password1.getText().toString().equals("")){

                } else if(firstName.getText().toString().equals("")){

                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSignUpInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignUpInteraction(Uri uri);
    }
}
