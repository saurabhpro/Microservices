package com.saurabh.eventmanagement.services;

import com.saurabh.eventmanagement.entities.Participant;
import com.saurabh.eventmanagement.repositories.ParticipantRepository;
import com.saurabh.eventmanagement.services.exceptions.AlreadyCheckedInException;
import com.saurabh.eventmanagement.services.exceptions.NotCheckedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private final ParticipantRepository participantRepository;

	@Autowired
	public ParticipantServiceImpl(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	@Override
	public Participant checkIn(final Long id) {
		Participant participant = participantRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

		if (participant.getCheckedIn()) {
			throw new AlreadyCheckedInException(participant.getName() + "is already checked in!");
		}

		participant.setCheckedIn(true);
		participantRepository.save(participant);

		return participant;
	}

	@Override
	public Participant checkOut(final Long id) {
		Participant participant = participantRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

		if (!participant.getCheckedIn()) {
			throw new NotCheckedInException(participant.getName() + "is already checked out!");
		}

		participant.setCheckedIn(false);
		participantRepository.save(participant);

		return participant;
	}
}
