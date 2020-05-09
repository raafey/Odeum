package domain;

public class Event {
    private String eventId;
    private String name;
    private String genre;
    private String rating;
    private String feedback;
    private String synopsis;
    private String trailerUrl;
    private String cast;
    private String imageURL;
    private String theaterId;
    
    public Event(String eventId, String name, String genre, String rating, String feedback, String synopsis, String trailerUrl, String cast, String theaterId, String imageURL) {
        this.eventId = eventId;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.feedback = feedback;
        this.synopsis = synopsis;
        this.trailerUrl = trailerUrl;
        this.cast = cast;
        this.imageURL = imageURL;
        this.theaterId = theaterId;
        this.feedback = "";
    }
    
    public void printEvent() {
        System.out.println("Id: " + eventId);
        System.out.println("Name: " + name);
        System.out.println("Genre: " + genre);
        System.out.println("Rating: " + rating);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Trailer URL: " + trailerUrl);
        System.out.println("Cast: " + cast);
        System.out.println("Image URL: " + imageURL);
        System.out.println("TheaterId: " + theaterId);
        System.out.println("Feedback: " + feedback);        
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public void setEventId(String eventId){
        this.eventId = eventId;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }
    
    public String getRating(){
        return rating;
    }
    
    public void setRating(String rating){
        this.rating = rating;
    }
    
    public String getFeedback(){
        return feedback;
    }
    
    public void setFeedback(String feedback){
        this.feedback = feedback;
    }
    
    public String getSynopsis(){
        return synopsis;
    }
    
    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }
    
    public String getTrailerUrl(){
        return trailerUrl;
    }
    
    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
    
    public String getCast() {
        return cast;
    }
    
    public String getImageUrl() {
        return imageURL;
    }
    
    public void setCast(String cast) {
        this.cast = cast;
    }
    
    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }
    
    public String getTheaterId() {
        return theaterId;
    }
}
