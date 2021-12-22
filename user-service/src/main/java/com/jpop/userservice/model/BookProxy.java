package com.jpop.userservice.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="BOOK-SERVICE" )
public interface BookProxy {
    @RequestMapping("/book/v1.1")
    public List<Book> findAll();
}

