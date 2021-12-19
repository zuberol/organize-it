

--https://stackoverflow.com/questions/18533625/copy-multiple-csv-files-into-postgres
create or replace procedure migrate(
    files_dir_path TEXT
)
    language plpgsql
as '
    DECLARE fn_i record; -- Variable to hold name of current CSV file being inserted
    begin

    drop table if exists spreedsheet_files;
    drop table if exists tmigration;

    -- create migration table
    create table IF NOT EXISTS tmigration (
                                              question varchar(1000),
                                              page varchar(1000),
                                              short_answer varchar(1000),
                                              long_answer varchar(1000),
                                              ref_url varchar(1000),
                                              code_sample_url varchar(1000),
                                              picture_url varchar(1000),
                                              deck_title varchar(1000)
    );

    -- get spreedsheets file names
    CREATE TABLE IF NOT EXISTS spreedsheet_files AS
    SELECT files_dir_path || ''/'' || pg_ls_dir AS fn
            , pg_ls_dir as fname
    FROM pg_ls_dir(files_dir_path);

    for fn_i in select * from spreedsheet_files
        LOOP
            raise notice ''fn: %'', fn_i.fn;
            EXECUTE ''COPY '' || ''tmigration(question, page, short_answer, long_answer, ref_url, code_sample_url, picture_url)'' || '' from '''''' || fn_i.fn || '''''' with csv header'';
            update tmigration set deck_title = fn_i.fname where deck_title is null;
        END LOOP;

    end;
';



--
create or replace procedure migrateToTables(

)
language plpgsql
as '
    declare row_to_migrate record;
    declare last_deck_id bigint;
    begin


        for row_to_migrate in select * from tmigration
            LOOP
                select deck_id into last_deck_id from decks where title like row_to_migrate.deck_title;
                if not found then
                    execute ''insert into decks(deck_id, title) values ($1, $2)'' using nextval(''decks_deck_id_seq''), row_to_migrate.deck_title;
                    select deck_id into last_deck_id from decks where title like row_to_migrate.deck_title;
                end if;


                execute ''insert into flashcards (fc_id, question, long_answer, short_answer) values ($1, $2, $3, $4)'' using nextval(''flashcards_fc_id_seq''), row_to_migrate.question, row_to_migrate.long_answer, row_to_migrate.short_answer;
                execute ''insert into decks_flashcards (deck_deck_id, flashcards_fc_id) values ($1, $2)'' using last_deck_id, currval(''flashcards_fc_id_seq'');

                execute ''insert into reference_resources (res_id, dtype, reference_url) values ($1, $2, $3)'' using nextval(''reference_resources_res_id_seq''), 1, row_to_migrate.ref_url;
                execute ''insert into flashcards_reference_resources (flashcard_fc_id, reference_resources_res_id) values ($1, $2)'' using currval(''flashcards_fc_id_seq''), currval(''reference_resources_res_id_seq'') ;
            END LOOP;


    end;
';

-- call migrate('/var/lib/postgresql/sheets');
-- call migrateToTables();