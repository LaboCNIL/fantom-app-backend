ALTER TABLE page_component
DROP COLUMN image;

ALTER TABLE page_component_translation
ADD COLUMN image text;
