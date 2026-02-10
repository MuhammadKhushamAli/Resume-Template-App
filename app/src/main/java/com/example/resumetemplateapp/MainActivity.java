package com.example.resumetemplateapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Personal Detail Section
    ImageView profilePhoto;
    TextView name;
    TextView email;
    TextView ph_no;
    TextView address;

    ImageButton nameEdit;
    ImageButton emailEdit;
    ImageButton ph_noEdit;
    ImageButton addressEdit;

    // Professional Detail Section
    TextView objectives;
    ConstraintLayout skills;
    ConstraintLayout projects;
    ConstraintLayout activities;

    ImageButton objectivesEdit;
    ImageButton skillsEdit;
    ImageButton projectsEdit;
    ImageButton activitiesEdit;


    ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        // Personal Info
        profilePhoto.setOnClickListener((v) -> {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivity(i);
        });

        email.setOnClickListener((v) -> {
            String userEmail = v.getText().toString();

            if (!userEmail.isEmpty()) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:" + userEmail));
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this, "Email not Found", Toast.LENGTH_SHORT).show();
            }
        });

        ph_no.setOnClickListener((v) -> {
            String userPh_no = v.getText().toString();

            if(!userPh_no.isEmpty())
            {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + userPh_no));
                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "Phone Number Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        address.setOnClickListener((v) -> {
            String userAddress = v.getText().toString();

            if(!userAddress.isEmpty())
            {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("google.navigation:q=" + userAddress));
                startActivity(i);
            }
            else
            {
                Toast.makeText(MainActivity.this, "Address Not Found", Toast.LENGTH_SHORT).show();
            }
        });


        nameEdit.setOnClickListener((v) -> {
            nameEdit
        });

    }

    private void init(){
        // Personal Info
        profilePhoto = findViewById(R.id.profile_photo);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        ph_no = findViewById(R.id.ph_no);
        address = findViewById(R.id.address);

        nameEdit = findViewById(R.id.name_field_edit);
        emailEdit = findViewById(R.id.email_field_edit);
        ph_noEdit = findViewById(R.id.ph_no_field_edit);
        addressEdit = findViewById(R.id.address_field_edit);

        // Professional Info
        objectives = findViewById(R.id.objectives_text);
        skills = findViewById(R.id.skills_text);
        projects = findViewById(R.id.projects_text);
        activities = findViewById(R.id.activities_text);

        objectivesEdit = findViewById(R.id.objectives_field_edit);
        skillsEdit = findViewById(R.id.skills_field_edit);
        projectsEdit = findViewById(R.id.projects_field_edit);
        activitiesEdit = findViewById(R.id.activities_field_edit);
    }
}