package com.amorna.ecole.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
	public List<Etudiant> findByNomContains(String mc);
}
