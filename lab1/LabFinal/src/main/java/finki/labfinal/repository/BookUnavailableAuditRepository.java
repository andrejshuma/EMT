package finki.labfinal.repository;

import finki.labfinal.model.domain.BookUnavailableAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookUnavailableAuditRepository extends JpaRepository<BookUnavailableAudit, Long> {
}

