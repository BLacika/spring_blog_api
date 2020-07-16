INSERT INTO CATEGORIES(title, created_at, updated_at) VALUES('Java', now(), now());
INSERT INTO CATEGORIES(title, created_at, updated_at) VALUES('PHP', now(), now());
INSERT INTO CATEGORIES(title, created_at, updated_at) VALUES('C#', now(), now());
INSERT INTO CATEGORIES(title, created_at, updated_at) VALUES('Javascript', now(), now());

INSERT INTO POSTS(title, author, created_at, updated_at, content, status, tags, category_id)
    VALUES('Post 1', 'Author 1', now(), now(), 'Content 1', 'published', 'post1, post 1', 1);
INSERT INTO POSTS(title, author, created_at, updated_at, content, status, tags, category_id)
    VALUES('Post 2', 'Author 1', now(), now(), 'Content 2', 'unpublished', 'post2, post 2', 4);
INSERT INTO POSTS(title, author, created_at, updated_at, content, status, tags, category_id)
    VALUES('Post 3', 'Author 2', now(), now(), 'Content 3', 'published', 'post3, post 3', 1);
INSERT INTO POSTS(title, author, created_at, updated_at, content, status, tags, category_id)
    VALUES('Post 4', 'Author 2', now(), now(), 'Content 4', 'published', 'post4, post 4', 2);

INSERT INTO USERS(username, password, email, roles, created_at, updated_at)
    VALUES('superuser', 'superpass', 's_user@email.com', 'admin, user', now(), now());
INSERT INTO USERS(username, password, email, roles, created_at, updated_at)
    VALUES('admin', 'adminpass', 'admin@email.com', 'admin', now(), now());
INSERT INTO USERS(username, password, email, roles, created_at, updated_at)
    VALUES('user1', 'password1', 'user1@email.com', 'user', now(), now());
