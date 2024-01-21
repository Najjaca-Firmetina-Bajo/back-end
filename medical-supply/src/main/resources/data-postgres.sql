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
VALUES (true, 1, 'Gacko', 'ci1', 'BiH, RS', 'sk1@gmail.com', 'Stefan', 'oc1', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Kovacevic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 2, 'Gacko', 'ci2', 'BiH, RS', 'chief_administrator@gmail.com', 'Slobodan', 'oc2', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Nikolic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 3, 'Gacko', 'ci3', 'BiH, RS', 'li3@gmail.com', 'Luka', 'oc3', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Ivkovic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 4, 'Gacko', 'ci4', 'BiH, RS', 'vm4@gmail.com', 'Vladimir', 'oc4', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Mandic');

INSERT INTO public.users(
    enabled, id, city, company_info, country, username, name, occupation, password, phone_number, surname)
VALUES (true, 5, 'Gacko', 'ci2', 'BiH, RS', 'new@gmail.com', 'Radoslav', 'oc2', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '064789632', 'Nikolic');

-----------------------------------------------------------------------------------------------------------------

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (3.6, 1, 'u-1-g-d', 'Kompanija 1');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.0, 2, 'u-2-g-d', 'Kompanija 2');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.3, 3, 'u-3-g-d', 'Kompanija 3');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.6, 4, 'u-4-g-d', 'Kompanija 4');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.9, 5, 'u-5-g-d', 'Kompanija 5');
---------------------------------------------
INSERT INTO public.user_role(
    role_id, user_id)
VALUES (1, 4);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (2, 5);

INSERT INTO public.company_administrator(
    company_id, id)
VALUES (3, 4);

INSERT INTO public.system_administrator(
    password_changed, id)
VALUES (true, 2);

INSERT INTO public.system_administrator(
    password_changed, id)
VALUES (false, 5);

----------------------------------------------------------------
INSERT INTO public.user_role(
    role_id, user_id)
VALUES (0, 1);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (2, 2);

INSERT INTO public.user_role(
    role_id, user_id)
VALUES (0, 3);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 1);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 2);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (0, 3);
------------------------------------------------------------------

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (10, 1, 'd1', 'jedan', 't1');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (11, 2, 'd2', 'dva', 't2');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (18.5, 3, 'd3', 'tri', 't3');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (26, 4, 'd4', 'cetiri', 't4');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (9.1, 5, 'd5', 'pet', 't5');

------------------------------------------------------

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (1, 1);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (1, 3);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (2, 2);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (2, 4);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (3, 3);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (4, 4);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (5, 1);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (5, 3);

INSERT INTO public.company_equipment(
    company_id, equipment_id)
VALUES (5, 5);

-----------------------------------------------------

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (1, 1);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (2, 2);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (3, 3);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (4, 4);

INSERT INTO public.working_calendars(
    company_id, id)
VALUES (5, 5);
-------------------------------------------------------
INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-01-21T12:30:00', 1, 1);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-03-19T12:30:00', 2, 5);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-02-1T12:30:00', 3, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-02-4T12:30:00', 4, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-02-7T12:30:00', 5, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-03-8T12:30:00', 6, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-04-10T12:30:00', 7, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-06-17T12:30:00', 8, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-07-7T12:30:00', 9, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-09-9T12:30:00', 10, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-10-7T12:30:00', 11, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-07-6T12:30:00', 12, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-12-7T12:30:00', 13, 3);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-11-6T12:30:00', 14, 3);
-----------------------------------------------------------------------
INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (1, false, 111, 0, 4, 1, '2024-02-01T14:00:00', 3);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (1, false, 151, 0, 4, 2, '2024-02-04T15:00:00', 4);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (2, true, 222, 1, 4, 3, '2024-02-07T15:10:00', 5);

-----------------------------------------------------------------------

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1, 1, 1, 'ABC123', 'NEW');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (2, 2, 2, 'XYZ456', 'PROCESSED');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (3, 3, 3, '123ABC', 'DECLINED');
-----------------------------------------------------------------------

INSERT INTO public.qrcode_equipment (equipment_id, qrcode_id)
VALUES (1, 1);

INSERT INTO public.qrcode_equipment (equipment_id, qrcode_id)
VALUES (2, 1);

INSERT INTO public.qrcode_equipment (equipment_id, qrcode_id)
VALUES (1, 2);

INSERT INTO public.qrcode_equipment (equipment_id, qrcode_id)
VALUES (3, 2);


