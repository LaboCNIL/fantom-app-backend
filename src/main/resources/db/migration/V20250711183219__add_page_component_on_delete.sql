alter table page_component_translation
   drop constraint page_component_translation_page_component_id_fkey;

alter table page_component_translation
   add foreign key (page_component_id) references page_component(id)
      on delete cascade;

alter table page_component
   drop constraint page_component_next_page_component_id_fkey;

alter table page_component
   add foreign key (next_page_component_id) references page_component(id)
      on delete set null;

alter table page_component
   drop constraint page_component_parent_page_component_id_fkey;

alter table page_component
   add foreign key (parent_page_component_id) references page_component(id)
      on delete set null;
