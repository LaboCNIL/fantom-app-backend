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
   position int not null,
   image varchar(255),
   created_at timestamptz not null,
   updated_at timestamptz,
   section_id int references section(id),
   previous_page_component_id int references page_component(id),
   next_page_component_id int references page_component(id)
);

CREATE TABLE page_component_translation
(
   id serial primary key,
   content varchar(255) not null,
   created_at timestamptz not null,
   updated_at timestamptz,
   page_component_id int references page_component(id) not null
);
