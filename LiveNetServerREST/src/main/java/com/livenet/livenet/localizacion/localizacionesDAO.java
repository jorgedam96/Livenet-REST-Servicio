package com.livenet.livenet.localizacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Iimplementa todas las funciones de un CRUD de forma automática
 * JDataObject
 * https://www.baeldung.com/spring-dao-jpa
 */
public interface localizacionesDAO extends JpaRepository<Localizacion, Long> {

    Localizacion findByAlias(String alias);

    Localizacion deleteByAlias(String alias);
}
