/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.Date;
import java.util.List;
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
public class DocumentoDao {

    private static final Logger log = LoggerFactory.getLogger(DocumentoDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Documento crea(Documento documento) {
        documento.setFecha(new Date());
        currentSession().save(documento);
        return documento;
    }

    public Documento actualiza(Documento documento) {
        documento.setFecha(new Date());
        currentSession().update(documento);
        currentSession().flush();
        return documento;
    }

    public String elimina(Long documentoId) {
        Documento documento = (Documento) currentSession().get(Documento.class, documentoId);
        String creador = documento.getCreador();
        currentSession().delete(documento);
        currentSession().flush();
        return creador;
    }

    public Documento obtiene(Long documentoId) {
        Documento articulo = (Documento) currentSession().get(Documento.class, documentoId);
        return articulo;
    }

    public List<Documento> lista(String tipoArchivo, String creador) {
        String statusA = "A";
        String statusAUT = "AUT";
        Query query = currentSession().createQuery("select a from Documento a where a.tipoDocumento =:tipoArchivo and a.creador=:creador "
                + "and a.status =:statusA OR a.status=:statusAUT order by fecha desc");
        query.setString("tipoArchivo", tipoArchivo);
        query.setString("creador", creador);
        query.setString("statusA", statusA);
        query.setString("statusAUT", statusAUT);
        return query.list();
    }

    public List<Documento> listaEnviados(String departamento) {
        String statusENV = "ENV";
        Query query = currentSession().createQuery("select a from Documento a where a.status =:statusENV "
                + "and a.departamento=:departamento order by fecha desc");
        query.setString("departamento", departamento);
        query.setString("statusENV", statusENV);
        return query.list();
    }

    public List<Documento> listaRecividos(String departamento) {
        Query query = currentSession().createQuery("select a from Documento a where a.status =:status and a.departamento:departamento "
                + "order by fecha desc");
        query.setString("status", "REC");
        query.setString("departamento", departamento);
        return query.list();
    }
}
