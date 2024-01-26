package uz.booker.bookstore.entity.permission;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.booker.bookstore.entity.BaseEntity;
import uz.booker.bookstore.entity.user.User;
import uz.booker.bookstore.enums.Role;



@Entity
@Table(name = "permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@RequiredArgsConstructor
public class Permission extends BaseEntity {


    @Enumerated( value = EnumType.STRING)
    Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

}
