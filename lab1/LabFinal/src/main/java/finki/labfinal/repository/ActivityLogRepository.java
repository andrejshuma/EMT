package finki.labfinal.repository;

import finki.labfinal.model.domain.ActivityLogEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLogEntry, Long> {

    Page<ActivityLogEntry> findAllByOrderByOccurredAtDesc(Pageable pageable);
}

