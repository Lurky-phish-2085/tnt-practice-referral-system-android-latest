package xyz.lurkyphish2085.tntpracticereferralsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import java.sql.SQLOutput;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.State;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.User;


public class OnBoardingActivity extends AppCompatActivity {

    MainViewModel mvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        mvm = new ViewModelProvider(this).get(MainViewModel.class);
        String Password = "Adminc";
    }
}