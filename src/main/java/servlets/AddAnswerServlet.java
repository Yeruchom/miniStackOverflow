package servlets;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;


@WebServlet(name = "AddAnswersServlet", value = "/AddAnswersServlet")
public class AddAnswerServlet extends HttpServlet {
    private HashMap<Integer,String> mapdb;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mapdb = new HashMap<Integer,String>();
    }


    //sends a json with all answers to the wanted question.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LinkedList<Answer> answers =((Db) getServletContext().getAttribute("db")).getAnswer(Integer.valueOf(request.getParameter("q")));

        JsonArrayBuilder array = Json.createArrayBuilder();
        for (Answer ans : answers) {
            array.add(Json.createObjectBuilder().add("name", ans.getName()).add("answer", ans.getAnswer()).build());
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(array.build());

    }


    //adds the answer to the db.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out = response.getWriter();
       response.setContentType("text/html");

       int num = Integer.parseInt(request.getParameter("questionNum"));
       if(request.getParameter("answer")!= null && request.getParameter("name")!=null) {
           Answer answer = new Answer(request.getParameter("name"), request.getParameter("answer"));
           Db db = (Db) request.getServletContext().getAttribute("db");
           db.addAnswer(num, answer);
       }
       response.sendRedirect("/QuestionsServlet");
    }
}
