package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpSessionZTH extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals("admin") && username.equals("admin")) {
            resp.getWriter().write("Welcome friend " + username);
            resp.getWriter().write("Session id: " + req.getSession().getId());
        }
        else {
            req.getSession().setAttribute("user", username);
            req.getSession().setAttribute("session", req.getSession());
            resp.sendRedirect("views/loginFail.jsp");
        }
    }
}
