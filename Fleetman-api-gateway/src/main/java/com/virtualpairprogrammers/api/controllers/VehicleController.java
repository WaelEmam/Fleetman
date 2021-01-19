package com.virtualpairprogrammers.api.controllers;

import com.virtualpairprogrammers.api.domain.geo;
import com.virtualpairprogrammers.api.domain.VehiclePosition;
import com.virtualpairprogrammers.api.services.PositionTrackingExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/")
public class VehicleController 
{	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	private PositionTrackingExternalService externalService;
	
	private Date lastUpdateTime = null;

	@GetMapping("/")
	@ResponseBody
	/**
	 * This is just a test mapping so we can easily check the API gateway is standing.
	 * When running through the Angular Front end, can visit this URL at /api/
	 */
	public String apiTestUrl()
	{
		return "<p>Fleetman API Gateway at " + new Date() + "</p>";
	}
	
	@GetMapping("/history/{vehicleName}")
	@ResponseBody
    @CrossOrigin(origins = "*")
	public Collection<geo> getHistoryFor(@PathVariable("vehicleName") String vehicleName)
	{
		Collection<geo> results = new ArrayList<>();
		Collection<VehiclePosition> vehicles = externalService.getHistoryFor(vehicleName);
		for (VehiclePosition next: vehicles)
		{
			geo position = new geo(next.getLatLong().getLat(), next.getLatLong().getLng());
			results.add(position);
		}
		Collections.reverse((List<?>) results);
		return results;
	}
	
    @Scheduled(fixedRate=100)
    public void updatePositions()
    {
    	Collection<VehiclePosition> results = externalService.getAllUpdatedPositionsSince(lastUpdateTime);
    	this.lastUpdateTime = new Date();

		for (VehiclePosition next: results)
    	{
			this.messagingTemplate.convertAndSend("/vehiclepositions/messages", next);
    	}
    }
}
