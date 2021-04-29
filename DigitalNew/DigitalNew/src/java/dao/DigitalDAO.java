/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Digital;

/**
 *
 * @author admin2
 */
public class DigitalDAO {

    public DigitalDAO() throws Exception {
        DBContext db = new DBContext();
        db.setValue();
    }

    
    //get most recent new
    public Digital getNewDigital() throws SQLException, Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select top 1 * from digitalnew \n"
                + "order by timepublish desc";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Digital(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("short"), rs.getString("author"), rs.getTimestamp("timepublish"), rs.getString("image"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return null;
    }

    //get top 5 news
    public List<Digital> getTop5() throws SQLException, Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Digital> getTop5 = new ArrayList<>();
        String sql = "select top 5 * from digitalnew "
                + "order by timepublish desc";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                getTop5.add(new Digital(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("short"), rs.getString("author"), rs.getTimestamp("timepublish"), rs.getString("image")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return getTop5;
    }

    //get new by id
    public Digital getNewByID(int id) throws SQLException, Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select  * from digitalnew "
                + "where id = ?";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Digital(rs.getInt("id"), rs.getString("title"), rs.getString("content"),
                        rs.getString("short"), rs.getString("author"), rs.getTimestamp("timepublish"), rs.getString("image"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return null;
    }

    //count number of new by txtSearch
    public int countNewBySearching(String txtSearch) throws SQLException, Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) as cnt from digitalnew where title like ?";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return 0;
    }

    //get news by index of page with size
    public List<Digital> getByPaging(String txt, int index, int size) throws SQLException, Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from(select ROW_NUMBER() over (order by id ASC) as rn,"
                + " * from digitalnew where title "
                + " like ?) as sub "
                + " where rn between ? and ?";
        List<Digital> list = new ArrayList<>();
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            //begin : set the number of taken starting from 
            int from = index * size - 1;
            //end: set the number of taken starting from 
            //begin : set the number of taking ends
            int to = index * size;
            //end : set the number of taking ends
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Digital(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("short"), rs.getString("author"), rs.getTimestamp("timepublish"), rs.getString("image")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return list;
    }

    //get msost recent description new 
    public String getMostRecentNew() throws SQLException, Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select top 1 [short] from digitalnew  "
                + "order by timepublish desc";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("short");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return "";
    }

    public static void main(String[] args) throws SQLException, Exception {
        DigitalDAO dao = new DigitalDAO();

        String a = dao.getMostRecentNew();
        System.out.println(a);

    }
}
