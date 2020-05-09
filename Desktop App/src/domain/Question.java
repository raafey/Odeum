package domain;

public class Question {
    private String Id;
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;
    private String triviaId;
    
    public Question(String Id, String question, String a, String b, String c, String d, String answer, String triviaId) {
        this.Id = Id;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.triviaId = triviaId;
    }

    public String getTriviaId() {
        return triviaId;
    }

    public void setTriviaId(String triviaId) {
        this.triviaId = triviaId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}