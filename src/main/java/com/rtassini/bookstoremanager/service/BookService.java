package com.rtassini.bookstoremanager.service;

import com.rtassini.bookstoremanager.dto.MessageResponseDTO;
import com.rtassini.bookstoremanager.entity.Book;
import com.rtassini.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public MessageResponseDTO create(Book book){
        Book bookSaved = this.bookRepository.save(book);

        return MessageResponseDTO.builder()
                .message("Book created with ID " + bookSaved.getId())
                .build();
    }
}
