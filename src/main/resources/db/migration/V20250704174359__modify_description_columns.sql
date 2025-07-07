ALTER TABLE page_component_translation
   RENAME COLUMN short_description to description;

ALTER TABLE page_component_translation
   ALTER COLUMN description TYPE text;
