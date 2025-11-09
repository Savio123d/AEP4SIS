package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.midia.MidiaTipo;
import com.aep4s.inovalocal.locais.EstadoEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class HistoriaDTO {

    public record CriarHistoriaRequest(
            @NotBlank @Size(max = 160)
            String titulo,
            @NotBlank @Size(max = 10000)
            String conteudo,
            @NotNull
            Long usuarioId,
            @Valid
            LocalDTO local,
            @Valid
            List<MidiaDTO> midias
    ){}

    public record LocalDTO(
            @NotBlank @Size(max = 60)
            String pais,
            @NotNull
            EstadoEnum estado,
            @NotBlank @Size(max = 80)
            String cidade,
            @Size(max = 80)
            String distrito
    ){}

    public record MidiaDTO(
            @NotBlank @Size(max = 512)
            String url,
            @NotNull
            MidiaTipo tipo,
            @Size(max = 160)
            String descricao
    ){}

    public record HistoriaResponse(
            Long id,
            String titulo,
            String conteudo,
            LocalDate dataPublicacao,
            HistoriaStatus status,

            Long autorId,
            String autorNome,

            Long localId,
            String pais,
            String estado,
            String cidade,
            String distrito,

            List<MidiaResponse> midias
    ){
        public static HistoriaResponse from(Historia h) {
            return new HistoriaResponse(
                    h.getId(),
                    h.getTitulo(),
                    h.getConteudo(),
                    h.getDataPublicacao(),
                    h.getStatus(),
                    h.getAutor() != null ? h.getAutor().getId() : null,
                    h.getAutor() != null ? h.getAutor().getNome() : null,
                    h.getLocal() != null ? h.getLocal().getId() : null,
                    h.getLocal() != null ? h.getLocal().getPais() : null,
                    h.getLocal() != null && h.getLocal().getEstado() != null ? h.getLocal().getEstado().toString() : null,
                    h.getLocal() != null ? h.getLocal().getCidade() : null,
                    h.getLocal() != null ? h.getLocal().getDistrito() : null,
                    h.getMidias() != null ? h.getMidias().stream().map(MidiaResponse::from).toList() : List.of()
            );
        }
    }

    public record MidiaResponse(
            Long id,
            String url,
            String tipo,
            String descricao
    ){
        public static MidiaResponse from(com.aep4s.inovalocal.historias.midia.Midia m){
            return new MidiaResponse(m.getId(), m.getUrl(), m.getTipo().toString(), m.getDescricao());
        }
    }

    public record AtualizarHistoriaRequest(
            @Size(max = 160)
            String titulo,
            @Size(max = 10000)
            String conteudo,

            Long localId,
            @Valid
            LocalDTO local,
            @Valid
            List<MidiaDTO> midias
    ){}

}
