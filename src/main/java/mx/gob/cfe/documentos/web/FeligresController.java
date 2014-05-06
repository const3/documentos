/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.web;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import mx.gob.cfe.documentos.dao.FeligresDao;
import mx.gob.cfe.documentos.dao.UsuarioDao;
import mx.gob.cfe.documentos.model.Feligres;
import mx.gob.cfe.documentos.model.Usuario;
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
@RequestMapping("/feligres")
public class FeligresController {

    private final Logger log = LoggerFactory.getLogger(DocumentoController.class);

    @Autowired
    private FeligresDao instance;
    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping
    public String lista(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioDao.obtinePorUsername(username);
        model.addAttribute("feligreses", instance.lista());
        List lista = instance.lista();
        log.error("lista{}", lista);
        return "feligres/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("feligres", new Feligres());
        return "feligres/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(HttpServletRequest request, @Valid Feligres feligres, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model,
            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "feligres/nuevo";
        }
        feligres.setFechaAlta(new Date());
        feligres = instance.crea(feligres);
        redirectAttributes.addFlashAttribute("mensaje", "El feligres para " + feligres.getNombre() + "ha sido creado");
        return "redirect:/feligres/ver/" + feligres.getId();
    }

    @RequestMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("feligres", instance.obtiene(id));
        return "feligres/ver";
    }

    @RequestMapping("/edita/{id}")
    public String edita(@PathVariable Long id, Model modelo) {
        log.debug("Edita Asociacion {}", id);
        Feligres feligres = instance.obtiene(id);
        modelo.addAttribute("feligres", feligres);
        return "feligres/edita";
    }

    @Transactional
    @RequestMapping(value = "/actualiza", method = RequestMethod.POST)
    public String actualiza(HttpServletRequest request, @Valid Feligres feligres, BindingResult bindingResult, Errors errors,
            Model modelo, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.debug("eeror");
            log.error("Hubo algun error en la forma, regresando");
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Error: {}", error);
            }
            modelo.addAttribute("feligres", feligres);
            return "feligres/edita";
        }
        try {

            log.debug("feligres{}", feligres.toString());

            feligres = instance.actualiza(feligres);
        } catch (ConstraintViolationException e) {
            log.error("No se pudo crear la Asociacion", e);
            log.debug("exception");
            modelo.addAttribute("feligres", feligres);
            return "feligres/edita";
        }
        redirectAttributes.addFlashAttribute("message", "asociacion.actualizada.message");
        redirectAttributes.addFlashAttribute("messageAttrs", new String[]{feligres.getNombre()});

        return "redirect:/feligres/ver/" + feligres.getId();
    }

    @RequestMapping("/elimina/{id}")
    public String elimina(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        String titulo = instance.elimina(id);
        redirectAttributes.addFlashAttribute("mensaje", "Se elimino el feligres " + titulo);
        return "redirect:/feligres";
    }

}
