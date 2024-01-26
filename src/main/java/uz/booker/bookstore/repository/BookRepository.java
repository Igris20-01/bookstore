package uz.booker.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.booker.bookstore.entity.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
