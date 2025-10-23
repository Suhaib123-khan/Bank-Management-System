package myPackage1;
import java.util.*;

import java.time.format.DateTimeFormatter;
class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message){
        super(message);
    }
}

class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(String message){
        super(message);
    }
}

class BankAccount{
    private String accountHolder;
    private String accountNumber;
    private String accountType;
    private double balance;
    private String bankBranch;
    private String ifscCode;

    public BankAccount(String accountHolder,String accountNumber,String accountType,double balance,String bankBranch,String ifscCode){
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.bankBranch = bankBranch;
        this.ifscCode = ifscCode;
    }

    public void deposit(double amount) {
        if(amount<=0){
            throw new InvalidAmountException("Invalid amount entered! "+amount);
        }
        balance +=amount;
    }

    public void withdraw(double amount) {
        if(amount<=0){
            throw new InvalidAmountException("Invalid amount entered! "+amount);
        }
        if(amount>balance){
            throw new InsufficientBalanceException("Insufficient balance! "+amount+" ::  Your Current Balance : "+balance);
        }
        balance -= amount;
    }
    public void checkAccountDetails(){

        System.out.println("Account Holder Name: "+accountHolder);
        System.out.println("Account Number     : "+accountNumber);
        System.out.println("Account Type       : "+accountType);
        System.out.println("Current Balance    : "+balance);
        System.out.println("Bank Branch        : "+bankBranch);
        System.out.println("IFSC Code          : "+ifscCode);

    }
}
public class BankingSystem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("---------Create Bank Account--------");
        System.out.println("Enter Bank details-----------------!");
        System.out.print("ACCOUNT HOLDER NAME : ");
        String nam =sc.nextLine();
        System.out.print("ACCOUNT  NUMBER : ");
        String num =sc.next();
        System.out.print("ACCOUNT  TYPE   : ");
        String typ =sc.next();
        System.out.print("CURRENT BALANCE : ");
        double bal =sc.nextDouble();
        System.out.print("BRANCH          : ");
        String brn =sc.next();
        System.out.print("IFSC CODE       : ");
        String ifc =sc.next();
        System.out.println("----Account Created Successfully!----");
        BankAccount acc = new BankAccount(nam,num,typ,bal,brn,ifc);

        System.out.println("----------Banking Menu--------------");
        System.out.println("Press (1) = For Deposit Amount");
        System.out.println("Press (2) = For Withdraw Amount");
        System.out.println("Press (3) = For Check Account");
        System.out.println("Press (0) = Exit ");
        boolean running = true;
        while (running) {
            System.out.println("------------------------------------");
            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("------------Exit--------------------");
                    running=false;
                    break;
                case 1:
                    System.out.print("Enter Deposit Amount : ");
                    double rsD = sc.nextDouble();
                    try {
                        acc.deposit(rsD);
                    }catch (InvalidAmountException e){
                        System.out.println("Deposit denied :: Amount must be greater than Zero!");
                    }
                    break;
                case 2:
                    System.out.print("Enter Withdraw Amount : ");
                    double rsW = sc.nextDouble();
                    try {
                        acc.withdraw(rsW);
                    }catch (InvalidAmountException e){
                        System.out.println("Withdraw denied :: Amount must be greater than Zero!");
                    }catch (InsufficientBalanceException e){
                        System.out.println("Withdraw denied :: Account balance insufficient");
                    }
                    break;
                case 3:
                    System.out.println("--------Account History-------------");
                    acc.checkAccountDetails();
                    break;
                default:
                    System.out.println("Invalid Choice -- Please select option  0 ,1 ,2 ,3 ");
                    break;
            }
        }
    }
}
