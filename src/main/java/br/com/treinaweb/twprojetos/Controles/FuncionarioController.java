package br.com.treinaweb.twprojetos.Controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.UF;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
   
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @GetMapping
    public ModelAndView home(){
        ModelAndView mView = new ModelAndView("funcionario/home"); 
        mView.addObject("funcionarios",funcionarioRepository.findAll());
       return mView; 
    }

  
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        funcionarioRepository.deleteById(id);

        return "redirect:/funcionarios";

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView mView = new ModelAndView("funcionario/formulario"); 
        mView.addObject("funcionario", new Funcionario());
        mView.addObject("cargos", cargoRepository.findAll());
        mView.addObject("ufs",UF.values());
        return mView; 
    }
    @PostMapping({"/cadastrar" , "/{id}/editar"})
    public String salvar(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
        return "redirect:/funcionarios"; 
    }
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("funcionario/formulario");
        mView.addObject("funcionario", funcionarioRepository.getOne(id));
        mView.addObject("cargos", cargoRepository.findAll());
        mView.addObject("ufs",UF.values());
        
        return mView; 
    }
    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("funcionario/detalhes");
        mView.addObject("funcionario", funcionarioRepository.getOne(id));
        return mView;
    }
}
