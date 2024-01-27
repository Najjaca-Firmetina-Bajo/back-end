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
VALUES (3.6, 1001, 'u-1-g-d', 'Kompanija 1');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.0, 1002, 'u-2-g-d', 'Kompanija 2');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.3, 1003, 'u-3-g-d', 'Kompanija 3');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.6, 1004, 'u-4-g-d', 'Kompanija 4');

INSERT INTO public.companies(
    average_rating, id, address, name)
VALUES (4.9, 1005, 'u-5-g-d', 'Kompanija 5');
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
VALUES (1000, 1001);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (1000, 1002);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (1000, 1003);

INSERT INTO public.registered_user(
    penal_points, id)
VALUES (1000, 1006);
------------------------------------------------------------------

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (10, 1001, 'd1', 'jedan', 't1');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (11, 1002, 'd2', 'dva', 't2');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (18.5, 1003, 'd3', 'tri', 't3');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (26, 1004, 'd4', 'cetiri', 't4');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (9.1, 1005, 'd5', 'pet', 't5');

------------------------------------------------------

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1001,1001, 1001,10);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1002,1001, 1003,11);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1003,1002, 1002,1);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1004,1002, 1004,8);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1005,1003, 1003,89);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1006,1004, 1004,5);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1007,1005, 1001,54);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1008,1005, 1003,67);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (1009,1005, 1005,34);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (10010,1003, 1001,12);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (10011,1003, 1002,17);
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
    date, id, working_calendar_id)
VALUES ('2023-01-21T12:30:00', 1001, 1001);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-03-19T12:30:00', 1002, 1005);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-02-1T12:30:00', 1003, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-02-4T12:30:00', 1004, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-02-7T12:30:00', 1005, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-03-8T12:30:00', 1006, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-04-10T12:30:00', 1007, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-06-17T12:30:00', 1008, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-07-7T12:30:00', 1009, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-09-9T12:30:00', 10010, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-10-7T12:30:00', 10011, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-07-6T12:30:00', 10012, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-12-7T12:30:00', 10013, 1003);

INSERT INTO public.working_days(
    date, id, working_calendar_id)
VALUES ('2023-11-6T12:30:00', 10014, 1003);
-----------------------------------------------------------------------
INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (1, false, 111, 0, 1004, 1001, '2023-02-01T14:00:00', 1003);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (1, false, 151, 0, 1004, 1002, '2023-02-04T15:00:00', 1004);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (2, true, 222, 1, 1004, 1003, '2023-02-07T15:10:00', 1005);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (4, false, 223, 0, 1004, 1004, '2023-03-08T15:10:00', 1006);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (4, false, 223, 0, 1004, 1005, '2024-03-06T15:10:00', 1006);

INSERT INTO public.appointments (duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id)
VALUES (4, false, 223, 0, 1004, 1006, '2024-03-09T15:10:00', 1006);

-----------------------------------------------------------------------

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1001, 1001, 1001, 'ABC123', 'NEW');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1002, 1002, 1002, 'XYZ456', 'PROCESSED');

INSERT INTO public.qrcodes (appointment_id, id, registered_user_id, code, status)
VALUES (1003, 1003, 1003, '123ABC', 'DECLINED');

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




