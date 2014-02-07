/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import mx.gob.cfe.documentos.dao.DocumentoDao;
import mx.gob.cfe.documentos.dao.UsuarioDao;
import mx.gob.cfe.documentos.model.Documento;
import mx.gob.cfe.documentos.model.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller creado para la depuracion de la base de datos. Al entrar en esta
 * parte el usuario se ara responsable de los daños ocasionados en posibles
 * fallos al depurar.
 *
 * @author develop
 */
@Controller
@RequestMapping("/depurar")
public class DepuracionController {

    private final Logger log = LoggerFactory.getLogger(DocumentoController.class);

    @Autowired
    private DocumentoDao instance;
    @Autowired
    private UsuarioDao usuarioDao;

    /**
     * Este metodo da entrada el usuario a la pantalla de depuracion
     *
     * @param model
     * @param principal
     * @return regresa jsp de depuracion si no es admin arroja a pagina de
     * prohibido
     */
    @RequestMapping
    public String lista(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        return "repurar/lista";
    }

    /**
     * Este metodo inicia el proceso para la descarga de informacion
     *
     * @param id
     * @param response
     * @param principal
     * @return con el response regresa el pdf con la informacion adecuada
     * @throws JRException
     * @throws IOException
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String getDownloadPage(@PathVariable Long id, HttpServletResponse response, Principal principal) throws JRException, IOException {
        log.debug("Received request to show download page");
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        List<Documento> documentos = instance.listaDocumentosCompleto();
        generaReporte("PDF", documentos, response);

        return "redirect:/depurar";
    }

    /**
     * Este metodo inicia el proceso para eliminar completamente los datos en la
     * base de datos de documentos
     *
     * @param id
     * @param response
     * @param principal
     * @return
     * @throws JRException
     * @throws IOException
     */
    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@PathVariable Long id, HttpServletResponse response, Principal principal) throws JRException, IOException {
        log.debug("Received request to show download page");
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        instance.eliminaDocumentosCompleto();
        return "redirect:/depurar";
    }

    /**
     * Metodo utilizado para generrar el pdf
     *
     * @param tipo
     * @param documentos
     * @param response
     * @throws JRException
     * @throws IOException
     */
    private void generaReporte(String tipo, List<Documento> documentos, HttpServletResponse response) throws JRException, IOException {
        log.debug("Generando reporte {}", tipo);
        byte[] archivo = null;
        switch (tipo) {
            case "PDF":
                archivo = generaPdf(documentos);
                response.setContentType("application/pdf");
                Date fecha = new Date();
                response.addHeader("Content-Disposition", "attachment; filename=" + fecha.toString() + "depuracion.pdf");
                break;

        }
        if (archivo != null) {
            response.setContentLength(archivo.length);
            try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
                bos.write(archivo);
                bos.flush();
            }
        }

    }

    /**
     * Metodo que genera el pdf
     *
     * @param documentos
     * @return regresa un pedf ya especificado
     * @throws JRException
     */
    private byte[] generaPdf(List documentos) throws JRException {
        Map<String, Object> params = new HashMap<>();
        JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/jasper/depurar.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(documentos));
        byte[] archivo = JasperExportManager.exportReportToPdf(jasperPrint);

        return archivo;
    }
}
