package api;

import api.UserData;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class RegresTest {
    private final static String URL = "https://reqres.in";
    @Test
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON) // какой тип данных
                .get(URL + "/api/users?page=2") // куда мы обращаемся
                .then().log().all() //извлекаем все
                .extract().body().jsonPath().getList("data", UserData.class); // что извлекаем и в какой класс
        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }
}
