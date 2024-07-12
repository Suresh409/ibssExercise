package account;

import io.cucumber.java.Scenario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class StatementGenerator {

  private static final Logger logger = Logger.getLogger(StatementGenerator.class.getName());
  
  public void generateStatement(Account account,Scenario scenario) {
    logger.info("Account Statement");
    logger.info("-----------------");
    logger.info("Name: " + account.getName());
    logger.info("Account: " + account.getAccountNumber());
    logger.info("Balance:" + String.format("%.2f", account.getBalance()));
    logger.info("Transactions:");
    logger.info(" Type       | Amount");
    logger.info("------------|-----------");

    List<Transactions> transactions = account.getTransactions();
    for (Transactions transaction : transactions) {
      System.out.printf("%s | %-10.2f%n",transaction.getDescription(),
          transaction.getAmount());
    }
    //below method will write the statement into text file
    generateAccountStatementIntoTextFile(account,scenario);

  }

  public String statementToString(Account account) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Name: %s%nAccount: %s%nBalance: %.2f%n", account.getName(), account.getAccountNumber(), account.getBalance()));
    sb.append("Transactions:\n");
    sb.append(String.format("%-10s %-10s", "Type", "Amount"));
    List<Transactions> transactions = account.getTransactions();
    for (Transactions transaction : transactions) {
      sb.append("\n").append(String.format("%-10s %-10s", transaction.getDescription(), transaction.getAmount()));
    }

    return sb.toString();

  }

  public  void generateAccountStatementIntoTextFile(Account account,Scenario scenario) {
    String statement = "Account Statement:\n";
    statement += "==================\n";
    statement += statementToString(account);
    scenario.log(statement);

    try (FileWriter writer = new FileWriter("account_statement.txt")) {
      writer.write(statement);
      logger.info("Statement has been written to account_statement.txt");
    } catch (IOException e) {
      logger.severe("Error writing to file: " + e.getMessage());
    }
  }

  public String readStatementFromFileToString(String filePath) {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append(System.lineSeparator());
      }
    } catch (IOException e) {
    logger.severe("Unable to read the statement from file "+filePath + " Reason: "+e.getMessage());
    }
    return content.toString();
  }

}
