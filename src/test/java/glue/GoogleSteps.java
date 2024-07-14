package glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.logging.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSteps {
  private static final Logger logger = Logger.getLogger(GoogleSteps.class.getName());

 String homePage_about_Link = "//div[@role='navigation']/a[contains(@href,'about')]";
 String aboutPage_headerText = "//h1[@class='modules-lib__mission-statement__headline glue-headline glue-headline--fluid-2']";
 String homePage_SearchBox = "//textarea[@title='Search']";
 String homePage_GoogleSearch_Btn = "//form[@role='search']/div/div/div[2]//input[@value='Google Search']";
 String homePage_Tools_link = "tools_1";
 String homePage_ResultStats = "result-stats";

  WebDriverWait wait= new WebDriverWait(W.get().driver, Duration.ofSeconds(10));

  @Given("url {string} is launched")
    public void url(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

  @When("About page is shown")
  public void aboutPageIsShown() {
    W.get().driver.findElement(By.xpath(homePage_about_Link)).click();
  }

  @Then("page displays {string}")
  public void pageDisplays(String expectedText) {
    String actualText= W.get().driver.findElement(By.xpath(aboutPage_headerText)).getText();
    Assert.assertEquals(expectedText,actualText);
  }

  @When("searching for {string}")
  public void searchingFor(String searchText) {
   W.get().driver.findElement(By.xpath(homePage_SearchBox)).sendKeys(searchText);
   W.get().driver.findElement(By.xpath(homePage_GoogleSearch_Btn)).click();
  }

  @Then("results contain {string}")
  public void resultsContain(String expectedText) {
   String actual_HeaderTexts= W.get().driver.findElement(By.tagName("h3")).getText();
   logger.info("Actual Search results Text:"+actual_HeaderTexts);
   Assert.assertEquals(expectedText,actual_HeaderTexts);
  }

  @And("result stats are displayed")
  public void resultStatsAreDisplayed(){
    W.get().driver.findElement(By.id(homePage_Tools_link)).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(homePage_ResultStats)));
    Assert.assertTrue(W.get().driver.findElement(By.id(homePage_ResultStats)).isDisplayed());
  }

  @And("number of {string} is more than {int}")
  public void numberOfIsMoreThan(String expectedText, int expectedNumber) {
    String actualText = W.get().driver.findElement(By.id(homePage_ResultStats)).getText();
    logger.info("Actual Results Text: "+actualText);
    AccountSteps.scenario.log("Actual Results Text: "+actualText);
    String actualNumberOfRecodes = actualText.split(" ")[1];
    String actualTimeTaken = actualText.split(" ")[3].split( " ")[0].replaceAll("\\(","");
    if(expectedText.equalsIgnoreCase("results")) {
      Assert.assertTrue(parseAboutValue(actualNumberOfRecodes) >(long) expectedNumber);
    }
    if(expectedText.equalsIgnoreCase("seconds")) {
      Assert.assertTrue(parseResultsValue(actualTimeTaken) > (double) expectedNumber);
    }

  }
  private  long parseAboutValue(String aboutValue) {
    try {
      return Long.parseLong(aboutValue.replaceAll(",",""));
    } catch (NumberFormatException e) {
      throw new NumberFormatException();
    }
  }
  private  double parseResultsValue(String resultsValue) {
    try {
      return Double.parseDouble(resultsValue);
    } catch (NumberFormatException e) {
      throw new NumberFormatException();
    }
  }
}
