package com.app.renteva.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentOfferRepository extends JpaRepository<RentOffer, Long> { }
