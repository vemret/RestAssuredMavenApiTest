package apiTestPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ApiTest {

    String baseURI = System.getProperty("baseURI","http://www.omdbapi.com/");

    public String getMovieId(String api, String searchedWord, String movieTitle){
        RestAssured.baseURI = baseURI;
        String id = null;
        String year = null;
        String title = null;
        String released = null;

        try {
            Response response = getResponse(api,searchedWord);

            JsonPath path = response.jsonPath();

            List<Movies> data = path.getList("Search", Movies.class);

            System.out.println("\n"+data.size()+" movies related to the word you searched found!\n");


            for (Movies snglObject: data){
                if(snglObject.getTitle().equals(movieTitle)){
                    id = snglObject.getImdbID();
                    year = snglObject.getYear();
                    title = snglObject.getTitle();
                    released = snglObject.getReleased();
                    System.out.println("\n Movies ID: "+ id+"\n Movies Year: "+ year+"\n Movies title: "+ title+"\n Movies released: "+ released);
                    break;
                }
            }
            return id;
        }catch (Exception e){
            System.out.println("Error! : "+e.getMessage());
            return null;
        }


    }


    private Response getResponse(String apiKey, String searchedWord) {
        try {
            return given()
                    .param("apikey", apiKey)
                    .param("s", searchedWord)
                    .when()
                    .get()
                    .then()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                    .and()
                    .body("Search.Title", not(emptyOrNullString()))
                    .body("Search.Year", not(emptyOrNullString()))
                    .body("Search.Released", not(emptyOrNullString()))
                    .extract()
                    .response();
        } catch (Exception e) {
            System.out.println("Error! : " + e.getMessage());
            return null;
        }
    }

    public void searchByID(String apiKey, String id) {
        try {
            given()
                    .param("apikey", apiKey)
                    .param("i",id)
                    .when()
                    .get()
                    .then()
                    .log()
                    .all()
                    .statusCode(200)
                    .body("Title", not(emptyOrNullString()))
                    .body("Year", not(emptyOrNullString()))
                    .body("Released", not(emptyOrNullString()));
        }catch (Exception e){
            System.out.println("Error! : "+e.getMessage());
        }
    }

}
