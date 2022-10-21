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
