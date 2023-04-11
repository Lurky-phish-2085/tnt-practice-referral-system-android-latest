package xyz.lurkyphish2085.tntpracticereferralsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.State;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.User;

public class UserSettingsFragment extends Fragment implements View.OnClickListener {

    NavController navController;

    MainViewModel mvm;
    TextView oldUserNameTv;
    EditText newUserNameEt, newPassEt;
    Button saveBtn;

    public UserSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        mvm = new ViewModelProvider(this).get(MainViewModel.class);

        oldUserNameTv = view.findViewById(R.id.settings_old_username_tv);
        newUserNameEt = view.findViewById(R.id.new_username_et);
        newPassEt = view.findViewById(R.id.new_password_et);
        saveBtn = view.findViewById(R.id.update_user_btn);
        saveBtn.setOnClickListener(this);

        // ToDo: Logic for update the textview with the username
        mvm.getCurrentState().observe(getViewLifecycleOwner(), states -> {
            String username = states.get(0).getName();
            oldUserNameTv.setText(username);
        });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mvm.getCurrentState().observe(getViewLifecycleOwner(), states -> {
            String username = states.get(0).getName();
            oldUserNameTv.setText(username);
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == saveBtn.getId()) {
            // ToDo: Logic for saving the input as an update for the session user

            mvm.getCurrentState().observe(this, states -> {
                for(State i : states){
                    if(i.getName().equals(newUserNameEt.getText().toString()) && i.getPassword().equals(newPassEt.getText().toString())){
                        navController.navigate(R.id.action_userSettingsFragment_to_dashboardFragment);
                    }
                    else{
                        i.setName(newUserNameEt.getText().toString());
                        i.setPassword(newPassEt.getText().toString());
                        mvm.updateState(i);
                        User append = new User(newPassEt.getText().toString(), newUserNameEt.getText().toString());
                        append.setId(i.getCurrentUserID());
                        mvm.update(append);
                        //User and state updated
                        System.out.println("User and state updated");
                    }
                }
            });


        }
    }
}