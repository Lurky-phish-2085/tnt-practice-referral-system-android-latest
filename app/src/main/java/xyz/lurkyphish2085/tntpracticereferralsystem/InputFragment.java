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

import java.text.SimpleDateFormat;
import java.util.Date;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.Transaction;

public class InputFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    Button addAmountBtn;
    EditText amount;

    MainViewModel mainViewModel;

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);
        addAmountBtn = view.findViewById(R.id.add_amount_btn);
        addAmountBtn.setOnClickListener(this);
        amount = view.findViewById(R.id.add_amount_et);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == addAmountBtn.getId()) {
            // ToDo: Logic to add amount to user
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/y h:m:s a");
            mainViewModel.getCurrentState().observe(getViewLifecycleOwner(), states -> {
                int currentUserID = states.get(0).getCurrentUserID();
                double toAdd = Double.parseDouble(amount.getText().toString());

                mainViewModel.insert(new Transaction("Bank transfer", toAdd, currentUserID, format.format(date)));
                mainViewModel.getTransactionByUser(currentUserID).observe(getViewLifecycleOwner(), transactions -> {
                    navController.navigate(R.id.action_inputFragment_to_dashboardFragment);
                });
            });
        }
    }
}