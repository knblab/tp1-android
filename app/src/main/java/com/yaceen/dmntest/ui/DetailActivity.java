package com.yaceen.dmntest.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yaceen.dmntest.model.Contact;
import com.yaceen.dmntest.data.DataBase;
import com.yaceen.dmntest.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent detail = getIntent();
        int position = detail.getIntExtra("position", -1);

        Contact contact = DataBase.contactList.get(position);

        TextView name = findViewById(R.id.tvname);
        TextView phone = findViewById(R.id.tvphone);

        name.setText(contact.getName());
        phone.setText(contact.getPhone());

        Button deleteItem = findViewById(R.id.btndelete);
        deleteItem.setOnClickListener( v -> {
            new AlertDialog.Builder(this).setTitle("Title")
                    .setMessage("azertyui")
                    .setPositiveButton("Delete", (d,w) -> {
                        // delete code here
                        DataBase.contactList.remove(position);
                        //Intent back = new Intent(this, ContactListActivity.class);
                        //startActivity(back);
                        finish();
                    })
                    .setNegativeButton("Cancel",null)
                    .show();

        });
    }
}