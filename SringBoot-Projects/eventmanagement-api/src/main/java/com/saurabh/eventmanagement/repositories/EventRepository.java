package com.saurabh.eventmanagement.repositories;

import com.saurabh.eventmanagement.entities.Event;
import com.saurabh.eventmanagement.entities.projections.PartialEventProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;

@Repository
@RepositoryRestResource(excerptProjection = PartialEventProjection.class)
public interface EventRepository extends JpaRepository<Event, Long> {

	// List<Event> findByName(@Param("name") String name); - returns everything once

	Page<Event> findByName(@Param("name") String name, Pageable pageable);

	Page<Event> findByNameAndZoneId(@Param("name") String name, @Param("zoneId") ZoneId zoneId, Pageable pageable);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void deleteById(Long aLong);
}


