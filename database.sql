CREATE TABLE public.table_name
(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);
CREATE UNIQUE INDEX table_name_id_uindex ON public.table_name (id);
CREATE UNIQUE INDEX table_name_username_uindex ON public.table_name (username);

CREATE TABLE public.Authorities
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(45) NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT Authorities_User_id_fk FOREIGN KEY (user_id) REFERENCES "User" (id)
);
CREATE UNIQUE INDEX "Authorities_id_uindex" ON public.Authorities (id);

CREATE TABLE public.Cars
(
    id SERIAL PRIMARY KEY NOT NULL,
    model_name VARCHAR(45) NOT NULL,
    purchase_date DATE NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT Cars_User_id_fk FOREIGN KEY (user_id) REFERENCES "User" (id)
);
CREATE UNIQUE INDEX "Cars_id_uindex" ON public.Cars (id);