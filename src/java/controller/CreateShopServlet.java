/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Shop;

/**
 *
 * @author thuy
 */
public class CreateShopServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateShopServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("index.jsp?navActive=myaccount&service=shopinfo");
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
        HttpSession session = request.getSession(true);
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            int accid = acc.getId();
            String brand = request.getParameter("shop_brand");
            String description = request.getParameter("shop_description");
            String address = request.getParameter("shop_address");
            String phone = request.getParameter("shop_phone");
            String facebook = request.getParameter("shop_facebook");
            String insta = request.getParameter("shop_insta");

            AccountDAO da = new AccountDAO();
            Shop shop = da.createShop(accid, brand, description, address, phone, facebook, insta, 0);

            if (shop != null) {
                session.setAttribute("shop", shop);
                request.getRequestDispatcher("index.jsp?navActive=myaccount&service=shopinfo").forward(request, response);
            } else {
                request.setAttribute("create_shop_error", "Fail! Can you choose another brand?");
                request.getRequestDispatcher("index.jsp?navActive=myaccount&service=shopinfo").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
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
