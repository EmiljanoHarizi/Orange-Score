package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupTabFragment extends Fragment {

    EditText email, name, surname, password;
    Button sign_up;
    boolean valid = true;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);
        
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

       email = root.findViewById(R.id.email_sign);
       name = root.findViewById(R.id.name);
       surname = root.findViewById(R.id.surname);
       password = root.findViewById(R.id.pass_sign);
       sign_up = root.findViewById(R.id.sign_up_btn);

       sign_up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               checkField(email);
               checkField(name);
               checkField(surname);
               checkField(password);

               if (valid) {
                   fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                       @Override
                       public void onSuccess(AuthResult authResult) {
                           FirebaseUser user = fAuth.getCurrentUser();
                           Toast.makeText(getActivity(), "Account has been created successfully", Toast.LENGTH_SHORT).show();
                           DocumentReference df = fStore.collection("Users").document(user.getUid());

                           Map<String, Object> userInfo = new HashMap<>();
                           userInfo.put("Email", email.getText().toString());
                           userInfo.put("Name", name.getText().toString());
                           userInfo.put("Surname", surname.getText().toString());
                           userInfo.put("Password", password.getText().toString());

                           userInfo.put("isUser", "1");
                           df.set(userInfo);

                           startActivity(new Intent(getActivity().getApplicationContext(),MainActivity.class));
                           getActivity().finish();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(getActivity(), "Failed to create Account", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }
       });
       return root;
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
}

