



-- init entity sequences TODO refactor that, make validation on entity definitions

drop sequence if exists flashcard_seq cascade;
drop sequence if exists deck_seq cascade;
drop sequence if exists task_seq cascade;

create sequence if not exists flashcard_seq start 1;
create sequence if not exists deck_seq start 1;
create sequence if not exists task_seq start 1;
create sequence if not exists reference_resource_seq start 1;





insert into tasks (note, task_id, parent_task_task_id) values ('nauczyc sie AWS', nextval('task_seq'), null);
insert into tasks (note, task_id, parent_task_task_id) values ('zdeplojowac przykladowa apke w AWSie', nextval('task_seq'), 1);
insert into tasks (note, task_id, parent_task_task_id) values ('zrobic bigos', nextval('task_seq'), 1);
insert into tasks (note, task_id, parent_task_task_id) values ('ogarnac EC2', nextval('task_seq'), 2);
insert into tasks (note, task_id, parent_task_task_id) values ('ogarnac role', nextval('task_seq'), 2);
insert into tasks (note, task_id, parent_task_task_id) values ('ogarnac code pipeline', nextval('task_seq'), 2);

insert into tasks (note, task_id, parent_task_task_id) values ('jedzenie', nextval('task_seq'), 1);
insert into tasks (note, task_id, parent_task_task_id) values ('zjesc obiad', nextval('task_seq'), 6);
insert into tasks (note, task_id, parent_task_task_id) values ('zjesc sniadanie', nextval('task_seq'), 6);


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
                                              question varchar(255),
                                              page varchar(255),
                                              short_answer varchar(255),
                                              long_answer varchar(255),
                                              ref_url varchar(255),
                                              code_sample_url varchar(255),
                                              picture_url varchar(255),
                                              deck_title varchar(255)
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
call migrate('/var/lib/postgresql/sheets');



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
                    execute ''insert into decks(deck_id, title) values ($1, $2)'' using nextval(''deck_seq''), row_to_migrate.deck_title;
                    select deck_id into last_deck_id from decks where title like row_to_migrate.deck_title;
                end if;


                execute ''insert into flashcards (fc_id, question, long_answer) values ($1, $2, $3)'' using nextval(''flashcard_seq''), row_to_migrate.question, row_to_migrate.long_answer;
                execute ''insert into decks_flashcards (deck_deck_id, flashcards_fc_id) values ($1, $2)'' using last_deck_id, currval(''flashcard_seq'') ;
            END LOOP;


    end;
';
call migrateToTables();