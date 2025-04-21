INSERT INTO status (id, name)
VALUES
    ('D6127F26-55B6-408D-A010-322FBEC9B1B1', 'Open'),
    ('C78BDBC7-E2D6-4C39-AF32-FDDAC2DC9004', 'In Progress'),
    ('31BD3B30-FFCC-44C5-B24B-F169E28FC138', 'Blocked'),
    ('9CD7D8F8-980B-4344-9A6C-44DA84A72974', 'Done');

-- create new task with status in progress
INSERT INTO tasks (id, name, description, created_at, status_id)
VALUES ('525B5626-D373-4023-A026-FBA5642EAEF0', 'Create CLI', 'This todo list as CLI', now(), 'C78BDBC7-E2D6-4C39-AF32-FDDAC2DC9004');