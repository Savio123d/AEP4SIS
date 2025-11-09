package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.midia.Midia;
import com.aep4s.inovalocal.locais.Local;
import com.aep4s.inovalocal.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "historia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Midia> midias = new ArrayList<>();
    private LocalDate dataUltimaEdicao;

    public void adicionarMidia(Midia midia){
        midias.add(midia);
        midia.setHistoria(this);
    }

    public void removerMidia(Midia m){
        midias.remove(m);
        m.setHistoria(null);
    }

    @PrePersist
    public void prePersist(){
        if(status == null) status = HistoriaStatus.CRIADA;
        if(dataPublicacao == null) dataPublicacao = LocalDate.now();
    }
}
