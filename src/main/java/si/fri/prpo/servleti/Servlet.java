package si.fri.prpo.servleti;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        BaseDao uporabnikDao = DaoUser.getInstance();

        User uporabnik = new User("Janez", "Novak", "JanezNovak");

        writer.append("Adding user:\n" + uporabnik.toString());
        uporabnikDao.vstavi(uporabnik);
        writer.append("\n\n");

        writer.append("List of users:\n");
        List<Entity> uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(u -> writer.append(u.toString() + "\n"));

        resp.getWriter().println("Return with ID = 1: " + DaoUser.getInstance().vrni(1));
    }
}
