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

INSERT INTO companies (average_rating, address, name) VALUES
                                                          (4.5, '123 Main Street', 'TechCo'),
                                                          (3.8, '456 Oak Avenue', 'ElectroCorp'),
                                                          (4.2, '789 Maple Lane', 'InnoTech');


INSERT INTO equipment (description, name, type) VALUES
                                                    ('Laptop for development', 'Developer Laptop', 'Hardware'),
                                                    ('High-performance servers', 'PowerServer X200', 'Hardware'),
                                                    ('Meeting room projector', 'Conference Projector', 'Hardware');


INSERT INTO company_equipment (company_id, equipment_id) VALUES
                                                             (1, 1), -- TechCo has Developer Laptop
                                                             (1, 2), -- TechCo has PowerServer X200
                                                             (2, 2), -- ElectroCorp has PowerServer X200
                                                             (3, 3); -- InnoTech has Conference Projector
