/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import mx.gob.cfe.documentos.model.ContadorArchivo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author develop
 */
@Repository
@Transactional
public class InicializaDao {

    @Autowired
    private ContadorArchivoDao contadorArchivoDao;

    private transient Logger log = LoggerFactory.getLogger(InicializaDao.class);

    /**
     * Metodo para inicializar los valores necesarios para que funciones basicas
     * del sistema
     */
    public void inicializa() {
        log.debug("Entrando en inicializa");
        Calendar calendar = GregorianCalendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int añoFundacion = 1954;
        int antiguedad = año - añoFundacion;
        log.debug("Estableciendo contadores en 0's");
        ContadorArchivo contadorArchivo = new ContadorArchivo();
        contadorArchivo.setAntiguedad(antiguedad);
        contadorArchivo.setContador(1);
        contadorArchivo.setDocumento("Circular");
        contadorArchivoDao.crea(contadorArchivo);
        contadorArchivo = new ContadorArchivo();
        contadorArchivo.setAntiguedad(antiguedad);
        contadorArchivo.setContador(1);
        contadorArchivo.setDocumento("Memo");
        contadorArchivoDao.crea(contadorArchivo);
        contadorArchivo = new ContadorArchivo();
        contadorArchivo.setAntiguedad(antiguedad);
        contadorArchivo.setContador(1);
        contadorArchivo.setDocumento("Memo Inter");
        contadorArchivoDao.crea(contadorArchivo);
        contadorArchivo = new ContadorArchivo();
        contadorArchivo.setAntiguedad(antiguedad);
        contadorArchivo.setContador(1);
        contadorArchivo.setDocumento("Oficio");
        contadorArchivoDao.crea(contadorArchivo);
    }

}
