package com.rtassini.bookstoremanager.service;

import com.rtassini.bookstoremanager.dto.BookDTO;
import com.rtassini.bookstoremanager.dto.MessageResponseDTO;
import com.rtassini.bookstoremanager.entity.Book;
import com.rtassini.bookstoremanager.exception.BookNotFoundException;
import com.rtassini.bookstoremanager.mapper.BookMapper;
import com.rtassini.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public MessageResponseDTO create(BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book bookSaved = this.bookRepository.save(bookToSave);

        return MessageResponseDTO.builder()
                .message("Book created with ID " + bookSaved.getId())
                .build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDTO(book);
    }
}
