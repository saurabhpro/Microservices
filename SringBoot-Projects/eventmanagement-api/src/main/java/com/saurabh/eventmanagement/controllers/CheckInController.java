package com.saurabh.eventmanagement.controllers;

import com.saurabh.eventmanagement.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/events")
public class CheckInController {

	private final ParticipantService participantService;

	@Autowired
	public CheckInController(ParticipantService participantService) {
		this.participantService = participantService;
	}

	@PostMapping("/checkin/{id}")
	public ResponseEntity<PersistentEntityResource> checkIn(
			@PathVariable Long id,
			PersistentEntityResourceAssembler assembler // to convert obj to response HAL object
	) {
		return ResponseEntity.ok(assembler.toResource(participantService.checkIn(id)));
	}

	@PostMapping("/checkout/{id}")
	public ResponseEntity<PersistentEntityResource> checkOut(
			@PathVariable Long id,
			PersistentEntityResourceAssembler assembler // to convert obj to response HAL object
	) {
		return ResponseEntity.ok(assembler.toResource(participantService.checkOut(id)));
	}

}