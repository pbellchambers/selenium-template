package uk.co.pbellchambers.skeleton.stepdefs.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class PdfPage {

    protected WebDriver driver;
    private String pageUrl = "file:///C:/Users/tregu_000/Documents/dev/test-skeleton/src/main/html/testpdf2.html";

    @FindBy(id="iframepdf")
    private WebElement iframePdf;


    public PdfPage(WebDriver driver){
        this.driver = driver;
    }

    public void open(){
        driver.get(pageUrl);
    }

    public String getTextInPdf() throws InterruptedException, IOException, UnsupportedFlavorException {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        String copy = Keys.chord(Keys.CONTROL, "c");

        Thread.sleep(1000);
        iframePdf.sendKeys(selectAll);

        Thread.sleep(500);
        iframePdf.sendKeys(copy);

        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }
}
