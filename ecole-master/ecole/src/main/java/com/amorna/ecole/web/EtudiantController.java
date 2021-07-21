package com.amorna.ecole.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amorna.ecole.dao.EtudiantRepository;
import com.amorna.ecole.entities.Etudiant;



@Controller
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	


@GetMapping(path = "/etudiants")	
public String list(Model model,@RequestParam(name="page",defaultValue = "0")int page,
@RequestParam(name="size",defaultValue = "5")int size,@RequestParam(name="keyword",defaultValue = "")String mc) {
	Page<Etudiant> pageEtudiants=etudiantRepository.findByNameContains(mc,PageRequest.of(page, size));
	model.addAttribute("etudiants",pageEtudiants.getContent());
	model.addAttribute("pages",new int[pageEtudiants.getTotalPages()]);
	model.addAttribute("currentPage",page);
	model.addAttribute("keyword",mc);
	model.addAttribute("size",size);
	return "etudiants";
}

@GetMapping(path = "/deleteEtudiant")	
public String delete(Long id,String keyword,int page,int size) {
	etudiantRepository.deleteById(id);
	return "redirect:/etudiants?keyword="+keyword+"&page="+page+"&size="+size;
}

@GetMapping("/formEtudiant")
public String formEtudiant(Model model ) {
	model.addAttribute("etudiant",new Etudiant());
	model.addAttribute("mode","new");
	return "formEtudiant";
}

@PostMapping("/saveEtudiant")
public String saveEtudiant(Model model,@Valid Etudiant etudiant,BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {return "formEtudiant";}
    etudiantRepository.save(etudiant);
    model.addAttribute("etudiant", etudiant);
    return "confirmation";
} 
@GetMapping("/editEtudiant")
public String editEtudiant(Model model,Long id ) {
	Etudiant etu =etudiantRepository.findById(id).get();
	model.addAttribute("etudiant",etu);
	model.addAttribute("mode","edit");
	return "formEtudiant";
}
}
