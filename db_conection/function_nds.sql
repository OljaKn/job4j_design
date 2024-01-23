create
or replace function nds()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.18
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';