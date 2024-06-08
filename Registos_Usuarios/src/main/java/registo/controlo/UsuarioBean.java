package registo.controlo;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import registo.dao.UsuarioDAO;
import registo.modelo.Usuario;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private List<Usuario> usuarios = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
        try {
            usuarios = usuarioDAO.listar();
        } catch (Throwable ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String guardar() throws Throwable {
        usuarioDAO.guardar(usuario);
        usuario = new Usuario();
        usuarios = usuarioDAO.listar();
        return "lista_usuario.faces?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public String editar() throws Throwable {
        usuarioDAO.editar(usuario);
        usuario = new Usuario();
        usuarios = usuarioDAO.listar();
        return "lista_usuario.xhtml.faces?faces-redirect=true";
    }

    public String eliminar() throws Throwable{
        usuarioDAO.eliminar(usuario.getCodigo());
        usuario = new Usuario();
        usuarios = usuarioDAO.listar();
        return "lista_usuario.xhtml.faces?faces-redirect=true";
    }
}
