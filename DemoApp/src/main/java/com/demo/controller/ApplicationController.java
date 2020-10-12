package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Book;
import com.demo.service.BookServiceHibernate;
import com.demo.service.BookServiceSpringDataJPA;

@RestController
public class ApplicationController {

	@Autowired
	BookServiceHibernate service;
	// BookServiceSpringDataJPA service;

	@RequestMapping(value = "/test")
	public String test() {
		return "Hi, spring boot works";
	}

	@GetMapping(value = "/getBooks")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = service.getAllBooks();
		return ResponseEntity.ok().body(books);

	}

	@GetMapping(value = "/getSpecificBook/{bookName}")
	public ResponseEntity<Book> retreiveSpecificBook(@PathVariable String bookName) throws Exception {
		Book book = service.fetchBook(bookName);
		return ResponseEntity.ok().body(book);
	}

	@PostMapping(value = "/addBook")
	// @RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public ResponseEntity<Object> insertBook(@RequestBody Book book) {
		System.out.println("insertBook called");
		service.addBook(book);
		return ResponseEntity.ok().body(book);
	}

	@PutMapping(value = "/updateBook/{bookName}")
	// @RequestMapping(value = "updateBook/{bookName}", method = RequestMethod.PUT)
	public ResponseEntity<Book> bookUpdate(@PathVariable String bookName, @RequestBody Book book) {
		Book updatedBook = service.updateBook(bookName, book);
		return ResponseEntity.ok().body(updatedBook);
	}

	@DeleteMapping(value = "/deleteBook/{bookName}")
	// @RequestMapping(value = "deleteBook/{bookName}", method =
	// RequestMethod.DELETE)
	public Map<String, Boolean> bookDelete(@PathVariable String bookName) {
		boolean status = service.removeBook(bookName);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", status);
		return response;
	}

}
