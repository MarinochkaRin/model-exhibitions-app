package com.mryndina.exhibitions.repository;

import com.mryndina.exhibitions.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Book, Long> {

/*	@Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
	public List<Book> search(String keyword);
	Book findById(Long id);*/
}
