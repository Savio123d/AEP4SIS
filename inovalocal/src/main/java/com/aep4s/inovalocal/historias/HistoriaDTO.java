package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.locais.EstadoEnum;

import java.time.LocalDate;

public class HistoriaDTO {

    public record AtualizarHistoriaRequest(
            String titulo,
            String conteudo,
            LocalDate dataPublicacao,
            Long localId,
            LocalDTO local
    ){}

    public record LocalDTO(
            String pais,
            EstadoEnum estado,
            String cidade,
            String distrito
    ){}
}
