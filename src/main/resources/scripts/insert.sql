SET FOREIGN_KEY_CHECKS = 0;
truncate table user;
truncate table book;
SET FOREIGN_KEY_CHECKS = 1;

insert into user(id, email, password, username, is_locked) values
(200, "test@email.com", "password","username", 0),
(201, "semicolon@email.com", "password","username", 0),
(202, "xplorer@email.com", "password","username", 0);

insert into book(book_id, id, title, user_id) values
(100, 2, 'Home alone', 200),
(101, 3, 'Independent', 200);

