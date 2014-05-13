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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import mx.gob.cfe.documentos.dao.FeligresDao;
import mx.gob.cfe.documentos.dao.SobreDao;
import mx.gob.cfe.documentos.model.Feligres;
import mx.gob.cfe.documentos.model.Sobre;
import mx.gob.cfe.documentos.utils.LabelValueBean;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author develop
 */
@Controller
@RequestMapping("/sobres")
public class SobreController {

    private final Logger log = LoggerFactory.getLogger(SobreController.class);
    @Autowired
    private SobreDao dao;
    @Autowired
    private FeligresDao fDao;

    @RequestMapping
    public String lista(Model model, Principal principal) {
        List lista = dao.lista();
        log.error("lista{}", lista);
        model.addAttribute("sobres", dao.lista());
        return "sobres/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("sobre", new Sobre());
        return "sobres/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(@Valid Sobre sobre, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "sobres/nuevo";
        }
        sobre.setFecha(new Date());
        dao.crea(sobre);
        redirectAttributes.addFlashAttribute("mensaje", "El informe" + sobre.getId() + "ha sido creado");
        return "redirect:/sobres/ver/" + sobre.getId();
    }

    @RequestMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("sobre", dao.obtiene(id));
        return "sobres/ver";
    }

    @RequestMapping("/eliminar/{id}")
    public String elimina(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        String nombre = dao.elimina(id);
        redirectAttributes.addFlashAttribute("mensaje", "Se elimino el informe de " + nombre);
        return "redirect:/sobres";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/feligreses", params = "term", produces = "application/json")
    public @ResponseBody
    List<LabelValueBean> feligreses(HttpServletRequest request,
            @RequestParam("term") String filtro) {
        for (String nombre : request.getParameterMap().keySet()) {
            log.debug("Param: {} : {}", nombre,
                    request.getParameterMap().get(nombre));
        }

        List<Feligres> feligresia = fDao.lista();
        List<LabelValueBean> valores = new ArrayList<>();
        for (Feligres feligres : feligresia) {
            StringBuilder sb = new StringBuilder();
            sb.append(feligres.getNombre());
            sb.append(" | ");
            sb.append(feligres.getApellidoPat());
            sb.append(" | ");
            sb.append(feligres.getApellidoMat());
            valores.add(new LabelValueBean(feligres.getId(), sb.toString(),
                    feligres.getNombre()));
        }
        return valores;
    }

    @RequestMapping("/reportes")
    public String reportes(@PathVariable Long id, Model model) {
//        model.addAttribute("sobre", dao.obtiene(id));
        return "sobres/reportes";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String getDownloadPage(HttpServletResponse response, HttpServletRequest request) throws JRException, IOException {
        log.debug("Received request to show download page");
        log.debug("Log");
        Date fecha1 = (Date) request.getAttribute("fecha");
        log.debug("fecha1{}", fecha1);
        Date fecha = new Date();
        List<Sobre> sobres = dao.lista();
        for (Sobre s : sobres) {
            log.debug("s:{}", s);
        }
        log.debug("sobressize{}", sobres.size());
        generaReporte("PDF", sobres, response);

        return "redirect:/sobres";
    }

    private void generaReporte(String tipo, List<Sobre> diezmos, HttpServletResponse response) throws JRException, IOException {
        log.debug("Generando reporte {}", tipo);
        byte[] archivo = null;
        switch (tipo) {
            case "PDF":
                archivo = generaPdf(diezmos);
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=diezmos.pdf");
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
        JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/jasper/diezmos.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(documentos));
        byte[] archivo = JasperExportManager.exportReportToPdf(jasperPrint);

        return archivo;
    }
}
