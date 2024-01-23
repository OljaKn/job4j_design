create
or replace function nds_row_function()
    returns trigger as
$$
    BEGIN
		NEW.price = NEW.price + NEW.price * 0.18;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

