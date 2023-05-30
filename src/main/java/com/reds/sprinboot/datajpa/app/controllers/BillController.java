package com.reds.sprinboot.datajpa.app.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.reds.sprinboot.datajpa.app.models.entity.Bill;
import com.reds.sprinboot.datajpa.app.models.entity.Client;
import com.reds.sprinboot.datajpa.app.services.IClientService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("bill")
public class BillController {

    private static final Logger log = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private IClientService iClientService;
    
    @GetMapping("/form/{clientId}")
    public String create(@PathVariable(value = "clientId") Long clienteId, Map<String, Object> model){
        
        Client client = iClientService.findOne(clienteId);
        if(client == null){
            log.info("error: " + "El cliente no existe en la base de datos");
            return "redirect:/lista";
        }

        /* Aqui seteamos una factura a un cliente */
        Bill bill = new Bill();
        bill.setClient(client);
        
        model.put("bill", bill);
        model.put("title", "Crear factura");

        return "factura/form";
    }

}
