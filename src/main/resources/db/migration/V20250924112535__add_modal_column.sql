ALTER TABLE page_component
ADD COLUMN modal_page_component_id int references page_component(id);
