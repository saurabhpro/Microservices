package com.saurabh.eventmanagement.entities.projections;

import com.saurabh.eventmanagement.entities.Participant;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "partialParticipant", types = {Participant.class})
public interface PartialParticipantProjection {
	String getName();

	Boolean getCheckedIn();
}
