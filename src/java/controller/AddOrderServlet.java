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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;
import model.Product;
import model.ProductGroup;
import model.Shop;

/**
 *
 * @author thuy
 */
public class AddOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet AddOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddOrderServlet at " + request.getContextPath() + "</h1>");
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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            ProductDAO da = new ProductDAO();
            int groupID = Integer.parseInt(request.getParameter("groupID"));
            int item1ID = 0;
            int item2ID = 0;
            try {
                item1ID = Integer.parseInt(request.getParameter("item1ID"));
            } catch (Exception e) {
                item1ID = 0;
            }
            try {
                item2ID = Integer.parseInt(request.getParameter("item2ID"));
            } catch (Exception e) {
                item2ID = 0;
            }
            Product product = da.getProductByItem(item1ID, item2ID, groupID);
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String img = request.getParameter("img");

            Order order = da.getOrderByUniqueKey(acc, product, "oncart");

            if (order != null) {
                da.updateQuantOfOrder(order.getId(), quantity);
            } else {
                da.addOrder(acc, product, "oncart", quantity, img);
            }
            session.setAttribute("orderListOnCart", da.getListOrderOnState(acc.getId(), "oncart"));
            response.sendRedirect("index.jsp?navActive=myaccount&service=oncart");
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
