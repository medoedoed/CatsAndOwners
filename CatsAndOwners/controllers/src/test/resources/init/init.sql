INSERT INTO owners (ownerid, owner_name, owner_birth_date)
VALUES (6, 'owner1', '1999-01-01'),
       (7, 'owner2', '1999-02-01');

INSERT INTO cat_colors (colorid, color_name)
VALUES (6, 'white'),
       (7, 'black');

INSERT INTO cats (catid, cat_name, ownerid, color_colorid, cat_breed, cat_birth_date)
VALUES (6, 'cat1', 6, 7, 'breed1', '2007-01-01'),
       (7, 'cat2', 7, 6, 'breed2', '2007-02-01');