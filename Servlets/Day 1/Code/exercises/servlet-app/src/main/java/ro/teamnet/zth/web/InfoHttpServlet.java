package ro.teamnet.zth.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by user on 7/12/2016.
 */
public class InfoHttpServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String headerTable = "<table>";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            headerTable += "<tr> <th> " + header + "</th> <th> " + request.getHeader(header) + "</th> </tr>";
        }
        headerTable += "</table>";
        response.getWriter().write("<p>" + headerTable + "</p>");
        response.getWriter().write("<p> The HTTP method is: " + request.getMethod() + "</p>");
        response.getWriter().write("<p> The Query String is: " + request.getQueryString() + "</p>");

        String cookieTable = "<table>";
        Cookie[] cookies = request.getCookies();
        cookieTable += "<tr> <th> Comment </th> <th> Domain </th> <th> MaxAge </th> <th> Name </th> <th> Path </th> <th> Secure </th> <th> Value </th> </tr>";
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookieTable += "<tr> <th> " + cookie.getComment() + "</th> ";
                cookieTable += "<th> " + cookie.getDomain() + "</th> ";
                cookieTable += "<th> " + cookie.getMaxAge() + "</th> ";
                cookieTable += "<th> " + cookie.getName() + "</th> ";
                cookieTable += "<th> " + cookie.getPath() + "</th> ";
                cookieTable += "<th> " + cookie.getSecure() + "</th> ";
                cookieTable += "<th> " + cookie.getValue() + "</th> </tr>";
            }
        cookieTable += "</table>";
        response.getWriter().write("<p>" + cookieTable + "</p>");
    }
}
