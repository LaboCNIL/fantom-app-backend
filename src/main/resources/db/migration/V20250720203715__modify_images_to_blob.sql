CREATE TABLE image
(
   id bigserial primary key,
   mime_type varchar(50) not null,
   content bytea not null,
   hash varchar(255) not null,
   created_at timestamptz not null,
   updated_at timestamptz
);

alter table page_component_translation add column image_id bigint references image(id);

-- Migration des données existantes des images base64 vers la nouvelle table image
DO $$
DECLARE
    rec RECORD;
    image_mime_type varchar(50);
    image_content bytea;
    image_hash varchar(255);
    new_image_id bigint;
BEGIN
    -- Parcourir toutes les lignes avec des images existantes
    FOR rec IN 
        SELECT id, image 
        FROM page_component_translation 
        WHERE image IS NOT NULL 
          AND image LIKE 'data:%base64,%'
    LOOP
        -- Extraire le mime_type (ex: "image/png" depuis "data:image/png;base64,...")
        image_mime_type := substring(rec.image FROM 'data:([^;]+)');
        
        -- Extraire et décoder le contenu base64
        image_content := decode(substring(rec.image FROM 'base64,(.*)'), 'base64');
        
        -- Calculer le hash MD5 du contenu binaire
        image_hash := md5(image_content);
        
        -- Vérifier si cette image existe déjà (éviter les doublons)
        SELECT id INTO new_image_id
        FROM image 
        WHERE hash = image_hash;
        
        -- Si l'image n'existe pas encore, l'insérer
        IF new_image_id IS NULL THEN
            INSERT INTO image (mime_type, content, hash, created_at)
            VALUES (image_mime_type, image_content, image_hash, now())
            RETURNING id INTO new_image_id;
        END IF;
        
        -- Mettre à jour la référence dans page_component_translation
        UPDATE page_component_translation 
        SET image_id = new_image_id 
        WHERE id = rec.id;
        
    END LOOP;
END $$;