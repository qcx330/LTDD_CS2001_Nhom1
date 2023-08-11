
package com.example.test.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.test.SignIn;
import com.example.test.controller.TaskController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserController {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public Intent ChangePassword(String newPass, Context context){
        Intent intent = new Intent(context, SignIn.class);

        user.updatePassword(newPass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            FirebaseAuth.getInstance().signOut();
                        }
                    }
                });
        return intent;
    }

    public void SetUserInformation(EditText edtFullName, TextView txtEmail){
        if(user != null){
            edtFullName.setText(user.getDisplayName());
            txtEmail.setText(user.getEmail());
        }
    }

    public void SetBitmapImageView(Bitmap bitmapImageView, ImageView imageAvatar){
        imageAvatar.setImageBitmap(bitmapImageView);
    }

}
