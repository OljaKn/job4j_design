create trigger nds_before_trigger
    after insert
    on products
    for each row
    execute procedure nds_row_function();