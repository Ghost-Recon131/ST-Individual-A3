/**
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

import java.util.ArrayList;
import java.util.List;

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
        //TODO
        // 1. Navigate to https://www.trybooking.com/book/searchLinks as above and check for featured and events near
        //    "you". If any such event has '(Cancelled)' in its title, then this test case should fail.
        final String FeaturedEventsURL = "https://www.trybooking.com/book/searchLinks";
        final int TotalFeatureEvents = 6;
        final int TotalEventsNearYou = 6;
        myDriver.get(FeaturedEventsURL);
        AddDelay();

        ArrayList<String> FeatureEventsByXPathStatus = new ArrayList<>(); //Arraylist to store Titles grabbed by XPath

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
            FeatureEvents.click(); // Go to each event in Featured events

            String EventTitleXPath = "/html/body/div[@id='event-app']/div[@class='full-height-container']/div" +
                    "[@id='partial-view-content']/div[@id='ko-event-landing']/div[@class='main-container  ']" +
                    "/div[@class='view-container ']/div[@id='view-id']/div[@class='wrapper ']/div[@class='main-panel']" +
                    "/div[@class='wrapper-narrow']/div[@class='padding-lr--sm']/div[@id='eventLandingSummary']/div[1]" +
                    "/span[@class='event-name-container']/span[@class='event-name']";
            WebElement EventTitle = myDriver.findElement(By.xpath(EventTitleXPath));
            FeatureEventsByXPathStatus.add(EventTitle.getText()); // Add title to arraylist

            // Return to main site
            myDriver.get(FeaturedEventsURL);
            AddDelay();
        }
        assertFalse(FeatureEventsByXPathStatus.contains("CANCELLED"), "Test will fail if an event is cancelled");



    }

    @Test
    @Order(3)
    @DisplayName("TryBooking: Book into an event")
    void BookIntoEvent() {
        //TODO
        // 1. Navigate to https://www.trybooking.com/BUOMOLinks and book to one of the Monday or Thursday sessions,
        // completing all of any optional data collection questions asked, using your student email ID.

        final String FeaturedEventsURL = "https://www.trybooking.com/BUOMOLinks";
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