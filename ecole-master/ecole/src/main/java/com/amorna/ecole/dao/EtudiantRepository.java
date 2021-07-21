package com.amorna.ecole.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amorna.ecole.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	public Page<Etudiant> findByNameContains(String mc, Pageable pageable);

}
