import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class TheTEST {

    BlueSiteTest maor = new BlueSiteTest();


    @Test
    public void Test_StatusCode(){
        int StatusCode = given().when().get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login").getStatusCode();
        System.out.println(" the status code is :"+ StatusCode);
        switch(StatusCode/100){
            case 2 -> System.out.println(" request passed (2xx  - family)");
            case 3 -> System.out.println(" request passed (3xx -family)");
            case 4 -> System.out.println(" user error (4xx - family)");
            case 5 -> System.out.println(" server error (5xx - family)");
        }
        int Expected_Code = 200/100;
        int Actual_Code = StatusCode/100;
        Assert.assertEquals(Actual_Code, Expected_Code);
        maor.Quit();
    }


    @Test
    public void test_1500_Transaction() throws InterruptedException {
        String Actual_numString = maor.get1500();//creating a String var for getText
        int Actual_numInt = Integer.parseInt(Actual_numString); // casting the String into Integer
        int Expected_numInt = 1500;
        System.out.println( " actual = "+ Actual_numInt + "and expected is :"+Expected_numInt);
        Assert.assertEquals(Expected_numInt, Actual_numInt);
        maor.Quit();
    }

    @Test
    public void Test_User_Removal(){
        String firstName = "Hermoine";
        String SecondName = maor.getHermoine();
        Assert.assertNotEquals(firstName ,SecondName);
        maor.Quit();
    }


}
