package com.edstem.records.auditing_with_spring_data_JPA.controllers;

import com.edstem.records.auditing_with_spring_data_JPA.contracts.BookDTO;
import com.edstem.records.auditing_with_spring_data_JPA.models.Book;
import com.edstem.records.auditing_with_spring_data_JPA.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Book>>getBookById(@PathVariable  Long id){
		return ResponseEntity.ok(bookService.getBookById(id));
	}
	@PostMapping
	public ResponseEntity<List<BookDTO>> createBooks(@RequestBody List<BookDTO> bookDTOS) {
		if (bookDTOS == null || bookDTOS.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(201).body(bookService.createBooks(bookDTOS));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book books) {
		return bookService.updateBook(id, books)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
