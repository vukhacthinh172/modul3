package business.daoIpl;

import business.dao.IUserDao;
import business.model.Users;
import business.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public List<Users> findAll() {
        List<Users> users = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("select * from user ");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setId(rs.getLong("id"));
                u.setFullName(rs.getString("fullName"));
                u.setAddress(rs.getString("address"));
                u.setStatus(rs.getString("status"));
                u.setRollId(rs.getInt("rollId"));
                users.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return users;
    }

    @Override
    public Users fingById(Long id) {
        Users u = null;
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("select * from user where id=?");
            call.setLong(1, id);
            ResultSet rs = call.executeQuery();
            u.setId(rs.getLong("id"));
            u.setFullName(rs.getString("fullName"));
            u.setAddress(rs.getString("address"));
            u.setStatus(rs.getString("status"));
            u.setRollId(rs.getInt("rollId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return u;
    }

    @Override
    public void save(Users users) {
        Connection conn = ConnectDB.openConnection();
        CallableStatement call ;
        try {

            if (users.getId() == null) {
                // chuc nang them moi
                call = conn.prepareCall("INSERT INTO User (fullName, address, status, roleId) values (?,?,?,?)");
                call.setString(1,users.getFullName());
                call.setString(2,users.getAddress());
                call.setString(3,users.getStatus());
                call.setInt(4,users.getRollId());

            }else {
                // chuc nang cap nhat
                call = conn.prepareCall("UPDATE User SET fullName = ?, address = ?, status = ?, roleId = ? WHERE id = ? ");
                call.setString(1,users.getFullName());
                call.setString(2,users.getAddress());
                call.setString(3,users.getStatus());
                call.setInt(4,users.getRollId());
                call.setLong(5,users.getId());

            }
            call.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }


    @Override
    public void delete(Long id) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("delete  from user where id = ?");
            call.setLong(1,id);
            call.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }


}
