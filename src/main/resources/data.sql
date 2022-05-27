insert into products(type) values ('chair');
insert into products(type) values ('table');

insert into production_phases(name, default_duration) values ('Cutting out: table legs',1);
insert into production_phases(name, default_duration) values ('Cutting out: chair legs',1);
insert into production_phases(name, default_duration) values ('Cutting out: table top',1);
insert into production_phases(name, default_duration) values ('Cutting out: chair seat',1);
insert into production_phases(name, default_duration) values ('Wood planing', 2);
insert into production_phases(name, default_duration) values ('Wood painting', 3);
insert into production_phases(name, default_duration) values ('Assembling the furniture', 1);

insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),2,1,2);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),CURRENT_DATE(),CURRENT_DATE(),3,1,4);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (2,CURRENT_DATE(),DATE_ADD(CURRENT_DATE(),INTERVAL 2 DAY),null,4,1,5);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (3,DATE_ADD(CURRENT_DATE(),INTERVAL 2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,5,1,6);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 6 DAY),null,null,1,7);

insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),7,2,1);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),null,8,2,3);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (2,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY),null,9,2,5);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (3,DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),null,10,2,6);
insert into stage_execution(duration,start_of_stage,estimated_end_of_stage,actual_end_of_stage,next_stage_id,product_id,production_phase_id) values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,null,2,7);

insert into orders(name,order_value) VALUES ('Pierwsze zamówienie',10250.50);
