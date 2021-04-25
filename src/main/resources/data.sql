INSERT INTO section(name) VALUES ('Init Section 1');

INSERT INTO category(name, color, open_count, section_id) VALUES ('Init Category1', '#000000', 1, 1);
INSERT INTO category(name, color, open_count, section_id) VALUES ('Init Category2', '#000000', 2, 1);
INSERT INTO category(name, color, open_count, section_id) VALUES ('Init Category3', '#000000', 3, 1);
INSERT INTO category(name, color, open_count, section_id) VALUES ('Init Category4', '#000000', 4, 1);

INSERT INTO bookmark(address, alias, color, category_id, open_count) VALUES ('address1-1', 'a', '#000000', 1, 0);
INSERT INTO bookmark(address, alias, color, category_id, open_count) VALUES ('address1-2', 'b', '#000000', 2, 0);
INSERT INTO bookmark(address, alias, color, category_id, open_count) VALUES ('address3-1', 'c', '#000000', 1, 0);
INSERT INTO bookmark(address, alias, color, category_id, open_count) VALUES ('address2-1', 'd', '#000000', 1, 0);