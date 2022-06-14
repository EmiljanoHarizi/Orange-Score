package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends Fragment {
    EditText email;
    EditText password;
    Button login;
    float v=0;
    boolean valid = true;

    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.log_in_fragment, container, false);

        /** Initialising Variables */
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.pass);
        login = root.findViewById(R.id.login_btn);

        email.setTranslationX(800);
        password.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        /** login button */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);

                if (valid) {
                    fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return root;
    }


    /** Method checks access level of user in case there is an admin it redirects the user to admin interface, if not redirects to simple user interface */
    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        /** Retrieving date from the database to check if user is admin or not */
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());

                /** Checking user access level */
                if(documentSnapshot.getString("isAdmin") != null) {
                    /** When the user is admin */
                    getActivity().startActivity(new Intent(getActivity().getApplicationContext(), Admin_Start_Activity.class));
                    getActivity().finish();
                }
                if(documentSnapshot.getString("isUser") != null) {
                    /** When the user is not admin */
                    getActivity().startActivity(new Intent(getActivity().getApplicationContext(), R4_Activity.class));
                    getActivity().finish();
                }

            }
        });
    }


    /** Η μέθοδος ελέγχει αν έχουν συμπληρωθεί όλα τα κενά*/
    private boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Fill in the required field");
            valid=false;
        } else {
            valid=true;
        }
        return valid;
    }

     public final void onStart() {
         super.onStart();
     }

}
