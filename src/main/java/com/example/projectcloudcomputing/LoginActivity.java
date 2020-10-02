package com.example.projectcloudcomputing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    String email=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();
        FirebaseUser user= auth.getCurrentUser();
        if (user != null) {
            goToChatActivity (user.getEmail());

        }
        final EditText usernameEd=findViewById(R.id.usernameEd);
        final EditText passwordEd=findViewById(R.id.passwordEd);
        Button loginBtn=findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameEd.getText().toString();
                String password=passwordEd.getText().toString();
                Task<AuthResult> task = auth.signInWithEmailAndPassword(username,password);
                task.addOnSuccessListener( new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_LONG).show();
                        goToChatActivity(authResult.getUser().getEmail());
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }


    private void goToChatActivity(String email) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("email",email);
        startActivity(i);
    }
}
