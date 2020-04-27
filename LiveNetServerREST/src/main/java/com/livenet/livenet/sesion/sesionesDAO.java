package com.livenet.livenet.sesion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface sesionesDAO extends JpaRepository<Sesion, Long> {

    Sesion findByAlias(String alias);

    Sesion findByToken(String token);

}
