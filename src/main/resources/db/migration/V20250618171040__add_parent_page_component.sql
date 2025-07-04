ALTER TABLE page_component
ADD COLUMN parent_page_component_id int references page_component(id);

UPDATE page_component SET type = 'CARD_1' where type = 'card-1';
