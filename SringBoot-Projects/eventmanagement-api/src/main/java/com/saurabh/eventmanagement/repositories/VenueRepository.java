package com.saurabh.eventmanagement.repositories;

import com.saurabh.eventmanagement.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

	List<Venue> findByPostalCode(@Param("postalCode") String postalCode);

	// Page<Venue> findByPostalCode(@Param("postalCode") String postalCode, Pageable pageable);
}
