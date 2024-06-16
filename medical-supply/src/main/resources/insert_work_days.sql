DO $$
DECLARE
    currentDate DATE := '2024-06-18';
    endDate DATE := '2024-09-01';
BEGIN
    WHILE currentDate <= endDate LOOP
        INSERT INTO public.working_days(date, end_date, working_calendar_id)
        VALUES (TO_TIMESTAMP(currentDate || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
                TO_TIMESTAMP(currentDate || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
                1003);
        currentDate := currentDate + INTERVAL '1 day';
    END LOOP;
END$$;