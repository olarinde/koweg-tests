/**
 * 
 */
package com.koweg.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObject extends AbstractBasePageObject {

//  :firefox
//  browser.navigate.to "https://uat1.simulator-api.db.com/gw/oidc/login"
//  browser.find_element(:xpath,"//input[@value='Execute Login']").click
//  browser.find_element(:id, "input_branch").send_keys("123")
//  browser.find_element(:id, "input_account").send_keys("1234567")
//  browser.find_element(:id, "input_subaccount").send_keys("12")
//  browser.find_element(:id, "j_password").send_keys("12345")
//  

    @FindBy(id = "input_branch")
    private WebElement branch;

    @FindBy(id = "input_account")
    private WebElement account;

    @FindBy(id = "input_subaccount")
    private WebElement subAccount;

    @FindBy(id = "j_password")
    private WebElement password;

    @FindBy(xpath = "//input[@value='Execute Login']")
    private WebElement executeButton;

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }
    
    public boolean isInitialised() {
        return account.isDisplayed();
    }

    public void enterCredentials(final String _branch, final String _account, final String _subAccount, final String _password) {
        this.branch.clear();
        this.branch.sendKeys(_branch);

        this.account.clear();
        this.branch.sendKeys(_account);

        this.subAccount.clear();
        this.branch.sendKeys(_subAccount);

        this.password.clear();
        this.branch.sendKeys(_password);
    }

    public LoginPageObject submitCredentials() {
        this.executeButton.submit();
        return new LoginPageObject(driver);
    }
}
