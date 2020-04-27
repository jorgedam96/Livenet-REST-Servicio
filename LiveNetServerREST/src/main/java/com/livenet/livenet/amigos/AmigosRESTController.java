package com.livenet.livenet.amigos;


import com.livenet.livenet.usuario.Usuario;
import com.livenet.livenet.usuario.usuariosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController         // Definimos la clase como controlador REST
@RequestMapping("/")
public class AmigosRESTController {

    @Autowired
    private amigosDAO pd;

    @Autowired
    private usuariosDAO ud;

    @RequestMapping(value="amigos/{alias}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<String[]>> findAllByAlias(@PathVariable("alias") String alias){
        ArrayList<String[]> users = new ArrayList<>();
        List<Amigo> rest = pd.findAllByAlias1OrAlias2(alias,alias);
        try {
            for (Amigo a : rest) {
                if (a.getAlias1().equals(alias)) {
                    Usuario user = ud.findByAlias(a.getAlias2());
                    users.add(new String[]{a.getAlias2(), user.getFoto(), user.getToken()});

                } else if (a.getAlias2().equals(alias)) {
                    Usuario user = ud.findByAlias(a.getAlias1());
                    users.add(new String[]{a.getAlias1(), user.getFoto(), user.getToken()});

                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return ResponseEntity.ok(users);
    }

    @RequestMapping(value="agregaramigo", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody String[] amigo){
        Amigo resultado = pd.findByAlias1AndAlias2(amigo[0], amigo[1]);
        Amigo resultado2 = pd.findByAlias1AndAlias2(amigo[1], amigo[0]);
        if(resultado != null || resultado2 != null){
            return ResponseEntity.noContent().build();
        }else{
            pd.save(new Amigo(amigo[0], amigo[1]));

        }
        return ResponseEntity.ok("ok google");
    }

    @RequestMapping(value="borraramigo/", method = RequestMethod.POST)
    public ResponseEntity<String> borraramigo(@RequestBody String[] amigos){

        Amigo resultado = pd.findByAlias1AndAlias2(amigos[0], amigos[1]);
        Amigo resultado2 = pd.findByAlias1AndAlias2(amigos[1],amigos[0]);
        if(resultado != null){
            pd.delete(resultado);
            //Amigo borrado = pd.deleteAmigoByAlias1AndAlias2(amigos[0],amigos[1]);
        }else if(resultado2 != null){
            pd.delete(resultado2);
            //Amigo borrado = pd.deleteAmigoByAlias1AndAlias2(amigos[1], amigos[0]);
        }else{
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok("Borrado");
    }

}
