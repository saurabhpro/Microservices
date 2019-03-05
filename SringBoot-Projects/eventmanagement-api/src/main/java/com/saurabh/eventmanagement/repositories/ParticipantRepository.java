package com.saurabh.eventmanagement.repositories;

import com.saurabh.eventmanagement.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	List<Participant> findByEmail(@Param("email") String email);
}
