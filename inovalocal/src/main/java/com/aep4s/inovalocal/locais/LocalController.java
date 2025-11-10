package com.aep4s.inovalocal.locais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = {"http://127.0.0.1:5500", "http://localhost:5500"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
@RestController
@RequestMapping(path = "/locais")
public class LocalController {
    @Autowired
    private LocalService localService;

    @PostMapping
    public Local cadastrarLocal(@RequestBody Local local){
        return localService.cadastrarLocal(local);
    }

    @GetMapping
    private List<Local> listarLocal(){
        return localService.listarLocal();
    }

    @DeleteMapping
    public void deletarLocal(@PathVariable Long id){
        localService.deletarLocal(id);
    }

    @PutMapping("/{id}")
    public Local atualizarLocal(@PathVariable Long id, @RequestBody Local local){
        return localService.atualizarLocal(id, local);
    }
}
