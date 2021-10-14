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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class TryBookingTest_s3843790 {
    WebDriver myDriver;
//TODO check out find by linked text

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

        AddDelay();
        // Add 1 ticket to continue checkout
        myDriver.findElement(By.name("quantity0")).clear();
        myDriver.findElement(By.name("quantity0")).sendKeys("1");
        myDriver.findElement(By.id("Next_addToCartBtn")).click();

        // Fill in information
        AddDelay();
        // Select the relevant drop-down for "Day"; Referenced https://www.javatpoint.com/selenium-webdriver-handling-drop-downs
        WebElement DayDropDownTest = myDriver.findElement(By.id("bookingDataField_546337_day"));
        DayDropDownTest.click();

        Select DayDropDown = new Select(DayDropDownTest); // TODO Fix error here
        DayDropDown.selectByIndex(28);
        AddDelay();
        AddDelay();
        AddDelay();
        AddDelay(); // TODO Remove these after testing

        // Select the relevant drop-down for "Month"
        Select MonthDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546337_month")));
        MonthDropDown.selectByIndex(10);
        // Select the relevant drop-down for "Year"
        Select YearDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546337_year")));
        YearDropDown.selectByIndex(11);
        // Select the relevant drop-down for "Do you have working video"
        Select HaveVideoDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546337_year")));
        HaveVideoDropDown.selectByIndex(1);
        // Select the relevant drop-down for "Which software did you mainly test"
        Select SoftwareDropDown = new Select(myDriver.findElement(By.id("bookingDataField_546339")));
        SoftwareDropDown.selectByIndex(3);
        myDriver.findElement(By.id("ticketHolderDetails_Next")).click(); // Go to next page

        AddDelay();
        // Fill in Booking Details
        myDriver.findElement(By.name("txtFirstName")).clear();
        myDriver.findElement(By.name("txtFirstName")).sendKeys("Jingxuan");
        myDriver.findElement(By.name("txtLastName")).clear();
        myDriver.findElement(By.name("txtLastName")).sendKeys("Feng");
        myDriver.findElement(By.name("txtLastName")).clear();
        myDriver.findElement(By.name("txtLastName")).sendKeys("Feng");
        myDriver.findElement(By.name("txtEmailAddress")).clear();
        myDriver.findElement(By.name("txtEmailAddress")).sendKeys("s3843790@student.rmit.edu.au");
        myDriver.findElement(By.name("txtConfirmEmailAddress")).clear();
        myDriver.findElement(By.name("txtConfirmEmailAddress")).sendKeys("s3843790@student.rmit.edu.au");
//        myDriver.findElement(By.name("btn-purchase-lg")).click();
    }

    void AddDelay(){
        try {
            Thread.sleep(3000);
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