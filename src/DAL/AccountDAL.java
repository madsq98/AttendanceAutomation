package DAL.Server;

import BE.Account;
import BE.UserType;

import javax.xml.transform.Result;
import java.sql.*;

public class AccountDAL {
    private Connection conn;

    public AccountDAL() {
        conn = MSSQLHandler.getConnection();
    }

    public Account getAccountByUsername(String username) throws SQLException {
        String query = "SELECT Accounts.id, Accounts.username, Accounts.type, UserInfo.firstName, UserInfo.lastName, UserInfo.email, UserInfo.phone FROM Accounts INNER JOIN UserInfo ON UserInfo.accountId = Accounts.id WHERE Accounts.username = ?";

        PreparedStatement execute = conn.prepareStatement(query);
        execute.setString(1,username);

        ResultSet rs = execute.executeQuery();

        if(!rs.next())
            return null;

        int id = rs.getInt("id");
        String user = rs.getString("username");
        String pass = rs.getString("password");
        UserType type = (rs.getInt("type") == 1) ? UserType.STUDENT : UserType.TEACHER;
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        int phone = rs.getInt("phone");

        Account returnAccount = new Account(user,pass,type,firstName,lastName,email,phone);
        returnAccount.setId(id);

        return returnAccount;
    }

    public int saveNewAccount(Account a) throws SQLException {
        String query = "INSERT INTO Accounts ('username','password','type') VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,a.getUsername());
        ps.setString(2,a.getPassword());
        ps.setInt(3,(a.getType() == UserType.STUDENT) ? 1 : 2);

        int rows = ps.executeUpdate();

        if(rows == 0)
            return -1;

        ResultSet rs = ps.getGeneratedKeys();

        if(rs.next()) {
            int newId = rs.getInt(1);

            String query2 = "INSERT INTO UserInfo ('accountId','firstName','lastName','email','phone') VALUES (?,?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps.setInt(1,newId);
            ps.setString(2,a.getFirstName());
            ps.setString(3,a.getLastName());
            ps.setString(4,a.getEmail());
            ps.setInt(5,a.getPhone());

            int rows2 = ps.executeUpdate();

            if(rows2 == 0)
                return -1;

            return newId;
        }
        else
            return -1;
    }
}
