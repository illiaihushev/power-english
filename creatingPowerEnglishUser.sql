create tablespace power_english_tabspace
datafile 'power_english_tabspace.dat'
size 10M autoextend on;
create temporary tablespace power_english_tabspace_temp
tempfile 'power_english_tabspace_temp.dat'
size 5M autoextend on;
create user power_english
  identified by password
  default tablespace power_english_tabspace
  temporary tablespace power_english_tabspace_temp;
grant create session to power_english; 
grant create table to power_english; 
grant create sequence to power_english; 
grant unlimited tablespace to power_english;
grant create procedure to power_english;
