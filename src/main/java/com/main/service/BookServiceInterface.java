package com.main.service;

import java.util.List;

import com.main.entity.BookDrive;

public interface BookServiceInterface {

	
	public BookDrive AddBook(int user_id   , int car_id , BookDrive bs);
	
	public List<BookDrive>viewBook();
	
	public void DeletBooking(int id);
	
	
	public BookDrive updateStatus(int id , String status);
}
