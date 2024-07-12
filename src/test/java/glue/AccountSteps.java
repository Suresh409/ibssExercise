package glue;

import account.Account;
import account.StatementGenerator;
import account.Transactions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.junit.Assert;

public class AccountSteps {

    Account account = new Account();
    StatementGenerator generator;
    Scenario scenario;

    @Before
    public void beforeStep(Scenario scenario) {
        this.scenario = scenario;
    }
    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {
            account.setAccountNumber(number);
            account.setName(name);
    }

    @And("deposits are made")
    public void depositsAreMade(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        for (List<String> columns : rows) {
            Transactions transactions = new Transactions();
            transactions.setDescription(columns.get(0));
            transactions.setAmount(Double.parseDouble(columns.get(1)));
            account.addDepositTransaction(transactions);
        }
    }

    @And("withdrawls are made")
    public void withdrawlsAreMade(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        for (List<String> columns : rows){
            Transactions transactions = new Transactions();
             transactions.setDescription(columns.get(0));
             transactions.setAmount(Double.parseDouble(columns.get(1)));
             account.addWithdrawTransaction(transactions);
        }
    }

    @When("statement is produced")
    public void statementIsProduced() {
        generator = new StatementGenerator();
        generator.generateStatement(account,scenario);
    }

    @Then("statement includes {string}")
    public void statementIncludes(String var1) {
        String statementFile = generator.readStatementFromFileToString("account_statement.txt");
        boolean dataExists = statementFile.contains(var1);
        Assert.assertTrue(dataExists);
    }
}
