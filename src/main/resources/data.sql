INSERT INTO FACULTY (NAME) VALUES ('BEST FACULTY');

INSERT INTO DEPARTMENT (NAME, FACULTY_ID)VALUES ('BEST DEPARTMENT', 1);

INSERT INTO TEACHER (NAME, SURNAME, ADDRESS, POSITION, DEPARTMENT_ID)
  VALUES ('PETRO', 'IVANENKO', 'KIEV', 'PROFESSOR', 1),
         ('SERGEY', 'TEACHER', 'KIEV', 'ASSISTANT', 1);

INSERT INTO SUBJECT (NAME, DEPARTMENT_ID)
  VALUES ('MATH', 1),
         ('HISTORY', 1),
         ('CHEMISTRY', 1);

INSERT INTO GROUPS (NAME, COURSE, DEPARTMENT_ID) VALUES ('SR-01', 1,  1), ('SR-02', 2,  1);

INSERT INTO STUDENT (CARD_NUMBER, NAME, SURNAME, ADDRESS, GROUP_ID)
 VALUES ('123A','Igor0', 'Igorenko0', 'Kiev', 1),
  ('123B','Igor1', 'Igorenko1', 'Kiev', 1),
  ('124B', 'Ivan', 'Ivanenko', 'Lviv', 2),
  ('122A', 'Vasiliy', 'Poroshenko', 'Moscow', 1);

INSERT INTO LECTURE_HALL (name) VALUES ('11-A');

INSERT INTO SCHEDULE (location, SUBJECT_ID, GROUP_ID, TEACHER_ID, DATE)
  VALUES (1, 1, 1, 1 , '10-02-2018');

INSERT INTO TEACHER_SUBJECT (teacher_id, subject_id) VALUES (1, 1);
