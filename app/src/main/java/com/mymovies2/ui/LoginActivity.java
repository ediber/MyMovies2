package com.mymovies2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;

import com.mymovies2.R;
import com.mymovies2.data.User;

public class LoginActivity extends AppCompatActivity implements SignUpFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = LoginFragment.newInstance(new LoginFragment.OnFragmentInteractionListener() {
            @Override
            public void onSignupClicked() {
                SignUpFragment signupFragment = SignUpFragment.newInstance();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.login_frame, signupFragment).commit();
            }

            @Override
            public void onLoginSucess(User user) {

            }
        });

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_frame, loginFragment).commit();
    }

    @Override
    public void onSignUpInteraction(Uri uri) {

    }
}
