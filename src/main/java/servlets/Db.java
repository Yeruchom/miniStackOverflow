package servlets;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * this id a class that represents a db and holds all answers in it
 */
public class Db {
    private HashMap<Integer, LinkedList<Answer>> myMap;

    //see class Answer (has a string for the answer and a string for the name)
    public Db(){
        myMap = new HashMap<Integer,LinkedList<Answer>>();
    }

    public LinkedList<Answer> getAnswer(int index) {
            return myMap.get(index);
    }

    public int getNumOfAnswers(int index) {
        if(myMap.containsKey(index))
            return myMap.get(index).size();
        else return 0;
    }
    public void addAnswer(int index, Answer answer) {
        if (!myMap.containsKey(index))
            myMap.put(index, new LinkedList<Answer>());
        myMap.get(index).add(answer);
    }

}
