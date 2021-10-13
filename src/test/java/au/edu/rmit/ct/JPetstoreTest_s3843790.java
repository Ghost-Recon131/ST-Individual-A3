/**
 JPetstore JUnit/Webdriver Tasks:
 1) Check the pet name, price and check if there is stock for one pet of your choice (outside of Male Chihuahua). (as outlined for W11 prac)
 2) Start a menagerie! Select a specific fish, specific cat, and a third pet (they will have a unique item ID). Add 3 multiples of the first, 2 multiples of the cat, and one of the third pet to the cart. Check the subtotal matches the expected price. You are expected to show that this test case passes.  */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class JPetstoreTest_s3843790 {
    WebDriver myDriver;

    @Test
    // @Disabled
    @Order(0)
    @DisplayName("Sanity test only")
     void sanityTest1(){
        // When this passes I know I have the webdriver and Junit set up correctly
        String petStoreURL = "https://petstore.octoperf.com";
        myDriver.get(petStoreURL);
        assertEquals("JPetStore Demo", myDriver.getTitle());
    }

    @Test
    @Order(1)
    @DisplayName("Task 11.1 : Check that the price is $125.50")
    void checkChihuahuaPrice() {

        String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-26";
        myDriver.get(chihuahuaURL);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get value using Xpath, then check against expected value
        WebElement webElement = myDriver.findElement(By.xpath("//td[text()='$125.50']"));
        assertEquals("$125.50", webElement.getText(), "Should Equal $125.50");
    }

    @Test
    @Order(2)
    @DisplayName("Task 11.2 : Check that the product name is correct (Adult Male Chihuahua) for this product page")
    void checkChihuahuaProductName() {

        String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-26";
        myDriver.get(chihuahuaURL);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement webElement2 = myDriver.findElement(By.xpath("/html/body/div[@id='Content']/div[@id='Catalog']/table/tbody/tr[3]/td/b/font"));
        assertEquals("Adult Male Chihuahua", webElement2.getText(), "Should Equal Adult Male Chihuahua");
    }

    @Test
    @Order(3)
    @DisplayName("Task 11.3 Check that the adult male chihuahua is in stock. ( > 0 )")
    void checkChihuahuaStock() {

        String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-26";
        myDriver.get(chihuahuaURL);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement webElement3 = myDriver.findElement(By.xpath("/html/body/div[@id='Content']/div[@id='Catalog']/table/tbody/tr[5]/td"));

        // get the string value and only keep the numerical value, then convert to int for final junit comparison
        String stockOnly = webElement3.getText().replaceAll("[^0-9]", "");
        int StockInteger = -1;
        try{
            StockInteger = Integer.parseInt(stockOnly);
        }catch (NumberFormatException e){
            System.err.println("Invalid value, value was " + stockOnly);
        }
        boolean hasStock = StockInteger > 0;
        assertTrue(hasStock);
    }

    @Test
    @Order(4)
    @DisplayName("Check Price for another pet you might like to own at product page")
    void checkAnotherPetPrice() {

        // Write the tests for these below using JUnit assertEquals and the findElements method
        fail("Task 11.4 : Check that the price is ??? using JUnit assertEquals");
        fail("Task 11.5 : Check that the product name is correct (???) for this product page");
        fail("Task 11.6 Check that the ??? is in stock. ( > 0 )");

        /**
         * You will be asked to submit this for your Assignment 3 .
         */
    }

    @Test
    @Order(5)
    @DisplayName("Check Price for another pet you might like to own at product page")
    void checkAnotherPetProductName() {

        // Write the tests for these below using JUnit assertEquals and the findElements method
        fail("Task 11.4 : Check that the price is ??? using JUnit assertEquals");
        fail("Task 11.5 : Check that the product name is correct (???) for this product page");
        fail("Task 11.6 Check that the ??? is in stock. ( > 0 )");

        /**
         * You will be asked to submit this for your Assignment 3 .
         */
    }

    @Test
    @Order(6)
    @DisplayName("Check Price for another pet you might like to own at product page")
    void checkAnotherPetStock() {

        // Write the tests for these below using JUnit assertEquals and the findElements method
        fail("Task 11.4 : Check that the price is ??? using JUnit assertEquals");
        fail("Task 11.5 : Check that the product name is correct (???) for this product page");
        fail("Task 11.6 Check that the ??? is in stock. ( > 0 )");

        /**
         * You will be asked to submit this for your Assignment 3 .
         */
    }

    @BeforeEach
    void setUp() {
        SeleniumDriverFactory sdf =new SeleniumDriverFactory ();
        this.myDriver = sdf.getDriver();
    }

    @AfterEach
    void tearDown() {
        //myDriver.close();
        myDriver.quit();
    }
}