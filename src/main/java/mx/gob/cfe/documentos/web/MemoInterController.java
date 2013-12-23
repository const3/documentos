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
import mx.gob.cfe.documentos.model.Memo;
import mx.gob.cfe.documentos.model.MemoInter;
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
@RequestMapping("/memoInter")
public class MemoInterController {

    private final Logger log = LoggerFactory.getLogger(DocumentoController.class);

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
        model.addAttribute("memosInter", instance.lista("Memo Inter", usuario.getIniciales()));
        List lista = instance.lista("Memo Inter", usuario.getIniciales());
        log.error("lista{}", lista);
        return "memoInter/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("memoInter", new Documento());
        return "memoInter/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(@Valid MemoInter memoInter, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "memoInter/nuevo";
        }
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        memoInter.setCreador(usuario.getIniciales());
        ContadorArchivo contadorArchivo = contadorDao.obtiene("Memo Inter", usuario.getOficina());
        int cosecutivo = contadorArchivo.getContador();
        log.debug("consecutivo{}", cosecutivo);
        Calendar calendar = GregorianCalendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DATE);
        int añoFuente = 1954;
        int resta = año - añoFuente;
        memoInter.setStatus("A");
        memoInter.setConsecutivo(cosecutivo);
        memoInter.setTipoDocumento("Memo Inter");
        int cosecutivo2 = cosecutivo + 1;
        log.debug("consecutivo{}", cosecutivo2);
        contadorArchivo.setContador(cosecutivo2);
        contadorDao.actualiza(contadorArchivo);
        String folio = memoInter.getDepartamento() + ":" + String.valueOf(resta) + "-" + memoInter.getConsecutivo().toString()
                + "/" + String.valueOf(año);
        memoInter.setFolio(folio);
        instance.crea(memoInter);
        redirectAttributes.addFlashAttribute("mensaje", "El documento para " + memoInter.getDestinatario() + "ha sido creado");
        return "redirect:/memo/ver/" + memoInter.getId();
    }

    @RequestMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("memoInter", instance.obtiene(id));
        return "memoInter/ver";
    }

    @RequestMapping("/envia/{id}")
    public String envia(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        MemoInter memoInter = (MemoInter) instance.obtiene(id);
        memoInter.setStatus("ENV");
        instance.actualiza(memoInter);
        return "redirect:/memoInter";
    }

    @RequestMapping("/autoriza/{id}")
    public String autoriza(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Memo memo = (Memo) instance.obtiene(id);
        memo.setStatus("AUT");
        instance.actualiza(memo);
        return "redirect:/memoInter";
    }

    @RequestMapping("/eliminar/{id}")
    public String elimina(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        String titulo = instance.elimina(id);
        redirectAttributes.addFlashAttribute("mensaje", "Se elimino el documento " + titulo);
        return "redirect:/memoInter";
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String getDownloadPage(@PathVariable Long id, HttpServletResponse response) throws JRException, IOException {
        log.debug("Received request to show download page");
        Documento documento = instance.obtiene(id);
        List<Documento> documentos = new ArrayList<>();
        documentos.add(documento);
        generaReporte("PDF", documentos, response);

        return "redirect:/memoInter";
    }

    private void generaReporte(String tipo, List<Documento> documentos, HttpServletResponse response) throws JRException, IOException {
        log.debug("Generando reporte {}", tipo);
        byte[] archivo = null;
        switch (tipo) {
            case "PDF":
                archivo = generaPdf(documentos);
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=memoInter.pdf");
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
        JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/jasper/memoInter.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(documentos));
        byte[] archivo = JasperExportManager.exportReportToPdf(jasperPrint);

        return archivo;
    }
}
