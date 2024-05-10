INSERT INTO owners (id, name,)
VALUES (1, "owner1"),
       (2, "owner2");

INSERT INTO colors (id, name)
VALUES (1, "white"),
       (2, "black");

INSERT INTO cats (id, name, owner_id, color_id)
VALUES (1, "cat1", 1, 2),
       (1, "cat2", 2, 1);