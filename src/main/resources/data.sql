insert into products(type) values ('chair');
insert into products(type) values ('table');

insert into production_phases(name, default_duration) values ('Cutting out: table legs',1);
insert into production_phases(name, default_duration) values ('Cutting out: chair legs',1);
insert into production_phases(name, default_duration) values ('Cutting out: table top',1);
insert into production_phases(name, default_duration) values ('Cutting out: chair seat',1);
insert into production_phases(name, default_duration) values ('Wood planing', 2);
insert into production_phases(name, default_duration) values ('Wood painting', 3);
insert into production_phases(name, default_duration) values ('Assembling the furniture', 1);

insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),1,2,1);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),CURRENT_DATE(),CURRENT_DATE(),1,4,2);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (2,CURRENT_DATE(),DATE_ADD(CURRENT_DATE(),INTERVAL 2 DAY),null,1,5,3);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (3,DATE_ADD(CURRENT_DATE(),INTERVAL 2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,1,6,4);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 6 DAY),null,1,7,5);
UPDATE stage_execution SET next_stage_id = 2 WHERE id = 1;
UPDATE stage_execution SET next_stage_id = 3 WHERE id = 2;
UPDATE stage_execution SET next_stage_id = 4 WHERE id = 3;
UPDATE stage_execution SET next_stage_id = 5 WHERE id = 4;


insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),2,1,1);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),null,2,3,2);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (2,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY),null,2,5,3);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (3,DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),null,2,6,4);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,2,7,5);
UPDATE stage_execution SET next_stage_id = 7 WHERE id = 6;
UPDATE stage_execution SET next_stage_id = 8 WHERE id = 7;
UPDATE stage_execution SET next_stage_id = 9 WHERE id = 8;
UPDATE stage_execution SET next_stage_id = 10 WHERE id = 9;

UPDATE products SET active_stage_id = 3 WHERE id = 1;
UPDATE products SET active_stage_id = 7 WHERE id = 2;

insert into orders(name,order_value) VALUES ('Pierwsze zamówienie',10250.50);
