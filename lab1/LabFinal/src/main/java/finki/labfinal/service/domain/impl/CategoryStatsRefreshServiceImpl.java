package finki.labfinal.service.domain.impl;

import finki.labfinal.service.domain.CategoryStatsRefreshService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryStatsRefreshServiceImpl implements CategoryStatsRefreshService {

    private static final Logger log = LoggerFactory.getLogger(CategoryStatsRefreshServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final boolean concurrently;

    public CategoryStatsRefreshServiceImpl(
            JdbcTemplate jdbcTemplate,
            @Value("${app.category-stats.refresh.concurrently:true}") boolean concurrently
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.concurrently = concurrently;
    }

    /**
     * Refresh materialized view mv_category_stats.
     *
     * Important: REFRESH ... CONCURRENTLY cannot run inside a transaction.
     * This method intentionally avoids @Transactional.
     */
    @Override
    public void refreshMaterializedView() {
        String sql = concurrently
                ? "REFRESH MATERIALIZED VIEW CONCURRENTLY mv_category_stats"
                : "REFRESH MATERIALIZED VIEW mv_category_stats";

        log.info("Refreshing materialized view mv_category_stats (concurrently={}) ...", concurrently);
        jdbcTemplate.execute(sql);
        log.info("Refreshing materialized view mv_category_stats finished.");
    }
}

