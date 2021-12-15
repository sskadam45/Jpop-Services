package com.jpop.bookservice.repository;

import com.jpop.bookservice.model.OldBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldBookRepository extends JpaRepository<OldBook,Long> {
}
