package com.virtualpairprogrammers.tracker.messaging;

import com.virtualpairprogrammers.tracker.data.Data;
import com.virtualpairprogrammers.tracker.domain.LatLong;
import com.virtualpairprogrammers.tracker.domain.VehicleBuilder;
import com.virtualpairprogrammers.tracker.domain.VehiclePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class MessageProcessor {
	
	@Autowired
	private Data data;
	
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@JmsListener(destination="${fleetman.position.queue}")
	public void processPositionMessageFromQueue(Map<String, String> incomingMessage ) throws ParseException 
	{
		String positionDatestamp = incomingMessage.get("time");
		Date convertedDatestamp = format.parse(positionDatestamp);

		LatLong latLong = LatLong.buildLatLong(new BigDecimal(incomingMessage.get("lat")),new BigDecimal(incomingMessage.get("long")));

		VehiclePosition newReport = new VehicleBuilder()
				                          .withId(UUID.randomUUID().toString())
				                          .withName(incomingMessage.get("vehicle"))
				                          .withLatLong(latLong)
				                          .withTimestamp(convertedDatestamp)
				                          .build();


				                          
		data.updatePosition(newReport);

	}

}
