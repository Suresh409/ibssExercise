package glue;

import account.Account;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;

public class AccountSteps {

    Account account = null;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {
        throw new PendingException();
    }

}
