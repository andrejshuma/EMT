-- Materialized view with aggregated statistics per book category.
--
-- Note:
-- - Materialized views are not updated automatically.
-- - We create a UNIQUE index on category to support
--   REFRESH MATERIALIZED VIEW CONCURRENTLY.

DROP MATERIALIZED VIEW IF EXISTS mv_category_stats;

CREATE MATERIALIZED VIEW mv_category_stats AS
SELECT
    b.category                                 AS category,
    COUNT(*)                                   AS total_books,
    COALESCE(SUM(b.available_copies), 0)        AS total_available_copies,
    COUNT(*) FILTER (WHERE b.state <> 'GOOD')   AS not_good_state_books
FROM books b
GROUP BY b.category;

CREATE UNIQUE INDEX IF NOT EXISTS ux_mv_category_stats_category
    ON mv_category_stats (category);

