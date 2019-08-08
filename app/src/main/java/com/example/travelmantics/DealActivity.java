package com.example.travelmantics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DealActivity extends AppCompatActivity {

        private FirebaseDatabase mFireBaseDataBase;
        private DatabaseReference mDataBaseReference;
        EditText txtTitle;
        EditText txtPrice;
        EditText txtDescription;
        TravelDeal deal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        FirebaseUtil.openFbReference("TravelDeals");
        mFireBaseDataBase = FirebaseUtil.mFireBaseDatabase;
        mDataBaseReference = FirebaseUtil.mDataBaseReference;
            txtTitle = findViewById(R.id.txt_title);
            txtDescription = findViewById(R.id.txt_description);
            txtPrice = findViewById(R.id.txt_price);
        Intent intent = getIntent();
        TravelDeal deal = (TravelDeal) intent.getSerializableExtra("Deal");
        if (deal == null){
            deal = new TravelDeal();
        }this.deal = deal;
        txtTitle.setText(deal.getTitle());
        txtDescription.setText(deal.getDescription());
        txtPrice.setText(deal.getPrice());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemSelected = item.getItemId();
        switch (itemSelected){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_SHORT).show();
                clean();
                backToList();
                return true;
            case R.id.delete_menu:
                deleteDeal();
                Toast.makeText(this, "Deal Deleted", Toast.LENGTH_SHORT).show();
                backToList();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void saveDeal() {
        deal.setTitle(txtTitle.getText().toString());
        deal.setDescription(txtDescription.getText().toString());
        deal.setDescription(txtPrice.getText().toString());
        if (deal.getId() == null){
            mDataBaseReference.push().setValue(deal);
        }else {
            mDataBaseReference.child(deal.getId()).setValue(deal);
        }

    }
    private void deleteDeal(){
        if (deal == null){
            Toast.makeText(this, "Please make a deal before deleting", Toast.LENGTH_SHORT).show();
            return;
        }mDataBaseReference.child(deal.getId()).removeValue();
    }
    private void backToList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    private void clean(){
         txtPrice.setText("");
         txtDescription.setText("");
         txtTitle.setText("");
         txtTitle.requestFocus();
    }
}
