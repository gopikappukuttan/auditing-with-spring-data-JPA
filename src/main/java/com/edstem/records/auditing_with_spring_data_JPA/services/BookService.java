package com.edstem.records.auditing_with_spring_data_JPA.services;

import com.edstem.records.auditing_with_spring_data_JPA.contracts.BookDTO;
import com.edstem.records.auditing_with_spring_data_JPA.exceptions.ResourceNotFoundException;
import com.edstem.records.auditing_with_spring_data_JPA.models.Book;
import com.edstem.records.auditing_with_spring_data_JPA.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

	public BookDTO getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
		return convertToDTO(book);
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

	public BookDTO updateBook(Long id, Book books) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found")); bookRepository.findById(id);
					book.setTitle(books.getTitle());
					book.setAuthor(books.getAuthor());
					book.setIsbn(books.getIsbn());
					book.setPrice(books.getPrice());
					Book updatedBook= bookRepository.save(book);
					return convertToDTO(updatedBook);
				}


	public void deleteBook(Long id) {
		Book book= bookRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Book with id " + id + " not found"));
		bookRepository.delete(book);
	}

	private BookDTO convertToDTO(Book book) {
		return BookDTO.builder()
				.id(book.getId())
				.title(book.getTitle())
				.isbn(book.getIsbn())
				.author(book.getAuthor())
				.price(book.getPrice())
				.createdDate(book.getCreatedDate())
				.createdBy(book.getCreatedBy())
				.lastModifiedBy(book.getLastModifiedBy())
				.lastModifiedDate(book.getLastModifiedDate())
				.build();
	}

	private Book convertToEntity(BookDTO bookDTO) {
		return Book.builder()
				.title(bookDTO.getTitle())
				.isbn(bookDTO.getIsbn())
				.author(bookDTO.getAuthor())
				.price(bookDTO.getPrice())
				.build();
	}
}

