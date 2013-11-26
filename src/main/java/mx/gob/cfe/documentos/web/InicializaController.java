/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.cfe.documentos.web;

import mx.gob.cfe.documentos.dao.InicializaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author develop
 */
@Controller
@RequestMapping("/inicializa")
public class InicializaController {

    private static final Logger log = LoggerFactory.getLogger(InicioController.class);
    @Autowired
    private InicializaDao instance;

    @RequestMapping
    public String inicio(Model model) {
        log.info("mostrando pagina de inicio");
        instance.inicializa();
        return "redirect:/";
    }
}
