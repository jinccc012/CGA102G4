package com.event.model;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;



public interface EventInformationDAO {
	
	public void insert(EventInformationVO event_InformationVO);
	
	public void update(EventInformationVO event_InformationVO);
	
	
	public EventInformationVO findByPrimaryKey(Integer event_no);
	
	public List<EventInformationVO> getAll();
	
	public List<EventInformationVO> getEventsByOrderTime(Timestamp start);
	
	
}
