CREATE TABLE hotels (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE guests (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE surveys (
    id BIGINT PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    guest_id BIGINT NOT NULL,
    score DOUBLE NOT NULL,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_survey_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id),
    CONSTRAINT fk_survey_guest FOREIGN KEY (guest_id) REFERENCES guests(id)
);