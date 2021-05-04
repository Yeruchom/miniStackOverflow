package servlets;


//a simple class that represents a answer
public class Answer {
    private String[] myAnswer;
    public Answer(String name, String answer) {
        myAnswer = new String[2];
        myAnswer[0] = name;
        myAnswer[1] = answer;
    }
    public String getName(){return myAnswer[0];}
    public String getAnswer(){return myAnswer[1];}

}
