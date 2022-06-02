insert into products(type, production_finished,planned_end_of_production)
values ('chair',0,DATE_ADD(CURRENT_DATE(),INTERVAL 6 DAY));
insert into products(type, production_finished,planned_end_of_production)
values ('table',0,DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY));

insert into production_phases(name, default_duration) values ('Cutting out: table legs',1);
insert into production_phases(name, default_duration) values ('Cutting out: chair legs',1);
insert into production_phases(name, default_duration) values ('Cutting out: table top',1);
insert into production_phases(name, default_duration) values ('Cutting out: chair seat',1);
insert into production_phases(name, default_duration) values ('Wood planing', 2);
insert into production_phases(name, default_duration) values ('Wood painting', 3);
insert into production_phases(name, default_duration) values ('Assembling the furniture', 1);

insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),1,2,1);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),CURRENT_DATE(),CURRENT_DATE(),1,4,2);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (2,CURRENT_DATE(),CURRENT_DATE(),DATE_ADD(CURRENT_DATE(),INTERVAL 2 DAY),null,1,5,3);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (3,DATE_ADD(CURRENT_DATE(),INTERVAL 2 DAY),null,DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,1,6,4);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,DATE_ADD(CURRENT_DATE(),INTERVAL 6 DAY),null,1,7,5);
UPDATE stage_execution SET next_stage_id = 2 WHERE id = 1;
UPDATE stage_execution SET next_stage_id = 3 WHERE id = 2;
UPDATE stage_execution SET next_stage_id = 4 WHERE id = 3;
UPDATE stage_execution SET next_stage_id = 5 WHERE id = 4;


insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),2,1,1);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),null,2,3,2);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (2,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),null,current_date(),null,2,5,3);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (3,current_date(),null,DATE_ADD(CURRENT_DATE(),INTERVAL 3 DAY),null,2,6,4);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage,product_id,production_phase_id,sequence_position)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 3 DAY),null,DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),null,2,7,5);
UPDATE stage_execution SET next_stage_id = 7 WHERE id = 6;
UPDATE stage_execution SET next_stage_id = 8 WHERE id = 7;
UPDATE stage_execution SET next_stage_id = 9 WHERE id = 8;
UPDATE stage_execution SET next_stage_id = 10 WHERE id = 9;

insert into orders(name,order_value) VALUES ('Pierwsze zamówienie',10250.50);

UPDATE products SET active_stage_id = 3 WHERE id = 1;
UPDATE products SET active_stage_id = 7 WHERE id = 2;

UPDATE products SET order_id = 1 WHERE id = 1;
UPDATE products SET order_id = 1 WHERE id = 2;

insert into product_types(product_type) values ('chair');
insert into product_types(product_type) values ('table');
insert into product_types(product_type) values ('stool');

insert into type_attribute(name, type_id) values ('seat height',1);
insert into type_attribute(name, type_id) values ('backrest height',1);
insert into type_attribute(name, type_id) values ('material',1);

insert into type_attribute(name, type_id) values ('table top width',2);
insert into type_attribute(name, type_id) values ('table top length',2);
insert into type_attribute(name, type_id) values ('table top height',2);
insert into type_attribute(name, type_id) values ('material',2);

insert into type_attribute(name, type_id) values ('seat height',3);
insert into type_attribute(name, type_id) values ('material',3);

insert into  attribute_values(value) values ('700');
insert into  attribute_values(value) values ('600');
insert into  attribute_values(value) values ('oak');

insert into  attribute_values(value) values ('750');
insert into  attribute_values(value) values ('1500');
insert into  attribute_values(value) values ('800');
insert into  attribute_values(value) values ('black wood');

update products SET product_type_id = 1 where id = 1;
update products SET product_type_id = 2 where id = 2;

insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (1,1,1);
insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (1,2,2);
insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (1,3,3);

insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (2,4,4);
insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (2,5,5);
insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (2,6,6);
insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
values (2,7,7);





