CREATE TABLE director (
                          id SERIAL PRIMARY KEY ,
                          name VARCHAR(255) NOT NULL,
                          age INT NOT NULL
);

CREATE TABLE movie (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       year INT NOT NULL,
                       genre VARCHAR(255),
                       duration INT
);

CREATE TABLE movie_director(
                               idMovie INT,
                               idDirector INT,
                               PRIMARY KEY (idMovie, idDirector),
                               FOREIGN KEY (idMovie) REFERENCES movie(id) ON UPDATE CASCADE ON DELETE CASCADE,
                               FOREIGN KEY (idDirector) REFERENCES director(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO movie(title,year,genre,duration)
VALUES ('Spiderman','2002','Action','78'),
       ('The Avengers','2012','Action','80'),
       ('World War Z','2013','Terror,80');


INSERT INTO director (name, age)
VALUES ('Sam Raimi','50'),
       ('Joss Whedon','58'),
       ('Marc Forster','70');

INSERT INTO movie_director (idMovie, idDirector)
VALUES ('1','1'),
       ('2','2'),
       ('3','3');