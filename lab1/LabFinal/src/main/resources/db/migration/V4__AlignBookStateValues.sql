-- Align previously seeded enum values with the current Java State enum.
UPDATE books
SET state = 'GOOD'
WHERE UPPER(state) = 'AVAILABLE';

UPDATE books
SET state = 'BAD'
WHERE UPPER(state) = 'BORROWED';

