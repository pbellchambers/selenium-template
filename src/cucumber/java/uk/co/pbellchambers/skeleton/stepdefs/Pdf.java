package uk.co.pbellchambers.skeleton.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import uk.co.pbellchambers.skeleton.stepdefs.pageobjects.PdfPage;
import uk.co.pbellchambers.skeleton.stepdefs.support.SharedDriver;

import static org.junit.Assert.assertTrue;

public class Pdf {

    private final WebDriver webDriver;
    private PdfPage pdfPage;
    private String pdfText;

    public Pdf(SharedDriver webDriver){
        this.webDriver = webDriver;
    }

    @Given("^I am on the PDF homepage$")
    public void I_am_on_the_PDF_homepage() throws Throwable {
        pdfPage = PageFactory.initElements(webDriver, PdfPage.class);
        pdfPage.open();
        pdfText = pdfPage.getTextInPdf();
    }

    @Then("^I should see PDF text: \"([^\"]*)\"$")
    public void I_should_see_PDF_text(String text) throws Throwable {
        assertTrue(pdfText.contains(text));
    }
}
