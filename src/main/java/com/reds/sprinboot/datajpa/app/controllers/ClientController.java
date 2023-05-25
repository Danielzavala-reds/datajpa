package com.reds.sprinboot.datajpa.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.reds.sprinboot.datajpa.app.models.entity.Client;
import com.reds.sprinboot.datajpa.app.services.IClientService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("client")
// @RequestMapping("/clientes") 
public class ClientController {
    
    @Autowired
    private IClientService iClientService;

    @GetMapping("/lista")
    public String toList(Model model){

      model.addAttribute("title", "Lista de clientes");
      model.addAttribute("clients", iClientService.findAll());

      
      return "listar";
    }

    /* Método para mostrar el formulario al cliente */
    @GetMapping("/formulario")
    public String create(Map<String, Object> model){
      Client client = new Client();
        model.put("client", client);
        model.put("title", "Registrar cliente");
        model.put("action", "Guardar");
        return "formulario";
    }

    @GetMapping("/editar-cliente/{id}")
    public String edit(@PathVariable (value = "id")  Long id ,Map<String, Object> model){
      Client client = null;

      if(id > 0){
        client = iClientService.findOne(id);
      } else{
        return "redirect:/lista";
      }
      model.put("client", client);
      model.put("title", "Editar cliente");
      model.put("action", "Actualizar");

      return "formulario";

    }

    @PostMapping("/formulario")  /* @Valid (en este caso la clase a validar que es Client) y BindingResult siempre van juntos para ejecutar la validadion */
    public String save(@Valid Client client, BindingResult res, Model model, SessionStatus sessionStatus){

      if(res.hasErrors()){
        model.addAttribute("title", "Formulario de Cliente");
        return "formulario";
      }

      iClientService.save(client);
      sessionStatus.setComplete();
      return "redirect:/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable (value = "id") Long id){
      if(id > 0){
        iClientService.delete(id);
      }
      return "redirect:/lista";
    }

    
}
