package si.fri.prpo.servleti;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/servlet")
public class JdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Console output");

        resp.getWriter().println("Website output");
        //resp.getWriter().println("Ime aplikacije:" + ConfigurationUtil.getInstance().get("kumuluzee.name").orElse("N/A"));
    }
}

// RUN:
// java -jar .\target\prpo-hello-world-1.0-SNAPSHOT.jar