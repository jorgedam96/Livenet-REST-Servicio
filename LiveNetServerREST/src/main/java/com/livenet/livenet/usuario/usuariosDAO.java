package com.livenet.livenet.usuario;

import com.livenet.livenet.localizacion.Localizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuariosDAO extends JpaRepository<Usuario, Long> {

    Usuario findByAlias(String alias);

    Usuario findByAliasAndPasswd(String alias,String passwd);

}
