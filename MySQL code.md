CREATE DATABASE BookMyMovies;
use BookMyMovies;
create table users(
   user_id INT PRIMARY KEY auto_increment,
   name VARCHAR(30),
   email VARCHAR(30),
   phone VARCHAR(30)
);


create table movie(
  movie_id INT primary KEY auto_increment,
  title VARCHAR(30),
  genre varchar(30),
  lang varchar(30),
  duration int
);


create table theater(
   theater_id int primary key auto_increment,
   name varchar(30),
   city varchar(30)
);



create table shows(
   show_id int primary key auto_increment ,
   movie_id int,
   theater_id int,
   timing varchar(30), 
   availabe_seat int,
   foreign key (movie_id) references movie(movie_id),
   foreign key (theater_id) references theater(theater_id)
);

alter table shows
change availabe_seat available_seat int;

create table seat(
    seat_id int primary key auto_increment,
    show_id int,
    seat_number varchar(10),
    is_booked boolean default false,
    foreign key (show_id) references shows(show_id)
);


create table booking(
    booking_id int primary key auto_increment,
    user_id int , 
    show_id int , 
    seats_booked varchar(30),
    total_price decimal(10,2)
);


insert into users 
(name , email , phone)
values
("yadvendra " , "yadvendrapal1@gmail.com" , "7017207619"),
("rajneesh" , "rajneesh@gmail.com" , "7858943498"),
("raman" , "raman@gmail.com" , "9568444634"),
("jyoti" , "hyoti@gmail.com" , "7687329856");


insert into movie
(title , genre , lang , duration)
values
("chaava" , "historical" , "hindi" , 145),
("deva" , "crime" , "hindi" , 120),
("bahubali" , "history" , "malyalam" , 160),
("pati patni or woh" , "romance comedy" , "hindi" , 128),
("hera pheri" , "comedy" , "hindi" ,135);

select * from movie;

insert into theater 
(name , city )
values
("PVR" , "delhi"),
("IMAX" , "agra"),
("INOX" , "pune"),
("cinepolse" , "mumbai" ),
("miraaj" , "nasik");


insert into shows 
( movie_id , theater_id , timing , available_seat)
values
(1 , 1 , "10:00 AM" , 40),
(1 , 2 , "3:00 PM" , 50),
(2 , 3 , "7:00 PM" , 60),
(3 , 4 , "9:00 PM"  , 30),
(4 , 5 , "9:00 AM" , 30),
(5 , 2 , "4:00 PM" , 35);

 insert into seat 
 (show_id , seat_number , is_booked)
 values
 (1 , "A1" , false),(1 , "A2" , false), (1 , "A3" , false), (1 , "A4" , false), (1 , "A5" , false),
 (2 , "B1" , false), (2 , "B2" , false),(2 , "B3" , false),(2 , "B4" , false),(2 , "B5" , false),
 (3 , "C1" , false),(3 , "C2" , false),(3 , "C3" , false),(3 , "C4" , false),(3 , "C5" , false),
 (4 , "D1" , false),(4 , "D2" , false),(4 , "D3" , false),(4 , "D4" , false),(4 , "D5" , false),
 (5 , "E1" , false),(5 , "E2" , false),(5 , "E3" , false),(5 , "E4" , false),(5 , "E5" , false);
 
 
 insert into booking 
 (user_id, show_id, seats_booked, total_price)
 values
 (1, 1 , "A1,A2" , 400);

update seat set is_booked = true where show_id = 1 and seat_number in ("A1" , "A2");

SELECT * FROM theater ;
DELETE FROM theater
WHERE theater_id NOT IN (
    SELECT MIN(theater_id)
    FROM theater
    GROUP BY name, city
);

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE shows;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO theater (name, city) VALUES
('PVR', 'delhi'),
('IMAX', 'agra'),
('INOX', 'pune'),
('cinepolse', 'mumbai'),
('miraaj', 'nasik');

select * from theater;
ALTER TABLE seat ADD COLUMN category VARCHAR(20);
ALTER TABLE seat ADD COLUMN price DOUBLE;
select * from seat;
drop table booking;
CREATE TABLE booking (
  booking_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  show_id INT,
  seats_booked TEXT,
  total_price DOUBLE,
  booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SELECT seat_id, seat_number, show_id
FROM seat
WHERE seat_number IN ('A1', 'A2', 'A3', 'A4', 'A5') AND show_id = 1
ORDER BY seat_number;
DELETE s1 FROM seat s1
JOIN seat s2 
  ON s1.seat_number = s2.seat_number AND s1.show_id = s2.show_id
WHERE s1.seat_id > s2.seat_id;

SET SQL_SAFE_UPDATES = 0;
DELETE s1 FROM seat s1
JOIN seat s2 
  ON s1.seat_number = s2.seat_number AND s1.show_id = s2.show_id
WHERE s1.seat_id > s2.seat_id;

SET SQL_SAFE_UPDATES = 1;

SELECT seat_number, price, is_booked FROM seat WHERE show_id = <your_show_id> AND seat_number IN ('B1', 'B3');

SELECT seat_number, price, is_booked 
FROM seat 
WHERE show_id = 2 
  AND seat_number IN ('B1', 'B3');
  
UPDATE seat SET price = 150 WHERE seat_number LIKE 'B%';
UPDATE seat SET price = 200 WHERE seat_number LIKE 'F%';  -- Front seats
UPDATE seat SET price = 100 WHERE seat_number LIKE 'L%';  -- Lobby seats
INSERT INTO shows (movie_id, theater_id, timing, available_seat)
VALUES (5, 3, '3:00 PM', 50);
INSERT INTO shows (movie_id, theater_id, timing, available_seat)
VALUES
(1, 3, '11:00 AM', 30),
(1, 4, '12:00 PM', 30),
(1, 5, '02:00 AM', 30),
(2, 1, '03:00 PM', 30),
(2, 2, '09:00 PM', 30),
(2, 4, '11:00 PM', 30),
(2, 5, '05:00 PM', 30), 
(3, 3, '11:00 AM', 30);
      
INSERT INTO shows (movie_id, theater_id, timing, available_seat)
VALUES
(3, 1, '10:00 AM', 30),
(3, 2, '12:00 PM', 30),
(3, 5, '05:00 PM', 30),
(4, 1, '09:00 PM', 30),
(4, 2, '07:00 pM', 30),
(4, 3, '05:00 PM', 30), 
(4, 4, '01:00 PM', 30);
SET SQL_SAFE_UPDATES = 0;

select * from shows;
