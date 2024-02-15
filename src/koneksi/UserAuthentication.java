package koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {
    public static boolean authenticateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isAuthenticated = false;

        try {
            conn = koneksi.getConnection();
            String query = "SELECT * FROM tabeladmin WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int uPNAdmin = rs.getInt("PNadmin");
                String uNama = rs.getString("nama");
                String uLevel = rs.getString("level");
                
                AdminSession.setUPNAdmin(uPNAdmin);
                AdminSession.setUUsername(username);
                AdminSession.setUNama(uNama);
                AdminSession.setULevel(uLevel);
                
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
            }
        }

        return isAuthenticated;
    }
}
