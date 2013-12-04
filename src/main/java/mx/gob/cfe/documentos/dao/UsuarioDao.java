/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.Date;
import java.util.List;
import mx.gob.cfe.documentos.model.Documento;
import mx.gob.cfe.documentos.model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author develop
 */
@Repository
@Transactional
public class UsuarioDao {

    private transient Logger log = LoggerFactory.getLogger(UsuarioDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Usuario crea(Usuario usuario) {
        currentSession().save(usuario);
        return usuario;
    }

    public Usuario actualiza(Usuario usuario) {
        currentSession().update(usuario);
        currentSession().flush();
        return usuario;
    }

    public String elimina(Long usuarioId) {
        Usuario usuario = (Usuario) currentSession().get(Usuario.class, usuarioId);
        String nombre = usuario.getNombre();
        currentSession().delete(usuario);
        currentSession().flush();
        return nombre;
    }

    public Usuario obtiene(Long usuarioId) {
        Usuario usuario = (Usuario) currentSession().get(Usuario.class, usuarioId);
        return usuario;
    }

    public List<Documento> lista() {
        Query query = currentSession().createQuery("select u from Usuario u order by nombre desc");
        return query.list();
    }

    public Usuario obtinePorUsername(String username) {
        Query query = currentSession().createQuery("select u from Usuario u where u.username=:username ");
        query.setString("username", username);
        return (Usuario) query.uniqueResult();
    }

}
