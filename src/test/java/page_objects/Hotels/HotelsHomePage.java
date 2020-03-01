package page_objects.Hotels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page_objects.BasePage;
import test_scripts.DriverWrapper;

import java.sql.Driver;
import java.sql.Wrapper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HotelsHomePage extends BasePage {

    private By popUpDismissButton = By.xpath("//*[@class='cta widget-overlay-close']");
    private By dateDiff = By.xpath("//*[@class='widget-query-num-nights']");
    private By checkInOver = By.xpath("//*[@id='qf-0q-localised-check-in']");
    private By checkOutOver = By.xpath("//*[@id='qf-0q-localised-check-out']");

    public void closePopUpOnLoad(){
        clickOn(popUpDismissButton);
    }

    public String getDaysSelected(){
        return getValueFromElement(dateDiff);
    }

    public void datePicks(String desiredCheckInMonth, String desiredCheckOutMonth){

        while(true) {
            clickOn(checkInOver);
            String monthText = DriverWrapper.getDriver().findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/div[1]/div[1]/div[1]")).getText();
            if(desiredCheckInMonth.equals(monthText)){
                break;
            } else {
                DriverWrapper.getDriver().findElement(By.xpath("//div[@class='widget-daterange-cont']//div[2]//div[1]//button[2]")).click();
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 0); // to get tomorrows day
        Date day = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        String tomorrow = sdf.format(day.getTime());
        //============================================
        Calendar chkout = Calendar.getInstance();
        chkout.add(Calendar.DAY_OF_MONTH, 7);
        Date plus6days = chkout.getTime();
        String sixNights = sdf.format(plus6days.getTime());


        DriverWrapper.getDriver().findElement(By.xpath("//*[@id='qf-0q-localised-check-in']")).click();
        WebElement dateWidget = DriverWrapper.getDriver().findElement(By.className("widget-daterange-cont"));
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        for (WebElement cell : columns) {
            String days = cell.getText();
            if (days.equals(tomorrow)) {
                cell.click();
            }
        }

        while(true) {
            clickOn(checkOutOver);
            String monthText = DriverWrapper.getDriver().findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/div[1]/div[1]/div[1]")).getText();
            if(desiredCheckOutMonth.equals(monthText)){
                break;
            } else {
                DriverWrapper.getDriver().findElement(By.xpath("//div[@class='widget-daterange-cont']//div[2]//div[1]//button[2]")).click();
            }
        }

        // select check out date
        DriverWrapper.getDriver().findElement(By.id("widget-query-label-3")).click();
        WebElement dateWidget1 = DriverWrapper.getDriver().findElement(By.className("widget-datepicker-bd"));
        List<WebElement> checkout = dateWidget.findElements(By.tagName("td"));

        for (WebElement dayOut : checkout) {
            String checkOutDate = dayOut.getText();
            if (checkOutDate.equals(sixNights)) {
                dayOut.click();
            }
        }
    }

}
