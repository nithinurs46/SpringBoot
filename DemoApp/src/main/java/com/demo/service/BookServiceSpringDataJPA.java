package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.BookRepository;
import com.demo.model.Book;

@Service
public class BookServiceSpringDataJPA {

	@Autowired
	private BookRepository repository;

	public List<Book> getAllBooks() {
		List<Book> bookList = new ArrayList<Book>();
		repository.findAll().forEach(bookList::add);
		return bookList;
	}

	public Book fetchBook(String bookName) throws Exception {
		return repository.findById(bookName).orElseThrow(() -> new Exception("Book not found"));
	}

	public Book addBook(Book book) {
		return repository.save(book);

	}

	public Book updateBook(String bookName, Book book) {
		return repository.save(book);

	}

	public boolean removeBook(String bookName) {
		try {
			repository.deleteById(bookName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
