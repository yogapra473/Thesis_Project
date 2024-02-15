/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

public class AdminSession {
    private static int uPNAdmin;
    private static String uUsername;
    private static String uNama;
    private static String uLevel;
     
    public static int getUPNAdmin() {
        return uPNAdmin;
    }
 
    public static void setUPNAdmin(int uPNAdmin) {
        AdminSession.uPNAdmin = uPNAdmin;
    }
 
    public static String getUUsername() {
        return uUsername;
    }
 
    public static void setUUsername(String uUsername) {
        AdminSession.uUsername = uUsername;
    }
 
    public static String getUNama() {
        return uNama;
    }
 
    public static void setUNama(String uNama) {
        AdminSession.uNama = uNama;
    }
    
    public static String getULevel() {
        return uLevel;
    }
    
    public static void setULevel(String uLevel) {
        AdminSession.uLevel = uLevel;
    }
}
