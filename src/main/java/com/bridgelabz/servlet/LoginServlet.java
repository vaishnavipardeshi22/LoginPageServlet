package com.bridgelabz.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPattern = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Vaishnavi"),
                @WebInitParam(name = "password", value = "Vaishnavipardeshi@123")
        }
)

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPattern = "^[A-Z][a-zA-Z]{2,}$";
        String passwordPattern = "^(?=.{8,})(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]*[^A-Za-z0-9][A-Za-z0-9]*$";
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String userId = getServletConfig().getInitParameter("user");
        String passwordValue = getServletConfig().getInitParameter("password");

        if (userId.equals(user) && passwordValue.equals(pwd) && Pattern.matches(userPattern, user) && Pattern.matches(passwordPattern, pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<font color = red> Please enter valid username or password </font>");
            requestDispatcher.include(request, response);
        }
    }
}
