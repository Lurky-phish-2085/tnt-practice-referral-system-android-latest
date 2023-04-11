package xyz.lurkyphish2085.tntpracticereferralsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.Transaction;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    List<Transaction> transactionList;

    NavController navController;
    ImageButton gotoInput, logoutBtn, gotoSettings;
    TransactionAdapter adapter;
    MainViewModel mainViewModel;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.transaction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);


        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getCurrentState().observe(getViewLifecycleOwner(), states -> {
            if(!states.isEmpty()){
                mainViewModel.getTransactionByUser(states.get(0).getCurrentUserID()).observe(getViewLifecycleOwner(), new Observer<List<Transaction>>() {
                    @Override
                    public void onChanged(List<Transaction> transactions) {
                        // ToDo: KEY USER AND UPDATE ADAPTER STUFF
                        System.out.println(transactions.size());
                        transactionList = transactions;
                        adapter.setTransactionList(transactions);
                    }
                });
            }
        });

        navController = NavHostFragment.findNavController(this);
        gotoInput = view.findViewById(R.id.goto_input_btn);
        gotoInput.setOnClickListener(this);
        logoutBtn = view.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(this);
        gotoSettings = view.findViewById(R.id.goto_user_settings_btn);
        gotoSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == gotoInput.getId()) {
            // Todo: HARDCODED USERKEY
            Bundle bundle = new Bundle();
            bundle.putInt("user-Key", 1);
            navController.navigate(R.id.action_dashboardFragment_to_inputFragment, bundle);
        }
        if (id == logoutBtn.getId()) {
            mainViewModel.logoutState();
            Intent intent = new Intent(getActivity(), OnBoardingActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        if (id == gotoSettings.getId()) {
            navController.navigate(R.id.action_dashboardFragment_to_userSettingsFragment);
        }
    }
}