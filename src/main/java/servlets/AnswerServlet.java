package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//this servlet handles the form to answer the questions.

@WebServlet(name = "AnswerServlet", value = "/AnswerServlet")
public class AnswerServlet extends HttpServlet {
    @Override

    //sends to the user the form to fill with his answer.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("header.html").include(request, response);
        out.println("</script></head>");

        out.println("<body>\n" +
                "    <div class=\"container\">\n" +
                "        <form action=\"/AddAnswersServlet\" method=\"post\">\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-12\">\n" +
                "                    <div class=\"alert alert-primary\" role=\"alert\">\n" +
                "                        please fill in your answer to question # " +
                        request.getParameter("questionNum") +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-12\">\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"name\">Your name</label>\n" +
                "                        <input type=\"text\" class=\"form-control\" name=\"name\" placeholder=\"Enter your name\" required>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-12\">\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"answer\">Your answer</label>\n" +
                "                        <input type=\"text\" class=\"form-control\" name=\"answer\" placeholder=\"Enter your answer\" required>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">" +
                "                <div class=\"col-2\">\n" +
                "                   <button type=\"submit\" class=\"btn btn-primary\">Send</button>\n" +
                "                </div> "+
                "               <input type=\"hidden\" name=\"questionNum\" value=\"" +
                            request.getParameter("questionNum") + "\">\n " +
                "                <div class=\"col-2\">\n" +
                "                    <a class=\"btn btn-primary\" href=\"/QuestionsServlet\">Cancel</a>"+
                "                </div> "+
                "            </div>\n" +
                "            <div class=\"row\">" +
                "                <a href=\"/QuestionsServlet\">back to questions</a>"+
                "            </div> "+
                "        </form>\n" +
                "    </div>\n" +
                "</body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
