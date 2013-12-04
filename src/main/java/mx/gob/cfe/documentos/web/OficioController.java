/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import mx.gob.cfe.documentos.dao.ContadorArchivoDao;
import mx.gob.cfe.documentos.dao.DocumentoDao;
import mx.gob.cfe.documentos.dao.UsuarioDao;
import mx.gob.cfe.documentos.model.ContadorArchivo;
import mx.gob.cfe.documentos.model.Documento;
import mx.gob.cfe.documentos.model.Oficio;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author develop
 */
@Controller
@RequestMapping("/oficio")
public class OficioController {

    private final Logger log = LoggerFactory.getLogger(OficioController.class);

    @Autowired
    private DocumentoDao instance;
    @Autowired
    private ContadorArchivoDao contadorDao;
    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping
    public String lista(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        model.addAttribute("oficios", instance.lista("Oficio", usuario.getIniciales()));
        List lista = instance.lista("Oficio", usuario.getIniciales());
        log.error("lista{}", lista);
        return "oficio/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("oficio", new Documento());
        return "oficio/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(@Valid Oficio oficio, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "oficio/nuevo";
        }
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        oficio.setCreador(usuario.getIniciales());
        ContadorArchivo contadorArchivo = contadorDao.obtiene("Oficio");
        int cosecutivo = contadorArchivo.getContador();
        Calendar calendar = GregorianCalendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DATE);
        int añoFuente = 1954;
        int resta = año - añoFuente;
        oficio.setTipoDocumento("Oficio");
        int consecutivo2 = cosecutivo + 1;
        oficio.setConsecutivo(cosecutivo);
        contadorArchivo.setContador(consecutivo2);
        contadorDao.actualiza(contadorArchivo);
        String consecutivo = oficio.getDepartamento() + ":" + String.valueOf(resta) + "-" + oficio.getConsecutivo() + "/" + String.valueOf(año);
        oficio.setFolio(consecutivo);
        instance.crea(oficio);
        redirectAttributes.addFlashAttribute("mensaje", "El documento para " + oficio.getDestinatario() + "ha sido creado");
        return "redirect:/oficio/ver/" + oficio.getId();
    }

    @RequestMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("oficio", instance.obtiene(id));
        return "oficio/ver";
    }

    @RequestMapping("/elimina/{id}")
    public String elimina(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        String titulo = instance.elimina(id);
        redirectAttributes.addFlashAttribute("mensaje", "Se elimino el documento " + titulo);
        return "redirect:/oficio";
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String getDownloadPage(@PathVariable Long id, HttpServletResponse response) throws JRException, IOException {
        log.debug("Received request to show download page");
        Documento documento = instance.obtiene(id);
        List<Documento> documentos = new ArrayList<>();
        documentos.add(documento);
        generaReporte("PDF", documentos, response);

        return "redirect:/oficio";
    }

    private void generaReporte(String tipo, List<Documento> documentos, HttpServletResponse response) throws JRException, IOException {
        log.debug("Generando reporte {}", tipo);
        byte[] archivo = null;
        switch (tipo) {
            case "PDF":
                archivo = generaPdf(documentos);
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=oficio.pdf");
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

    private byte[] generaPdf(List documentos) throws JRException {
        Map<String, Object> params = new HashMap<>();
        JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/jasper/oficio.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(documentos));
        byte[] archivo = JasperExportManager.exportReportToPdf(jasperPrint);

        return archivo;
    }
}
