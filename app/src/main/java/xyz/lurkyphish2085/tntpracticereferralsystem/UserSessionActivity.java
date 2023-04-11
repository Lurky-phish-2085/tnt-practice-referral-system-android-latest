package xyz.lurkyphish2085.tntpracticereferralsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;

public class UserSessionActivity extends AppCompatActivity {

    public int currentUserID = 0;
    public String password;
    public String reference_code;
    public String username;

    MainViewModel mvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_session);
        mvm = new ViewModelProvider(this).get(MainViewModel.class);
        initializeFunction();
    }

    public void initializeFunction(){
        System.out.println("------- My InitializeFunction -----------");
        if(currentUserID == 0){
            mvm.getCurrentState().observe(this, states -> {
                if(!states.isEmpty()){
                    currentUserID = states.get(0).getId();
                    password = states.get(0).getPassword();
                    username = states.get(0).getName();
                }
            });

        }


    }

}