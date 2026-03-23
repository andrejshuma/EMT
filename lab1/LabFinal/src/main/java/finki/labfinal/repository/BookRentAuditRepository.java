package finki.labfinal.repository;

import finki.labfinal.model.domain.BookRentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRentAuditRepository extends JpaRepository<BookRentAudit, Long> {
}

