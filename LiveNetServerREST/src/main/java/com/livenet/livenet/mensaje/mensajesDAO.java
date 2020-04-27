package com.livenet.livenet.mensaje;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface mensajesDAO extends JpaRepository<Mensaje, Long> {

    List<Mensaje> findAllByDestino(String destino);

}
