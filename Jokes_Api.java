package Chuck_Norris_API_practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Jokes_Api {

    String url1 ="https://api.chucknorris.io/jokes/categories";
    int categories_num = 16;

    String url2 = "https://api.chucknorris.io/jokes/search?query=";
    final String Task2_Query = "Barack Obama";
    final int total_obama_jokes =8;

    String url3 = "https://api.chucknorris.io/jokes/random?category=";
    String Task4_category = "movie";
    public WebDriver driver;
    public RequestSpecification httpRequest;
    public Response response;
    JsonPath jp;




    @BeforeClass
    public void start_session(){
        RestAssured.baseURI = url1;
        httpRequest = RestAssured.given();
        WebDriverManager.chromedriver().setup();
    }


    @Test(description = "retrieve all available categories and counts them")
    public void print_and_count(){
        response = httpRequest.get(url1);
        response.prettyPrint();
        jp = response.jsonPath();
        List<String> Categories = jp.get();
        System.out.println("Number of categories is: "+Categories.size()); // if dev want to check
        Assert.assertEquals(Categories.size(),categories_num, "the number of categories is not as expected "+Categories.size()+" instead of "+categories_num );

    }
    @Test(description = "how many Barack Obama jokes are there")
    public void Barack_Obama_count(){
        response = httpRequest.get(url2+Task2_Query);
        jp = response.jsonPath();
        int jokes_sum_actual = Integer.parseInt(jp.getString("total"));
        System.out.println("number of jokes is: "+jokes_sum_actual);// if dev want to check
        Assert.assertEquals(jokes_sum_actual,total_obama_jokes, "number of jokes are not equal. the number was "+jokes_sum_actual+" instead of "+total_obama_jokes);
    }

    @Test(description = "test for random jokes Api's and assert result with the html page")
    public void Random_Jokes(){
        response = httpRequest.get(url3+Task4_category);
        jp = response.jsonPath();
        String value_api = jp.getString("value");

        // initializing the driver here to not interfere in the other tests
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(jp.getString("url"));
        WebElement WebValueElement = driver.findElement(By.xpath("//p[@id='joke_value']"));
        String value_web = WebValueElement.getText();
        // if dev want to check
        System.out.println("web="+value_web);
        System.out.println("api="+value_api);

        Assert.assertEquals(value_api, value_web, "the two variable don't match: api gave "+ value_api+" and the web gave "+value_web );
        driver.quit();
    }

}
