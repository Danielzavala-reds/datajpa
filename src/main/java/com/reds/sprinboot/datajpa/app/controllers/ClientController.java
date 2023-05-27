package com.reds.sprinboot.datajpa.app.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.reds.sprinboot.datajpa.app.models.entity.Client;
import com.reds.sprinboot.datajpa.app.services.IClientService;
import com.reds.sprinboot.datajpa.app.services.IUploadFileService;
import com.reds.sprinboot.datajpa.app.utils.PageRender;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("client")
// @RequestMapping("/clientes")
public class ClientController {

  @Autowired
  private IClientService iClientService;

  @Autowired
  private IUploadFileService iUploadFileService;

  private final static Logger log = LoggerFactory.getLogger(ClientController.class);

  @GetMapping("/uploads/{filename:.+}") /*
                                         * El parametro es el nombre del archivo con extensiòn ":.+" esta expresiòn
                                         * regular permite que Spring no borre la extension del archivo
                                         */
  public ResponseEntity<Resource> watchPhoto(@PathVariable String filename) {

    Resource resource = null;
    try {
      resource = iUploadFileService.load(filename);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @GetMapping("/ver/{id}")
  public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model) {

    Client client = iClientService.findOne(id);
    if (client == null) {
      return "redirect:/lista";
    }

    model.put("client", client);
    model.put("title", "Detalle cliente" + client.getName());
    return "ver";
  }

  @GetMapping("/lista")
  public String toList(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

    /*
     * Implementacion de paginaciòn que es un Pageable, indicamos tipo de dato int
     * page como parametro
     * posteriormente indicamos el size, los registros que se mostraran en la vista
     */
    Pageable pageRequest = PageRequest.of(page, 4);

    /*
     * Retorna un Page de tipo cliente, lo asignamos al mètodo findAll paginable que
     * se definio en la interface del servicio
     */
    Page<Client> clients = iClientService.findAll(pageRequest);
    PageRender<Client> pageRender = new PageRender<>("/lista", clients);

    model.addAttribute("title", "Lista de clientes");
    model.addAttribute("clients", clients);
    model.addAttribute("page", pageRender);

    return "listar";
  }

  /* Método para mostrar el formulario al cliente */
  @GetMapping("/formulario")
  public String create(Map<String, Object> model) {
    Client client = new Client();
    model.put("client", client);
    model.put("title", "Registrar cliente");
    model.put("action", "Guardar");
    return "formulario";
  }

  @GetMapping("/editar-cliente/{id}")
  public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model) {
    Client client = null;

    if (id > 0) {
      client = iClientService.findOne(id);
    } else {
      return "redirect:/lista";
    }
    model.put("client", client);
    model.put("title", "Editar cliente");
    model.put("action", "Actualizar");

    return "formulario";

  }

  @PostMapping("/formulario") /*
                               * @Valid (en este caso la clase a validar que es Client) y BindingResult
                               * siempre van juntos para ejecutar la validadion
                               */
  public String save(@Valid Client client, BindingResult res, Model model, @RequestParam("file") MultipartFile photo,
      SessionStatus sessionStatus) {

    if (res.hasErrors()) {
      model.addAttribute("title", "Formulario de Cliente");
      return "formulario";
    }

    // Subida de archivos con Path
    if (!photo.isEmpty()) {

      /* Editar usuario, donde se elimina la antigua y se guarda la nueva */
      if (client.getId() != null && client.getId() > 0 && client.getImage() != null && client.getImage().length() > 0) {

        iUploadFileService.delete(client.getImage());

      }

      String uniqueFileName = null;
      try {
        uniqueFileName = iUploadFileService.copy(photo);
      } catch (IOException e) {
        e.printStackTrace();
      }

      client.setImage(uniqueFileName);

      /*
       * Manera de guardar los uploads de forma local en el mismo empaquetado jar, lo
       * que no es buena practica
       */
      // Path directoryResources =
      // Paths.get("datajpa/src/main/resources/static/uploads");
      // String rootPath = directoryResources.toFile().getAbsolutePath();

      /*
       * Manera de desacoplar los uploads, haciendo una ruta totalmente separada del
       * jar
       */

      // String rootPath = "opt/uploads";

      /* Directorio uploads externo al proyecto */
      /* Damos nombres unicos con esta libreria UUID */

    }

    iClientService.save(client);
    sessionStatus.setComplete();
    return "redirect:/lista";
  }

  @GetMapping("/eliminar/{id}")
  public String delete(@PathVariable(value = "id") Long id) {
    if (id > 0) {
      /*
       * Obtenemos el objeto cliente (registro a eliminar) antes de eliminarlo de la
       * DB
       */
      Client client = iClientService.findOne(id);
      iClientService.delete(id);

      /* Obtener ruta absoluta de la imagen */

      if (iUploadFileService.delete(client.getImage())) {
        log.info("Archivo eliminado: " + client.getImage());
      }

    }
    return "redirect:/lista";
  }

}
