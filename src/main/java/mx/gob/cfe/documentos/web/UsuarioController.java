/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.web;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import mx.gob.cfe.documentos.dao.RolDao;
import mx.gob.cfe.documentos.dao.UsuarioDao;
import mx.gob.cfe.documentos.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author develop
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioDao instance;
    @Autowired
    private RolDao rolDao;

    @RequestMapping
    public String lista(Model model, Principal principal) {
        String name = principal.getName();
        Usuario usuario = instance.obtinePorUsername(name);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        model.addAttribute("usuarios", instance.lista());
        List lista = instance.lista();
        log.error("lista{}", lista);
        return "usuario/lista";

    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model, Principal principal) {
        String name = principal.getName();
        Usuario usuario = instance.obtinePorUsername(name);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuario/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(@Valid Usuario usuario, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model, 
            Principal principal) {
       String name = principal.getName();
        Usuario usuarioSession = instance.obtinePorUsername(name);
        if (!usuarioSession.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        if (bindingResult.hasErrors()) {
            return "usuario/nuevo";
        }
        Set rolesUser = new HashSet();
        rolesUser.add(rolDao.obtiene("ROLE_USER"));
        usuario.setRoles(rolesUser);
        usuario.setFechaAlta(new Date());
        instance.crea(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "El usuario" + usuario.getNombre() + "ha sido creado");
        return "redirect:/usuario/ver/" + usuario.getId();
    }

    @RequestMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model, Principal principal) {
      String name = principal.getName();
        Usuario usuario = instance.obtinePorUsername(name);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        model.addAttribute("usuario", instance.obtiene(id));
        return "usuario/ver";
    }

    @RequestMapping("/eliminar/{id}")
    public String elimina(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, Principal principal) {
       String name = principal.getName();
        Usuario usuario = instance.obtinePorUsername(name);
        if (!usuario.isAdministrador()) {
            return "usuario/noAutorizado";
        }
        String nombre = instance.elimina(id);
        redirectAttributes.addFlashAttribute("mensaje", "Se elimino el usuario" + nombre);
        return "redirect:/usuario";
    }
}
