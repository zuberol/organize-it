





insert into task (note, task_id, parent_task_task_id) values ('nauczyc sie AWS', nextval('task_seq'), null);
insert into task (note, task_id, parent_task_task_id) values ('zdeplojowac przykladowa apke w AWSie', nextval('task_seq'), 1);
insert into task (note, task_id, parent_task_task_id) values ('ogarnac EC2', nextval('task_seq'), 2);
insert into task (note, task_id, parent_task_task_id) values ('ogarnac role', nextval('task_seq'), 2);
insert into task (note, task_id, parent_task_task_id) values ('ogarnac code pipeline', nextval('task_seq'), 2);

insert into task (note, task_id, parent_task_task_id) values ('jedzenie', nextval('task_seq'), 1);
insert into task (note, task_id, parent_task_task_id) values ('zjesc obiad', nextval('task_seq'), 6);
insert into task (note, task_id, parent_task_task_id) values ('zjesc sniadanie', nextval('task_seq'), 6);
