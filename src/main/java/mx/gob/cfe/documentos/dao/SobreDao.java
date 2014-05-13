/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.Date;
import java.util.List;
import mx.gob.cfe.documentos.model.Sobre;
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
public class SobreDao {

    private transient Logger log = LoggerFactory.getLogger(SobreDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Sobre crea(Sobre sobre) {
        currentSession().save(sobre);
        return sobre;
    }

    public Sobre actualiza(Sobre sobre) {
        currentSession().update(sobre);
        currentSession().flush();
        return sobre;
    }

    public String elimina(Long sobreId) {
        Sobre sobre = (Sobre) currentSession().get(Sobre.class, sobreId);
        String nombre = sobre.getNombre();
        currentSession().delete(sobre);
        currentSession().flush();
        return nombre;
    }

    public Sobre obtiene(Long sobreId) {
        Sobre sobre = (Sobre) currentSession().get(Sobre.class, sobreId);
        return sobre;
    }

    @Transactional(readOnly = true)
    public Sobre obtiene(String nombre) {
        Query query = currentSession().createQuery(
                "select s from Sobre s where nombre =:nombre");
        query.setString("nombre", nombre);
        Sobre sobre = (Sobre) query.uniqueResult();
        return sobre;
    }

    public List<Sobre> lista() {
        Query query = currentSession().createQuery("select s from Sobre s  order by fecha desc");

        return query.list();
    }

    public List<Sobre> reporteSabado(Date fecha) {
        Query query = currentSession().createQuery("select s from Sobre s  where s.fecha=:fecha ");
        query.setDate("fecha", fecha);
        return query.list();
    }
}
