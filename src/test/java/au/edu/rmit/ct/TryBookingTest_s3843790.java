/*
 Trybooking JUnit/Webdriver Tasks:
 1) Navigate to https://www.trybooking.com/book/search (Links to an external site.) and print-to-screen the featured event titles. Store these titles in a suitable variable in your test case (list? arrays?). Update your test case to use assertEquals to compare these titles with a new test case run. You are expected to show that this test case passes.
 2) Navigate to https://www.trybooking.com/book/search (Links to an external site.) as above and check for featured and events near "you". If any such event has '(Cancelled)' in its title, then this test case should fail.
    Example Event Title: Prahran Market Discovery Trail (CANCELLED)
    Example Event URL: https://www.trybooking.com/events/landing?eid=758678
 3) Navigate to https://www.trybooking.com/BUOMO (Links to an external site.) and book to one of the Monday or Thursday sessions, completing all of any optional data collection questions asked, using your student email ID.
 */
package au.edu.rmit.ct;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class TryBookingTest_s3843790 {
    WebDriver myDriver;

    @Test
    // @Disabled
    @Order(0)
    @DisplayName("Sanity test only")
     void sanityTest1(){
        // When this passes I know I have the webdriver and Junit set up correctly
        String url = "https://www.trybooking.com/book/search";
        myDriver.get(url);
        assertEquals("Buy tickets | TryBooking Australia", myDriver.getTitle());
    }

    @Test
    @Order(1)
    @DisplayName("TryBooking: Check featured events")
    void CheckFeaturedEvents() {
        final String FeaturedEventsURL = "https://www.trybooking.com/book/searchLinks";
        final int TotalFeatureEvents = 6;
        myDriver.get(FeaturedEventsURL);
        AddDelay(); // 3-second delay was used here, it appeared 1sec was not enough and would result in exceptions

        ArrayList<String> FeatureEventsByXPath = new ArrayList<>(); //Arraylist to store Titles grabbed by XPath
        ArrayList<String> FeatureEventsByHand = new ArrayList<>(); //Arraylist to store Titles entered by hand

        // Add Featured Events titles using XPath
        for (int i = 0, j = 1; i < TotalFeatureEvents; i++, j++) {
            String FeatureEventsXPath = "/html[@class='no-js']/body[@id='body_id']/div[@class='full-height-container ng-scope']" +
                    "/div[@id='mainContainer']/div[@id='view-id']/div[@class='init-search_wrapper_full ng-scope']/div[@class='init-search_wrapper']/div" +
                    "[@class='init-search-results']/div[@id='FeaturedEventsSection']/div[@class='init-search-results-parent margin-center no-style']/div" +
                    "[@class='init-search-results-child ng-scope'][" +
                    j +
                    "]/div[@class='init-search-result-container']/div[@class='init-search-result']" +
                    "/a[@id='initSearchNavigateHeadingFeaturedEventId_" +
                    i +
                    "']/h2[@class='ng-binding']";
            WebElement FeatureEvents = myDriver.findElement(By.xpath(FeatureEventsXPath));
            FeatureEventsByXPath.add(FeatureEvents.getText());
        }
        // Manually added titles to a second arraylist to compare against
        FeatureEventsByHand.add("That’s Amore - The Dean Martin Story");
        FeatureEventsByHand.add("BLAK STAGE AT LA MAMA");
        FeatureEventsByHand.add("Marysville Music Weekend - Rescheduled");
        FeatureEventsByHand.add("Adventure Travel Film Festival 2021 (V2)");
        FeatureEventsByHand.add("2022 Port Fairy Jazz Festival");
        FeatureEventsByHand.add("Dance Hall - The Pheonix Tour");

        assertEquals(FeatureEventsByHand, FeatureEventsByXPath, "Expect arraylists to match");
    }

    @Test
    @Order(2)
    @DisplayName("TryBooking: Check cancelled events")
    void CheckCancelledEvents() {
        final String FeaturedEventsURL = "https://www.trybooking.com/book/searchLinks";
        final int TotalFeatureEvents = 6;
        final int TotalEventsNearYou = 6;
        myDriver.get(FeaturedEventsURL);
        AddDelay();

        ArrayList<String> FeatureEventsByXPathStatus = new ArrayList<>(); //Arraylist to store Titles grabbed by XPath
        ArrayList<String> EventsNearYouByXPathStatus = new ArrayList<>();

        // Add all Featured Titles to an arraylist
        for (int i = 0, j = 1; i < TotalFeatureEvents; i++, j++) {
            String FeatureEventsXPath = "/html[@class='no-js']/body[@id='body_id']/div[@class='full-height-container ng-scope']" +
                    "/div[@id='mainContainer']/div[@id='view-id']/div[@class='init-search_wrapper_full ng-scope']/div[@class='init-search_wrapper']/div" +
                    "[@class='init-search-results']/div[@id='FeaturedEventsSection']/div[@class='init-search-results-parent margin-center no-style']/div" +
                    "[@class='init-search-results-child ng-scope'][" +
                    j +
                    "]/div[@class='init-search-result-container']/div[@class='init-search-result']" +
                    "/a[@id='initSearchNavigateHeadingFeaturedEventId_" +
                    i +
                    "']/h2[@class='ng-binding']";
            WebElement FeatureEvents = myDriver.findElement(By.xpath(FeatureEventsXPath));
            FeatureEventsByXPathStatus.add(FeatureEvents.getText().toUpperCase());
            // Used toUpperCase() here to avoid different ways Cancelled could be written ie cancelled, Cancelled, CANCELLED
        }

        // Add all Events Near You Titles to an arraylist
        for (int i = 0, j = 1; i < TotalEventsNearYou; i++, j++) {
            String FeatureEventsXPath = "/html[@class='no-js']/body[@id='body_id']/div[@class='full-height-container ng-scope']" +
                    "/div[@id='mainContainer']/div[@id='view-id']/div[@class='init-search_wrapper_full ng-scope']" +
                    "/div[@class='init-search_wrapper']/div[@class='init-search-results']/div[@id='EventsNearbySection']" +
                    "/div[@class='init-search-results-parent margin-center no-style']/div[@class='init-search-results-child ng-scope']" +
                    "[" +
                    j +
                    "]/div[@class='init-search-result-container']/div[@class='init-search-result']/a[@id='initSearchNavigateHeadingNearbyEventId_" +
                    i +
                    "']/h2[@class='ng-binding']";
            WebElement EventsNearYou = myDriver.findElement(By.xpath(FeatureEventsXPath));
            EventsNearYouByXPathStatus.add(EventsNearYou.getText().toUpperCase());
        }

        // Look for the word "CANCELLED" in both arraylists
        assertFalse(FeatureEventsByXPathStatus.contains("CANCELLED"), "Test will fail if an event is cancelled");
        assertFalse(EventsNearYouByXPathStatus.contains("CANCELLED"), "Test will fail if an event is cancelled");
    }

    @Test
    @Order(3)
    @DisplayName("TryBooking: Book into an event")
    void BookIntoEvent() {
        final String EventURL = "https://www.trybooking.com/BUOMO";
        myDriver.get(EventURL);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) myDriver;
        AddDelay();
        // XPath of the "Book now" button
        final String ButtonXPath = "/html/body/div[@id='event-app']/div[@class='full-height-container']/div[@id='partial-view-content']" +
                "/div[@id='ko-event-landing']/div[@class='main-container  ']/div[@class='view-container ']/div[@id='view-id']" +
                "/div[@class='wrapper ']/div[@class='main-panel']/div[@class='wrapper-narrow']/div[@class='padding-lr--sm']" +
                "/div[@id='book-button-top']/div[@id='sticky-bottom-btn']/div[@class='ko-container book-now-btn-container']" +
                "/button[@class='btn btn-secondary text-uppercase btn-landing go-to-session']";
        // Click on the button to go to Sessions page
        WebElement webElement = myDriver.findElement(By.xpath(ButtonXPath));
        webElement.click();

        AddDelay();
        // XPath of the "Select" button for Monday session
        final String SelectButtonXPath = "/html[@class='no-js']/body[@id='body_id']/div[@class='full-height-container ng-scope']" +
                "/div[@id='mainContainer']/div[@id='view-id']/div[@class='wrapper main-content-wrapper ng-scope']/div[6]" +
                "/div[@class='wrapper-narrow sessions-wrapper']/div[@class='grid--nopadding--sm sessions']/table[@class='table-style ng-scope']" +
                "/tbody[@class='ng-scope odd-row-color']/tr[@class='no-border-top']/td[@class='td-md text-right vertical-center']" +
                "/div[@class='control-column']/button[@id='sessionsButtonSessionsPageLargeId_0']/span[@class='btn-txt ng-binding']";
        // Click on the button to go to Sessions page
        WebElement webElement2 = myDriver.findElement(By.xpath(SelectButtonXPath));
        webElement2.click();

        AddShortDelay();
        // Add 1 ticket to continue checkout
        myDriver.findElement(By.name("quantity0")).clear();
        myDriver.findElement(By.name("quantity0")).sendKeys("1");
        myDriver.findElement(By.id("Next_addToCartBtn")).click();

        AddDelay();
        // Fill in information
        // Select the relevant drop-down for "Day"; Referenced [1], see README file
        Select DayDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546337_day")));
        DayDropDown.selectByIndex(28);
        // Select the relevant drop-down for "Month"
        Select MonthDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546337_month")));
        MonthDropDown.selectByIndex(10);
        // Select the relevant drop-down for "Year"
        Select YearDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546337_year")));
        YearDropDown.selectByIndex(11);
        // Select the relevant drop-down for "Do you have working video"
        Select HaveVideoDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546338")));
        HaveVideoDropDown.selectByVisibleText("Yes");

        // Select the relevant drop-down for "Which software did you mainly test", this dropdown is in a Div rather than
        // a Select, thus a different approach is used
        myDriver.findElement(By.id("bookingDataField_546339")).click(); // first expand the dropdown list
        AddShortDelay();
        WebElement FOXITSelect = myDriver.findElement(By.id("ui-select-choices-row-0-2")); // locate the 2nd div
        FOXITSelect.click();
        myDriver.findElement(By.id("ticketHolderDetails_Next")).click(); // Go to next page

        AddDelay();
        // Fill in Booking Details
        myDriver.findElement(By.id("txtFirstName")).clear();
        myDriver.findElement(By.id("txtFirstName")).sendKeys("Jingxuan");
        myDriver.findElement(By.id("txtLastName")).clear();
        myDriver.findElement(By.id("txtLastName")).sendKeys("Feng");
        myDriver.findElement(By.id("txtLastName")).clear();
        myDriver.findElement(By.id("txtLastName")).sendKeys("Feng");
        myDriver.findElement(By.id("txtEmailAddress")).clear();
        myDriver.findElement(By.id("txtEmailAddress")).sendKeys("s3843790@student.rmit.edu.au");
        myDriver.findElement(By.id("txtConfirmEmailAddress")).clear();
        myDriver.findElement(By.id("txtConfirmEmailAddress")).sendKeys("s3843790@student.rmit.edu.au");

        // Checkbox is a CSS field, need CSS selector, referenced [2]
        WebElement SpecialOfferCheckbox = myDriver.findElement(By.cssSelector("label[for=terms-and-privacy-cb]"));
        // Javascript execution is needed, referenced [3]
        javascriptExecutor.executeScript("arguments[0].scrollIntoView()", SpecialOfferCheckbox);
        SpecialOfferCheckbox.click();
        myDriver.findElement(By.id("btn-purchase-lg")).click(); // proceed to "checkout"
        // Long delay is used since sometimes the checkout confirmation page can get stuck for quite a while
        AddLongDelay();

        final String TransactionResultXPath = "/html/body/div[@class='checkout-container']/div[@class='container container-auto-height']" +
                "/main[@class='pb-0 pb-md-3']/div[@class='tryb-confirmation']/div[@class='pt-5']/div[@class='mb-3']/h1[@class='mb-3 mt-3']/span";
        WebElement TransactionResult = myDriver.findElement(By.xpath(TransactionResultXPath));
        assertEquals("Transaction successful", TransactionResult.getText(), "Expect 'Transaction successful'");
    }

    void AddDelay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void AddShortDelay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void AddLongDelay(){
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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