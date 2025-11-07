package com.aep4s.inovalocal.usuarios;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "usuario")
@Table(name = "tb_usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataCadastro = LocalDate.now();
}
