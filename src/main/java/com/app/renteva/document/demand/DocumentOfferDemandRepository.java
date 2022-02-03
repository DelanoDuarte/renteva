package com.app.renteva.document.demand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentOfferDemandRepository extends JpaRepository<DocumentOfferDemand, Long> {

}
