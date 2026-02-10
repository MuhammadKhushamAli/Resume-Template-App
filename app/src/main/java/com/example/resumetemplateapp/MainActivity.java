package com.example.resumetemplateapp;

import android.content.Intent;
import android.graphics.Color;
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
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        // Launcher
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_CANCELED)
                    {
                        Toast.makeText(MainActivity.this, "Enter All the Information First", Toast.LENGTH_SHORT).show();
                    }
                    else if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        if (i != null) {

                            String universal_key = i.getStringExtra(keys.universal_key);

                            if (universal_key != null && !(universal_key.trim().isEmpty())) {
                                String response = null;
                                switch (universal_key) {
                                    case (keys.objective_key):
                                        objectives.setText(i.getStringExtra(keys.objective_key));
                                        objectives.setTextColor(Color.BLACK);
                                        break;
                                    case (keys.skills_key):
                                        response = i.getStringExtra(keys.skills_key);

                                        if (response != null && !(response.trim().isEmpty())) {
                                            String[] str_arr = response.split("$");

                                            constraintChainingByAddingTextView(skills, str_arr);
                                        }
                                        break;
                                    case (keys.projects_key):
                                        response = i.getStringExtra(keys.projects_key);

                                        if (response != null && !(response.trim().isEmpty())) {
                                            String[] str_arr = response.split("$");

                                            constraintChainingByAddingTextView(projects, str_arr);
                                        }
                                        break;
                                    case (keys.activities_key):
                                        response = i.getStringExtra(keys.activities_key);

                                        if (response != null && !(response.trim().isEmpty())) {
                                            String[] str_arr = response.split("$");

                                            constraintChainingByAddingTextView(activities, str_arr);
                                        }
                                        break;

                                    case (keys.name_key):
                                        String userName = i.getStringExtra(keys.name_key);
                                        if (userName != null && !(userName.trim().isEmpty()))
                                        {
                                           userName = "Name: " + userName;
                                           name.setText(userName);
                                        }
                                        break;
                                    case (keys.email_key):
                                        String userEmail = i.getStringExtra(keys.email_key);
                                        if (userEmail != null && !(userEmail.trim().isEmpty()))
                                        {
                                            userEmail = "Email: " + userEmail;
                                            email.setText(userEmail);
                                        }
                                        break;
                                    case (keys.ph_no_key):
                                        String userPh_no = i.getStringExtra(keys.ph_no_key);
                                        if (userPh_no != null && !(userPh_no.trim().isEmpty()))
                                        {
                                            userPh_no = "Phone: " + userPh_no;
                                            email.setText(userPh_no);
                                        }
                                        break;
                                    case (keys.address_key):
                                        String userAddress = i.getStringExtra(keys.address_key);
                                        if (userAddress != null && !(userAddress.trim().isEmpty()))
                                        {
                                            userAddress = "Address: " + userAddress;
                                            email.setText(userAddress);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
        );


        // Personal Info
        profilePhoto.setOnClickListener((v) -> {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivity(i);
        });

        email.setOnClickListener((v) -> {
            String userEmail = email.getText().toString();

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
            String userPh_no = ph_no.getText().toString();

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
            String userAddress = address.getText().toString();

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
            String userName = name.getText().toString();
            userName = userName.replace("Name: ", "");
            Intent i = new Intent(MainActivity.this, DescriptionActivity.class);

            if (!(userName.trim().isEmpty()))
            {
                i.putExtra(keys.name_key, userName);
            }
            i.putExtra(keys.hint_key, "Enter Your Name Here");
            i.putExtra(keys.universal_key, keys.name_key);
            launcher.launch(i);
        });

        emailEdit.setOnClickListener((v) -> {
            String userEmail = email.getText().toString();
            userEmail = userEmail.replace("Email: ", "");
            Intent i = new Intent(MainActivity.this, DescriptionActivity.class);

            if (!(userEmail.trim().isEmpty()))
            {
                i.putExtra(keys.email_key, userEmail);
            }
            i.putExtra(keys.hint_key, "Enter Your Email Here");
            i.putExtra(keys.universal_key, keys.email_key);
            launcher.launch(i);
        });

        ph_noEdit.setOnClickListener((v) -> {
            String userPh_no = ph_no.getText().toString();
            userPh_no = userPh_no.replace("Phone: ", "");
            Intent i = new Intent(MainActivity.this, DescriptionActivity.class);

            if (!(userPh_no.trim().isEmpty()))
            {
                i.putExtra(keys.ph_no_key, userPh_no);
            }
            i.putExtra(keys.hint_key, "Enter Your Phone Number Here");
            i.putExtra(keys.universal_key, keys.ph_no_key);
            launcher.launch(i);
        });

        addressEdit.setOnClickListener((v) -> {
            String userAddress = ph_no.getText().toString();
            userAddress = userAddress.replace("Address: ", "");
            Intent i = new Intent(MainActivity.this, DescriptionActivity.class);

            if (!(userAddress.trim().isEmpty()))
            {
                i.putExtra(keys.address_key, userAddress);
            }
            i.putExtra(keys.hint_key, "Enter Your Address Here");
            i.putExtra(keys.universal_key, keys.address_key);
            launcher.launch(i);
        });


        // Professional Info
        objectivesEdit.setOnClickListener((v) -> {
            String userObjectives = objectives.getText().toString();
            Intent i = new Intent(MainActivity.this, DescriptionActivity.class);

            if (!(userObjectives.trim().isEmpty()))
            {
                i.putExtra(keys.objective_key, userObjectives);
            }
            i.putExtra(keys.hint_key, "Enter Your Objectives Here");
            i.putExtra(keys.universal_key, keys.objective_key);
            launcher.launch(i);
        });

        skillsEdit.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, PointsActivity.class);
            i.putExtra(keys.universal_key, keys.skills_key);
            i.putExtra(keys.skills_key, "Enter Your Skills Here");
            launcher.launch(i);
        });

        projectsEdit.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, PointsActivity.class);
            i.putExtra(keys.universal_key, keys.projects_key);
            i.putExtra(keys.projects_key, "Enter your Projects Here");
            launcher.launch(i);
        });

        activitiesEdit.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, PointsActivity.class);
            i.putExtra(keys.universal_key, keys.activities_key);
            i.putExtra(keys.activities_key, "Enter Your Activities Here");
            launcher.launch(i);
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

    private void constraintChainingByAddingTextView(ConstraintLayout layout, String[] str_arr)  {
        int prevTempId = ConstraintSet.PARENT_ID;
        for (int it = 0; it < str_arr.length; it++) {
            TextView text_view = new TextView(MainActivity.this);
            text_view.setId(View.generateViewId());
            text_view.setText(str_arr[it]);
            text_view.setTextColor(Color.BLACK);

            layout.addView(text_view);

            ConstraintSet set = new ConstraintSet();
            set.clone(layout);

            if (prevTempId == ConstraintSet.PARENT_ID) {
                set.connect(
                        text_view.getId(),
                        ConstraintSet.TOP,
                        prevTempId,
                        ConstraintSet.TOP
                );
            } else {
                set.connect(
                        text_view.getId(),
                        ConstraintSet.TOP,
                        prevTempId,
                        ConstraintSet.BOTTOM
                );
                if (it == (str_arr.length - 1)) {
                    set.connect(
                            text_view.getId(),
                            ConstraintSet.BOTTOM,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.BOTTOM
                    );
                }
            }
            set.connect(
                    prevTempId,
                    ConstraintSet.BOTTOM,
                    text_view.getId(),
                    ConstraintSet.TOP
            );
            set.connect(
                    text_view.getId(),
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
            );
            set.connect(
                    text_view.getId(),
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
            );
            set.applyTo(layout);
            prevTempId = text_view.getId();
        }
    }
}