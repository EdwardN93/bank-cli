package bankApp;

import java.util.ArrayList;

public class AccountsList {
    private ArrayList<Account> accountsDb = new ArrayList<>();

    public ArrayList<Account> getAccountsDb(){
        return new ArrayList<>(accountsDb);
    }

    public void addAccount(Account newAccount){
        accountsDb.add(newAccount);
    }

    public void printAllAccounts(){
        for(Account acc : accountsDb){
            System.out.println(acc.getInfo());
        }
    }

    public Account login(String email, int pin){
        for(Account acc : accountsDb){
            if(acc.getEmail().equals(email) && acc.getPin() == pin){
                return acc;
            }
        }
        return null;
    }

}
