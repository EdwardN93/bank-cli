package bankApp;
import java.util.Scanner;

public class Main {


	
    public static void main(String[] args) {	
    
        Scanner myObj = new Scanner(System.in);
        int selected = 0;
        
        Account acc = null;
        
        System.out.println("Welcome to Edward Bank");
        System.out.println("To get starded, enter your name");
        String name = myObj.nextLine();
        int deposit = readInt(myObj, "How much to deposit?");
        
        System.out.println("Your info: ");
        System.out.println("Name: " + name + "\nDeposited sum: " + deposit);
        System.out.println("Please note, after creation of account you must change your pin first in order to activate your bank account");
        System.out.println("Enter Y to confirm");
        String confirm = myObj.nextLine();
        
        
        if(confirm.equalsIgnoreCase("y")) {
        	acc = new Account(name, deposit);
        }
        
        if(acc == null) {
        	System.out.println("Account not created");
        	return;
        }
        
        while (true) {
            System.out.println("\nSelect from menu: ");
            System.out.println("1. Show Account Info");
            System.out.println("2. Add balance");
            System.out.println("3. Withdraw");
            System.out.println("4. Show balance");
            System.out.println("5. Change pin");
            if(acc.isAccountActive()) {            	
            	System.out.println("6. Deactivate account");
            }
            System.out.println("0. Exit");

            String select = myObj.nextLine();
            String block = acc.canOperateMessage();
            try {
            	selected = Integer.parseInt(select);

                if (selected == 1) {
                    System.out.println(acc.getInfo());
                } else if (selected == 2) {
                	
                	if(requireAccess(acc, myObj)) {
                			System.out.println("Enter amount to add: ");
                			int amount = Integer.parseInt(myObj.nextLine());
                			System.out.println(acc.addBalance(amount));
                	}
                	
                } else if(selected == 3) {
                	if(requireAccess(acc,myObj)) {	
                		System.out.println("Enter amount to withdraw: ");
                		int amount = Integer.parseInt(myObj.nextLine());
                		System.out.println(acc.withdraw(amount));
                	}
                	
                } else if (selected == 4) {
                	if(requireAccess(acc, myObj)) {	
                		System.out.println(acc.getBalance());
                	}
                } else if(selected == 5){
                	System.out.println("Change your pin: ");
                	int pin = Integer.parseInt(myObj.nextLine());
                	System.out.println(acc.changePin(pin));
                } else if(selected == 6) {
                	System.out.println("Are you sure you want to deactivate your account? Y/N");
                	String conf= myObj.nextLine();
                	if(conf.equalsIgnoreCase("y")) {
                		System.out.println(acc.deactivateAccount());
                		break;
                	} else continue;
                }
                
                else if(selected == 0) break;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        System.out.println("Goodbye!");
        myObj.close();
    }
    
    
    public static boolean requireAccess(Account acc, Scanner sc) {

        String block = acc.canOperateMessage();
        if (block != null) {
            System.out.println(block);
            return false;
        }

        System.out.println("Enter pin:");
        int tryPin = Integer.parseInt(sc.nextLine());

        String access = acc.checkPin(tryPin);
        System.out.println(access);

        return access.equals("Login successful");
    }
    
    
    public static int readInt(Scanner sc, String msg) {
        while (true) {
            System.out.println(msg);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } else {
                System.out.println("Invalid number");
                sc.nextLine();
            }
        }
    }
}
