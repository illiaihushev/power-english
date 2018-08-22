delete book_excerpts;
delete books;
delete users;

insert into users (user_id, login, passphrase, registration_date)
values (user_seq.nextval,'randyblythe147', 'logLog147', current_date);

insert into books (book_id, user_id, book_name, author, release_date)
values (book_seq.nextval, 1, '1984', 'George Orwell', to_date('1949', 'YYYY'));

insert into book_excerpts(book_excerpt_id, book_id, page, chapter, original, translation)
values (book_excerpt_seq.nextval, 1, null, 2,
'But, he realized, even in his panic he hadn''t wanted to smudge the creamy paper by shutting the book, while the ink wet.',
'Нет, сообразил он, стало жалко пачкать кремовую бумагу, даже в панике не захотел  захлопнуть дневник на непросохшей странице.');

insert into book_excerpts(book_excerpt_id, book_id, page, chapter, original, translation)
values (book_excerpt_seq.nextval, 1, null, 2,
'A colourless, crushed-looking woman with wispy hair and a lined face, was standing outside.',
'Бесцветная, подавленная женщина с жидкими волосами и морщинистым лицом стояла у двери.');
commit;

