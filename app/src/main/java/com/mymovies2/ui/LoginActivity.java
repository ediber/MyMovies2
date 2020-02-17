package com.mymovies2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mymovies2.AlertDialogHelper;
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
                transaction.replace(R.id.login_frame, signupFragment).addToBackStack(null).commit();
            }

            @Override
            public void onLoginSuccess() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_frame, loginFragment).commit();
    }

    @Override
    public void onSignUpSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            new AlertDialogHelper(this, new AlertDialogHelper.DialogListener() {
                @Override
                public void onYesClicked() {
                    LoginActivity.super.onBackPressed();
                }

                @Override
                public void onNoClicked() {

                }
            }).showDialog();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int a = 1 + 3;
        int b = a;
    }
}
