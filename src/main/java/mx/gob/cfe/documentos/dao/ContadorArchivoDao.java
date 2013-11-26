/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import mx.gob.cfe.documentos.model.ContadorArchivo;
import mx.gob.cfe.documentos.model.Documento;
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
public class ContadorArchivoDao {

    private static final Logger log = LoggerFactory.getLogger(DocumentoDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public ContadorArchivo crea(ContadorArchivo contadorArchivo) {
        currentSession().save(contadorArchivo);
        return contadorArchivo;
    }

    public ContadorArchivo actualiza(ContadorArchivo contadorArchivo) {
        currentSession().update(contadorArchivo);
        currentSession().flush();
        return contadorArchivo;
    }

    public ContadorArchivo obtiene(Long tipoArchivoId) {
        ContadorArchivo contadorArchivo = (ContadorArchivo) currentSession().get(Documento.class, tipoArchivoId);
        return contadorArchivo;
    }

    @Transactional(readOnly = true)
    public ContadorArchivo obtiene(String documento) {
        Query query = currentSession().createQuery(
                "select c from ContadorArchivo c where c.documento = :documento");
        query.setString("documento", documento);
        return (ContadorArchivo) query.uniqueResult();
    }

}
