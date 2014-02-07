/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
    public ContadorArchivo obtiene(String documento, String departamento) {
        Query query = currentSession().createQuery(
                "select c from ContadorArchivo c where c.documento = :documento AND c.departamento =:departamento");
        query.setString("documento", documento);
        query.setString("departamento", departamento);
        return (ContadorArchivo) query.uniqueResult();
    }

    public void reiniciarContador() {
        Calendar calendar = GregorianCalendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int añoFundacion = 1954;
        int antiguedad = año - añoFundacion;

        log.debug("Estableciendo contadores en 1's");
        List<String> departamentos = new ArrayList();
        departamentos.add("DA");
        departamentos.add("DCL");
        departamentos.add("DCFE");
        departamentos.add("DC");
        departamentos.add("DCSC");
        departamentos.add("DZ");
        departamentos.add("DM");
        departamentos.add("DP");
        departamentos.add("DPL");
        departamentos.add("DI");
        departamentos.add("SZ");

        for (String x : departamentos) {

            ContadorArchivo contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Circular");
            contadorArchivo.setDepartamento(x);
            crea(contadorArchivo);
            contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Memo");
            contadorArchivo.setDepartamento(x);
            crea(contadorArchivo);
            contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Memo Inter");
            contadorArchivo.setDepartamento(x);
            crea(contadorArchivo);
            contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Oficio");
            contadorArchivo.setDepartamento(x);
            crea(contadorArchivo);
        }
    }

}
