package si.fri.prpo.servleti;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet")
public class JdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Console output");

        resp.getWriter().println("Website output\n");
        resp.getWriter().println("Ime aplikacije: " + ConfigurationUtil.getInstance().get("kumuluzee.name").orElse("N/A"));
        resp.getWriter().println("Verzija: " + ConfigurationUtil.getInstance().get("kumuluzee.version").orElse("N/A"));
        resp.getWriter().println("Ime izvajalnega okolja: " + ConfigurationUtil.getInstance().get("kumuluzee.env.name").orElse("N/A"));
    }
}

// RUN:
// java -jar .\target\prpo-hello-world-1.0-SNAPSHOT.jar