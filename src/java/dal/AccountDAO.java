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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Address;
import model.BankAcc;
import model.Shop;

/**
 *
 * @author thuy
 */
public class AccountDAO extends BaseDAO {

    public Account getAccount(String user, String pass) {
        Account a = null;

        try {
            String sql = "SELECT id, name, avatar, mail, phone, gender, dob\n"
                    + "FROM Account WHERE username like ? and password like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                a = new Account(
                        rs.getInt("id"),
                        user, pass,
                        rs.getString("name"),
                        rs.getString("avatar"),
                        rs.getString("mail"),
                        rs.getString("phone"),
                        rs.getBoolean("gender"),
                        rs.getString("dob"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    public Account insert(String user, String pass, String name, String avatar, String mail, String phone, boolean gender, String dob) {
        try {
            String sql = "INSERT INTO Account(username, password, name, avatar, mail, phone, gender, dob) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.setString(3, name);
            statement.setString(4, avatar);
            statement.setString(5, mail);
            statement.setString(6, phone);
            statement.setBoolean(7, gender);
            statement.setDate(8, Date.valueOf(dob));
            statement.executeUpdate();
            return getAccount(user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Account editProfile(int id, String user, String pass, String name, String mail, String phone, boolean gender, String dob) {
        try {
            String sql = "update Account\n"
                    + "set \n"
                    + "	username = ?, \n"
                    + " password = ?, \n"
                    + "	name = ?,\n"
                    + "	mail = ?,\n"
                    + "	phone = ?,\n"
                    + "	dob = ?,\n"
                    + "	gender = ?\n"
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.setString(3, name);
            statement.setString(4, mail);
            statement.setString(5, phone);
            statement.setDate(6, Date.valueOf(dob));
            statement.setBoolean(7, gender);
            statement.setInt(8, id);
            statement.executeUpdate();
            return getAccount(user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Account editAvatar(int id, String avatar, String user, String pass) {
        try {
            String sql = "update Account\n"
                    + "set \n"
                    + "	avatar = ?\n"
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, avatar);
            statement.setInt(2, id);
            statement.executeUpdate();
            return getAccount(user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Account editPass(int id, String user, String pass) {
        try {
            String sql = "update Account\n"
                    + "set \n"
                    + "	password = ?\n"
                    + "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pass);
            statement.setInt(2, id);
            statement.executeUpdate();
            return getAccount(user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteAcc(int id) {
        try {
            String sql = "delete from Account where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Shop getShop(int accid) {
        Shop a = null;

        try {
            String sql = "SELECT Shop.id, brand, description, address, Shop.phone, facebook, insta, rating\n"
                    + "FROM Account \n"
                    + "JOIN Shop\n"
                    + "ON Account.id = Shop.accID\n"
                    + "WHERE accid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                a = new Shop(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("description"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("facebook"),
                        rs.getString("insta"),
                        rs.getInt("rating"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    public Shop createShop(int accid, String brand, String description, String address, String phone, String facebook, String insta, int rating) {
        try {
            String sql = "INSERT INTO Shop(accid, brand, description, address, phone, facebook, insta, rating) "
                    + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accid);
            statement.setString(2, brand);
            statement.setString(3, description);
            statement.setString(4, address);
            statement.setString(5, phone);
            statement.setString(6, facebook);
            statement.setString(7, insta);
            statement.setInt(8, rating);
            statement.executeUpdate();
            return getShop(accid);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Shop editShop(int accid, String brand, String description, String address, String phone, String facebook, String insta) {
        try {
            String sql = "update Shop\n"
                    + "set \n"
                    + "	brand = ?, \n"
                    + "	description = ?,\n"
                    + "	address = ?,\n"
                    + "	phone = ?,\n"
                    + "	facebook = ?,\n"
                    + "	insta = ?\n"
                    + "where accid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, brand);
            statement.setString(2, description);
            statement.setString(3, address);
            statement.setString(4, phone);
            statement.setString(5, facebook);
            statement.setString(6, insta);
            statement.setInt(7, accid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getShop(accid);
    }

    public ArrayList<Address> getAddressList(int accid) {
        ArrayList<Address> list = new ArrayList<>();

        String sql = "SELECT * FROM Address where accID = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Address a = new Address(rs.getInt("id"),
                        rs.getString("fullname"),
                        rs.getString("phone"),
                        rs.getString("provincial"),
                        rs.getString("district"),
                        rs.getString("commune"),
                        rs.getString("apartment"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void deleteAddress(int id) {
        String sql = "DELETE FROM Address where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addAddress(int accID, String fullname, String phone, String provincial, String district, String commune, String apartment) {
        String sql = "insert into Address values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accID);
            statement.setString(2, fullname);
            statement.setString(3, phone);
            statement.setString(4, provincial);
            statement.setString(5, district);
            statement.setString(6, commune);
            statement.setString(7, apartment);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<BankAcc> getBankAccList(int accid) {
        ArrayList<BankAcc> list = new ArrayList<>();

        String sql = "SELECT * FROM BankAcc where accID = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BankAcc a = new BankAcc(rs.getInt("id"),
                        rs.getString("holder"),
                        rs.getString("number"),
                        rs.getString("expiration"),
                        rs.getInt("CVV"),
                        rs.getString("bank"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void deleteBankAcc(int id) {
        String sql = "DELETE FROM BankAcc where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBankAcc(int accID, String holder, String number, String expiration, int CVV, String bank) {
        String sql = "insert into BankAcc values(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accID);
            statement.setString(2, holder);
            statement.setString(3, number);
            statement.setDate(4, Date.valueOf(expiration));
            statement.setInt(5, CVV);
            statement.setString(6, bank);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
