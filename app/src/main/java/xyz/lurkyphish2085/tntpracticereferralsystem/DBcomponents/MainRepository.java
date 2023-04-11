package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MainRepository implements OperationsInterface {
    private UsrDao usrDao;
    private TransactionDao transactionDao;
    private ReferenceKeyDao referenceKeyDao;
    private StateDao stateDao;

    private LiveData<List<User>> allUsers;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<List<ReferenceKey>> allReferences;

    public MainRepository(Application application) {
        MainDatabase db = MainDatabase.getInstance(application);
        usrDao = db.usrDao();
        transactionDao = db.transactionDao();
        referenceKeyDao = db.referenceKeyDao();
        stateDao = db.stateDao();

        allUsers = usrDao.getAllUsers();
        allTransactions = transactionDao.getAllTransactions();
        allReferences = referenceKeyDao.getAllReferences();
    }

    @Override
    public void insert(User user) {
        new AsyncRepoTask(usrDao, 1, user).execute();
    }

    @Override
    public void delete(User user) {
        new AsyncRepoTask(usrDao, 3, user).execute();
    }

    @Override
    public void update(User user) {
        new AsyncRepoTask(usrDao, 2, user).execute();
    }

    @Override
    public void insert(Transaction transaction) {
        new AsyncRepoTask(transactionDao, 4, transaction).execute();
    }

    @Override
    public void delete(Transaction transaction) {
        new AsyncRepoTask(transactionDao, 6, transaction).execute();
    }

    @Override
    public void update(Transaction transaction) {
        new AsyncRepoTask(transactionDao, 5, transaction).execute();
    }

    @Override
    public void insert(ReferenceKey referenceKey) {
        new AsyncRepoTask(referenceKeyDao, 7, referenceKey).execute();
    }

    @Override
    public void delete(ReferenceKey referenceKey) {
        new AsyncRepoTask(referenceKeyDao, 9, referenceKey).execute();
    }

    @Override
    public void update(ReferenceKey referenceKey) {
        new AsyncRepoTask(referenceKeyDao, 8, referenceKey).execute();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<ReferenceKey>> getAllReferences() {
        return allReferences;
    }

    @Override
    public LiveData<List<Transaction>> getTransactionByUser(int UserKey) {
        return transactionDao.getTransactionByUser(UserKey);
    }

    @Override
    public LiveData<List<ReferenceKey>> getReferenceByUserKey(int UserKey) {
        return referenceKeyDao.getReferenceByUserKey(UserKey);
    }

    @Override
    public LiveData<List<ReferenceKey>> getReferenceByCode(String code) {
        return referenceKeyDao.getReferenceByCode(code);
    }

    public LiveData<List<User>> getUserByName(String name){
        return usrDao.getUserByName(name);
    }

    public void insert(State state){
        new AsyncRepoTask(stateDao, 10, state).execute();
    }

    public void update(State state){
        new AsyncRepoTask(stateDao, 11, state).execute();
    }

    public void logoutState(){
        new AsyncRepoTask(stateDao, 12, new State()).execute();
    }

    public LiveData<List<State>> getCurrentState(){
        return stateDao.getStates();
    }

    public LiveData<List<User>> VerifyUpdate(String name, String pass, int id){
        return usrDao.verifyUpdate(name, pass, id);
    }


    private static class AsyncRepoTask extends AsyncTask<Void, Void, Void>{
        private UsrDao usrDao;
        private TransactionDao transactionDao;
        private ReferenceKeyDao referenceKeyDao;
        private int mode;
        private User user;
        private Transaction transaction;
        private ReferenceKey referenceKey;
        private StateDao stateDao;
        private State state;


        public AsyncRepoTask(UsrDao usrDao, int mode, User user) {
            this.usrDao = usrDao;
            this.mode = mode;
            this.user = user;
        }

        public AsyncRepoTask(TransactionDao transactionDao, int mode, Transaction transaction) {
            this.transactionDao = transactionDao;
            this.mode = mode;
            this.transaction = transaction;
        }

        public AsyncRepoTask(ReferenceKeyDao referenceKeyDao, int mode, ReferenceKey referenceKey) {
            this.referenceKeyDao = referenceKeyDao;
            this.mode = mode;
            this.referenceKey = referenceKey;
        }

        public AsyncRepoTask(StateDao stateDao, int mode, State state) {
            this.stateDao = stateDao;
            this.mode = mode;
            this.state = state;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            switch(mode){
                case 1:
                    usrDao.insert(user);
                    break;
                case 2:
                    usrDao.update(user);
                    break;
                case 3:
                    usrDao.delete(user);
                    break;
                case 4:
                    transactionDao.insert(transaction);
                    break;
                case 5:
                    transactionDao.update(transaction);
                    break;
                case 6:
                    transactionDao.delete(transaction);
                    break;
                case 7:
                    referenceKeyDao.insert(referenceKey);
                    break;
                case 8:
                    referenceKeyDao.update(referenceKey);
                    break;
                case 9:
                    referenceKeyDao.delete(referenceKey);
                    break;
                case 10:
                    stateDao.insert(state);
                    break;
                case 11:
                    stateDao.update(state);
                    break;
                case 12:
                    stateDao.logoutState();
                    break;
            }
            return null;
        }
    }
}
