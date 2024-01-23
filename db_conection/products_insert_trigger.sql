CREATE TRIGGER products_insert_trigger
  AFTER INSERT
  ON "products"
  FOR EACH ROW
  EXECUTE PROCEDURE products_fnc();