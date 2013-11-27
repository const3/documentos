/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import mx.gob.cfe.documentos.model.Rol;
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
public class RolDao {

    private transient Logger log = LoggerFactory.getLogger(RolDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Rol crea(Rol rol) {
        currentSession().save(rol);
        return rol;
    }

    public Rol actualiza(Rol rol) {
        currentSession().update(rol);
        currentSession().flush();
        return rol;
    }

    public String elimina(Long rolId) {
        Rol rol = (Rol) currentSession().get(Rol.class, rolId);
        String autority = rol.getAuthority();
        currentSession().delete(rol);
        currentSession().flush();
        return autority;
    }

    public Rol obtiene(Long rolId) {
        Rol rol = (Rol) currentSession().get(Rol.class, rolId);
        return rol;
    }

    @Transactional(readOnly = true)
    public Rol obtiene(String nombre) {
        Query query = currentSession().createQuery(
                "select r from Rol r where authority =:Rol");
        query.setString("Rol", nombre);
        Rol rol = (Rol) query.uniqueResult();
        return rol;
    }

}
