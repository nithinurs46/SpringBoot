package com.demo.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.BookDao;
import com.demo.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Book getBook(String bookName) {
		Session session = getSession();
		Book book = session.get(Book.class, bookName);
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		Session session = getSession();
		List<Book> booksList = session.createQuery("from Book").list(); // mention the entity name here
		return booksList;
	}

	@Override
	public Book addNewBook(Book book) {
		Session session = getSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		session.close();
		return book;
	}

	@Override
	public Book updateBook(Book book) {
		Session session = getSession();
		session.beginTransaction();
		session.update(book);
		session.getTransaction().commit();
		session.close();
		return book;
	}

	@Override
	public boolean deleteBook(String bookName) {
		Session session = getSession();
		Book book = session.load(Book.class, bookName);
		if (book != null) {
			session.delete(book);
			return true;
		}
		return false;
	}

}
