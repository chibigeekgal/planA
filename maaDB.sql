DROP TABLE Person;

/*The table contains the use's login and password*/
CREATE TABLE Person (
  Login text unique not null,
  Pass_word text not null 
);

---------------
INSERT INTO Person VALUES('dz1611','123');

