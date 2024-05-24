delete
from users
where 1 = 1;
delete
from friendship
where 1 = 1;
delete
from cat
where 1 = 1;
delete
from owner
where 1 = 1;
INSERT INTO Owner(id, name, BIRTHDAY_DATE)
VALUES (1, 'dd', PARSEDATETIME('26 Jul 2016, 05:15:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'));

INSERT INTO Owner(id, name, BIRTHDAY_DATE)
VALUES (2, 'dd', PARSEDATETIME('26 Jul 2016, 05:15:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'));

INSERT INTO Users(id, username, password, role, owner_id)
VALUES (1, 'neaboba', '123', 1, 1);

INSERT INTO Users(id, username, password, role, owner_id)
VALUES (2, 'neaboba2', '123', 0, 2);

INSERT INTO Cat(id, name, BIRTHDAY_DATE, breed, color, owner_id)
VALUES (1, 'dd', PARSEDATETIME('26 Jul 2016, 05:15:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'), 'persian', 'BLACK', 1);

INSERT INTO Cat(id, name, BIRTHDAY_DATE, breed, color, owner_id)
VALUES (2, 'dd', PARSEDATETIME('26 Jul 2016, 05:15:58 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'), 'tiger', 'RED', 1);
