package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Book")
public class Book {
	
	@Id
	private String bookName;
	private String author;
	private String publisherName;
	
	public Book() {
	}
	
	
	public Book(String bookName, String author, String publisherName) {
		this.bookName = bookName;
		this.author = author;
		this.publisherName = publisherName;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}
