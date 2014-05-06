/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.List;
import mx.gob.cfe.documentos.model.Documento;
import mx.gob.cfe.documentos.model.Feligres;
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
public class FeligresDao {

    private transient Logger log = LoggerFactory.getLogger(FeligresDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Feligres> lista() {
        Query query = currentSession().createQuery("select f from Feligres f order by nombre desc");
        return query.list();
    }

    public Feligres crea(Feligres feligres) {
        currentSession().save(feligres);
        return feligres;
    }

    public Feligres actualiza(Feligres feligres) {
        currentSession().update(feligres);
        currentSession().flush();
        return feligres;
    }

    public String elimina(Long feligresId) {
        Feligres feligres = (Feligres) currentSession().get(Feligres.class, feligresId);
        String nombre = feligres.getNombre();
        currentSession().delete(feligres);
        currentSession().flush();
        return nombre;
    }

    public Feligres obtiene(Long feligresId) {
        Feligres feligres = (Feligres) currentSession().get(Feligres.class, feligresId);
        return feligres;
    }

    @Transactional(readOnly = true)
    public Feligres obtiene(String nombre) {
        Query query = currentSession().createQuery(
                "select f from Feligres f where nombre  =:nombre");
        query.setString("nombre", nombre);
        Feligres feligres = (Feligres) query.uniqueResult();
        return feligres;
    }

}
