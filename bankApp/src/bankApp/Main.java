package bankApp;
import java.util.Scanner;

public class Main {


	
    public static void main(String[] args) {	
    
        Scanner myObj = new Scanner(System.in);
        AccountsList db = new AccountsList();
        int selected = 0;

        Account acc = startFlow(myObj, db);
        if (acc == null) return;


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
            System.out.println("7. Logout");
            System.out.println("0. Exit");

            String select = myObj.nextLine();

            try {
            	selected = Integer.parseInt(select);

                if (selected == 1) {
                    System.out.println(acc.getInfo());
                } else if (selected == 2) {
                	
                	if(requireAccess(acc, myObj)) {
                			System.out.print("Enter amount to add: ");
                			int amount = Integer.parseInt(myObj.nextLine());
                			System.out.println(acc.addBalance(amount));
                	}
                	
                } else if(selected == 3) {
                	if(requireAccess(acc,myObj)) {	
                		System.out.print("Enter amount to withdraw: ");
                		int amount = Integer.parseInt(myObj.nextLine());
                		System.out.println(acc.withdraw(amount));
                	}
                	
                } else if (selected == 4) {
                	if(requireAccess(acc, myObj)) {	
                		System.out.println(acc.getBalance());
                	}
                } else if(selected == 5){
                	System.out.print("Change your pin: ");
                	int pin = Integer.parseInt(myObj.nextLine());
                	System.out.println(acc.changePin(pin));
                } else if(selected == 6) {
                    if (!acc.isAccountActive()) {
                        System.out.println("Account is not active.");
                        continue;
                    }
                	System.out.print("Are you sure you want to deactivate your account? Y/N");
                	String conf= myObj.nextLine();
                	if(conf.equalsIgnoreCase("y")) {
                		System.out.println(acc.deactivateAccount());
                		break;
                	} else continue;
                } else if (selected == 7) {
                    System.out.println("Logged out.");
                    acc = startFlow(myObj, db);
                    if (acc == null) break;
                    continue;
                }
                
                else if(selected == 0) break;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        System.out.println("Goodbye!");
        myObj.close();
    }

    private static Account startFlow(Scanner sc, AccountsList db){
        while (true) {
            System.out.println("\n1. Create account");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Select: ");

            String opt = sc.nextLine();

            if (opt.equals("1")) {
                Account created = createAccount(sc, db);
                if (created != null) return created;
                continue;
            }

            if (opt.equals("2")) {
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Pin: ");
                int pin = Integer.parseInt(sc.nextLine());

                Account acc = db.login(email, pin);
                if (acc != null) {
                    System.out.println("Login successful!");
                    return acc;
                } else {
                    System.out.println("Invalid email or pin (or account locked).");
                }
            }

            if (opt.equals("0")) {
                return null;
            }

            System.out.println("Invalid option.");
        }
    }

    private static Account createAccount(Scanner sc, AccountsList db) {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Deposit: ");
        int deposit = Integer.parseInt(sc.nextLine());

        System.out.print("Confirm create account? (Y/N): ");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Account not created");
            return null;
        }

        Account acc = new Account(name, email, deposit);
        db.addAccount(acc);
        System.out.println("Account created!");
        return acc;
    }


    public static boolean requireAccess(Account acc, Scanner sc) {

        String block = acc.canOperateMessage();
        if (block != null) {
            System.out.println(block);
            return false;
        }

        System.out.print("Enter pin: ");
        int tryPin = Integer.parseInt(sc.nextLine());

        boolean access = acc.checkPin(tryPin);

        if(!access){
            System.out.println("Wrong pin");
        }

        return access;
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
