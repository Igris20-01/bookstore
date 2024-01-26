package uz.booker.bookstore.mapper;

import org.mapstruct.Mapper;
import uz.booker.bookstore.dto.PermissionDto;
import uz.booker.bookstore.entity.permission.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends BaseMapper<Permission, PermissionDto> {

}
