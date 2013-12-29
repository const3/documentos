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
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author develop
 */
@Controller
@RequestMapping("/documento")
public class DocumentoController {

    private final Logger log = LoggerFactory.getLogger(DocumentoController.class);

    @Autowired
    private DocumentoDao instance;
    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping
    public String lista(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        model.addAttribute("documentos", instance.lista("Documento", usuario.getIniciales()));
        List lista = instance.lista("Documento", usuario.getIniciales());
        log.error("lista{}", lista);
        return "documento/lista";
    }

    @RequestMapping("/reporte")
    public String reporte(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        model.addAttribute("documentos", instance.listaReporte(usuario.getIniciales()));
        List lista = instance.listaReporte(usuario.getIniciales());
        log.error("lista{}", lista);
        return "documento/reporte";
    }

    @RequestMapping("/enviados")
    public String listaEnviados(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        model.addAttribute("documentos", instance.listaEnviados(usuario.getOficina()));
        List lista = instance.listaEnviados(usuario.getOficina());
        log.error("lista{}", lista);
        return "documento/enviados";
    }

    @RequestMapping("/autorizar")
    public String autorizar(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        if (!"jefe".equals(usuario.getPuesto())) {
            return "usuario/noAutorizado";
        }
        model.addAttribute("documentos", instance.autoriza(usuario.getOficina()));
        List lista = instance.listaEnviados(usuario.getOficina());
        log.error("lista{}", lista);
        return "documento/autorizar";
    }

    @RequestMapping("/autoriza/{id}")
    public String autoriza(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        if (!"jefe".equals(usuario.getPuesto())) {
            return "usuario/noAutorizado";
        }
        Documento documento = instance.obtiene(id);
        documento.setStatus("AUT");
        instance.actualiza(documento);
        return "redirect:/documento/autorizar";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("documento", new Documento());
        return "documento/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(HttpServletRequest request, @Valid Documento documento, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "documento/nuevo";
        }

        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        Calendar calendar = GregorianCalendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DATE);
        int añoFuente = 1954;
        int resta = año - añoFuente;
        documento.setTipoDocumento("Documento");
        documento.setCreador(usuario.getIniciales());
        documento.setStatus("A");
        documento = instance.crea(documento);
        String consecutivo = documento.getDepartamento() + ":" + String.valueOf(resta) + "-" + documento.getId().toString() + "/" + String.valueOf(año);
        documento.setFolio(consecutivo);
        instance.actualiza(documento);
        redirectAttributes.addFlashAttribute("mensaje", "El documento para " + documento.getDestinatario() + "ha sido creado");
        return "redirect:/documento/ver/" + documento.getId();
    }

    @RequestMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("documento", instance.obtiene(id));
        return "documento/ver";
    }

    @RequestMapping("/edita/{id}")
    public String edita(@PathVariable Long id, Model modelo) {
        log.debug("Edita Asociacion {}", id);
        Documento documento = instance.obtiene(id);
        modelo.addAttribute("documento", documento);
        String tipo = documento.getTipoDocumento();
        switch (tipo) {
            case "Circular":
                return "circular/edita";
            case "Memo":
                return "memo/edita";
            case "Memo Inter":
                return "memoInter/edita";
            case "Oficio":
                return "oficio/edita";

        }

        return "documento/edita";
    }

    @Transactional
    @RequestMapping(value = "/actualiza", method = RequestMethod.POST)
    public String actualiza(HttpServletRequest request, @Valid Documento documento, BindingResult bindingResult, Errors errors,
            Model modelo, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.debug("eeror");
            log.error("Hubo algun error en la forma, regresando");
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Error: {}", error);
            }
            modelo.addAttribute("documento", documento);
            return "documento/edita";
        }
        try {

            log.debug("documento{}", documento.toString());

            documento = instance.actualiza(documento);
        } catch (ConstraintViolationException e) {
            log.error("No se pudo crear la Asociacion", e);
            log.debug("exception");
            modelo.addAttribute("documento", documento);
            return "documento/edita";
        }
        redirectAttributes.addFlashAttribute("message", "asociacion.actualizada.message");
        redirectAttributes.addFlashAttribute("messageAttrs", new String[]{documento.getAsunto()});
        String tipo = documento.getTipoDocumento();
        switch (tipo) {
            case "Circular":
                return "redirect:/circular/ver/" + documento.getId();
            case "Memo":
                return "redirect:/memo/ver/" + documento.getId();
            case "Memo Inter":
                return "redirect:/memoInter/ver/" + documento.getId();
            case "Oficio":
                return "redirect:/oficio/ver/" + documento.getId();

        }

        return "redirect:/documento/ver/" + documento.getId();
    }

    @RequestMapping("/elimina/{id}")
    public String elimina(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        String titulo = instance.elimina(id);
        redirectAttributes.addFlashAttribute("mensaje", "Se elimino el documento " + titulo);
        return "redirect:/documento";
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String getDownloadPage(@PathVariable Long id, HttpServletResponse response, Principal principal) throws JRException, IOException {
        log.debug("Received request to show download page");
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);

        List<Documento> documentos = instance.listaReporte(usuario.getIniciales());
        generaReporte("PDF", documentos, response);

        return "redirect:/documento/reporte";
    }

    private void generaReporte(String tipo, List<Documento> documentos, HttpServletResponse response) throws JRException, IOException {
        log.debug("Generando reporte {}", tipo);
        byte[] archivo = null;
        switch (tipo) {
            case "PDF":
                archivo = generaPdf(documentos);
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=documento.pdf");
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
        JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/jasper/circular.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(documentos));
        byte[] archivo = JasperExportManager.exportReportToPdf(jasperPrint);

        return archivo;
    }
}
