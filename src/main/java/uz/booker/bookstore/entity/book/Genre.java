package uz.booker.bookstore.entity.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.booker.bookstore.entity.BaseEntity;

@Entity
@Table(name = "genres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre extends BaseEntity {

    @Column(nullable = false)
    String name;

}
