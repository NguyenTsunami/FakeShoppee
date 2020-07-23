/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Address;
import model.BankAcc;
import model.Brand;
import model.Category;
import model.Classify;
import model.Item;
import model.Order;
import model.Product;
import model.ProductGroup;
import model.Shop;

/**
 *
 * @author thuy
 */
public class ProductDAO extends BaseDAO {

    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> list = new ArrayList<>();

        try {
            String sql = "SELECT id, name\n"
                    + "FROM Category\n"
                    + "order by id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setBrandList(getBrandList(c.getId()));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Brand> getBrandList(int categoryID) {
        ArrayList<Brand> list = new ArrayList<>();

        try {
            String sql = "select id, name\n"
                    + "from Brand\n"
                    + "where categoryID=?\n"
                    + "order by id";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Brand(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ProductGroup addProductGroup(int shopid) {
        try {
            String sql = "insert into ProductGroup(shopID) values(?) \n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shopid);
            statement.executeUpdate();
            sql = "select top 1 id from ProductGroup\n"
                    + "order by id desc";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ProductGroup p = new ProductGroup();
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteProductGroup(int id) {
        try {
            String sql = "delete from ProductGroup where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Classify addNewClassify(String name) {
        try {
            String sql = "insert into Classify(name) values(?)\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
            sql = "select top 1 id from Classify order by id desc";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Classify c = new Classify();
                c.setName(name);
                c.setId(rs.getInt("id"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Item addNewItem(String name, int classifyID, String img) {
        try {
            String sql = "insert into Item(value, classifyID, img) values(?,?,?)\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, classifyID);
            statement.setString(3, img);
            statement.executeUpdate();
            sql = "select top 1 id from Item order by id desc";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                return item;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addNewProductImages(String imgCover, String img1, String img2, String img3, String img4, String img5) {
        try {
            String sql = "insert into ProductImages(imgCover, img1, img2, img3, img4, img5) values(?,?,?,?,?,?)\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imgCover);
            statement.setString(2, img1);
            statement.setString(3, img2);
            statement.setString(4, img3);
            statement.setString(5, img4);
            statement.setString(6, img5);
            statement.executeUpdate();
            sql = "select top 1 id from ProductImages order by id desc";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void editProductGroup(int id, String name, int shopID, int categoryID, int brandID, double cost,
            String description, int imageID, int classify1ID, int classify2ID, String state) {
        try {
            String sql = "update ProductGroup\n"
                    + "set \n"
                    + "	name = ?,\n"
                    + " shopID = ?,\n"
                    + " categoryID = ?,\n"
                    + " brandID = ?,\n"
                    + " cost = ?,\n"
                    + " description = ?,\n"
                    + " imagesID = ?,\n"
                    + " classify1ID = ?,\n"
                    + " classify2ID = ?,\n"
                    + " state = ?\n"
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, shopID);
            if (categoryID == 0) {
                statement.setNull(3, java.sql.Types.INTEGER);
            } else {
                statement.setInt(3, categoryID);
            }
            if (brandID == 0) {
                statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                statement.setInt(4, brandID);
            }
            statement.setDouble(5, cost);
            statement.setString(6, description);
            statement.setInt(7, imageID);
            if (classify1ID != -1) {
                statement.setInt(8, classify1ID);
            } else {
                statement.setNull(8, java.sql.Types.INTEGER);
            }
            if (classify2ID != -1) {
                statement.setInt(9, classify2ID);
            } else {
                statement.setNull(9, java.sql.Types.INTEGER);
            }
            statement.setString(10, state);
            statement.setInt(11, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addNewProduct(int groupID, int item1ID, int item2ID, double price, int quantity) {
        try {
            String sql = "insert into Product(groupID, item1ID, item2ID, price, quantity) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, groupID);
            if (item1ID == -1) {
                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(2, item1ID);
            }
            if (item2ID == -1) {
                statement.setNull(3, java.sql.Types.INTEGER);
            } else {
                statement.setInt(3, item2ID);
            }
            statement.setDouble(4, price);
            statement.setInt(5, quantity);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Shop getShopByID(int id) {
        Shop shop = new Shop();

        try {
            String sql = "select * from Shop where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                shop.setId(rs.getInt("id"));
                int accid = rs.getInt("accID");
                shop.setBrand(rs.getString("brand"));
                shop.setDescription(rs.getString("description"));
                shop.setAddress(rs.getString("address"));
                shop.setPhone(rs.getString("phone"));
                shop.setFacebook(rs.getString("facebook"));
                shop.setInsta(rs.getString("insta"));
                shop.setRating(rs.getInt("rating"));
                return shop;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Category getCategoryByID(int id) {
        try {
            Category cat = new Category();
            String sql = "select * from Category where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
            }
            sql = "select id, name from Brand where categoryID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            ArrayList<Brand> listBrand = new ArrayList<>();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt("id"), rs.getString("name"));
                listBrand.add(b);
            }
            cat.setBrandList(listBrand);
            return cat;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Brand getBrandByID(int id) {
        try {
            String sql = "select id, name from Brand where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt("id"), rs.getString("name"));
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<String> getImagesByID(int id) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String sql = "select * from ProductImages where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                for (int i = 2; i <= 7; i++) {
                    list.add(rs.getString(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Classify getClassifyByID(int id) {
        try {
            Classify cat = new Classify();
            String sql = "select * from Classify where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
            }
            sql = "select * from Item where classifyID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            ArrayList<Item> listItem = new ArrayList<>();
            while (rs.next()) {
                Item b = new Item(rs.getInt("id"), rs.getString("value"), rs.getString("img"));
                listItem.add(b);
            }
            cat.setList(listItem);
            return cat;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<ProductGroup> getAllProductGroupViaCatID(int catID) {
        ArrayList<ProductGroup> list = new ArrayList<>();

        try {
            String sql = "select * from ProductGroup";
            PreparedStatement statement = connection.prepareStatement(sql);
            if (catID != 0) {
                sql = sql + " where categoryID = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, catID);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int shopID = rs.getInt("shopID");
                int categoryID = rs.getInt("categoryID");
                int brandID = rs.getInt("brandID");
                String description = rs.getString("description");
                int imageID = rs.getInt("imagesID");
                int classify1ID = rs.getInt("classify1ID");
                int classify2ID = rs.getInt("classify2ID");
                double sale = rs.getDouble("sale");
                int rating = rs.getInt("rating");
                String state = rs.getString("state");
                double cost = rs.getDouble("cost");

                Shop shop = getShopByID(shopID);
                Category category = getCategoryByID(categoryID);
                Brand brand = getBrandByID(brandID);
                ArrayList<String> images = getImagesByID(imageID);
                Classify classify1 = getClassifyByID(classify1ID);
                Classify classify2 = getClassifyByID(classify2ID);

                ProductGroup pg = new ProductGroup(id, name, shop, category, description, brand, classify1, classify2, sale, rating, state, cost, images);
                list.add(pg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ProductGroup getPGbyID(int id) {
        try {
            String sql = "select * from ProductGroup where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int shopID = rs.getInt("shopID");
                int categoryID = rs.getInt("categoryID");
                int brandID = rs.getInt("brandID");
                String description = rs.getString("description");
                int imageID = rs.getInt("imagesID");
                int classify1ID = rs.getInt("classify1ID");
                int classify2ID = rs.getInt("classify2ID");
                double sale = rs.getDouble("sale");
                int rating = rs.getInt("rating");
                String state = rs.getString("state");
                double cost = rs.getDouble("cost");

                Shop shop = getShopByID(shopID);
                Category category = getCategoryByID(categoryID);
                Brand brand = getBrandByID(brandID);
                ArrayList<String> images = getImagesByID(imageID);
                Classify classify1 = getClassifyByID(classify1ID);
                Classify classify2 = getClassifyByID(classify2ID);

                ProductGroup pg = new ProductGroup(id, name, shop, category, description, brand, classify1, classify2, sale, rating, state, cost, images);
                return pg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Item getItemByID(int id) {
        try {
            String sql = "select * from Item where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Item b = new Item(rs.getInt("id"), rs.getString("value"), rs.getString("img"));
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Product> getProductListOfGroup(int groupID) {
        ArrayList<Product> list = new ArrayList<>();

        try {
            String sql = "select id, item1ID, item2ID, price, quantity from Product where groupID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, groupID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setItem1(getItemByID(rs.getInt("item1ID")));
                product.setItem2(getItemByID(rs.getInt("item2ID")));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Product getProductByItem(int item1ID, int item2ID, int groupID) {
        try {
            String sql;
            PreparedStatement statement = null;
            if (item1ID != 0 && item2ID != 0) {
                sql = "select id, price, quantity from Product where groupID = ? and item1ID = ? and item2ID = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, groupID);
                statement.setInt(2, item1ID);
                statement.setInt(3, item2ID);
            } else if (item1ID != 0 && item2ID == 0) {
                sql = "select id, price, quantity from Product where groupID = ? and item1ID = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, groupID);
                statement.setInt(2, item1ID);
            } else if (item1ID == 0 && item2ID == 0) {
                sql = "select id, price, quantity from Product where groupID = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, groupID);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setGroup(getPGbyID(groupID));
                product.setItem1(getItemByID(item1ID));
                product.setItem2(getItemByID(item2ID));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Order getOrderByUniqueKey(Account acc, Product product, String state) {
        try {
            String sql = "select * from [Order] where accID = ? and productID = ? and state = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, acc.getId());
            statement.setInt(2, product.getId());
            statement.setString(3, state);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProduct(product);
                order.setState(state);
                order.setQuantity(rs.getInt("quantity"));
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void updateQuantOfOrder(int id, int quantity) {
        try {
            String sql = "update [Order] set quantity = quantity + ? \n"
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addOrder(Account acc, Product product, String state, int quantity, String img) {
        try {
            String sql = "insert into [Order](accID, productID, date, quantity, state, img) \n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, acc.getId());
            statement.setInt(2, product.getId());
            statement.setDate(3, Date.valueOf("2020-07-23"));
            statement.setInt(4, quantity);
            statement.setString(5, state);
            statement.setString(6, img);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProductByID(int id) {
        try {
            String sql = "select groupID, item1ID, item2ID, price, quantity from Product where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(id);
                product.setGroup(getPGbyID(rs.getInt("groupID")));
                product.setItem1(getItemByID(rs.getInt("item1ID")));
                product.setItem2(getItemByID(rs.getInt("item2ID")));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public BankAcc getBankAccByID(int id) {
        try {
            String sql = "select * from BankAcc where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BankAcc b = new BankAcc();
                b.setId(id);
                b.setHolder(rs.getString("holder"));
                b.setNumber(rs.getString("number"));
                b.setExpiration(rs.getString("expiration"));
                b.setCVV(rs.getInt("CVV"));
                b.setBank(rs.getString("bank"));
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Address getAddressByID(int id) {
        try {
            String sql = "select * from Address where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Address b = new Address();
                b.setId(id);
                b.setFullname(rs.getString("fullname"));
                b.setPhone(rs.getString("phone"));
                b.setProvincial(rs.getString("provincial"));
                b.setDistrict(rs.getString("district"));
                b.setCommune(rs.getString("commune"));
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Order> getListOrderOnState(int accid, String state) {
        ArrayList<Order> list = new ArrayList<>();

        try {
            String sql = "select * from [Order] where accID = ? and state = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accid);
            statement.setString(2, state);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProduct(getProductByID(rs.getInt("productID")));
                order.setDate(rs.getString("date"));
                order.setQuantity(rs.getInt("quantity"));
                order.setState(state);
                order.setCard(getBankAccByID(rs.getInt("cardID")));
                order.setAddress(getAddressByID(rs.getInt("addressID")));
                order.setImg(rs.getString("img"));
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void checkoutOrder(Order order, BankAcc card, Address address) {
        try {
            String sql = "update Product set quantity = quantity - ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, order.getProduct().getId());
            statement.setInt(1, order.getQuantity());
            statement.executeUpdate();

            sql = "update [Order] set state = ?, cardID = ?, addressID = ? where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "checkout");
            statement.setInt(2, card.getId());
            statement.setInt(3, address.getId());
            statement.setInt(4, order.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order getOrderByID(int id) {
        try {
            String sql = "select * from [Order] where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setProduct(getProductByID(rs.getInt("productID")));
                order.setDate(rs.getString("date"));
                order.setQuantity(rs.getInt("quantity"));
                order.setState(rs.getString("state"));
                order.setCard(getBankAccByID(rs.getInt("cardID")));
                order.setAddress(getAddressByID(rs.getInt("addressID")));
                order.setImg(rs.getString("img"));
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
