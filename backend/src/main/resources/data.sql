





insert into task (note, task_id, parent_task_task_id) values ('nauczyc sie AWS', nextval('task_seq'), null);
insert into task (note, task_id, parent_task_task_id) values ('zdeplojowac przykladowa apke w AWSie', nextval('task_seq'), 1);
insert into task (note, task_id, parent_task_task_id) values ('ogarnac EC2', nextval('task_seq'), 2);
insert into task (note, task_id, parent_task_task_id) values ('ogarnac role', nextval('task_seq'), 2);
insert into task (note, task_id, parent_task_task_id) values ('ogarnac code pipeline', nextval('task_seq'), 2);

insert into task (note, task_id, parent_task_task_id) values ('jedzenie', nextval('task_seq'), 1);
insert into task (note, task_id, parent_task_task_id) values ('zjesc obiad', nextval('task_seq'), 6);
insert into task (note, task_id, parent_task_task_id) values ('zjesc sniadanie', nextval('task_seq'), 6);


create table IF NOT EXISTS flashcard_decki_var as
    select * from flashcards
        full join flashcards_references fr on flashcards.fc_id = fr.flashcard_fc_id
        full join reference r on fr.references_id = r.id
;

--
COPY flashcard_decki_var(question, reference_url, reference_url)
FROM '/var/data/pytaniaScala.csv' DELIMITER ',' CSV HEADER;

-- select * from flashcards
--     full join
--
--
--
--
--
-- COPY (select * from flashcards)
-- FROM '/var/data/pytaniaScala.csv' DELIMITER ',' CSV HEADER;

-- create function initFlascardsTables



CREATE OR REPLACE FUNCTION create_mytable()
  RETURNS void
  LANGUAGE plpgsql AS
$func$
BEGIN
   IF EXISTS (SELECT FROM pg_catalog.pg_tables
              WHERE  schemaname = 'myschema'
              AND    tablename  = 'mytable') THEN
      RAISE NOTICE 'Table myschema.mytable already exists.';
   ELSE
      CREATE TABLE mytable (i integer);
   END IF;
END
$func$;

SELECT create_mytable();

create temp table hehe

select * from flashcards
full join flashcards_references fr on flashcards.fc_id = fr.flashcard_fc_id
full join reference r on fr.references_id = r.id;


