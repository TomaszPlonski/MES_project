use factory;

insert into users(id, enabled, password, role, username) VALUES (1,1,'$2a$10$MLe6oZG96z6oGvjmolvz9en0EjOX4dxCBxL8RhQE59kSiwpMjZItu','ROLE_FACTORY','factory');
insert into users(id, enabled, password, role, username) VALUES (2,1,'$2a$10$BB5a363ESgOZ8JKbOch0rupghu5FQGfCXWBtB4suzdCWyL19aLj9G','ROLE_OFFICE','office');

insert into product_types(product_type) values ('chair');
insert into product_types(product_type) values ('table');
insert into product_types(product_type) values ('stool');

insert into production_phases(name, default_duration,sequence_position,type_id) values ('Cutting out: table legs',1,1,2);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Cutting out: chair legs',1,1,1);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Cutting out: table top',1,2,2);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Cutting out: chair seat',1,2,1);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Wood planing', 2,3,2);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Wood planing', 2,3,1);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Wood painting', 3,4,2);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Wood painting', 3,4,1);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Assembling table', 1,5,2);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Assembling chair', 1,5,1);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Cutting out: stool legs', 1,1,3);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Cutting out: stool seat', 1,2,3);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Wood planing', 1,3,3);
insert into production_phases(name, default_duration,sequence_position,type_id) values ('Assembling stool', 1,4,3);

insert into type_attribute(name, type_id) values ('seat height',1);
insert into type_attribute(name, type_id) values ('backrest height',1);
insert into type_attribute(name, type_id) values ('material',1);

insert into type_attribute(name, type_id) values ('table top width',2);
insert into type_attribute(name, type_id) values ('table top length',2);
insert into type_attribute(name, type_id) values ('table top height',2);
insert into type_attribute(name, type_id) values ('material',2);

insert into type_attribute(name, type_id) values ('seat height',3);
insert into type_attribute(name, type_id) values ('material',3);


# Order nr 1
insert into orders(name,order_value,order_finished) VALUES ('Very important order',10250.50,false);

#Product id 1 of order 1
insert into products(production_finished,planned_end_of_production,duration)
values (false,DATE_ADD(CURRENT_DATE(),INTERVAL 10 DAY),8);

#stage execution of product 1
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY));
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),CURRENT_DATE(),CURRENT_DATE());
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (2,CURRENT_DATE(),CURRENT_DATE(),DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),null);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (3,DATE_ADD(CURRENT_DATE(),INTERVAL 4 DAY),null,DATE_ADD(CURRENT_DATE(),INTERVAL 7 DAY),null);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 7 DAY),null,DATE_ADD(CURRENT_DATE(),INTERVAL 10 DAY),null);
#Product/phase/stage mapping  of product 1
insert into production_mapping(product_id, stage_execution_id, production_phase_id)
VALUES (1,1,2);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (1,2,4);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (1,3,6);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (1,4,8);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (1,5,10);

#set active stage of product 1
UPDATE products SET active_stage_id = 3 WHERE id = 1;
UPDATE products SET order_id = 1 WHERE id = 1;
UPDATE products SET product_type_id = 1 WHERE id = 1;

#atribute Value of product 1
insert into  attribute_values(value) values ('700');
insert into  attribute_values(value) values ('600');
insert into  attribute_values(value) values ('oak');

#product/Type Attribute/AttributeValue of product 1
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (1,1,1);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (1,2,2);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (1,3,3);




#Product id 2 of order 1
insert into products(production_finished,planned_end_of_production,duration, order_id,product_type_id)
values (false,DATE_ADD(CURRENT_DATE(),INTERVAL 8 DAY),8, 1,1);
#stage execution of product 2
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY));
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),null);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (2,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),null,current_date(),null);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (3,current_date(),null,DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null);
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL 5 DAY),null,DATE_ADD(CURRENT_DATE(),INTERVAL 6 DAY),null);
#Product/phase/stage mapping  of product 2
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (2,6,1);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (2,7,3);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (2,8,5);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (2,9,7);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (2,10,9);

#set active stage of product 2
UPDATE products SET active_stage_id = 7 WHERE id = 2;

#atribute Value of product 2

insert into  attribute_values(value) values ('750');
insert into  attribute_values(value) values ('1500');
insert into  attribute_values(value) values ('800');
insert into  attribute_values(value) values ('black wood');

#Product/phase/stage mapping  of product 2
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (2,4,4);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (2,5,5);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (2,6,6);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (2,7,7);


# Order nr 2
insert into orders(name,order_value,order_finished) VALUES ('Some random finished order',5280.76,true);

#Product id 3 of order 2
insert into products(production_finished,planned_end_of_production,duration, order_id,product_type_id)
values (true,DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),4, 2,3);
#stage execution of product 3
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -4 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY));
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -3 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY));
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY));
insert into stage_execution(duration,estimated_start_of_stage,actual_start_of_stage,estimated_end_of_stage,actual_end_of_stage)
values (1,DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -2 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL 0 DAY),DATE_ADD(CURRENT_DATE(),INTERVAL -1 DAY));
#Product/phase/stage mapping  of product 3
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (3,11,11);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (3,12,12);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (3,13,13);
# insert into production_mapping(product_id, stage_execution_id, production_phase_id)
# VALUES (3,14,14);


#set active stage of product 3
UPDATE products SET active_stage_id = 14 WHERE id = 3;

#atribute Value of product 3

insert into  attribute_values(value) values ('600');
insert into  attribute_values(value) values ('willow');


#Product/phase/stage mapping  of product 2
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (3,8,8);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (3,9,9);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (3,10,10);
# insert into product_attribute_mapping(product_id, attribute_value_id, type_attribute_id)
# values (3,11,11);





