package com.jpop.userservice.model;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="BOOK-SERVICE",fallback = HystrixClientFallback.class )
public interface BookProxy {

    @RequestMapping("/book/v1.1")
    public List<Book> findAll();
}

@Component
class HystrixClientFallback implements BookProxy {

    List<Book> blist = new ArrayList<>();
    @Override
    public List<Book> findAll() {
        Book b1 = new Book();
        b1.setAuthor("Default Author");
        b1.setTitle("default title");
        b1.getCategory("default category");
        b1.getDescription("default description");
        blist.add(b1);
        return blist;
    }
}