/* Generating data for GROUPS table: */
insert into groups(number, department) values(1234, 'Informatics');
insert into groups(number, department) values(2345, 'Economics');
insert into groups(number, department) values(3456, 'Cybernetics');
insert into groups(number, department) values(4567, 'History');
insert into groups(number, department) values(5678, 'Religious');
insert into groups(number, department) values(6789, 'Journalism');

/* Generating data for STUDENTS table: */
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Zimin','Yaroslav', 'Georgievich', '1989-12-31', 1234);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Melnikov','Semyon', 'Vladislavovich', '1985-06-30', 1234);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Kalashnikov','Danila', 'Pavlovich', '1990-04-21', 1234);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Lavrentev','Vitaliy', 'Dmitrievich', '1992-06-22', 2345);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Ilin','Semyon', 'Anatolevich', '1990-03-15', 2345);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Kotov','Gavriil', 'Romanovich', '1991-02-28', 2345);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Avdeev','Innokentiy', 'Mihaylovich', '1988-05-09', 3456);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Borisov','Andrey', 'Viktorovich', '1987-03-05', 3456);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Lukin','Daniil', 'Bogdanovich', '1991-08-07', 3456);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Nikitin','Eduard', 'Maksimovich', '1993-04-12', 4567);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Haritonov','Stepan', 'Antonovich', '1994-11-05', 4567);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Biryukov','Lev', 'Romanovich', '1989-10-20', 4567);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Kuzneczov','Aleksandr', 'Ilich', '1992-04-12', 5678);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Shubin','Vladimir', 'Egorovich', '1991-06-03', 5678);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Makarov','Valentin', 'Viktorovich', '1991-06-11', 5678);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Lihachyov','Maksim', 'Evgenevich', '1990-08-25', 6789);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Evseev','Rostislav', 'Viktorovich', '1985-12-01', 6789);
insert into students(lastname, firstname, midname, birthdate, groupnumber)
values('Maksimov','Stepan', 'Grigorevich', '1987-03-05', 6789);