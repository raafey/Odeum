package domain;

public class Trivia {
    private String trivia_id;
    private String name;

    public Trivia(String id, String triviaName) {
        this.trivia_id = id;
        this.name = triviaName;
    }

    public String getId() {
        return trivia_id;
    }

    public void setId(String id) {
        this.trivia_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String triviaName) {
        this.name = triviaName;
    }
}
