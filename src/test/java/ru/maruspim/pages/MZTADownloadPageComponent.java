package ru.maruspim.pages;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MZTADownloadPageComponent {
    // Selenide elements / locator / etc
    SelenideElement navigationBar = $("[class='uk-nav uk-navbar-nav uk-flex-wrap']"),
            priceListItemRef = $("a[href*='/skachat/prajs-list']"),
            headingText = $("[class='uk-h4 uk-heading-bullet']"),
            priceListPDFRef = $("a[href*='/price-pdf?cid=4662']"),
            priceListXLSRef = $("a[href*='/price-xls?cid=4662']");


    // Actions
    @Step("Open page with price list")
    public MZTADownloadPageComponent openPriceListPage() {
        navigationBar.find(byText("Скачать")).hover();
        priceListItemRef.click();

        return this;
    }

    @Step("Download and check PDF content")
    public MZTADownloadPageComponent pdfDownloadAndContentCheck() throws Exception {

        File download = priceListPDFRef.download();
        PDF pdf = new PDF(download);
        Assertions.assertEquals(
                "Программно-технический комплекс Комега Basic",
                pdf.title);
        return this;
    }

    @Step("Download XLS and check its content")
    public MZTADownloadPageComponent xlsDownloadAndContentCheck() throws Exception {

        File download = priceListXLSRef.download();
        XLS xls = new XLS(download);
        Assertions.assertTrue(
                xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()
                        .startsWith("Наименование"));
        return this;

    }

}
