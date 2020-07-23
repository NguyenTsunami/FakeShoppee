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
import model.Account;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author thuy
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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

        AccountDAO da = new AccountDAO();
        Account acc = da.insert("", "", "", "", "", "", true, "2000-09-17");

        if (acc != null) {
            if (handleData(request, response, acc)) {
                request.setAttribute("success", "Regist successfully!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                da.deleteAcc(acc.getId());
                request.setAttribute("error", "Regist fail!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Regist fail!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private boolean handleData(HttpServletRequest request, HttpServletResponse response, Account acc)
            throws ServletException, IOException {
        String user = "";
        String pass = "";
        String name = "";
        String avatar = "";
        String mail = "";
        String phone = "";
        boolean gender = true;
        String dob = "";

        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        ServletContext context = getServletContext();
        String filePath = context.getInitParameter("file-upload");
        filePath = filePath + "\\acc_" + acc.getId();
        File folder = new File(filePath);
        folder.mkdir();

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
                    } else {
                        String field = fi.getFieldName();
                        String value = fi.getString();
                        if (field.equals("user")) {
                            user = value;
                        } else if (field.equals("pass")) {
                            pass = value;
                        } else if (field.equals("name")) {
                            name = value;
                        } else if (field.equals("mail")) {
                            mail = value;
                        } else if (field.equals("phone")) {
                            phone = value;
                        } else if (field.equals("gender")) {
                            gender = value.equals("mail");
                        } else if (field.equals("dob")) {
                            dob = value;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
                deleteDir(folder);
                return false;
            }

            //handle all data
            AccountDAO da = new AccountDAO();
            Account newacc = da.editProfile(acc.getId(), user, pass, name, mail, phone, gender, dob);
            if (newacc != null) {
                avatar = "images/acc_" + acc.getId() + "/avatar.png";
                newacc = da.editAvatar(acc.getId(), avatar, user, pass);
                if (newacc != null) {
                    System.out.println("something...");
                    return true;
                };
            }
        }
        deleteDir(folder);
        return false;
    }

    private void deleteDir(File file) {
        // neu file la thu muc thi xoa het thu muc con va file cua no
        if (file.isDirectory()) {
            // liet ke tat ca thu muc va file
            String[] files = file.list();
            for (String child : files) {
                File childDir = new File(file, child);
                if (childDir.isDirectory()) {
                    // neu childDir la thu muc thi goi lai phuong thuc deleteDir()
                    deleteDir(childDir);
                } else {
                    // neu childDir la file thi xoa
                    childDir.delete();
                    System.out.println("File bi da bi xoa "
                            + childDir.getAbsolutePath());
                }
            }
            // Check lai va xoa thu muc cha
            if (file.list().length == 0) {
                file.delete();
                System.out.println("File bi da bi xoa " + file.getAbsolutePath());
            }

        } else {
            // neu file la file thi xoa
            file.delete();
            System.out.println("File bi da bi xoa " + file.getAbsolutePath());
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
