CREATE TABLE status
(
    id          UUID PRIMARY KEY NOT NULL,
    name        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE tasks
ADD COLUMN status_id UUID,
ADD CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(id);