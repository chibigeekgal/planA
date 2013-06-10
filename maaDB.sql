DROP TABLE Person;
DROP TABLE Question;
DROP TABLE Answer;

/*The table contains the use's login and password*/
CREATE TABLE Person (
  Login text not null Primary Key,
  Pass_word text not null,
  Points integer not null 
);

/*The table contains the questions asked by each user*/
CREATE TABLE Question (
  Question_index integer not null Primary Key,
  Question_title text not null,
  Login text not null,
  Content text not null,
  Best_answer integer not null
);

/*The table contains the answer to question*/
CREATE TABLE Answer (
  Question_index integer not null,
  Answer_index integer not null,
  Login text not null,
  Answer_content text,
  Primary Key (Question_index, Answer_index)
);



---------------------------------------------------------
INSERT INTO Person VALUES('dz1611','123', 10);
INSERT INTO Question VALUES(1,'simple','dz1611','1+2=?',0);
INSERT INTO Answer VALUES(1,2,'dr411','1+2=3');
