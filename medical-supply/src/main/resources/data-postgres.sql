INSERT INTO users (
    email,
    password,
    role,
    name,
    surname,
    city,
    country,
    phone_number,
    occupation,
    company_info,
    activated
)
VALUES
    ('marko@gmail.com', 'password123', 0, 'Marko', 'Marković', 'Belgrade', 'Serbia', '+381123456789', 'Student', 'NFB University', true),
    ('milan@gmail.com', 'securepass', 1, 'Milan', 'Milanović', 'Belgrade', 'Serbia', '+381987654321', 'Administrator', 'NFB Tech', true),
    ('ivana@gmail.com', 'secret123', 2, 'Ivana', 'Ivanović', 'Novi Sad', 'Serbia', '+38155555555', 'Student', 'NFB University', false);
