package dat.ticketdemoDB;

import dat.ticketdemoDB.exceptions.DatabaseException;
import dat.ticketdemoDB.persistance.Connector;
import dat.ticketdemoDB.persistance.Datamapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WaitingList", urlPatterns = {"/waitinglist"})
public class WaitingList extends HttpServlet {

    Datamapper datamapper;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setAttribute("waitinglist", datamapper.getAllTickets());
        } catch (DatabaseException ex) {
            new DatabaseException(ex.getMessage());
        }

        request.getRequestDispatcher("/waitinglist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter("command");

        switch (command) {
            case "add":
                try {
                    datamapper.createTicket(new Ticket(new Student(request.getParameter("requestname"))));
                } catch (DatabaseException ex) {
                    new DatabaseException(ex.getMessage());
                }
                break;

            case "remove":
                try {
                    datamapper.removeTicket();
                } catch (DatabaseException ex) {
                    new DatabaseException(ex.getMessage());
                }
                break;
        }

        try {
            request.setAttribute("waitinglist", datamapper.getAllTickets());
        } catch (DatabaseException ex) {
            new DatabaseException(ex.getMessage());
        }

        request.getRequestDispatcher("/waitinglist.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
