package finki.labfinal.service.application.impl;

import finki.labfinal.model.dto.CreateBookDTO;
import finki.labfinal.model.dto.DisplayBookDTO;
import finki.labfinal.service.application.BookAppService;
import finki.labfinal.service.domain.BookService;
import finki.labfinal.web.dto.BookSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAppServiceImpl implements BookAppService {

    private final BookService bookService;

    public BookAppServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Optional<DisplayBookDTO> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDTO::from);
    }

    @Override
    public Optional<DisplayBookDTO> findByIdWithAuthorsAndCountry(Long id) {
        return bookService.findByIdWithAuthorsAndCountry(id).map(DisplayBookDTO::from);
    }

    @Override
    public List<DisplayBookDTO> findAll() {
        return bookService.findAll()
                .stream()
                .map(DisplayBookDTO::from)
                .toList();
    }

    @Override
    public Page<DisplayBookDTO> search(BookSearchRequest request, Pageable pageable) {
        return bookService.search(request, pageable)
                .map(DisplayBookDTO::from);
    }

    @Override
    public DisplayBookDTO create(CreateBookDTO bookdto) {
        return DisplayBookDTO.from(bookService.create(bookdto.toBook()));
    }

    @Override
    public Optional<DisplayBookDTO> update(Long id, CreateBookDTO book) {
        return bookService.update(id, book.toBook()).map(DisplayBookDTO::from);
    }

    @Override
    public Optional<DisplayBookDTO> rent(Long id) {
        return bookService.rent(id).map(DisplayBookDTO::from);
    }

    @Override
    public Optional<DisplayBookDTO> deleteById(Long id) {
        return bookService.deleteById(id).map(DisplayBookDTO::from);
    }
}
