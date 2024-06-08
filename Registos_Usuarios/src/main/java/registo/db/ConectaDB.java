
package registo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {
    
    public static final String URL="jdbc:mysql://localhost:3306/rg_usuario?useSSL=false";
    public static final String USUARIO="root";
    public static final String SENHA="root";
    public static final String DRIVERMYSQL8="com.mysql.cj.jdbc.Driver";
    public static Connection conexao;
    
    public static Connection ligarBD(){
        try {
            if(conexao == null){
            Class.forName(DRIVERMYSQL8);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);}
        } catch (SQLException |  ClassNotFoundException ex) {
        }
        return conexao;
    }
}
