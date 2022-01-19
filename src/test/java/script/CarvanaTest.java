package script;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

import java.util.List;

public class CarvanaTest extends Base {
    @Test(testName = "Validate Carvana home page title and url", priority = 1)
    public void validateCarvanaHomePageTitleAndUrl() {
        driver.get("https://www.carvana.com/");
        Assert.assertEquals( driver.getTitle(), "Carvana | Buy & Finance Used Cars Online | At Home Delivery");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/");
    }

    @Test(testName = "Validate the Carvana logo", priority = 2)
    public void validateTheCarvanaLogo() {
        driver.get("https://www.carvana.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("div[data-qa='logo-wrapper']")).isDisplayed());// Logo is displayed
    }

    @Test(testName = "Validate the main navigation section items", priority = 3)
    public void validateTheMainNavigationSectionItems() {
        driver.get("https://www.carvana.com/");//URL page
        List<WebElement> carvanaDropdowns = driver.findElements(By.cssSelector("div[class*='__MenuWrapper-']"));//carvanaDropdowns(4)
        String[] expectedDropdowns = {"HOW IT WORKS", "ABOUT CARVANA", "SUPPORT & CONTACT"};

        for (int i = 0; i < carvanaDropdowns.size() -1; i++) {
            Assert.assertTrue(carvanaDropdowns.get(i).isDisplayed()); //check each one if it's displayed
            Waiter.waitForElementToBeVisible(driver, 5, carvanaDropdowns.get(i));
            Assert.assertEquals(carvanaDropdowns.get(i).getText(), expectedDropdowns[i]); // Text as expected
        }
    }

    @Test(testName = "Validate the sign in error message", priority = 4)
    public void validateTheSignInErrorMessage() {
        driver.get("https://www.carvana.com/");//URL page
        driver.findElement(By.cssSelector("div[data-qa='desktop-wrapper']")).click(); //Sign in button

        WebElement signInModal = driver.findElement(By.cssSelector("div[class*='ModalContent-qk9d85']"));
        Assert.assertTrue(signInModal.isDisplayed());// validate Sign in Modal


        driver.findElement(By.cssSelector("#usernameField")).sendKeys("johndoe@gmail.com");//email input
        driver.findElement(By.cssSelector("#passwordField")).sendKeys("abcd1234"); //password input

        driver.findElement(By.cssSelector("button[data-cv*='sign-in']")).click(); //sign in button

        Assert.assertTrue(driver.findElement(By.cssSelector("div[data-qa*='error-message']")).isDisplayed()); // Error message
    }

    @Test(testName = "Validate the search filter options and search button", priority = 5)
    public void validateTheSearchFilterOptionsAndSearchButton() {
        driver.get("https://www.carvana.com/");//URL page
        driver.findElement(By.cssSelector("a[data-cv-test*='.SearchLink']")).click();// *Search All Vehicles link*

        Waiter.pause(5);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars"); //make sure the Right URL

        WebElement searchInputBox = driver.findElement(By.cssSelector("input[data-qa='search-bar-input']"));
        Assert.assertTrue(searchInputBox.isDisplayed());// User can see it

        List<WebElement> filtersOption = driver.findElements(By.cssSelector("div[data-qa='menu-flex'] button"));
        String[] expectedFiltersOptionText = {"PAYMENT & PRICE", "MAKE & MODEL", "BODY TYPE", "YEAR & MILEAGE", "FEATURES", "MORE FILTERS"};

        for (int i = 0; i < filtersOption.size(); i++) {
            Assert.assertTrue(filtersOption.get(i).isDisplayed());// make sure of each one is displayed
            Assert.assertEquals(filtersOption.get(i).getText(), expectedFiltersOptionText[i]); // make sure of the text
        }

        searchInputBox.sendKeys("Tesla"); // Type in the Search box

        WebElement goButton = driver.findElement(By.cssSelector("button[data-qa='go-button']"));
        Assert.assertTrue(goButton.isDisplayed()); // Go button is displayed
        Assert.assertEquals(goButton.getText(), "GO");// Right text
    }

    @Test(testName = "Validate the search result tiles", priority = 6)
    public void validateTheSearchResultTiles() {
        driver.get("https://www.carvana.com/");//URL page
        driver.findElement(By.cssSelector("a[data-cv-test*='.SearchLink']")).click();// *Search All Vehicles link*

        Waiter.pause(5);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars"); //make sure the Right URL

        WebElement searchInputBox = driver.findElement(By.cssSelector("input[data-qa='search-bar-input']"));
        searchInputBox.sendKeys("mercedes-benz");//type mercedes-benz on input box

        WebElement goButton = driver.findElement(By.cssSelector("button[data-qa='go-button']"));
        goButton.click();
        Waiter.pause(5);

//      Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars/mercedes-benz?email-capture="); //Same URL
        Assert.assertTrue(driver.getCurrentUrl().contains("mercedes-benz")); //Contains "mercedes-benz" on URL

        List<WebElement> mercedesTiles = driver.findElements(By.className("tk-shell")); //size = 20
        List<WebElement> imagesOfMercedesCarsResults = driver.findElements(By.className("vehicle-image"));
        List<WebElement> favoriteMercedesCarsButtons = driver.findElements(By.className("favorite-icon"));
        List<WebElement> inventoryMercedesCarsResults = driver.findElements(By.cssSelector("div[class*='inventory']"));
        List<WebElement> yearMakeAndModelMercedesCarsResults = driver.findElements(By.className("year-make-experiment"));
        List<WebElement> trimMercedesCarsResults = driver.findElements(By.className("trim-mileage"));
        List<WebElement> pricesMercedesCarsResults = driver.findElements(By.className("price-variant"));
        List<WebElement> monthlyPaymentMercedesCarsResults = driver.findElements(By.className("monthly-payment"));
        List<WebElement> downPaymentMercedesCarsResults = driver.findElements(By.className("down-payment"));
        List<WebElement> deliveryChipMercedesCarsResults = driver.findElements(By.cssSelector(".delivery"));


        for (int i = 0; i < mercedesTiles.size(); i++) {
            Assert.assertTrue(mercedesTiles.get(i).isDisplayed()); // check each result title
            Assert.assertTrue(imagesOfMercedesCarsResults.get(i).isDisplayed()); //check each img result
            Assert.assertTrue(favoriteMercedesCarsButtons.get(i).isDisplayed()); //check each favorite result
            Assert.assertTrue(inventoryMercedesCarsResults.get(i).isDisplayed()); //check each inventory result
            Assert.assertNotNull(inventoryMercedesCarsResults.get(i).getText());
            Assert.assertTrue(yearMakeAndModelMercedesCarsResults.get(i).isDisplayed()); //check each yearMake&&Model result
            Assert.assertNotNull(yearMakeAndModelMercedesCarsResults.get(i).getText());
            Assert.assertTrue(trimMercedesCarsResults.get(i).isDisplayed()); //check each trim result
            Assert.assertNotNull(trimMercedesCarsResults.get(i).getText());
            Assert.assertTrue(Integer.parseInt(pricesMercedesCarsResults.get(i).getText()
                    .replaceAll("[^0-9]", "")) > 0); //check each price result
            Assert.assertTrue(monthlyPaymentMercedesCarsResults.get(i).isDisplayed()); //check each monthly payment result
            Assert.assertNotNull(monthlyPaymentMercedesCarsResults.get(i).getText());
            Assert.assertTrue(downPaymentMercedesCarsResults.get(i).isDisplayed()); //check each down payment result
            Assert.assertNotNull(downPaymentMercedesCarsResults.get(i).getText());
            Assert.assertTrue(deliveryChipMercedesCarsResults.get(i).isDisplayed()); //check each delivery Chip result
            Waiter.waitForElementToBeVisible(driver, 5, deliveryChipMercedesCarsResults.get(i));
            Assert.assertEquals(deliveryChipMercedesCarsResults.get(i).getText(), "Free Shipping"); //check each delivery Chip result text

        }
    }
}