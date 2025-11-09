package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.exceptions.ForbiddenException;
import com.aep4s.inovalocal.historias.exceptions.NotFoundException;
import com.aep4s.inovalocal.historias.midia.Midia;
import com.aep4s.inovalocal.locais.Local;
import com.aep4s.inovalocal.locais.LocalRepository;
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
    private final LocalRepository localRepository;
    private final UsuarioRepository usuarioRepository;

    public HistoriaService(HistoriaRepository historiaRepository, LocalRepository localRepository, UsuarioRepository usuarioRepository){
        this.historiaRepository = historiaRepository;
        this.localRepository = localRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Historia cadastrarHistoria(HistoriaDTO.CriarHistoriaRequest dto){
        var autor = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        var h = new Historia();
        h.setTitulo(dto.titulo());
        h.setConteudo(dto.conteudo());
        h.setAutor(autor);

        if(dto.local() != null){
            var L = dto.local();
            var novo = new Local();
            novo.setPais(L.pais());
            novo.setEstado(L.estado());
            novo.setCidade(L.cidade());
            novo.setDistrito(L.distrito());
            localRepository.save(novo);
            h.setLocal(novo);
        }

        if(dto.midias() != null){
            dto.midias().forEach(m -> {
                var midia = new Midia();
                midia.setUrl(m.url());
                midia.setTipo(m.tipo());
                midia.setDescricao(m.descricao());
                h.adicionarMidia(midia);
            });
        }
        return historiaRepository.save(h);
    }

    public HistoriaDTO.HistoriaResponse buscarHistoriaResponse(Long id){
        var h = historiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("História não encontrada."));
        return HistoriaDTO.HistoriaResponse.from(h);
    }

    public List<Historia> listarHistorias(){
        return historiaRepository.findAll();
    }

    public void deletarHistoria(Long id){
        historiaRepository.deleteById(id);
    }

    @Transactional
    public Historia atualizarHistoria(Long id, HistoriaDTO.AtualizarHistoriaRequest dto, Long idUsuario) throws NotFoundException {
        var h = historiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("História não encontrada."));
        if (!h.getAutor().getId().equals(idUsuario)){
            throw new ForbiddenException("Você não pode editar esta história.");
        }

        if(dto.titulo() != null) h.setTitulo(dto.titulo());
        if(dto.conteudo() != null) h.setConteudo(dto.conteudo());

        if(dto.localId() != null){
            var loc = localRepository.findById(dto.localId())
                    .orElseThrow(() -> new NotFoundException("Local não encontrado."));
            h.setLocal(loc);
        } else if (dto.local() != null){
            var L = dto.local();
            var novo = new Local();
            novo.setPais(L.pais());
            novo.setEstado(L.estado());
            novo.setCidade(L.cidade());
            novo.setDistrito(L.distrito());
            localRepository.save(novo);
            h.setLocal(novo);
        }

        if(dto.midias() != null){
            var existentes = List.copyOf(h.getMidias());
            existentes.forEach(h::removerMidia);
            dto.midias().forEach(m -> {
                var midia = new Midia();
                midia.setUrl(m.url());
                midia.setTipo(m.tipo());
                midia.setDescricao(m.descricao());
                h.adicionarMidia(midia);
            });
        }
        h.setDataUltimaEdicao(LocalDate.now());
        h.setStatus(HistoriaStatus.EDITADA);
        return h;
    }
}
