-- Example 1 REG
INSERT INTO public.users (
    activated, role, id,
    city, company_info, country, email, name, occupation,
    password, phone_number, surname
) VALUES (
             true, 0, 1,
             'City1', 'CompanyInfo1', 'Country1', 'email1@example.com',
             'John', 'Developer', 'password1', '123456789', 'Doe'
         );

-- Example 2 SYS
INSERT INTO public.users (
    activated, role, id,
    city, company_info, country, email, name, occupation,
    password, phone_number, surname
) VALUES (
             false, 1, 2,
             'City2', 'CompanyInfo2', 'Country2', 'email2@example.com',
             'Jane', 'Manager', 'password2', '987654321', 'Doe'
         );

-- Example 3 COMP
INSERT INTO public.users (
    activated, role, id,
    city, company_info, country, email, name, occupation,
    password, phone_number, surname
) VALUES (
             true, 2, 3,
             'City3', 'CompanyInfo3', 'Country3', 'email3@example.com',
             'Bob', 'Engineer', 'password3', '456789012', 'Smith'
         );

-- Example 4 REG
INSERT INTO public.users (
    activated, role, id,
    city, company_info, country, email, name, occupation,
    password, phone_number, surname
) VALUES (
             true, 0, 4,
             'City4', 'CompanyInfo4', 'Country4', 'email4@example.com',
             'Alice', 'Designer', 'password4', '789012345', 'Johnson'
         );

INSERT INTO public.companies (id,average_rating, address, name) VALUES
                                                             (1,4.5, '123 Main Street', 'TechCo'),
                                                             (2,3.8, '456 Oak Avenue', 'ElectroCorp'),
                                                             (3,4.2, '789 Maple Lane', 'InnoTech');


INSERT INTO public.equipment (id,description, name, type,price) VALUES
                                                             (1,'Laptop for development', 'Developer Laptop', 'Hardware',30),
                                                             (2,'High-performance servers', 'PowerServer X200', 'Hardware',40),
                                                             (3,'Meeting room projector', 'Conference Projector', 'Hardware',50);


INSERT INTO public.company_equipment (company_id, equipment_id) VALUES
                                                             (1, 1), -- TechCo has Developer Laptop
                                                             (1, 2), -- TechCo has PowerServer X200
                                                             (2, 2), -- ElectroCorp has PowerServer X200
                                                             (3, 3); -- InnoTech has Conference Projector

--
INSERT INTO public.registered_user (
    penal_points, id
) VALUES (
             0, 1
         );

INSERT INTO public.registered_user (
    penal_points, id
) VALUES (
             0, 4
         );

-- Example System Administrator
INSERT INTO public.system_administrator (
    jacina, id
) VALUES (
             5, 2
         );

-- Example Company Administrator
INSERT INTO public.company_administrator (
    company_id, id
) VALUES (
             1, 3
         );


