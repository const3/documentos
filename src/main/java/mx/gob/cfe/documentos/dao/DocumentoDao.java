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

    /**
     * Crea documento
     *
     * @param documento
     * @return documento
     */
    public Documento crea(Documento documento) {
        documento.setFecha(new Date());
        currentSession().save(documento);
        return documento;
    }

    /**
     * Actualiza documento
     *
     * @param documento
     * @return documento
     */
    public Documento actualiza(Documento documento) {
        documento.setFecha(new Date());
        currentSession().update(documento);
        currentSession().flush();
        return documento;
    }

    /**
     * Elimina documento apartir del id
     *
     * @param documentoId
     * @return regresa el nombre del creador del documento.
     */
    public String elimina(Long documentoId) {
        Documento documento = (Documento) currentSession().get(Documento.class, documentoId);
        String creador = documento.getCreador();
        currentSession().delete(documento);
        currentSession().flush();
        return creador;
    }

    /**
     * Obtiene documento apartir de id
     *
     * @param documentoId
     * @return regresa el objeto completo
     */
    public Documento obtiene(Long documentoId) {
        Documento articulo = (Documento) currentSession().get(Documento.class, documentoId);
        return articulo;
    }

    /**
     * Este metodo lista los documentos de quien lo creo por tipo y estatus
     *
     * @param tipoArchivo
     * @param creador
     * @return regresa la lista de documentos que aun no han sido enviados.
     */
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

    /**
     * Este metodo regresa la lista de todos los documentos por usuario para
     * hacer un reporte
     *
     * @param creador
     * @return regresa la lista de todos los documentos creados por el usuario
     */
    public List<Documento> listaReporte(String creador) {
        Query query = currentSession().createQuery("select a from Documento a where a.creador=:creador  order by fecha desc");
        query.setString("creador", creador);
        return query.list();
    }

    /**
     * Este metodo lista todos los archivos en la base de datos
     *
     * @return Regresa los documentos en la lista
     */
    public List<Documento> listaDocumentosCompleto() {
        Query query = currentSession().createQuery("select a from Documento a order by fecha desc");
        return query.list();
    }

    /**
     * Elimina todos los documentos a la fecha
     */
    public void eliminaDocumentosCompleto() {
        Query query = currentSession().createQuery("delete a from Documento a ");
    }

    /**
     * Lista de los documentos enviados a cada departamento
     *
     * @param departamento
     * @return Regresa la lista de los documentos que han sido enviados a su
     * departamento
     */
    public List<Documento> listaEnviados(String departamento) {
        String statusENV = "ENV";
        Query query = currentSession().createQuery("select a from Documento a where a.status =:statusENV "
                + "and a.departamento=:departamento order by fecha desc");
        query.setString("departamento", departamento);
        query.setString("statusENV", statusENV);
        return query.list();
    }

    /**
     * Lista los documentos por autorizar para un jefe
     *
     * @param fuente
     * @return regresa la lista de documentos que aun no han sido autorizados
     */
    public List<Documento> autoriza(String fuente) {
        String statusA = "A";

        Query query = currentSession().createQuery("select a from Documento a where  a.status =:statusA and a.fuente =:fuente order by fecha desc");
        query.setString("statusA", statusA);
        query.setString("fuente", fuente);
        return query.list();
    }
}
