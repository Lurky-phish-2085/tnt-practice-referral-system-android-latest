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

import java.util.Random;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.ReferenceKey;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.User;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    Button registerUserBtn;
    EditText etName, etPass, etRef;
    MainViewModel mvm;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mvm = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        registerUserBtn = view.findViewById(R.id.register_btn);
        registerUserBtn.setOnClickListener(this);
        etName = view.findViewById(R.id.register_name_et);
        etPass = view.findViewById(R.id.register_pass_et);
        etRef = view.findViewById(R.id.register_refcode_et);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == registerUserBtn.getId()) {
            // ToDo: Logic for registering the user in DB
            String name = etName.getText().toString();
            String pass = etPass.getText().toString();
            String ref = etRef.getText().toString();
            mvm.getReferenceByCode(ref).observe(getViewLifecycleOwner(), refs ->{
                if(!refs.isEmpty()){
                    mvm.insert(new User(pass, name));
                    mvm.getUserByName(name).observe(getViewLifecycleOwner(), names -> {
                        for(User i: names){
                            System.out.println("User Registered");
                            Random rand = new Random(i.getId());
                            mvm.insert(new ReferenceKey(String.valueOf(rand.nextInt(1000)), i.getId()));
                        }
                    });
                }
            });

            checkUpdate(name);
        }
    }

    private void checkUpdate(String name) {
        mvm.getUserByName(name).observe(getViewLifecycleOwner(), names -> {
            if(!names.isEmpty()){
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
    }
}