package com.livenet.livenet.sesion;

import com.livenet.livenet.localizacion.Localizacion;
import com.livenet.livenet.usuario.Usuario;
import com.livenet.livenet.usuario.usuariosDAO;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class SesionesRESTController {

    @Autowired
    private sesionesDAO pd;


    //Codigo para las sesiones:
    //121 OK -> Inicia
    //323 Ok pero ha expirado
    @RequestMapping(value="sesion/{token}", method = RequestMethod.GET)
    public ResponseEntity<Integer> findByToken(@PathVariable("token") String token){
        Sesion s = pd.findByToken(token);

        if (s != null) {
            int code;
            if(s.getLoggedout() >  System.currentTimeMillis()) {
                code = 121;
                s.setLoggedin(System.currentTimeMillis());
                pd.save(s);
            }else{
                code = 323;
                borrarSesion(token);
            }
            return ResponseEntity.ok(code);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "insertsesion", method = RequestMethod.POST)
    public ResponseEntity<Sesion> create(@RequestBody Sesion sesion){
        String alias = sesion.getAlias();
        sesion.setAlias(alias);
        Sesion actual = pd.findByAlias(alias);
        if(actual != null){
            actual.setToken(sesion.getToken());
            actual.setLoggedin(sesion.getLoggedin());
            actual.setLoggedin(sesion.getLoggedout()+259200000);
            pd.save(actual);
        }else {
            sesion.setLoggedout(sesion.getLoggedin()+259200000);
            actual = pd.save(sesion);
        }
        return ResponseEntity.ok(actual);
    }


    @RequestMapping(value = "cerrarsesion/{token}", method = RequestMethod.GET)
    public ResponseEntity<String> borrarSesion(@PathVariable("token") String token) {
        // Buscamos el localizacion por alias
            String tok = token;
            Sesion s = pd.findByToken(token);

            pd.delete(s);
            return ResponseEntity.ok().build();
    }
}
