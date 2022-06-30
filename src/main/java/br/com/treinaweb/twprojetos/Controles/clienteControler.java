package br.com.treinaweb.twprojetos.Controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojetos.entidades.Cliente;
import br.com.treinaweb.twprojetos.entidades.UF;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class clienteControler {
    @Autowired
    private ClienteRepository clienteRepository; 

    @GetMapping
    public ModelAndView home(){
        ModelAndView mView = new ModelAndView("cliente/home"); 
        mView.addObject("clientes", clienteRepository.findAll());
       return mView; 
    }

  
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        clienteRepository.deleteById(id);

        return "redirect:/clientes";

    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView mView = new ModelAndView("cliente/formulario"); 
        mView.addObject("cliente", new Cliente());
        mView.addObject("ufs",UF.values());
        return mView; 
    }
    @PostMapping({"/cadastrar" , "/{id}/editar"})
    public String salvar(Cliente cliente){
        clienteRepository.save(cliente);
        return "redirect:/clientes"; 
    }
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("cliente/formulario");
        mView.addObject("cliente", clienteRepository.getOne(id));
        mView.addObject("ufs",UF.values());
        
        return mView; 
    }
    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id){
        ModelAndView mView = new ModelAndView("cliente/detalhes");
        mView.addObject("cliente", clienteRepository.getOne(id));
        return mView;
    }

}
