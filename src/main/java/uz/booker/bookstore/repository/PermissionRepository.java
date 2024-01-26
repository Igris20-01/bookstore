package uz.booker.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.booker.bookstore.entity.permission.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
