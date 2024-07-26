CREATE VIEW scenes_View AS
SELECT s.id AS scene_id, s.description AS scene_description, s.minutes, s.scene_place, s.budget, f.title AS film_title
FROM scene s
         JOIN film f ON s.film_id = f.id;


CREATE VIEW characters_with_scene AS
SELECT c.*, s.description AS scene_description
FROM characters c
         JOIN scene s ON c.scene_id = s.id;