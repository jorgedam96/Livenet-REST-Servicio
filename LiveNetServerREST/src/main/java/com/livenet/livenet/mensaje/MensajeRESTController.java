package com.livenet.livenet.mensaje;


import com.livenet.livenet.usuario.Usuario;
import com.livenet.livenet.usuario.usuariosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensaje")
public class MensajeRESTController {


    @Autowired
    private mensajesDAO pd;
    private usuariosDAO ud;


    @RequestMapping(value="recibir/{destino}", method = RequestMethod.GET)
    public ResponseEntity<List<Mensaje>> findByDestino(@PathVariable("destino") String destino){
        List<Mensaje> m = pd.findAllByDestino(destino);


        if (m != null) {
            for(Mensaje ms : m){
                pd.delete(ms);

            }
            return ResponseEntity.ok(m);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @RequestMapping(value="enviar",method = RequestMethod.POST)
    public ResponseEntity<Mensaje> create(@RequestBody Mensaje mensaje){
        Usuario us = ud.findByAlias(mensaje.getDestino());

        if(us != null) {
            Mensaje m = pd.save(mensaje);

            return ResponseEntity.ok(m);
        }
        return ResponseEntity.notFound().build();
    }

}
