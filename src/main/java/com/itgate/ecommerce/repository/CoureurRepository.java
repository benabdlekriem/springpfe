package com.itgate.ecommerce.repository;

import com.itgate.ecommerce.models.Arbitre;
import com.itgate.ecommerce.models.Coureur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoureurRepository extends JpaRepository<Coureur,Long> {
}
