package com.saurabh.eventmanagement.services;

import com.saurabh.eventmanagement.entities.Participant;

public interface ParticipantService {
	Participant checkIn(Long id);

	Participant checkOut(Long id);
}