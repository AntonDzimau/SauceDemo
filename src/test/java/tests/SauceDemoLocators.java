package tests;

import configuration.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import services.BrowsersService;

public class SauceDemoLocators {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        BrowsersService browsersService = new BrowsersService();
        driver = browsersService.getDriver();
        driver.get(ReadProperties.getUrl());
    }

    @Test(invocationCount = 5)
    public void allLocatorsTest() throws InterruptedException {
        //By.id
        driver.findElement(By.id("user-name")).sendKeys(ReadProperties.usernameProblem());

        //By.name
        driver.findElement(By.name("password")).sendKeys(ReadProperties.password());

        //By.className
        driver.findElement(By.className("submit-button")).click();
        Thread.sleep(500);

        //By.id
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500);
        driver.findElement(By.id("about_sidebar_link")).click();
        Thread.sleep(1500);

        //By.linkText
        driver.findElement(By.linkText("Solutions")).click();

        //By.partialLinkText
        driver.findElement(By.partialLinkText("Testing")).click();
        Thread.sleep(1500);
        driver.navigate().back();
        driver.navigate().back();

        //By.id
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500);
        try {
            driver.findElement(By.linkText("Logout")).click();
        } catch (NoSuchElementException e) {
            System.out.println("It's a trap!");
        }
        driver.findElement(By.id("logout_sidebar_link")).click();

        //XPath - Поиск по частичному совпадению атрибута, By.xpath("//tag[contains(@attribute,'text')]");
        driver.findElement(By.xpath("//*[contains(@placeholder,'User')]")).sendKeys(ReadProperties.usernameLockedOut());

        //XPath - Поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys(ReadProperties.password());

        //CSS Selectors - .class1.class2
        driver.findElement(By.cssSelector(".submit-button.btn_action")).click();

        //XPath - Поиск по частичному совпадению текста, By.xpath("//tag[contains(text(),'text')]");
        driver.findElement(By.xpath("//*[contains(text(),'locked')]"));

        //CSS Selectors - .class
        driver.findElement(By.cssSelector(".fa-times")).click();

        //CSS Selectors - tagname.class
        System.out.println(driver.findElement(By.cssSelector("div.login_password")).getText());

        //CSS Selectors - #id
        driver.findElement(By.cssSelector("#user-name")).clear();
        driver.findElement(By.cssSelector("#user-name")).sendKeys(ReadProperties.usernameStandard());

        //CSS Selectors - [attribute=value]
        driver.findElement(By.cssSelector("[type='password']")).clear();
        driver.findElement(By.cssSelector("[type='password']")).sendKeys(ReadProperties.password());

        //CSS Selectors - [attribute~=value]
        driver.findElement(By.cssSelector("[class~=submit-button]")).click();
        Thread.sleep(500);

        //CSS Selectors - [attribute|=value]
        driver.findElement(By.cssSelector("[class|=bm-burger]")).click();

        //CSS Selectors - tagname
        if (driver.findElement(By.cssSelector("nav")).isDisplayed()) {
            System.out.println("Navigation panel is displayed.");
        } else {
            System.out.println("Navigation panel isn't displayed.");
        }

        //CSS Selectors - [attribute*=value]
        driver.findElement(By.cssSelector("[id*=burger-cross]")).click();

        //XPath - Поиск по тексту, By.xpath("//tag[text()='text']");
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();

        //XPath - Поиск элемента с условием AND, //input[@class='_2zrpKA _1dBPDZ' and @type='text']
        driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory' and @name='add-to-cart-sauce-labs-backpack']")).click();
        Thread.sleep(500);

        //CSS Selectors - [attribute^=value]
        driver.findElement(By.cssSelector("[name^='remove']")).click();
        driver.navigate().back();

        //XPath - Поиск по частичному совпадению атрибута, By.xpath("//tag[contains(@attribute,'text')]");
        Assert.assertEquals(driver.findElements(By.xpath("//*[contains(@name,'add')]")).size(), 6);

        //CSS Selectors - [attribute$=value]
        Assert.assertEquals(driver.findElements(By.cssSelector("[class$=item_name]")).size(), 6);

        //CSS Selectors - .class1 .class2
        Assert.assertEquals(driver.findElements(By.cssSelector(".inventory_item_description .inventory_item_label")).size(), 6);

        //XPath - ancestors
        System.out.println("Numbers of ancestors elements is " + driver.findElements(By.xpath("//*[text()='Products']//ancestor::div")).size());

        //XPath - descendant
        System.out.println("Numbers of descendants elements is " + driver.findElements(By.xpath("//ul[@class = 'social']//descendant::li")).size());

        //XPath - following
        System.out.println("Numbers of following elements is " + driver.findElements(By.xpath("//div[@class = 'app_logo']//following::a")).size());

        //XPath - parent
        System.out.println("Numbers of parents elements is " + driver.findElements(By.xpath("//div[text()='29.99']//parent::*")).size());

        //XPath - preceding
        System.out.println("Numbers of preceding elements is " + driver.findElements(By.xpath("//span[text()='Products']//preceding::div")).size());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
