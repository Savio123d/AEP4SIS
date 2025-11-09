package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.exceptions.NotFoundException;
import com.aep4s.inovalocal.historias.exceptions.ForbiddenException;
import jakarta.validation.Valid;
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
    public ResponseEntity<HistoriaDTO.HistoriaResponse> cadastrar(
            @Valid @RequestBody HistoriaDTO.CriarHistoriaRequest dto) {
        var criada = historiaService.cadastrarHistoria(dto);
        var body = HistoriaDTO.HistoriaResponse.from(criada);
        return ResponseEntity
                .created(URI.create("/historias/" + criada.getId()))
                .body(body);
    }

    @GetMapping
    public ResponseEntity<List<HistoriaDTO.HistoriaResponse>> listar() {
        var list = historiaService.listarHistorias()
                .stream()
                .map(HistoriaDTO.HistoriaResponse::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaDTO.HistoriaResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(historiaService.buscarHistoriaResponse(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HistoriaDTO.HistoriaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody HistoriaDTO.AtualizarHistoriaRequest dto,
            @RequestHeader("idUsuario") Long idUsuario // provisório: pego do header
    ) {
        var atualizada = historiaService.atualizarHistoria(id, dto, idUsuario);
        return ResponseEntity.ok(HistoriaDTO.HistoriaResponse.from(atualizada));
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
