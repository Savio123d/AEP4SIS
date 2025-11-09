package com.aep4s.inovalocal.locais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {
    @Autowired
    private LocalRepository localRepository;

    public Local cadastrarLocal(Local local){
        return localRepository.save(local);
    }

    public List<Local> listarLocal(){
        return localRepository.findAll();
    }

    public void deletarLocal(Long id){
        localRepository.deleteById(id);
    }

    public Local atualizarLocal(Long id, Local local){
        Local localExistente = localRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Local não encontrado."));
        localExistente.setPais(local.getPais());
        localExistente.setEstado(local.getEstado());
        localExistente.setCidade(local.getCidade());
        return localRepository.save(localExistente);
    }
}
