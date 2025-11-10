package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.exceptions.ForbiddenException;
import com.aep4s.inovalocal.historias.exceptions.NotFoundException;
import com.aep4s.inovalocal.historias.midia.Midia;
import com.aep4s.inovalocal.usuarios.UsuarioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Data
@Service
public class HistoriaService {
    @Autowired
    private final HistoriaRepository historiaRepository;
    private final UsuarioRepository usuarioRepository;

    public HistoriaService(HistoriaRepository historiaRepository, UsuarioRepository usuarioRepository){
        this.historiaRepository = historiaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Historia cadastrarHistoria(HistoriaDTO.CriarHistoriaRequest dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        var h = new Historia();
        h.setTitulo(dto.titulo());
        h.setConteudo(dto.conteudo());
        h.setPais(dto.pais());
        h.setEstado(dto.estado());
        h.setCidade(dto.cidade());
        h.setAutor(usuario);
        
        return h;
    }
//        if(dto.midias() != null){
//            dto.midias().forEach(m -> {
//                var midia = new Midia();
//                midia.setUrl(m.url());
//                midia.setTipo(m.tipo());
//                midia.setDescricao(m.descricao());
//                h.adicionarMidia(midia);
//            });
//        }

        public HistoriaDTO.HistoriaResponse buscarHistoriaResponse (Long id){
            var h = historiaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("História não encontrada."));
            return HistoriaDTO.HistoriaResponse.from(h);
        }

        public List<Historia> listarHistorias () {
            return historiaRepository.findAll();
        }

        public void deletarHistoria (Long id){
            historiaRepository.deleteById(id);
        }

        @Transactional
        public Historia atualizarHistoria (Long id, HistoriaDTO.AtualizarHistoriaRequest dto, Long idUsuario) throws
        NotFoundException {
            var h = historiaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("História não encontrada."));
            if (!h.getAutor().getId().equals(idUsuario)) {
                throw new ForbiddenException("Você não pode editar esta história.");
            }

            if (dto.titulo() != null) h.setTitulo(dto.titulo());
            if (dto.conteudo() != null) h.setConteudo(dto.conteudo());

//        if(dto.midias() != null){
//            var existentes = List.copyOf(h.getMidias());
//            existentes.forEach(h::removerMidia);
//            dto.midias().forEach(m -> {
//                var midia = new Midia();
//                midia.setUrl(m.url());
//                midia.setTipo(m.tipo());
//                midia.setDescricao(m.descricao());
//                h.adicionarMidia(midia);
//            });
//        }
            h.setDataUltimaEdicao(LocalDate.now());
            h.setStatus(HistoriaStatus.EDITADA);
            return historiaRepository.save(h);
        }


}
