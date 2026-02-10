package com.example.resumetemplateapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class DescriptionActivity extends AppCompatActivity {

    TextInputEditText descriptionInput;
    Button clearButton;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_description);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        defaultHintAndValueSetter();

        clearButton.setOnClickListener((v) -> {
            descriptionInput.setText(null);
            setResult(RESULT_CANCELED);
            finish();
        });

        submitButton.setOnClickListener((v) -> {
            String value = descriptionInput.getText().toString();

            if (!(value.trim().isEmpty()))
            {
                Intent i = new Intent();
                String key = i.getStringExtra(keys.universal_key);
                if (key != null) {
                    switch (key) {
                        case (keys.name_key):
                            i.putExtra(keys.universal_key, keys.name_key);
                            i.putExtra(keys.name_key, value);
                            break;

                        case (keys.email_key):
                            i.putExtra(keys.universal_key, keys.email_key);
                            i.putExtra(keys.email_key, value);
                            break;

                        case (keys.ph_no_key):
                            i.putExtra(keys.universal_key, keys.ph_no_key);
                            i.putExtra(keys.ph_no_key, value);
                            break;

                        case (keys.address_key):
                            i.putExtra(keys.universal_key, keys.address_key);
                            i.putExtra(keys.address_key, value);
                            break;

                        case (keys.objective_key):
                            i.putExtra(keys.universal_key, keys.objective_key);
                            i.putExtra(keys.objective_key, value);
                            break;
                    }
                }
                setResult(RESULT_OK, i);
                finish();
            }
            else
            {
                Toast.makeText(DescriptionActivity.this, "Please Enter Before Submit", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void init()
    {
        descriptionInput = findViewById(R.id.descriptiveInput);
        submitButton = findViewById(R.id.descriptionSubmitBtn);
        clearButton = findViewById(R.id.descriptionClearBtn);
    }

    private void defaultHintAndValueSetter()
    {
        Intent i = getIntent();
        descriptionInput.setHint(i.getStringExtra(keys.hint_key));
        String key = i.getStringExtra(keys.universal_key);
        String value = null;
        if (key != null) {
            switch (key) {
                case (keys.name_key):
                    value = i.getStringExtra(keys.name_key);
                    break;

                case (keys.email_key):
                    value = i.getStringExtra(keys.email_key);
                    break;

                case (keys.ph_no_key):
                    value = i.getStringExtra(keys.ph_no_key);
                    break;

                case (keys.address_key):
                    value = i.getStringExtra(keys.address_key);
                    break;

                case (keys.objective_key):
                    value = i.getStringExtra(keys.objective_key);
                    break;
            }

            if (value != null)
            {
                descriptionInput.setText(value);
            }
        }
    }
}