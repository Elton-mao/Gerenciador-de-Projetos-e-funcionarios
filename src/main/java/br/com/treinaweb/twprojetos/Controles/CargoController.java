package br.com.treinaweb.twprojetos.Controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.entidades.UF;
import br.com.treinaweb.twprojetos.repository.CargoRepository;

@Controller
@RequestMapping("/cargos")
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;
    @GetMapping
    public ModelAndView home(){
        ModelAndView mView = new ModelAndView("cargo/home"); 
        mView.addObject("cargos", cargoRepository.findAll());
       return mView; 
    }

  
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        cargoRepository.deleteById(id);

        return "redirect:/cargos";

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView mView = new ModelAndView("cargo/formulario"); 
        mView.addObject("cargo", new Cargo());
        mView.addObject("ufs",UF.values());
        return mView; 
    }
    @PostMapping({"/cadastrar" , "/{id}/editar"})
    public String salvar(Cargo cargo){
        cargoRepository.save(cargo);
        return "redirect:/cargos"; 
    }
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("cargo/formulario");
        mView.addObject("cargo", cargoRepository.getOne(id));
        mView.addObject("ufs",UF.values());
        
        return mView; 
    }


}
