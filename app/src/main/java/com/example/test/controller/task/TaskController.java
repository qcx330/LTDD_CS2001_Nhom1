//package com.example.test.controller.task;
//
//import android.util.Log;
//import android.widget.CheckBox;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.test.R;
//import com.example.test.model.TaskModel;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//public class TaskController extends AppCompatActivity {
//
//    public void CreateTask(String nameTask){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        // private String task;
//        //    private int done;
//        //    private int impo;
//        //    private static int id = 0;
//
////        CheckBox chbxDone = findViewById(R.id.chbxDone);
////        CheckBox chbxImp = findViewById(R.id.chbxImp);
//
//        TaskModel taskModel = new TaskModel(nameTask);
//
//        db.collection("TaskModel")
//                .document(String.valueOf(taskModel.getId()))
//                .set(taskModel.ConvertHashMap())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("Ghi chú: ", "Thêm thành công");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("Ghi chú: ", "Thêm thất bại");
//                    }
//                });
//
//    }
//}
