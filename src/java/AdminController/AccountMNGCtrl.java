/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.*;
import dao.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
@WebServlet(name = "AccountMNGCtrl", urlPatterns = {"/AccountMNGCtrl"})
public class AccountMNGCtrl extends HttpServlet {
    private AccountDAO userDAO;
    HttpSession session;
    
    private EntityManager em;
    private EntityManagerFactory emf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "/adminAccountMNG.jsp";
        
        emf=Persistence.createEntityManagerFactory("WebdoanPU");
        em = emf.createEntityManager();
        userDAO = new AccountDAO(emf);
        session = request.getSession();
        
        List<Accounts> listAccounts = new ArrayList<Accounts>();
        listAccounts = userDAO.getAllAccounts();
        
        session.setAttribute("listAccounts", listAccounts);
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
 
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
