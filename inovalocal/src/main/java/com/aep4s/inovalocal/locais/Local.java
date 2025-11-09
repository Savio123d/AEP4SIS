package com.aep4s.inovalocal.locais;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "local")
@Table(name = "tb_local")

@Data
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pais;
    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;
    private String cidade;
//    private String distrito;



}
