package com.livenet.livenet.localizacion;


import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController         // Definimos la clase como controlador REST
@RequestMapping("/")    // Definimos la url o entrada de la API REST, este caso la raíz: localhost:8080/
public class LocalizacionesRESTController {

    /**
     * //@RequestMapping @GetMapping @PostMapping, etc (estos dos últimos no redirigen la URL, si no toman la url base
     * //@GetMapping Consume el servicio en la url raiz usando GET localhots:8080/
     * // @RequestMapping(value = "hola", method = RequestMethod.GET) de esta manera indicamos el protocolo y la URL de entrada
     * // localhost:8080/hola
     */

    // Inyección de dependecias para CRUD con ProductosDAO, JDataObject
    // https://www.baeldung.com/spring-dao-jpa
    @Autowired
    private localizacionesDAO pd;


    /**
     * Lista todos las localizaciones. Protocolo GET
     * GET: http://localhost:8080/localizaciones
     *
     * @return Lista de localizaciones
     */
    @RequestMapping(value = "localizaciones", method = RequestMethod.GET)
    public ResponseEntity<List<Localizacion>> findAll() {

        // Nos conectamos y realizamos el select
        List<Localizacion> l = pd.findAll();
        // Devolvemos la ista de localizaciones
        return ResponseEntity.ok(l);
    }

    @RequestMapping(value = "locamigos", method = RequestMethod.POST)
    public ResponseEntity<List<Localizacion>> findAllByAmigos(@RequestBody List<String> amigos) {
        List<Localizacion> resultado = new ArrayList<>();
        // Nos conectamos y realizamos el select
        for (String amigo : amigos) {
            Localizacion loc = pd.findByAlias(amigo);
            resultado.add(loc);
        }
        //List<Localizacion> l = pd.findAllByAlias(amigos);
        // Devolvemos la ista de localizaciones
        return ResponseEntity.ok(resultado);
    }

    /**
     * Devuelve un localizacion dado su ID protocolo GET
     * GET: http://localhost:8080/localizaciones/{alias}
     *
     * @param alias ID del localizacion
     * @return Producto
     */
    //para @query poner value= y la select
    @RequestMapping(value = "localizaciones/{alias}", method = RequestMethod.GET)
    public ResponseEntity<Localizacion> findByAlias(@PathVariable("alias") String alias) {
        // Buscamos el localizacion por alias
        Localizacion loc = pd.findByAlias(alias);

        if (loc != null) {
            return ResponseEntity.ok(loc);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    /**
     * Crea un nuevo localizacion. Protocolo POST
     * POST: http://localhost:8080/localizaciones
     *
     * @param localizacion Producto a crear mendiante JSON
     * @return Producto creado si lo consigue
     */
    @RequestMapping(value = "actualizarLoc", method = RequestMethod.PUT)
    public ResponseEntity<Localizacion> create(@RequestBody Localizacion localizacion) {
        // Creamos un nuevo localizacion a partir de los datos una vez insertado
        System.out.println(localizacion);
        //devolvemos el nuevo localizacion
        Localizacion loc = pd.findByAlias(localizacion.getAlias());
        if (loc != null) {
            loc.setLatitud(localizacion.getLatitud());
            loc.setLongitud(localizacion.getLongitud());
            loc.setFecha_hora(localizacion.getFecha_hora());
            loc.setAccuracy(localizacion.getAccuracy());
            return ResponseEntity.ok(pd.save(loc));
        }
        return ResponseEntity.ok(pd.save(localizacion));
    }

    /**
     * Borra un localizacion de la base de datos. Protocolo DELETE
     * DELETE: http://localhost:8080/localizaciones/{alias}
     *
     * @param id, alias del localizacion a eliminar
     * @return
     */
    @RequestMapping(value = "borrarloc/{alias}", method = RequestMethod.DELETE)
    public ResponseEntity<Localizacion> delete(@PathVariable("alias") Long id) {
        // Buscamos el localizacion por alias
        Optional<Localizacion> op = pd.findById(id);
        // si existe lo borramos y devolvemos
        if (op.isPresent()) {
            // Le pasamos los datos
            pd.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
