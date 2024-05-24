delete
from friendship
where 1 = 1;
delete
from cat
where 1 = 1;

INSERT INTO Cat(id, name, BIRTHDAY_DATE, breed, color, owner)
VALUES (1, 'dd', PARSEDATETIME('26 Jul 2016, 05:15:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'), 'persian', 'BLACK', 1);

INSERT INTO Cat(id, name, BIRTHDAY_DATE, breed, color, owner)
VALUES (2, 'dd', PARSEDATETIME('26 Jul 2016, 05:15:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'), 'tiger', 'RED', 1);
