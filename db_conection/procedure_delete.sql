create
or replace procedure delete_data(u_count integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products
        where count = u_count AND count < 10;
    END;
$$;
