package ro.teamnet.zth.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/12/2016.
 */
public class ZeroToHeroServlet extends HttpServlet {
    private String handleRequest(HttpServletRequest req) {
        String response = "Hello <b>" + req.getParameter("firstname") + " " + req.getParameter("lastname") + "</b>! Enjoy Zero to Hero!";
        return response;

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write(handleRequest(request));
    }
}
