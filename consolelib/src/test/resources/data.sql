insert into author(id,fullname) values (1,'Orwell'),(2,'Bronte'),(3,'Dart');
insert into genre(id,genrename) values (1,'Science Fiction'),(2,'Novel'),(3,'Fantasy');
insert into book (id,title, AUTHORID, GENREID) values (1,'1984', 1, 1),(2,'Jane Eyre', 2, 2),(3,'The Professor', 3, 3);
insert into comment(id,commenttext,BOOKID) values (1,'good',1),(2,'bad', 3)