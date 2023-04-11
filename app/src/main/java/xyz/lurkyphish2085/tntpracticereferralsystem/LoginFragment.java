package xyz.lurkyphish2085.tntpracticereferralsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.State;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.User;

public class LoginFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    Button loginBtn, gotoRegister;
    EditText nameEt, passEt;
    MainViewModel mvm;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mvm = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        nameEt = view.findViewById(R.id.login_name_et);
        passEt = view.findViewById(R.id.login_pass_et);
        navController = NavHostFragment.findNavController(this);
        gotoRegister = view.findViewById(R.id.goto_register_btn);
        gotoRegister.setOnClickListener(this);
        loginBtn = view.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);

        mvm.getCurrentState().observe(getViewLifecycleOwner(), states -> {
            if(!states.isEmpty()){
                Intent intent = new Intent(getActivity(), UserSessionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mvm.getCurrentState().observe(getViewLifecycleOwner(), states -> {
            if(!states.isEmpty()){
                Intent intent = new Intent(getActivity(), UserSessionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == loginBtn.getId()) {
            // ToDo: Logic for validating user before moving to UserSession Activity
            String name = nameEt.getText().toString();
            String pass = passEt.getText().toString();
            mvm.getUserByName(name).observe(this, names ->{
                for(User i: names){
                    System.out.println(i.getName());
                    System.out.println(i.getId());
                    System.out.println(i.getPassword());
                    System.out.println("This is our password: " + pass);

                    if(i.getPassword().equals(pass)){
                        System.out.println("loginVerified");
                        mvm.insertState(new State(i.getId(), i.getPassword(), i.getName()));
                    }else{
                        System.out.println("loginNotVerified");
                    }
                }
            });

        }
        if (id == gotoRegister.getId()) {
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        }
    }
}