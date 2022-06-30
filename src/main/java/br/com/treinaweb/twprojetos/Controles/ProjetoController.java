package br.com.treinaweb.twprojetos.Controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.repository.ProjetoRepository;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoRepository projetoRepository; 
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @GetMapping
    public ModelAndView home(){
        ModelAndView mView = new ModelAndView("projeto/home"); 
        mView.addObject("projetos", projetoRepository.findAll());
       return mView; 
    }

  
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        projetoRepository.deleteById(id);;

        return "redirect:/projetos";

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView mView = new ModelAndView("projeto/formulario"); 
        mView.addObject("projeto", new Projeto());
        mView.addObject("clientes", clienteRepository.findAll());
       // mView.addObject("lideres", funcionarioRepository.buscarPorCargo("Gerente"));
        mView.addObject("funcionarios", funcionarioRepository.findAll());

        return mView; 
    }
    @PostMapping({"/cadastrar" , "/{id}/editar"})
    public String salvar(Projeto projeto){
        projetoRepository.save(projeto);
        return "redirect:/projetos"; 
    }
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("projeto/formulario");
        mView.addObject("projeto", projetoRepository.getOne(id));
        mView.addObject("clientes", clienteRepository.findAll());
        mView.addObject("lideres", funcionarioRepository.findAll());
        mView.addObject("funcionarios", funcionarioRepository.findAll());
        return mView; 
    }
    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("projeto/detalhes");
        mView.addObject("projeto", projetoRepository.getOne(id));
        return mView;
    }

}
