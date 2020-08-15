package apiTestPackage;

public class Movies {
    private String Title;
    private String Year;
    private String imdbID;
    private String Released;



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {

        this.Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    @Override
    public String toString() {
        return    "title: "+ Title +"\nyear: "+ Year +"\nimdbID: "+ imdbID +"\nreleased: "+ Released;
    }

}
