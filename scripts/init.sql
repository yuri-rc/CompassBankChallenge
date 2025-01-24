CREATE TABLE BankUser (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    phone VARCHAR(15),
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    balance NUMERIC(15, 2) DEFAULT 0,
    user_id INT REFERENCES BankUser(id) ON DELETE CASCADE
);

CREATE TABLE Transaction (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(15, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    source_account_id INT REFERENCES Account(id),
    destination_account_id INT REFERENCES Account(id)
);
