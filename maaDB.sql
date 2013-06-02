DROP TABLE Person;
DROP TABLE Question;
DROP TABLE Answer;

/*The table contains the use's login and password*/
CREATE TABLE Person (
  Login text unique not null,
  Pass_word text not null,
  Points integer not null 
);

/*The table contains the questions asked by each user*/
CREATE TABLE Question (
  Question_index integer unique not null,
  Login text unique not null,
  Content text not null
);

/*The table contains the answer to question*/
CREATE TABLE Answer (
  Question_index integer unique not null,
  Login text unique not null,
  Answer_content text
);

---------------------------------------------------------
INSERT INTO Person VALUES('dz1611','123', 10);
INSERT INTO Question VALUES(0001,'dz1611','1+2=?');
INSERT INTO Answer VALUES(0001,'dr411','1+2=3');
