//package com.example.test.controller.user;
//
//
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.test.R;
//import com.example.test.model.UserModel;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.auth.User;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class UserController extends AppCompatActivity{
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//    public void CreateUser(UserModel newUer){
//
//        // Write a message to the database
//
//        TextView userName = findViewById();
//        ImageView imgUser = findViewById();
//        EditText editEmail = findViewById();
//        EditText editUserName = findViewById();
//        EditText editPass = findViewById();
//        EditText editConfirmPass = findViewById();
//        newUer = new UserModel(userName.toString()
//                , editPass.toString()
//                , editConfirmPass.toString()
//                , editEmail.toString()
//                , imgUser, null);
//
//
//
//        db.collection("/UserModel")
//                .add()
//    }
//}
