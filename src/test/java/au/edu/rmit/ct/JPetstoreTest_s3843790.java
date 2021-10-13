/*
 JPetstore JUnit/Webdriver Tasks:
 1) Check the pet name, price and check if there is stock for one pet of your choice (outside of Male Chihuahua). (as outlined for W11 Prac)
 2) Start a menagerie! Select a specific fish, specific cat, and a third pet (they will have a unique item ID).
    Add 3 multiples of the first, 2 multiples of the cat, and one of the third pet to the cart. Check the subtotal matches the expected price.
    You are expected to show that this test case passes.
 */

package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class JPetstoreTest_s3843790 {
    WebDriver myDriver;
    WebElement webElement;

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

    @Nested
    class checkChihuahuaNamePriceStock{
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
    }

    @Nested
    class checkAdultMalePersianNamePriceStock{
        @Test
        @Order(4)
        @DisplayName("Task 11.4 : Check that the price is $93.50")
        void checkAnotherPetPrice() {
            String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17";
            myDriver.get(chihuahuaURL);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Get value using Xpath, then check against expected value
            WebElement webElement = myDriver.findElement(By.xpath("//td[text()='$93.50']"));
            assertEquals("$93.50", webElement.getText(), "Should Equal 93.50");
        }

        @Test
        @Order(5)
        @DisplayName("Task 11.5 : Check that the product name is correct (Adult Male Persian) for this product page")
        void checkAnotherPetProductName() {

            String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17";
            myDriver.get(chihuahuaURL);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement webElement2 = myDriver.findElement(By.xpath("/html/body/div[@id='Content']/div[@id='Catalog']/table/tbody/tr[3]/td/b/font"));
            assertEquals("Adult Male Persian", webElement2.getText(), "Should Equal Adult Male Persian");
        }

        @Test
        @Order(6)
        @DisplayName("Task 11.6 Check that the Adult Male Persian is in stock. ( > 0 )")
        void checkAnotherPetStock() {
            String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17";
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
    }

    @Test
    @Order(7)
    @DisplayName("StartAMenagerie Task")
    void StartAMenagerie() {
        // Setting constants; the webpage URL and Expected price (calculated manually)
        final String ToothlessTigerSharkURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-3";
        final String AdultMalePersianURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17";
        final String AdultMaleAmazonParrotURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-18";
        final String ExpectedCartTotal = "Sub Total: $436.00";

        // Add the 3 sharks to cart:
        for (int i = 0; i < 3; i++) {
            myDriver.get(ToothlessTigerSharkURL);
            webElement = myDriver.findElement(By.className("Button"));
            if (Objects.equals(webElement.getText(), "Add to Cart")){
                webElement.click();
            }
        }

        // Add the 2 cats to cart:
        for (int i = 0; i < 2; i++) {
            myDriver.get(AdultMalePersianURL);
            webElement = myDriver.findElement(By.className("Button"));
            if (Objects.equals(webElement.getText(), "Add to Cart")){
                webElement.click();
            }
        }

        // Add the 1 bird to cart:
        myDriver.get(AdultMaleAmazonParrotURL);
        webElement = myDriver.findElement(By.className("Button"));
        if (Objects.equals(webElement.getText(), "Add to Cart")){
            webElement.click();
        }

        // Go to cart
        myDriver.get("https://petstore.octoperf.com/actions/Cart.action?viewCart=");
        webElement = myDriver.findElement(By.xpath("//*[contains(text(),'Sub Total')]"));
        // System.out.println(we.getText());
        assertEquals(ExpectedCartTotal, webElement.getText(), "Expect the values to match" );
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