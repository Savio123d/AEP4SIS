package com.aep4s.inovalocal.historias.midia;

import com.aep4s.inovalocal.historias.Historia;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private MidiaTipo tipo;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_id")
    private Historia historia;

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }
}
