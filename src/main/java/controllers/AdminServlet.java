package controllers;

import dao.AdminDao;
import dao.AdminDaoImpl;
import models.Compte;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", urlPatterns = "/")
public class AdminServlet extends HttpServlet {
    private AdminDao adminDao = new AdminDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> types = adminDao.getAllComptesType();
        request.setAttribute("types", types);
        String action  = request.getServletPath();
        switch (action) {
            case "/new" :
                showNewForm(request, response);
                break;
            case "/insert" :
                insertClient(request, response);
                break;
            case "/edit" :
                showEditForm(request, response);
                break;
            case "/update" :
                updateClient(request, response);
                break;
            case "/delete" :
                deleteClient(request, response);
                break;
            case "/list" :
                listClients(request, response);
                break;
            case "/clients" :
                listClientsBySearch(request, response);
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/client-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("id"));
        Compte existentCompte = adminDao.getClient(idClient);
        String type = existentCompte.getType();
        request.setAttribute("type", type);
        request.setAttribute("compte", existentCompte);
        request.getRequestDispatcher("/client-form.jsp").forward(request, response);
    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        long accountNum = Long.parseLong(request.getParameter("account-number"));
        String accountType = request.getParameter("type");
        double balance = Double.parseDouble(request.getParameter("balance"));
        Compte compte = new Compte(firstName, lastName, accountNum, accountType, balance);
        adminDao.addClient(compte);
        response.sendRedirect("/list");
    }



    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("id-client"));
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        long accountNum = Long.parseLong(request.getParameter("account-number"));
        double balance = Double.parseDouble(request.getParameter("balance"));
        Compte compte = new Compte(firstName, lastName, idClient, accountNum, balance);
        adminDao.updateClient(compte);
        response.sendRedirect("/list");

    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("id"));
        adminDao.deleteCompte(idClient);
        adminDao.deleteClient(idClient);
        response.sendRedirect("/list");
    }

    private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Compte> comptes =  adminDao.getAllClient();
        request.setAttribute("comptes", comptes);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void listClientsBySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String last = request.getParameter("last");
        String type = request.getParameter("type");
        ArrayList<Compte> comptes =  adminDao.getAllClientBySearch(last, type);
        request.setAttribute("comptes", comptes);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
