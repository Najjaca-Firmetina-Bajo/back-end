------------------------------------------------------------------

INSERT INTO public.role(
    id, name)
VALUES (0, 'REGISTERED_USER');

INSERT INTO public.role(
    id, name)
VALUES (1, 'COMPANY_ADMINISTRATOR');

INSERT INTO public.role(
    id, name)
VALUES (2, 'SYSTEM_ADMINISTRATOR');

------------------------------------------------------------------------
-- SIFRA ZA USERE 12345678
INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1001, 'Gacko', 'ci1', 'BiH, RS', 'sk1@gmail.com', 'Stefan', 'oc1', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Kovacevic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1002, 'Gacko', 'ci2', 'BiH, RS', 'chief_administrator@gmail.com', 'Slobodan', 'oc2', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Nikolic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1003, 'Gacko', 'ci3', 'BiH, RS', 'li3@gmail.com', 'Luka', 'oc3', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Ivkovic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1004, 'Gacko', 'ci4', 'BiH, RS', 'vm4@gmail.com', 'Vladimir', 'oc4', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Mandic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1005, 'Gacko', 'ci2', 'BiH, RS', 'new@gmail.com', 'Radoslav', 'oc2', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Nikolic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1006, 'Ns', 'ci2', 'SRB', 'ivan.mikic169@gmail.com', 'Ivan', 'oc2', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Mikic');

-----------------------------------------------------------------------------------------------------------------

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (3.6, 1001, 'Balzakova-12-Novi Sad-Srbija', 'Apoteka Zdravlje');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.0, 1002, 'Glavna-23-Novi Sad-Srbija', 'Medicinska Laboratorija 2');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.3, 1003, 'Bulevar Oslobodjenja-7-Niš-Srbija', 'Bolnica Vita');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.6, 1004, 'Zdravlja-45-Kragujevac-Srbija', 'Zdravstveni Centar Srbija');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.9, 1005, 'Svetog Save-10-Novi Pazar-Srbija', 'Apoteka Srce');

---------------------------------------------
INSERT INTO public.user_role(
    role_id, user_id)
VALUES (1, 1004);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (2, 1005);

INSERT INTO public.company_administrator(
    company_id, id)
VALUES (1003, 1004);

INSERT INTO public.system_administrator(
    password_changed, id)
VALUES (true, 1002);

INSERT INTO public.system_administrator(
    password_changed, id)
VALUES (false, 1005);

----------------------------------------------------------------
INSERT INTO public.user_role(
    role_id, user_id)
VALUES (0, 1001);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (2, 1002);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (0, 1003);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (0, 1006);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 1001);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 1002);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 1003);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 1006);
------------------------------------------------------------------

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (10, 1001, 'Digitalni rendgen aparat za pluća', 'Rendgen', 'Medicinski Aparat');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (11, 1002, 'Laboratorijski mikroskop za analizu uzoraka', 'Mikroskop', 'Laboratorijska Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (18.5, 1003, 'Defibrilator za hitne intervencije', 'Defibrilator', 'Hitna Medicinska Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (26, 1004, 'Ultrazvučni aparat za dijagnostiku trudnoće', 'Ultrazvuk', 'Ginekološka Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (9.1, 1005, 'Infuziona pumpa za preciznu administraciju lekova', 'Infuziona Pumpa', 'Intenzivna Nega Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (15, 1006, 'EKG aparat za praćenje srčane aktivnosti', 'EKG', 'Kardiovaskularna Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (22, 1007, 'Endoskopski sistem za vizualizaciju unutrašnjih organa', 'Endoskop', 'Hirurška Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (12.5, 1008, 'Inhalator za terapiju disajnih problema', 'Inhalator', 'Respiratorna Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (30, 1009, 'MRI aparat za detaljne snimke unutrašnjih struktura', 'MRI', 'Dijagnostička Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (14.2, 1010, 'Laboratorijski analizator za hemijske testove', 'Analizator', 'Laboratorijska Oprema');


------------------------------------------------------


-- Company 1 (Apoteka Zdravlje)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10027, 1001, 1001, 15);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10028, 1001, 1003, 8);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10029, 1001, 1005, 20);

-- Company 2 (Medicinska Laboratorija 2)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10030, 1002, 1002, 12);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10031, 1002, 1004, 25);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10032, 1002, 1005, 18);

-- Company 3 (Bolnica Vita)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10033, 1003, 1001, 30);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (10011,1003, 1002,17);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10034, 1003, 1003, 10);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10035, 1003, 1005, 15);

-- Company 4 (Zdravstveni Centar Srbija)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10036, 1004, 1002, 22);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10037, 1004, 1004, 7);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10038, 1004, 1005, 14);

-- Company 5 (Apoteka Srce)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10039, 1005, 1001, 18);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10040, 1005, 1003, 20);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10041, 1005, 1005, 12);

-----------------------------------------------------

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (1001, 1001);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (1002, 1002);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (1003, 1003);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (1004, 1004);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (1005, 1005);
-------------------------------------------------------
INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-01-21T12:30:00', '2024-01-22T04:30:00', 1001, 1001);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-03-19T12:30:00', '2024-03-20T04:30:00', 1002, 1005);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-02-1T12:30:00', '2024-02-02T04:30:00', 1003, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-02-4T12:30:00', '2024-02-05T04:30:00', 1004, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-02-7T12:30:00', '2024-02-08T04:30:00', 1005, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-03-8T12:30:00', '2024-03-09T04:30:00', 1006, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-04-10T12:30:00', '2024-04-11T04:30:00', 1007, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-06-17T12:30:00', '2024-06-18T04:30:00', 1008, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-07-7T12:30:00', '2024-07-08T04:30:00', 1009, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-09-9T12:30:00', '2024-09-10T04:30:00', 10010, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-10-7T12:30:00', '2024-10-08T04:30:00', 10011, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-07-6T12:30:00', '2024-07-07T04:30:00', 10012, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-12-7T12:30:00', '2024-12-08T04:30:00', 10013, 1003);

INSERT INTO public.working_days(
    date, end_date, id, working_calendar_id)
VALUES ('2024-11-6T12:30:00', '2024-11-07T04:30:00', 10014, 1003);

-----------------------------------------------------------------------
INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id)
VALUES (1, false, 111, 0, 1004, 1001, '2024-06-06T14:00:00', 1003,-1);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id)
VALUES (1, false, 151, 0, 1004, 1002, '2024-07-06T15:00:00', 1004,-1);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id)
VALUES (2, true, 222, 1, 1004, 1003, '2024-05-07T15:10:00', 1005,-1);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id)
VALUES (4, false, 223, 0, 1004, 1004, '2024-04-04T15:10:00', 1006,-1);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id)
VALUES (4, false, 223, 0, 1004, 1005, '2024-07-07T15:10:00', 1006,-1);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id)
VALUES (4, false, 223, 0, 1004, 1006, '2024-06-06T15:10:00', 1006,-1);

-----------------------------------------------------------------------

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1001, 1001, 1001, 'ABC123', 'NEW');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1002, 1002, 1003, 'XYZ456', 'PROCESSED');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1003, 1003, 1006, '123ABC', 'DECLINED');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1004, 1004, 1003, 'ABC3123', 'CANCELED');


-----------------------------------------------------------------------

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1001,1001, 1001,1);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1002,1002, 1001,2);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1003,1001, 1002,3);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1004,1003, 1002,4);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1005,1003, 1004,6);

-------------------------------------------------------------------------------

-- Za radni dan 1
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-01-21T12:30:00', 30, 0, false, 111, -1, 1004, 1001),
       ('2024-01-21T13:00:00', 30, 0, false, 112, -1, 1004, 1001),
       ('2024-01-21T13:30:00', 30, 0, false, 113, -1, 1004, 1001),
       ('2024-01-21T14:00:00', 30, 0, false, 114, -1, 1004, 1001),
       ('2024-01-21T14:30:00', 30, 0, false, 115, -1, 1004, 1001),
       ('2024-01-21T15:00:00', 30, 0, false, 116, -1, 1004, 1001);

-- Za radni dan 2
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-03-19T12:30:00', 30, 0, false, 121, -1, 1004, 1002),
       ('2024-03-19T13:00:00', 30, 0, false, 122, -1, 1004, 1002),
       ('2024-03-19T13:30:00', 30, 0, false, 123, -1, 1004, 1002),
       ('2024-03-19T14:00:00', 30, 0, false, 124, -1, 1004, 1002),
       ('2024-03-19T14:30:00', 30, 0, false, 125, -1, 1004, 1002),
       ('2024-03-19T15:00:00', 30, 0, false, 126, -1, 1004, 1002);

-- Nastaviti slično za preostale radne dane...
-- Za radni dan 3
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-02-01T12:30:00', 30, 0, false, 131, -1, 1004, 1003),
       ('2024-02-01T13:00:00', 30, 0, false, 132, -1, 1004, 1003),
       ('2024-02-01T13:30:00', 30, 0, false, 133, -1, 1004, 1003),
       ('2024-02-01T14:00:00', 30, 0, false, 134, -1, 1004, 1003),
       ('2024-02-01T14:30:00', 30, 0, false, 135, -1, 1004, 1003),
       ('2024-02-01T15:00:00', 30, 0, false, 136, -1, 1004, 1003);

-- Za radni dan 4
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-02-04T12:30:00', 30, 0, false, 141, -1, 1004, 1004),
       ('2024-02-04T13:00:00', 30, 0, false, 142, -1, 1004, 1004),
       ('2024-02-04T13:30:00', 30, 0, false, 143, -1, 1004, 1004),
       ('2024-02-04T14:00:00', 30, 0, false, 144, -1, 1004, 1004),
       ('2024-02-04T14:30:00', 30, 0, false, 145, -1, 1004, 1004),
       ('2024-02-04T15:00:00', 30, 0, false, 146, -1, 1004, 1004);

-- Za radni dan 5
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-02-07T12:30:00', 30, 0, false, 151, -1, 1004, 1005),
       ('2024-02-07T13:00:00', 30, 0, false, 152, -1, 1004, 1005),
       ('2024-02-07T13:30:00', 30, 0, false, 153, -1, 1004, 1005),
       ('2024-02-07T14:00:00', 30, 0, false, 154, -1, 1004, 1005),
       ('2024-02-07T14:30:00', 30, 0, false, 155, -1, 1004, 1005),
       ('2024-02-07T15:00:00', 30, 0, false, 156, -1, 1004, 1005);

-- Nastaviti slično za preostale radne dane...
-- Za radni dan 6
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-03-08T12:30:00', 30, 0, false, 161, -1, 1004, 1006),
       ('2024-03-08T13:00:00', 30, 0, false, 162, -1, 1004, 1006),
       ('2024-03-08T13:30:00', 30, 0, false, 163, -1, 1004, 1006),
       ('2024-03-08T14:00:00', 30, 0, false, 164, -1, 1004, 1006),
       ('2024-03-08T14:30:00', 30, 0, false, 165, -1, 1004, 1006),
       ('2024-03-08T15:00:00', 30, 0, false, 166, -1, 1004, 1006);

-- Za radni dan 7
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-04-10T12:30:00', 30, 0, false, 171, -1, 1004, 1007),
       ('2024-04-10T13:00:00', 30, 0, false, 172, -1, 1004, 1007),
       ('2024-04-10T13:30:00', 30, 0, false, 173, -1, 1004, 1007),
       ('2024-04-10T14:00:00', 30, 0, false, 174, -1, 1004, 1007),
       ('2024-04-10T14:30:00', 30, 0, false, 175, -1, 1004, 1007),
       ('2024-04-10T15:00:00', 30, 0, false, 176, -1, 1004, 1007);

-- Za radni dan 8
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-06-17T12:30:00', 30, 0, false, 181, -1, 1004, 1008),
       ('2024-06-17T13:00:00', 30, 0, false, 182, -1, 1004, 1008),
       ('2024-06-17T13:30:00', 30, 0, false, 183, -1, 1004, 1008),
       ('2024-06-17T14:00:00', 30, 0, false, 184, -1, 1004, 1008),
       ('2024-06-17T14:30:00', 30, 0, false, 185, -1, 1004, 1008),
       ('2024-06-17T15:00:00', 30, 0, false, 186, -1, 1004, 1008);

-- Nastaviti slično za preostale radne dane...
-- Za radni dan 9
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-07-07T12:30:00', 30, 0, false, 191, -1, 1004, 1009),
       ('2024-07-07T13:00:00', 30, 0, false, 192, -1, 1004, 1009),
       ('2024-07-07T13:30:00', 30, 0, false, 193, -1, 1004, 1009),
       ('2024-07-07T14:00:00', 30, 0, false, 194, -1, 1004, 1009),
       ('2024-07-07T14:30:00', 30, 0, false, 195, -1, 1004, 1009),
       ('2024-07-07T15:00:00', 30, 0, false, 196, -1, 1004, 1009);

-- Za radni dan 10
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-09-09T12:30:00', 30, 0, false, 201, -1, 1004, 10010),
       ('2024-09-09T13:00:00', 30, 0, false, 202, -1, 1004, 10010),
       ('2024-09-09T13:30:00', 30, 0, false, 203, -1, 1004, 10010),
       ('2024-09-09T14:00:00', 30, 0, false, 204, -1, 1004, 10010),
       ('2024-09-09T14:30:00', 30, 0, false, 205, -1, 1004, 10010),
       ('2024-09-09T15:00:00', 30, 0, false, 206, -1, 1004, 10010);

-- Za radni dan 11
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-10-07T12:30:00', 30, 0, false, 211, -1, 1004, 10011),
       ('2024-10-07T13:00:00', 30, 0, false, 212, -1, 1004, 10011),
       ('2024-10-07T13:30:00', 30, 0, false, 213, -1, 1004, 10011),
       ('2024-10-07T14:00:00', 30, 0, false, 214, -1, 1004, 10011),
       ('2024-10-07T14:30:00', 30, 0, false, 215, -1, 1004, 10011),
       ('2024-10-07T15:00:00', 30, 0, false, 216, -1, 1004, 10011);

-- Nastaviti slično za preostale radne dane...
-- Za radni dan 12
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-07-07T12:30:00', 30, 0, false, 221, -1, 1004, 10012),
       ('2024-07-07T13:00:00', 30, 0, false, 222, -1, 1004, 10012),
       ('2024-07-07T13:30:00', 30, 0, false, 223, -1, 1004, 10012),
       ('2024-07-07T14:00:00', 30, 0, false, 224, -1, 1004, 10012),
       ('2024-07-07T14:30:00', 30, 0, false, 225, -1, 1004, 10012),
       ('2024-07-07T15:00:00', 30, 0, false, 226, -1, 1004, 10012);

-- Za radni dan 13
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-12-07T12:30:00', 30, 0, false, 231, -1, 1004, 10013),
       ('2024-12-07T13:00:00', 30, 0, false, 232, -1, 1004, 10013),
       ('2024-12-07T13:30:00', 30, 0, false, 233, -1, 1004, 10013),
       ('2024-12-07T14:00:00', 30, 0, false, 234, -1, 1004, 10013),
       ('2024-12-07T14:30:00', 30, 0, false, 235, -1, 1004, 10013),
       ('2024-12-07T15:00:00', 30, 0, false, 236, -1, 1004, 10013);

-- Za radni dan 14
INSERT INTO public.extraordinary_appointments (pick_up_date, duration, type, is_downloaded, reservation_number, winner_id, company_administrator_id, working_day_id)
VALUES ('2024-11-06T12:30:00', 30, 0, false, 241, -1, 1004, 10014),
       ('2024-11-06T13:00:00', 30, 0, false, 242, -1, 1004, 10014),
       ('2024-11-06T13:30:00', 30, 0, false, 243, -1, 1004, 10014),
       ('2024-11-06T14:00:00', 30, 0, false, 244, -1, 1004, 10014),
       ('2024-11-06T14:30:00', 30, 0, false, 245, -1, 1004, 10014),
       ('2024-11-06T15:00:00', 30, 0, false, 246, -1, 1004, 10014);

-- Nastaviti slično za preostale radne dane...




