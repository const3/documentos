/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mx.gob.cfe.documentos.model.ContadorArchivo;
import mx.gob.cfe.documentos.model.Rol;
import mx.gob.cfe.documentos.model.Usuario;
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
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;

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
            contadorArchivoDao.crea(contadorArchivo);
            contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Memo");
            contadorArchivo.setDepartamento(x);
            contadorArchivoDao.crea(contadorArchivo);
            contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Memo Inter");
            contadorArchivo.setDepartamento(x);
            contadorArchivoDao.crea(contadorArchivo);
            contadorArchivo = new ContadorArchivo();
            contadorArchivo.setAntiguedad(antiguedad);
            contadorArchivo.setContador(1);
            contadorArchivo.setDocumento("Oficio");
            contadorArchivo.setDepartamento(x);
            contadorArchivoDao.crea(contadorArchivo);
        }
        Rol rol = new Rol("ROLE_ADMIN");
        rolDao.crea(rol);
        rol = new Rol("ROLE_USER");
        rolDao.crea(rol);

        Usuario usuario = new Usuario();
        usuario.setApMaterno("prueba");
        usuario.setApPaterno("prueba");
        usuario.setNombre("samuel");
        usuario.setIniciales("sms");
        usuario.setPuesto("Jefe");
        usuario.setOficina("DA");
        usuario.setCorreo("samuel@pruebas.com");
        usuario.setPassword("hola");
        usuario.setFechaAlta(new Date());
        Set rolesUser = new HashSet();
        rolesUser.add(rolDao.obtiene("ROLE_ADMIN"));
        rolesUser.add(rolDao.obtiene("ROLE_USER"));
        usuario.setRoles(rolesUser);
        usuario.setUsername("sam");
        usuario.setAdministrador(Boolean.TRUE);
        usuarioDao.crea(usuario);
    }

}
