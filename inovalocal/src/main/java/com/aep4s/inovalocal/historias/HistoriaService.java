package com.aep4s.inovalocal.historias;

import com.aep4s.inovalocal.historias.exceptions.ForbiddenException;
import com.aep4s.inovalocal.historias.exceptions.NotFoundException;
import com.aep4s.inovalocal.locais.EstadoEnum;
import com.aep4s.inovalocal.locais.Local;
import com.aep4s.inovalocal.locais.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class HistoriaService {
    @Autowired
    private HistoriaRepository historiaRepository;
    private LocalRepository localRepository;

    public HistoriaService(HistoriaRepository historiaRepository, LocalRepository localRepository){
        this.historiaRepository = historiaRepository;
        this.localRepository = localRepository;
    }

    public Historia cadastrarHistoria(Historia historia){
        return historiaRepository.save(historia);
    }

    public List<Historia> listarHistorias(){
        return historiaRepository.findAll();
    }

    public void deletarHistoria(Long id){
        historiaRepository.deleteById(id);
    }

    @Transactional
    public Historia atualizarHistoria(Long id, HistoriaDTO.AtualizarHistoriaRequest dto, Long idUsuario) throws NotFoundException {
        Historia h = historiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("História não encontrada"));
        if (!h.getAutor().getId().equals(idUsuario)){
            throw new ForbiddenException("Você não pode editar esta história");
        }

        if(dto.titulo() != null) h.setTitulo(dto.titulo());
        if(dto.conteudo() != null) h.setConteudo(dto.conteudo());
        if(dto.dataPublicacao() != null) h.setDataPublicacao(dto.dataPublicacao());
        if(dto.localId() != null){
            Local loc = localRepository.findById(dto.localId())
                    .orElseThrow(() -> new NotFoundException("Local não encontrado."));
            h.setLocal(loc);
        } else if (dto.local() != null){
            HistoriaDTO.LocalDTO L = dto.local();

            Local novo = new Local();
            novo.setPais(L.pais());
            novo.setEstado(EstadoEnum.valueOf(String.valueOf(L.estado())));
            novo.setCidade(L.cidade());
            novo.setDistrito(L.distrito());
            localRepository.save(novo);
            h.setLocal(novo);
        }
        h.setStatus(HistoriaStatus.EDITADA);
        return h;
    }
}
