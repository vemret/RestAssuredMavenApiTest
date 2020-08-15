package apiTestPackage;

import org.junit.Test;

public class MainTest {
    ApiTest apiTest = new ApiTest();

    String apiKey = System.getProperty("apiKey","be6db152");
    String searchedWord = System.getProperty("searchedWord", "Harry Potter");
    String movieTitle = System.getProperty("movieTitle", "Harry Potter and the Sorcerer's Stone");

    @Test
    public void  searchHarryPotter(){

        String id = apiTest.getMovieId(apiKey, searchedWord, movieTitle);

        apiTest.searchByID(apiKey, id);
    }
}
