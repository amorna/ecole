package com.amorna.ecole.etudiantController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.amorna.ecole.model.Etudiant;
import com.amorna.ecole.model.EtudiantRepository;



@Controller
public class EtudiantController {
	@Autowired
	EtudiantRepository etudiantRepository;
	
	
	@GetMapping("saisieEtudiant")
	public String ajouterEtudiant(Etudiant etudiant) {
		etudiantRepository.save(etudiant);
		return "saisieEtudiant";
	}
	
	@GetMapping("/listeEtudiant")
	 public String listeEtudiant(Model model) {
		 List<Etudiant> etudiants=etudiantRepository.findAll();
		 model.addAttribute("etudiants", etudiants);
		 return ("listeEtudiant");
	 }
	
	@RequestMapping("/recherche")
	public  String getEtudiant(Model model,
			
			@RequestParam(name="mc",defaultValue="") String mc) {
		
		List<Etudiant> etudiants=etudiantRepository.findByNomContains(mc);
		
		
		model.addAttribute("etudiants",etudiants);
		model.addAttribute("mc",mc);
		
		return "resultatRecherche";				
	}
	
	@GetMapping("deleteEtudiant")
	public String delete(int id) {
		etudiantRepository.deleteById(id);
		return "redirect:/listeEtudiant";
	}
}
