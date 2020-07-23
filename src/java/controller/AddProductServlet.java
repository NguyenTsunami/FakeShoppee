/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Classify;
import model.Item;
import model.ProductGroup;
import model.Shop;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author thuy
 */
public class AddProductServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            int accid = acc.getId();
            AccountDAO da = new AccountDAO();
            Shop shop = da.getShop(accid);
            if (shop != null) {
                //-----------------------------------------------------------------------------

                ProductDAO pda = new ProductDAO();
                ProductGroup productGroup = pda.addProductGroup(shop.getId());
                if (productGroup != null) {
                    boolean oke = handleData(request, response, acc, shop, productGroup);
                    if (!oke) {
                        pda.deleteProductGroup(productGroup.getId());
                        request.setAttribute("error", "Add Fail!");
                        request.getRequestDispatcher("index.jsp?navActive=myaccount&service=addproduct").forward(request, response);
                    } else {
                        request.setAttribute("success", "Add Done!");
                        request.getRequestDispatcher("index.jsp?navActive=myaccount&service=addproduct").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Add Fail!");
                    request.getRequestDispatcher("index.jsp?navActive=myaccount&service=addproduct").forward(request, response);
                }

                //-----------------------------------------------------------------------------    
            } else {
                request.getRequestDispatcher("index.jsp?navActive=myaccount&service=shopinfo").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private boolean handleData(HttpServletRequest request, HttpServletResponse response, Account acc, Shop shop, ProductGroup productGroup)
            throws ServletException, IOException {
        //All info of productGroup
        String name = "";
        String description = "";
        int categoryID = 0;
        int brandID = 0;
        double cost = 0;
        String classify1Name = "";
        String classify2Name = "";
        ArrayList<String> classifyList1 = new ArrayList<>();
        ArrayList<String> classifyList2 = new ArrayList<>();
        ArrayList<Double> priceList = new ArrayList<>();
        ArrayList<Integer> quantityList = new ArrayList<>();
        String state = "";
        String imgCover = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/imgCover.png";
        String img1 = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/img1.png";
        String img2 = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/img2.png";
        String img3 = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/img3.png";
        String img4 = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/img4.png";
        String img5 = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/img5.png";
        ArrayList<String> imgItemList = new ArrayList<>();

        //create folder products
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        ServletContext context = getServletContext();
        String filePath = context.getInitParameter("file-upload");
        filePath = filePath + "\\acc_" + acc.getId() + "\\products";
        File folder_products = new File(filePath);
        if (!folder_products.exists()) {
            folder_products.mkdir();
        }
        filePath = filePath + "\\product_" + productGroup.getId();
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

                int count_img = 0;
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        count_img++;
                        String fieldName = fi.getFieldName();
                        String fileName = "";
                        if (count_img == 1) {
                            fileName = "imgCover.png";
                        } else if (count_img <= 6) {
                            fileName = "img" + (count_img - 1) + ".png";
                        } else {
                            fileName = "imgItem" + (count_img - 6) + ".png";
                        }
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file
                        file = new File(filePath + "\\" + fileName);
                        fi.write(file);

                        // Add to Image Item List
                        if (count_img > 6) {
                            String url = "images/acc_" + acc.getId() + "/products/product_" + productGroup.getId() + "/imgItem" + (count_img - 6) + ".png";
                            imgItemList.add(url);
                        }

                    } else {
                        String field = fi.getFieldName();
                        String value = fi.getString();
                        if (field.equals("name")) {
                            name = value;
                        } else if (field.equals("description")) {
                            description = value;
                        } else if (field.equals("category")) {
                            categoryID = Integer.parseInt(value);
                        } else if (field.equals("brand")) {
                            brandID = Integer.parseInt(value);
                        } else if (field.equals("classify-group-1-name")) {
                            classify1Name = value;
                        } else if (field.equals("classify-group-2-name")) {
                            classify2Name = value;
                        } else if (field.equals("classify-1-item-name")) {
                            classifyList1.add(value);
                        } else if (field.equals("classify-2-item-name")) {
                            classifyList2.add(value);
                        } else if (field.equals("price-item")) {
                            priceList.add(Double.parseDouble(value));
                        } else if (field.equals("quantity-item")) {
                            quantityList.add(Integer.parseInt(value));
                        } else if (field.equals("state")) {
                            state = value;
                        } else if (field.equals("cost")) {
                            cost = Double.parseDouble(value);
                        } else if (field.equals("classify-default-price")) {
                            priceList.add(Double.parseDouble(value));
                        } else if (field.equals("classify-default-quantity")) {
                            quantityList.add(Integer.parseInt(value));
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
                return false;
            }

            // Handle all data
            ProductDAO da = new ProductDAO();

            //
            try {

                int classify1ID = -1;
                int classify2ID = -1;
                ArrayList<Item> item1List = new ArrayList<>();
                ArrayList<Item> item2List = new ArrayList<>();
                //add new classify1 with item
                if (!classify1Name.isEmpty()) {
                    Classify c1 = da.addNewClassify(classify1Name);
                    classify1ID = c1.getId();

                    for (int i = 0; i < classifyList1.size(); i++) {
                        Item item = da.addNewItem(classifyList1.get(i), classify1ID, imgItemList.get(i));
                        item1List.add(item);
                    }
                }
                //add new classify2 with item
                if (!classify2Name.isEmpty()) {
                    Classify c2 = da.addNewClassify(classify2Name);
                    classify2ID = c2.getId();

                    for (int i = 0; i < classifyList2.size(); i++) {
                        Item item = da.addNewItem(classifyList2.get(i), classify2ID, "");
                        item2List.add(item);
                    }
                }
                //add new image-list of product
                int imagesID = da.addNewProductImages(imgCover, img1, img2, img3, img4, img5);
                //shopid
                int shopID = shop.getId();
                //edit product group
                da.editProductGroup(productGroup.getId(), name, shopID, categoryID, brandID, cost, description, imagesID, classify1ID, classify2ID, state);
                //add product
                int cnt = 0;
                if (!item1List.isEmpty()) {
                    for (Item item1 : item1List) {
                        if (item2List.isEmpty()) {
                            cnt++;
                            double price = priceList.get(cnt - 1);
                            int quantity = quantityList.get(cnt - 1);
                            da.addNewProduct(productGroup.getId(), item1.getId(), -1, price, quantity);
                        } else {
                            for (Item item2 : item2List) {
                                cnt++;
                                double price = priceList.get(cnt - 1);
                                int quantity = quantityList.get(cnt - 1);
                                da.addNewProduct(productGroup.getId(), item1.getId(), item2.getId(), price, quantity);
                            }
                        }
                    }
                } else {
                    cnt++;
                    double price = priceList.get(cnt - 1);
                    int quantity = quantityList.get(cnt - 1);
                    da.addNewProduct(productGroup.getId(), -1, -1, price, quantity);
                }

            } catch (Exception e) {
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
