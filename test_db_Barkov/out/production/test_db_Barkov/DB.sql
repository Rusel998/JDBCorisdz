DROP TABLE IF EXISTS users;

CREATE TABLE users(
                      id serial primary key,
                      first_name char(20),
                      second_name char(20),
                      age integer
)