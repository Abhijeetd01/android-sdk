package io.hasura.android_sdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.hasura.android_sdk.R;
import io.hasura.sdk.auth.HasuraException;
import io.hasura.sdk.auth.request.RegisterRequest;
import io.hasura.sdk.auth.response.LoginResponse;
import io.hasura.sdk.auth.response.RegisterResponse;
import io.hasura.sdk.core.Callback;
import io.hasura.sdk.utils.Hasura;
import io.hasura.sdk.utils.HasuraSessionStore;


public class AuthenticationActivity extends BaseActivity implements View.OnClickListener {

    EditText username, password;
    Button signInButton, registerButton;

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity, AuthenticationActivity.class));
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hasura.initialise(this);
        setContentView(R.layout.activity_authentication);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signInButton = (Button) findViewById(R.id.signInButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        signInButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        if (HasuraSessionStore.getUserSession() != null) {
            ToDoActivity.startActivity(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButton:
                handleLogin();
                break;
            case R.id.registerButton:
                handleRegistration();
                break;
        }
    }

    private void handleLogin() {
        showProgressIndicator();
        Hasura.auth.login(username.getText().toString(), password.getText().toString())
                .enqueue(new Callback<LoginResponse, HasuraException>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        hideProgressIndicator();
                        ToDoActivity.startActivity(AuthenticationActivity.this);
                    }

                    @Override
                    public void onFailure(HasuraException e) {
                        hideProgressIndicator();
                        Toast.makeText(AuthenticationActivity.this, "Something went wrong, please ensure that you have a working internet connection", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void handleRegistration() {
        showProgressIndicator();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username.getText().toString());
        registerRequest.setPassword(password.getText().toString());
        Hasura.auth.register(registerRequest)
                .enqueue(new Callback<RegisterResponse, HasuraException>() {
                    @Override
                    public void onSuccess(RegisterResponse response) {
                        hideProgressIndicator();
                        ToDoActivity.startActivity(AuthenticationActivity.this);
                    }

                    @Override
                    public void onFailure(HasuraException e) {
                        hideProgressIndicator();
                        Toast.makeText(AuthenticationActivity.this, "Something went wrong, please ensure that you have a working internet connection", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
