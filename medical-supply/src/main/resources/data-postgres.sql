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

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 1007, 'Ns', 'ci2', 'SRB', 'milan@gmail.com', 'Milan', 'oc4', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Mikic');

-----------------------------------------------------------------------------------------------------------------

INSERT INTO public.companies(
    average_rating, id, address, name, description)
VALUES (3.6, 1001, 'Balzakova-12-Novi Sad-Srbija', 'Apoteka Zdravlje', 'Duga tradicija');

INSERT INTO public.companies(
    average_rating, id, address, name, description)
VALUES (4.0, 1002, 'Glavna-23-Novi Sad-Srbija', 'Medicinska Laboratorija 2', 'Duga tradicija');

INSERT INTO public.companies(
    average_rating, id, address, name, description)
VALUES (4.3, 1003, 'Bulevar Oslobodjenja-7-Niš-Srbija', 'Bolnica Vita', 'Duga tradicija');

INSERT INTO public.companies(
    average_rating, id, address, name, description)
VALUES (4.6, 1004, 'Zdravlja-45-Kragujevac-Srbija', 'Zdravstveni Centar Srbija', 'Duga tradicija');

INSERT INTO public.companies(
    average_rating, id, address, name, description)
VALUES (4.9, 1005, 'Svetog Save-10-Novi Pazar-Srbija', 'Apoteka Srce', 'Duga tradicija');

---------------------------------------------
INSERT INTO public.user_role(
    role_id, user_id)
VALUES (1, 1004);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (2, 1005);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (1, 1007);

INSERT INTO public.company_administrator(
    company_id, id)
VALUES (1003, 1004);

INSERT INTO public.company_administrator(
    company_id, id)
VALUES (1003, 1007);

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
VALUES (1001, 1001, 1001, 'ABC123', 'PROCESSED');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1003, 1005, 1001, 'ABD111', 'PROCESSED');

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
VALUES (1006,1002, 1005,3);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1002,1002, 1001,2);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1003,1001, 1002,3);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1004,1003, 1002,4);

INSERT INTO public.qrcode_equipment (id,equipment_id, qrcode_id,quantity)
VALUES (1005,1003, 1004,6);

-------------------------------------------------------------------------------
