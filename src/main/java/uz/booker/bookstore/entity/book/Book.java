package uz.booker.bookstore.entity.book;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.booker.bookstore.entity.BaseEntity;
import uz.booker.bookstore.entity.user.User;

import java.util.Set;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

    @Column(nullable = false, length = 50)
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    @ManyToMany
    @JoinTable(name = "book_genre",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Set<Genre> genres;

    Short pages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


}
