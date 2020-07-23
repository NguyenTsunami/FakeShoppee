/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author thuy
 */
public class EditAvatarServlet extends HttpServlet {

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
            out.println("<title>Servlet EditAvatarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAvatarServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("index.jsp?navActive=myaccount&service=aboutme");
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
            int id = acc.getId();
            String user = acc.getUsername();
            String pass = acc.getPassword();
            
            AccountDAO da = new AccountDAO();
            handleData(request, response, acc);
            session.setAttribute("acc", da.getAccount(user, pass));
            response.sendRedirect("index.jsp?navActive=myaccount&service=aboutme");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    private boolean handleData(HttpServletRequest request, HttpServletResponse response, Account acc)
            throws ServletException, IOException {
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        ServletContext context = getServletContext();
        String filePath = context.getInitParameter("file-upload");
        filePath = filePath + "\\acc_" + acc.getId();
        File folder = new File(filePath);

        // Verify the content type
        String contentType = request.getContentType();
        
        if ((contentType.contains("multipart/form-data"))) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);

            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("E:\\SUM20\\PRJ321\\Demo\\web\\images\\temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize);
            
            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = "avatar.png";
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file
                        file = new File(filePath + "\\" + fileName);
                        fi.write(file);
                    }                    
                }
            } catch (Exception ex) {
                System.out.println(ex);
                return false;
            }
            
            return true;
        }
        
        return false;
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
