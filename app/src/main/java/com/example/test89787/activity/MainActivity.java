package com.example.test89787.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.example.test89787.R;
import com.example.test89787.adapter.TitleAdapter;
import com.example.test89787.helper.RecyclerInterface;
import com.example.test89787.model.ParentClass;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {


    RecyclerView titleRecyclerView;
    List<ParentClass> titleList;
    TitleAdapter titleAdapter;

    int gPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleList = new ArrayList<>();

        initializeRecyclerView();

        findViewById(R.id.addButton).setOnClickListener(v -> showAlertDialog());

    }

    private void initializeRecyclerView() {

        titleRecyclerView = findViewById(R.id.parent_recycler);
        titleRecyclerView.setNestedScrollingEnabled(false);
        titleRecyclerView.setHasFixedSize(true);

        LinearLayoutManager latestLinearLayout = new GridLayoutManager(getApplicationContext(),1);
        titleRecyclerView.setLayoutManager(latestLinearLayout);

        titleAdapter = new TitleAdapter(MainActivity.this, titleList);
        titleRecyclerView.setAdapter(titleAdapter);

    }

    private void showAlertDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        //adding layout file to Alert Dialog
        View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();

        //Initializing Alert Dialog components
        TextInputEditText titleET = dialogView.findViewById(R.id.titleET);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button okayButton = dialogView.findViewById(R.id.okayButton);
        cancelButton.setOnClickListener(v1 -> alertDialog.dismiss());
        okayButton.setOnClickListener(v1 -> {

            String titleST = Objects.requireNonNull(titleET.getText()).toString();
            if(titleST.isEmpty())
            {
                Toast.makeText(this, "Title is Empty", Toast.LENGTH_SHORT).show();
            }
            else
            {
                titleList.add(new ParentClass(titleST));
                alertDialog.dismiss();
                titleAdapter.notifyDataSetChanged();
            }

        });
        alertDialog.show();

    }

    @Override
    public void onAddClicked(int position) {

        gPosition = position;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

        } else {

            ImagePicker.create(this)
                    .limit(50)
                    .start();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            List<com.esafirm.imagepicker.model.Image> images = ImagePicker.getImages(data);

            for (int i = 0; i < images.size(); i++) {
                titleList.get(gPosition).addImage(images.get(i).getUri());
            }
            titleAdapter.notifyDataSetChanged();
        }

    }




}