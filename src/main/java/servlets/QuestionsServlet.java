package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * a servlet that sends the form with all questions to the user
 */
@WebServlet(name = "QuestionsServlet", value = "/QuestionsServlet", initParams = @WebInitParam(name = "fileName", value = "questions.txt", description = "the name of the file with questions"))
public class QuestionsServlet extends HttpServlet {

    public BufferedReader reader;
    private LinkedList<String> lines;

    /**
     *
     * the init function that gets the file name as a init parameter and initializes the file reader
     * in addition, sets up the db.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String fileName = config.getInitParameter("fileName");
        String file = config.getServletContext().getRealPath(fileName);

        try {
            reader = new BufferedReader(new FileReader(file));
            readQuestions();
        } catch (IOException e) {
            e.printStackTrace();}

        getServletContext().setAttribute("db", new Db());


    }

//writes to the user a dynamic html file that includes all the questions.
    //in addition, includes also the js file that handles all buttons.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        request.getRequestDispatcher("header.html").include(request, response);
        request.getRequestDispatcher("fetchAjax.js").include(request, response);

        out.println("</script></head>");
        out.println("<body><div class=\"container\" id=\"container\"><h1>Exercise 2</h1>");
        Db db = (Db)request.getServletContext().getAttribute("db");

        for (int i = 0; i < lines.size(); i++) {
            out.println("<div class=\"row\">\n" +
                    "            <div class=\"col-12\">" +
                    "                <div class=\"alert alert-primary\" role=\"alert\">\n" +
                    lines.get(i) +
                    "                </div>" +
                    "            </div>" +
                    "        </div>" +
                    "<div class=\"row\"> "+
                    "   <div class=\"col-2\">\n" +
                    db.getNumOfAnswers(i+1) + " answers\n" +
                    "            </div>\n" +
                    "      <div class=\"col-2\">\n" +
                    "          <button type=\"button\" class=\"btn btn-info\" name=\"show\" id=\"" +
                    Integer.toString(i +1) +
                    "           \">Show</button>\n" +
                    "            </div>\n" +
                    "            <div class=\"col-2\">\n" +
                    "               <a class=\"btn btn-primary\" href=\"/AnswerServlet?questionNum="+
                    Integer.toString(i +1) +
                    "\">Answer this question</a>"+
                    "        </div>" +
                    " </div>");
        }

        out.println( "<div class=\"row justify-content-center col-6\" style=\"display: none\" id=\"ans\"> " +
                "   <table class=\"table table-sm table-primary\t\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">#</th>\n" +
                "      <th scope=\"col\">Name</th>\n" +
                "      <th scope=\"col\">Answer</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "<tbody></tbody>" +
                "</table> " +
                " <div class=\"row\">\n"+
                "<button type=\"button\" class=\"btn btn-primary\" id=\"hide\">Hide</button>\n</div>" +
//                " <div class=\"row\"><button type=\"button\" class=\"btn btn-secondary\" id=\"hide\">Hide<button></div>" +
                "</div>");
        out.println("</body></html>");
        out.close();

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //this function reads line by line from the file of questions.
    private void readQuestions() throws IOException {

        lines = new LinkedList<String>();
        String line = reader.readLine();
        while (line != null) {

            lines.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

}


