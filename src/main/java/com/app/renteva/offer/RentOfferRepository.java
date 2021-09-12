package com.app.renteva.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentOfferRepository extends JpaRepository<RentOffer, Long> {

    List<RentOffer> findByPlaceId(Long id);
}
