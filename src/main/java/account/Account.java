package account;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private String name;
  private String accountNumber;
  private  double balance;
  private List<Transactions> transactions = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  //Adding transactions to transactions list and also adding(SUM) the balance to the existing
  public void addDepositTransaction(Transactions transaction) {
      this.transactions.add(transaction);
      balance += transaction.getAmount();
  }
  //Adding transactions to transactions list and also subtracting the balance from the existing balance
  public void addWithdrawTransaction(Transactions transaction) {
      this.transactions.add(transaction);
      balance -= transaction.getAmount();
  }

  public List<Transactions> getTransactions() {
    return this.transactions;
  }
}
