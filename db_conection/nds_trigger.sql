create trigger nds_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure nds();