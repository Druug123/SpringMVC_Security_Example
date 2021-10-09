insert into role(id,role) values (1,'ROLE_ADMIN');
insert into role(id,role) values (2,'ROLE_USER');
insert into user(id, name, password, age, email) values (1,'admin','$2a$12$5zLbHwcxY9ZeeTh6pTNtce8TW.W3Vth8HlxrVSni8DSKJNVQp59SG', 99, 'admin@admin.com');
insert into user_roles(user_id,role_id) values (1,1);
insert into user_roles(user_id,role_id) values (1,2);
update hibernate_sequence set next_val=2;