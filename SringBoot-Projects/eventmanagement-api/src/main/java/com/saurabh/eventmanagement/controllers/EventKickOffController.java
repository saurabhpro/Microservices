package com.saurabh.eventmanagement.controllers;

import com.saurabh.eventmanagement.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController // uses base path from app.properties
// @RestController      // uses root as starting path
@RequestMapping("/events")
public class EventKickOffController {

	private final EventService eventService;

	@Autowired
	public EventKickOffController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping("/start/{id}")
	public ResponseEntity start(@PathVariable Long id) {
		return ResponseEntity.ok(eventService.startEvent(id) + " has started.");
	}

}
