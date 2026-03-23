package finki.labfinal.service.application.scheduler;

import finki.labfinal.service.domain.CategoryStatsRefreshService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CategoryStatsRefreshScheduler {

    private static final Logger log = LoggerFactory.getLogger(CategoryStatsRefreshScheduler.class);

    private final CategoryStatsRefreshService categoryStatsRefreshService;

    public CategoryStatsRefreshScheduler(CategoryStatsRefreshService categoryStatsRefreshService) {
        this.categoryStatsRefreshService = categoryStatsRefreshService;
    }

    @Scheduled(fixedDelayString = "${app.category-stats.refresh.fixed-delay-ms:60000}")
    public void refresh() {
        log.info("Scheduled refresh of mv_category_stats started.");
        categoryStatsRefreshService.refreshMaterializedView();
        log.info("Scheduled refresh of mv_category_stats completed.");
    }
}

