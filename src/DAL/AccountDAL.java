package DAL.Server;

import BE.Account;
import BE.UserType;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAL {
    private Connection conn;

    public AccountDAL() {
        conn = MSSQLHandler.getConnection();
    }

    public Account getAccountLogin(String username, String password) throws SQLException {
        String query = "SELECT Accounts.id, Accounts.username, Accounts.type, UserInfo.firstName, UserInfo.lastName, UserInfo.email, UserInfo.phone FROM Accounts INNER JOIN UserInfo ON UserInfo.accountId = Accounts.id WHERE Accounts.username = ? AND Accounts.password = ?";

        PreparedStatement execute = conn.prepareStatement(query);
        execute.setString(1,username);
        execute.setString(2,password);

        ResultSet rs = execute.executeQuery();

        if(!rs.next())
            return null;

        int id = rs.getInt("id");
        String user = rs.getString("username");
        UserType type = (rs.getInt("type") == 1) ? UserType.STUDENT : UserType.TEACHER;
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        int phone = rs.getInt("phone");

        Account returnAccount = new Account(user,type,firstName,lastName,email,phone);
        returnAccount.setId(id);

        return returnAccount;
    }
}
