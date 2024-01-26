package uz.booker.bookstore.entity.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.booker.bookstore.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "author")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends BaseEntity {


    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<Book> book;
}
