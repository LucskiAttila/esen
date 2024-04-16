package com.esen.bookstore.repository;

import com.esen.bookstore.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreRepository extends JpaRepository<BookStore, Long> {

}
