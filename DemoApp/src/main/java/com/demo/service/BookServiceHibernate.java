package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.BookDao;
import com.demo.model.Book;

@Service
public class BookServiceHibernate {

	@Autowired
	BookDao dao;

	public List<Book> getAllBooks() {
		return dao.getAllBooks();
	}

	public Book fetchBook(String bookName) throws Exception {
		return dao.getBook(bookName);
	}

	public Book addBook(Book book) {
		return dao.addNewBook(book);

	}

	public Book updateBook(String bookName, Book book) {
		return dao.updateBook(book);

	}

	public boolean removeBook(String bookName) {
		return dao.deleteBook(bookName);
	}

}
