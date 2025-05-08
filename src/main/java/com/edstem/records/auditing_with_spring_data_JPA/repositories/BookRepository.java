package com.edstem.records.auditing_with_spring_data_JPA.repositories;

import com.edstem.records.auditing_with_spring_data_JPA.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {


}
