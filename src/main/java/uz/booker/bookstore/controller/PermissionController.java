package uz.booker.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uz.booker.bookstore.service.impl.PermissionServiceImpl;


@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    @Autowired
    private PermissionServiceImpl service;
}
