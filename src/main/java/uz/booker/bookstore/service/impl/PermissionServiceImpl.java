package uz.booker.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.booker.bookstore.repository.PermissionRepository;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl  {

    @Autowired
    private PermissionRepository permissionRepository;



}
