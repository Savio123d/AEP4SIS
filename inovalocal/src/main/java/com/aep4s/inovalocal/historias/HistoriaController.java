package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.exceptions.NotFoundException;
import com.aep4s.inovalocal.historias.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/historias")
public class HistoriaController {

    private final HistoriaService historiaService;

    public HistoriaController(HistoriaService historiaService) {
        this.historiaService = historiaService;
    }

    @PostMapping
    public ResponseEntity<Historia> cadastrar(@RequestBody Historia historia) {
        Historia criada = historiaService.cadastrarHistoria(historia);
        return ResponseEntity
                .created(URI.create("/historias/" + criada.getId())) // Location header
                .body(criada);
    }

    @GetMapping
    public ResponseEntity<List<Historia>> listar() {
        return ResponseEntity.ok(historiaService.listarHistorias());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Historia> atualizar(
            @PathVariable Long id,
            @RequestBody HistoriaDTO.AtualizarHistoriaRequest dto,
            @RequestHeader("idUsuario") Long idUsuario // provisório: pego do header
    ) {
        Historia atualizada = historiaService.atualizarHistoria(id, dto, idUsuario);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        historiaService.deletarHistoria(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
