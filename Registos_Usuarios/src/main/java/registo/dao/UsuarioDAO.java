package registo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import registo.db.ConectaDB;
import registo.modelo.Usuario;

public class UsuarioDAO {

    private static final String INSERT = "INSERT INTO tbl_usuario (id_usuario, nome_usuario, email_usuario, senha_usuario, genero_usuario, endereco_usuario) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE tbl_usuario SET(id_usuario=?, nome_usuario=?, email_usuario=?, senha_usuario=?, genero_usuario=?, endereco_usuario=?) WHERE id_usuario = ?";
    private static final String UPDATE_BY_ID = "UPDATE tbl_usuario SET(id_usuario=?, nome_usuario=?, email_usuario=?, senha_usuario=?, genero_usuario=?, endereco_usuario=?";
    private static final String DELETE = "DELETE FROM tbl_usuario WHERE id_usuario = ?";
    private static final String SELECT = "SELECT * FROM tbl_usuario";
    private static final String SELECT_BY_ID = "SELECT * FROM tbl_usuario WHERE id_usuario = ?";

    public void guardar(Usuario usuario) throws Throwable {
        
        try {
            Connection conn = ConectaDB.ligarBD();
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setInt(1, usuario.getCodigo());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getGenero());
            ps.setString(6, usuario.getEndereco());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, "Erro de conexão", ex.getMessage());
            throw ex.getCause();
        }
    
    }

    public void editar(Usuario usuario) throws Throwable {

        try {
            Connection conn = ConectaDB.ligarBD();
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, usuario.getCodigo());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getGenero());
            ps.setString(6, usuario.getEndereco());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, "Erro de conexão", ex.getMessage());
            throw ex.getCause();
        }
    }
    
    public void eliminar( int codigo) throws Throwable{
    
        try {
            Connection conn = ConectaDB.ligarBD();
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, "Erro de conexão", ex.getMessage());
            throw ex.getCause();
        }
    }

    public List<Usuario> listar() throws Throwable {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            Connection conn = ConectaDB.ligarBD();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setCodigo(rs.getInt("id_usuario"));
                us.setNome(rs.getString("nome_usuario"));
                us.setEmail(rs.getString("email_usuario"));
                us.setSenha(rs.getString("senha_usuario"));
                us.setGenero(rs.getString("genero_usuario"));
                us.setEndereco(rs.getString("endereco_usuario"));
                usuarios.add(us);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, "Erro de conexão", ex.getMessage());
            throw ex.getCause();
        }

        return usuarios;
    }
}
