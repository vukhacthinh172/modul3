package present;

import business.model.Users;
import business.service.IUsersService;
import business.serviceIpl.UsersService;
import business.util.ConnectDB;
import business.util.InputMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserManager {
    private static final IUsersService userService = new UsersService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=======================Menu=======================");
            System.out.println("1. Hiển thị danh sách user");
            System.out.println("2. Thêm mới user");
            System.out.println("3. Chỉnh sửa thông tin user theo id");
            System.out.println("4. Xóa user");
            System.out.println("5. Thoát chng trình");


            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    displayInput();
                    break;
                case 2:
                    addNewUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nập không hợp lệ");
                    break;
            }
        }
    }


    public static void displayInput() {
        List<Users> users = userService.fingAll();
        if (users.isEmpty()) {
            System.err.println("Danh sach rong ");
            return;
        }
        for (Users u : users) {
            System.out.println(u);
        }
    }

    public static void addNewUser() {
        System.out.println("nhap ten quyen  ");
        String ten_quyen = InputMethods.getString();
        Long roleId = findRoleIdByName(ten_quyen);
        if (roleId == null) {
            System.err.println("Quyền không tồn tại trong bảng Quyen.");
            return;
        }
        System.out.println("Nhập số lươg người dùng  cần thêm");
        int count = InputMethods.getInteger();
        for (int i = 1; i <=count ; i++) {
            System.out.println("------Nhập thông tin cho user thứ "+i+"--------");
            String fullName  = InputMethods.getString();
            String address  = InputMethods.getString();
            String status = InputMethods.getString();
            Integer roleID = InputMethods.getInteger();
            Users  u = new Users(null,fullName,address,status,roleID);
            userService.add(u);
        }
        System.out.println("Thêm mới thành công "+count+" sinh viên");
    }
    public static  void  updateUser(){
         Users u = new Users();
        System.out.println("nhap id muon cap nhat ");
        Long idUp = InputMethods.getLong();
        if (userService.findById(idUp)==null){
            System.err.println("ko tim thay id phu hop");
            return;
        }
        // Nhập thông tin cập nhật từ người dùng
        System.out.print("Nhập tên mới: ");
        String updatedFullName = InputMethods.getString();
        System.out.print("Nhập địa chỉ mới: ");
        String updatedAddress = InputMethods.getString();
        System.out.print("Nhập trạng thái mới: ");
        String updatedStatus = InputMethods.getString();
        System.out.print("Nhập ID quyền mới: ");
        int updatedRoleId = InputMethods.getInteger();

        // Cập nhật thông tin người dùng
        u.setFullName(updatedFullName);
        u.setAddress(updatedAddress);
        u.setStatus(updatedStatus);
        u.setRollId(updatedRoleId);

        // Gọi phương thức cập nhật trong service hoặc repository của bạn
        userService.update(u);

        System.out.println("Người dùng đã được cập nhật thành công.");
    }

    public  static void delete() {
        System.out.println("nhap id muon xoa ");
        Long idUp = InputMethods.getLong();
        if (userService.findById(idUp)==null){
            System.err.println("ko tim thay id phu hop");
            return;
        }
        userService.delete(idUp);
        System.out.println("Người dùng đã được xóa thành công.");
    }
    public static Long findRoleIdByName(String ten_quyen) {
        Connection connection = null;
        try {
            // Mở kết nối
            connection = ConnectDB.openConnection();

            String query = "SELECT id FROM Quyen WHERE ten_quyen = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ten_quyen);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            // Đóng kết nối trong khối finally để đảm bảo kết nối được đóng dù có lỗi hay không
            ConnectDB.closeConnection(connection);
        }
        return null;
    }


}
