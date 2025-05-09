package com.edstem.records.auditing_with_spring_data_JPA.services;

import com.edstem.records.auditing_with_spring_data_JPA.contracts.BookDTO;
import com.edstem.records.auditing_with_spring_data_JPA.models.Book;
import com.edstem.records.auditing_with_spring_data_JPA.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<BookDTO> getAllBooks() {
		return bookRepository.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public Optional<Book> getBookById(Long id) {
		return bookRepository.findById(id);
	}

	public List<BookDTO> createBooks(List<BookDTO> bookDTO) {
		List<Book> books = bookDTO.stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());
		List<Book> savedBooks = bookRepository.saveAll(books);
		return savedBooks.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public Optional<Book> updateBook(Long id, Book books) {
		return bookRepository.findById(id).map(
				book -> {
					book.setTitle(books.getTitle());
					book.setAuthor(books.getAuthor());
					book.setIsbn(books.getIsbn());
					book.setPrice(books.getPrice());
					return bookRepository.save(book);
				}
		);
	}

	public boolean deleteBook(Long id) {
		return bookRepository.findById(id).map(book -> {
			bookRepository.delete(book);
			return true;
		}).orElse(false);
	}

	private BookDTO convertToDTO(Book book) {
		return BookDTO.builder()
				.id(book.getId())
				.title(book.getTitle())
				.isbn(book.getIsbn())
				.author(book.getAuthor())
				.price(book.getPrice())
				.build();
	}

	private Book convertToEntity(BookDTO bookDTO) {
		return Book.builder()
				.id(bookDTO.getId())
				.title(bookDTO.getTitle())
				.isbn(bookDTO.getIsbn())
				.author(bookDTO.getAuthor())
				.price(bookDTO.getPrice())
				.build();
	}
}
