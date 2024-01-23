CREATE OR REPLACE FUNCTION products_fnc()
  RETURNS trigger AS
$$
BEGIN
 INSERT INTO "history_of_price" ( "name", "price", "data")
VALUES(NEW."name",NEW."price",NEW."date");
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';
