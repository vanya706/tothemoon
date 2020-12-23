package controller;

import dao.UsersDao;
import entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginForm")
public class LoginFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/loginForm.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("login", login);
        session.setAttribute("password", password);

        UsersDao usersDao = new UsersDao();

        usersDao.findByLogin(login).orElseGet(() ->
                usersDao.create(
                        Users.builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .login(login)
                                .password(password)
                                .build()
                )
        );

        req.getRequestDispatcher("/WEB-INF/userPage.jsp").forward(req,resp);
    }
}
