create trigger nds_row_trigger
    before insert
    on products
    for each row
    execute procedure nds_row();