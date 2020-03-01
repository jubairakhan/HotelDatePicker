package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.Hotels.HotelsHomePage;


public class testDatePicker extends DriverWrapper {

    @Test
    public void HotelsDateTest() throws InterruptedException {
        HotelsHomePage hotelsHomePage = new HotelsHomePage();
        hotelsHomePage.closePopUpOnLoad();
        hotelsHomePage.datePicks("March 2020", "March 2020");
        Assert.assertEquals(hotelsHomePage.getDaysSelected(), "7");
    }
}
