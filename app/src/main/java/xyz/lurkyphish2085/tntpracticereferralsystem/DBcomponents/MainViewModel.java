package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel implements OperationsInterface {
    private MainRepository repository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<List<ReferenceKey>> allReferences;

    private boolean isTrue;



    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new MainRepository(application);
        isTrue = false;

        allUsers = repository.getAllUsers();
        allReferences = repository.getAllReferences();
        allTransactions = repository.getAllTransactions();
    }


    //region BaseActivity
    //region CUD
    @Override
    public void insert(User user) {
        repository.insert(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public void update(User user) {
        repository.update(user);
    }

    @Override
    public void insert(Transaction transaction) {
        repository.insert(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        repository.delete(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        repository.update(transaction);
    }

    @Override
    public void insert(ReferenceKey referenceKey) {
        repository.insert(referenceKey);
    }

    @Override
    public void delete(ReferenceKey referenceKey) {
        repository.delete(referenceKey);
    }

    @Override
    public void update(ReferenceKey referenceKey) {
        repository.update(referenceKey);
    }
    //endregion

    //region FrontEnd UI
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<ReferenceKey>> getAllReferences() {
        return allReferences;
    }

    public LiveData<List<User>> VerifyUpdate(String name, String pass, int id){
        return repository.VerifyUpdate(name, pass, id);
    }
    public LiveData<List<User>> getUserByName(String name){
        return repository.getUserByName(name);
    }
    @Override
    public LiveData<List<Transaction>> getTransactionByUser(int UserKey) {
        return repository.getTransactionByUser(UserKey);
    }

    @Override
    public LiveData<List<ReferenceKey>> getReferenceByUserKey(int UserKey) {
        return repository.getReferenceByUserKey(UserKey);
    }

    @Override
    public LiveData<List<ReferenceKey>> getReferenceByCode(String code) {
        return repository.getReferenceByCode(code);
    }

    public LiveData<List<State>> getCurrentState(){
        return repository.getCurrentState();
    }
    //endregion

    public void logoutState(){
        repository.logoutState();
    }

    public void insertState(State state){
        repository.insert(state);
    }

    public void updateState(State state){
        repository.update(state);
    }
    //endregion

}

