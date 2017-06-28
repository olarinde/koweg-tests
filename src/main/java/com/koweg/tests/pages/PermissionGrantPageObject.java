package com.koweg.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PermissionGrantPageObject extends AbstractBasePageObject {

    public PermissionGrantPageObject(final WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "scope_read_user_info")
    private WebElement scopeReadUserInfo;

    @FindBy(id = "scope_read_transactions")
    private WebElement scopeReadRransactions;

    @FindBy(id = "scope_read_accounts")
    private WebElement scopeReadAccounts;

    @FindBy(id = "scope_read_addresses")
    private WebElement scopeReadAddresses;

    @FindBy(name = "authorize")
    private WebElement authoriseButton;

    public void grantAll() {
        scopeReadUserInfo.click();
        scopeReadRransactions.click();
        scopeReadAccounts.click();
        scopeReadAddresses.click();
    }

    public void submitCredentials() {
        this.authoriseButton.click();
    }
    
    public boolean isInitialised() {
        return scopeReadAccounts.isSelected();
    }

}
