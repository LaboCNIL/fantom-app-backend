-- INITIAL SCRIPT
CREATE TABLE section
(
   id serial primary key,
   code varchar(255) not null
);

CREATE TABLE page_component
(
   id serial primary key,
   code varchar(255) not null,
   type varchar(255) null,
   image varchar(255),
   created_at timestamptz not null,
   updated_at timestamptz,
   section_id int references section(id) not null,
   next_page_component_id int references page_component(id)
);

CREATE TABLE page_component_translation
(
   id serial primary key,
   language varchar(50),
   device varchar(50),
   first_title varchar(255),
   second_title varchar(255),
   short_description varchar(255),
   long_description varchar(255),
   created_at timestamptz not null,
   updated_at timestamptz,
   page_component_id int references page_component(id) not null
);

INSERT INTO section (code) VALUES ('problem'), ('secure-myself');
