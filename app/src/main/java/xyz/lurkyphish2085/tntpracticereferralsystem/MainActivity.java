package xyz.lurkyphish2085.tntpracticereferralsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.ReferenceKey;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.Transaction;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.User;

public class MainActivity extends AppCompatActivity {

    xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel mvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("OnCreate");
        mvm = new ViewModelProvider(this).get(MainViewModel.class);
        mvm.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                System.out.println("Test");
                mvm.getCurrentState().observe(MainActivity.this, states ->{
                    System.out.println(states.size());
                });
                for(User i : users) {
                    System.out.println("Username :" + i.getName());
                    System.out.println("Password :" + i.getPassword());
                    System.out.println("------------------- References -------------------");
                    mvm.getReferenceByUserKey(i.getId()).observe(xyz.lurkyphish2085.tntpracticereferralsystem.MainActivity.this, referenceKeys -> {
                        for(ReferenceKey j : referenceKeys){
                            System.out.println("Reference code: " + j.getCode());
                        }
                    });
                    System.out.println("-------------------Transactions-------------------");
                    mvm.getTransactionByUser(i.getId()).observe(xyz.lurkyphish2085.tntpracticereferralsystem.MainActivity.this, transactions -> {
                        for(Transaction k : transactions){
                            System.out.println("Transaction source: " + k.getSource());
                            System.out.println("Transaction amount: " + k.getAmount());
                        }
                    });
                }
            }
        });


        mvm.getCurrentState().observe(this, states ->{
            if(states.isEmpty()){
                Intent intent = new Intent(this, OnBoardingActivity.class);
                startActivity(intent);
                finish();
            }else{
                System.out.println("There is already a current state L");
                System.out.println(states.get(0).getId());
                System.out.println(states.get(0).getName());
                System.out.println(states.get(0).getPassword());

                Intent intent = new Intent(this, UserSessionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}