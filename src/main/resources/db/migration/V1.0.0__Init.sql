CREATE TABLE
  stocked_food (
    id uuid NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    purchased_at DATE NOT NULL,
    best_before DATE NOT NULL,
    use_up BOOLEAN NOT NULL,
    memo VARCHAR(255)
  );

-- INSERT INTO
--   stocked_food (
--     id,
--     name,
--     price,
--     purchased_at,
--     best_before,
--     use_up,
--     memo
--   )
-- VALUES
--   (
--     'a313a456-7890-1234-5678-901234567890',
--     'カップラーメン',
--     150,
--     '2023-12-23',
--     '2025-12-01',
--     TRUE,
--     ''
--   ),
--   (
--     'b424b567-8901-2345-6789-012345678901',
--     'お米',
--     125,
--     '2023-12-13',
--     '2024-07-01',
--     FALSE,
--     '10kg'
--   ),
--   (
--     'c535c678-9012-3456-7890-123456789012',
--     '鯖缶',
--     200,
--     '2023-12-25',
--     '2025-09-01',
--     TRUE,
--     'ドラックストアで初めて見つけた'
--   );
