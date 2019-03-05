package com.saurabh.eventmanagement.services;

import com.saurabh.eventmanagement.entities.Event;
import com.saurabh.eventmanagement.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;

	@Autowired
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public String startEvent(final Long id) {
		Event event = eventRepository.getOne(id);  // already throws entity not found exception

		event.setStarted(true);
		eventRepository.save(event);

		return event.getName();
	}
}
