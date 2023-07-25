package ru.maruspim.pages;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DownloadPage {
    private SelenideElement navigationBar = $("[class='uk-nav uk-navbar-nav uk-flex-wrap']"),
            priceListItemRef = $("a[href*='/skachat/prajs-list']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']"),
            priceListPDFRef = $("a[href*='/price-pdf?cid=4662']"),
            priceListXLSRef = $("a[href*='/price-xls?cid=4662']");


    @Step("Download PDF and check its content")
    public DownloadPage parseTestForPDF() throws Exception {
        navigationBar.find(byText("Скачать")).hover();
        priceListItemRef.click();
        headingText.shouldHave(text(("Прайс-лист")));
        File download = priceListPDFRef.download();
        PDF pdf = new PDF(download);
        Assertions.assertEquals(
                "Программно-технический комплекс Комега Basic",
                pdf.title);

        return this;
    }

    @Step("Download XLS and check its content")
    public DownloadPage parseTestForXLS() throws Exception {

        navigationBar.find(byText("Скачать")).hover();
        priceListItemRef.click();
        headingText.shouldHave(text(("Прайс-лист")));
        File download = priceListXLSRef.download();
        XLS xls = new XLS(download);
        Assertions.assertTrue(
                xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()
                        .startsWith("Наименование"));

        return this;
    }
}
