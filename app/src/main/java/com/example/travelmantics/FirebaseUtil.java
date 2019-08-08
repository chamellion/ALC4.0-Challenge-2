package com.example.travelmantics;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUtil {
    public static FirebaseDatabase mFireBaseDatabase;
    public static DatabaseReference mDataBaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<TravelDeal> mDeals;

    private FirebaseUtil(){ }

    public static void openFbReference(String ref){
        if (firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFireBaseDatabase = FirebaseDatabase.getInstance();
        }
        mDeals = new ArrayList<TravelDeal>();
        mDataBaseReference = mFireBaseDatabase.getReference().child(ref);
    }

}
