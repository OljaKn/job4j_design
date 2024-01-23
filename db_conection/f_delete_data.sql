create
or replace function f_delete_data(u_count integer)
returns void
language 'plpgsql'
as
$$
    begin
        delete from products
            where count = u_count;
    end;
$$;