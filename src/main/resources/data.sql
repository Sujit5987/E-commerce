-- data.sql: sample data for users
INSERT INTO users (name, email, password, contact_number) VALUES
('alice', 'alice@example.com', 'password1', '1111111111'),
('bob', 'bob@example.com', 'password2', '2222222222')
ON CONFLICT DO NOTHING;
