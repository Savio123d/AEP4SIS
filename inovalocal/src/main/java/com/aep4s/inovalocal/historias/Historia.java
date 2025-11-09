package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.locais.Local;
import com.aep4s.inovalocal.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "historias")
@Table(name = "tb_historias")

@Data
public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String conteudo;
    private LocalDate dataPublicacao;
    @Enumerated(EnumType.STRING)
    private HistoriaStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Local local;

    @PrePersist
    public void prePersist(){
        this.status = HistoriaStatus.CRIADA;
        if(dataPublicacao == null) dataPublicacao = LocalDate.now();
    }
}
