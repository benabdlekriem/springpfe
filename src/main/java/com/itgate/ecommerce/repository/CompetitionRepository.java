package com.itgate.ecommerce.repository;

import com.itgate.ecommerce.models.Arbitre;
import com.itgate.ecommerce.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
}
