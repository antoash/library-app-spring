CREATE TABLE IF NOT EXISTS Entries (
   studentId INT NOT NULL,
   name varchar(300) NOT NULL,
   branch varchar(20) NOT NULL,
   entryTime timestamp NOT NULL,
   exitTime timestamp NOT NULL,
   booksBorrowed INT NOT NULL,
   PRIMARY KEY (studentId)
);