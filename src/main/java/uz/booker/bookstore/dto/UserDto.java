package uz.booker.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.booker.bookstore.entity.permission.Permission;
import uz.booker.bookstore.enums.Role;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

    @JsonProperty("name")
    String fullName;

    String email;

    String password;

    Role role;

    Set<Permission> permissions;





}
