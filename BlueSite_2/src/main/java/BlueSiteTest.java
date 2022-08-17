import com.google.errorprone.annotations.Var;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By.ByClassName;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class BlueSiteTest {

    WebDriver driver;


    public BlueSiteTest() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Maor\\Desktop\\selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize(); // editing the screen to fullscreen
    }   // constructor

    public void Quit(){
        this.driver.quit();
    } // quits the browser

    public String OpenSite(String Url){
        this.driver.get(Url);// Navigating to the Url given to us
        return  driver.getCurrentUrl();// return the url as a string
    } // open site at given url and give the StatusCode

    private String login_Customer_1500Deposit(String actualNum_1) throws InterruptedException {

        OpenSite("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        Thread.sleep(2000);
        this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[1]/button")).click(); // get into the customer page
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);

        this.driver.findElement(By.xpath("//*[@id=\"userSelect\"]/option[2]")).click(); // opening the options of customer to choose from - Hermionie
        this.driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);
        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > form > button")).click(); // Login Bt
        this.driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);

        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div:nth-child(5) > button:nth-child(1)")).click(); // transaction page
        this.driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);
        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.fixedTopBox > button:nth-child(3)")).click(); // Reset button
        this.driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);
        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.fixedTopBox > button:nth-child(1)")).click(); // Back button
        this.driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);


        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div:nth-child(5) > button:nth-child(2)")).click(); // choosing "deposit" option
        this.driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);
        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > form > div > input")).sendKeys("1500"); // the numbers bar and 1500 deposit


        this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")).click(); // making the transaction (Submit)
        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div:nth-child(5) > button:nth-child(1)")).click();//////////// // transaction page
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);
        this.driver.navigate().refresh(); // refreshing the page                        //
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);
        this.driver.navigate().refresh(); // refreshing the page                        //
        Thread.sleep(4000);
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);
        WebElement TransactionConformation = this.driver.findElement(By.cssSelector("#anchor0 > td:nth-child(2)"));// creating an element for the text
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        actualNum_1 =TransactionConformation.getText();
        return actualNum_1;   //return the number that is being deposited

    }

    private String User_Removal_Hermione (String name_At_Top1 , String name_At_Top2){
        OpenSite("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);/////
        this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click(); // login as manager
        this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[3]")).click(); // login to customers table
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);/////
        name_At_Top1 = this.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]")).getText(); // getting the top name before removal
        System.out.println(" the name at top is : "+ name_At_Top1);
        this.driver.manage().timeouts().implicitlyWait(20 , TimeUnit.SECONDS);/////
        this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > button")).click(); // delete top customer from table
        this.driver.manage().timeouts().implicitlyWait(2 , TimeUnit.SECONDS);/////
        name_At_Top2 = this.driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > table > tbody > tr:nth-child(1) > td:nth-child(1)")).getText(); // getting the top name now
        System.out.println("now the top name is :"+ name_At_Top2);
        return  name_At_Top2;
    }

    public String get1500() throws InterruptedException {
        return login_Customer_1500Deposit("1");
    }


    public String getHermoine(){
        return User_Removal_Hermione("1", "1");
    }



}
