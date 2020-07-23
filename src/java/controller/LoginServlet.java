/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Address;
import model.BankAcc;
import model.Order;
import model.Shop;

/**
 *
 * @author thuy
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String rem = request.getParameter("rem");

        AccountDAO da = new AccountDAO();
        ProductDAO pda = new ProductDAO();
        Account acc = da.getAccount(user, pass);

        if (acc != null) {
            Shop shop = da.getShop(acc.getId());
            ArrayList<Address> addressList = da.getAddressList(acc.getId());
            ArrayList<BankAcc> bankaccList = da.getBankAccList(acc.getId());
            ArrayList<Order> orderListOnCart = pda.getListOrderOnState(acc.getId(), "oncart");
            ArrayList<Order> orderListCheckout = pda.getListOrderOnState(acc.getId(), "checkout");
            ArrayList<Order> orderListOnShip = pda.getListOrderOnState(acc.getId(), "onship");
            ArrayList<Order> orderListReceived = pda.getListOrderOnState(acc.getId(), "received");
            ArrayList<Order> orderListFeedbacked = pda.getListOrderOnState(acc.getId(), "feedbacked");

            //session
            HttpSession session = request.getSession(true);
            session.setAttribute("acc", acc);
            session.setAttribute("shop", shop);
            session.setAttribute("addressList", addressList);
            session.setAttribute("bankaccList", bankaccList);
            session.setAttribute("orderListOnCart", orderListOnCart);
            session.setAttribute("orderListCheckout", orderListCheckout);
            session.setAttribute("orderListOnShip", orderListOnShip);
            session.setAttribute("orderListReceived", orderListReceived);
            session.setAttribute("orderListFeedbacked", orderListFeedbacked);

            //cookie
            //-create cookie-
            Cookie cuser = new Cookie("user", user);
            Cookie cpass = new Cookie("pass", pass);
            Cookie cremember = new Cookie("rem", rem);
            //-set life time for cookie-
            if (rem != null) {
                cuser.setMaxAge(1000 * 60 * 60 * 24);
                cpass.setMaxAge(1000 * 60 * 60 * 24);
                cremember.setMaxAge(1000 * 60 * 60 * 24);
            } else {
                cuser.setMaxAge(0);
                cpass.setMaxAge(0);
                cremember.setMaxAge(0);
            }
            //-send cookie to http response header-
            response.addCookie(cuser);
            response.addCookie(cpass);
            response.addCookie(cremember);

            //change direct to index.jsp
            response.sendRedirect("index.jsp?navActive=myaccount&service=aboutme");
        } else {
            request.setAttribute("error", "Login fail!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
