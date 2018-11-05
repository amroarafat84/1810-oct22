-- Section 2.1

SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' and REPORTSTO IS NULL;

-- Section 2.2

SELECT * FROM ALBUM ORDER BY TITLE DESC;

SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;

-- Section 2.3

INSERT INTO GENRE
VALUES (26, 'SWAGMETAL');

INSERT INTO GENRE
VALUES (27, 'MATH JAZZ');

INSERT INTO EMPLOYEE
VALUES(10, 'Tara', 'Dillon', 'IT STAFF', 6, '14-FEB-96', '10-APR-2018', '789 SWAG ST', 
'THUNDERMOUNTAIN', 'AB', 'CANADA', 'T1H 7OH', '+1 (703) 345-6879', '+1 (789) 434-1039', 'dillon@chinookcorp.com');

INSERT INTO EMPLOYEE
VALUES(11, 'Liluzi', 'Vert', 'RAPPER', 7, '14-FEB-96', '10-APR-2018', '789 SWAG ST', 
'THUNDERMOUNTAIN', 'AB', 'CANADA', 'T1H 7OH', '+1 (703) 345-0879', '+1 (789) 009-1039', 'liluzivert@chinookcorp.com');

INSERT INTO CUSTOMER
VALUES (60, 'Tom', 'Selleck', 'A company Inc.', '1234 Skirr skirr Ave', 'DT',
'USA', 'MARS', '22049', '+1 (123)453-9506', '+1 (345)347-6934', 'tom@company.com', 3);

INSERT INTO CUSTOMER
VALUES (61, 'John', 'Lennon', 'Abc company Inc.', '124 Skirr skirr Ave', 'VA',
'USA', 'MARS', '22949', '+1 (123)453-9546', '+1 (342)347-6934', 'lennon@company.com', 3);

--Sections 2.4

UPDATE CUSTOMER SET FIRSTNAME = 'ROBERT', LASTNAME = 'WALTER' WHERE FIRSTNAME = 'Aaron';

UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

-- Section 2.5

SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

-- Section 2.6

SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

-- Section 2.7
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'; 

-- Section 3.1 

SELECT SYSTIMESTAMP FROM DUAL;

SELECT LENGTH(NAME) FROM MEDIATYPE;

--Section 3.2

SELECT AVG(TOTAL) FROM INVOICE;

SELECT MAX(UNITPRICE) FROM TRACK;

--Section 3.3 

CREATE OR REPLACE FUNCTION AVERAGEPRICE 
RETURN NUMBER
IS
    AVERAGE NUMBER(3,2);
BEGIN 
    SELECT AVG(UNITPRICE) INTO AVERAGE FROM INVOICELIINE;
    RETURN AVERAGE;
END;
/

--Section 3.4 
CREATE OR REPLACE FUNCTION EMPLOYEEBIRTHDAY
    RETURN SYS_REFCURSOR
    AS cursor SYS_REFCURSOR;
BEGIN
    OPEN cursor FOR SELECT FIRSTNAME, BIRTHDATE FROM EMPLOYEE
    WHERE BIRTHDATE >= TO_DATE('1968', 'YYYY');
    RETURN cursor;
END;
/

--Section 4.1

CREATE OR REPLACE PROCEDURE FIRSTLAST(cursor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursor FOR
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/

--Section 4.2

CREATE OR REPLACE PROCEDURE UPEMPLOYEEADDRESS(
    EMPID NUMBER,
    NEW_ADDRESS VARCHAR2,
    NEW_CITY VARCHAR2)
AS
BEGIN
    UPDATE EMPLOYEE
    SET ADDRESS = NEW_ADDRESS,
    CITY = NEW_CITY
    WHERE EMPLOYEEID = EMPID;
END;
/

CREATE OR REPLACE PROCEDURE GETMANAGERS(
    EMPID NUMBER,
    cursor OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN cursor FOR 
    SELECT REPORTSTO FROM EMPLOYEE WHERE EMPLOYEEID = EMPID;
END;
/

--Section 4.3

CREATE OR REPLACE PROCEDURE CUSTOMERNAMECOMPANY(
    CUSTID NUMBER,
    CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN CURSOR FOR
    SELECT FIRSTNAME,COMPANY FROM CUSTOMER WHERE CUSTOMERID = CUSTID;
END;
/

--Section 5.0

CREATE OR REPLACE PROCEDURE DELETEINVOICE(INVOID IN NUMBER)
AS
BEGIN
    DELETE FROM INVOICELINE WHERE INVOICEID = INVOID;
    DELETE FROM INVOICE WHERE INVOICEID = INVOID;
    COMMIT;
END;
/

--Section 6.1 

CREATE OR REPLACE TRIGGER AFTERTRIGGER
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
    WHEN (new.employeeid > 1)
BEGIN
    INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, 
    HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
    VALUES (:NEW.EMPLOYEEID, :NEW.LASTNAME, :NEW.FIRSTNAME, :NEW.TITLE, :NEW.REPORTSTO, 
    :NEW.BIRTHDATE, :NEW.HIREDATE, :NEW.ADDESS, :NEW.CITY, :NEW.STATE, :NEW.COUNTRY, 
    :NEW.POSTALCODE, :NEW.PHONE, :NEW.FAX, :NEW.EMAIL);
END;
/

CREATE OR REPLACE TRIGGER ALBUMTABLE
AFTER UPDATE ON ALBUM
FOR EACH ROW
    WHEN(NEW.ALBUM > 1)
BEGIN
    INSERT INTO ALBUM(ALBUMID, TITLE, ARTISTID)
    VALUES(:NEW.ALBUMID, :NEW.TITLE, :NEW.ARTISTID);
END;
/

CREATE OR REPLACE TRIGGER DELETECUSTOMER
AFTER DELETE ON CUSTOMER
FOR EACH ROW
DECLARE CUSTOMERID NUMBER(10);
BEGIN
    DELETE FROM CUSTOMER WHERE CUSTOMERID = 1;
END;

--Section 7.1
SELECT A.CUSTOMERID, A.FIRSTNAME, A.LASTNAME, B.INVOICEID
FROM CUSTOMER A
INNER JOIN INVOICE B ON A.CUSTOMERID = B.CUSTOMERID;

--Section 7.2 

SELECT A.CUSTOMERID, A.FIRSTNAME, A.LASTNAME, B.INVOICEID, B.TOTAL
FROM CUSTOMER A
FULL OUTER JOIN INVOICE B ON A.CUSTOMERID = B.CUSTOMERID;

--Section 7.3 

SELECT A.NAME, B.TITLE
FROM ARTIST A
RIGHT JOIN ALBUM B ON A.ARTISTID = B.ARTISTID;

--Section 7.4

SELECT B.NAME, A.TITLE 
FROM ALBUM A
CROSS JOIN ARTIST B
ORDER BY B.NAME ASC;

--Section 7.5

SELECT A.LASTNAME, B.LASTNAME
FROM EMPLOYEE A
JOIN EMPLOYEE B ON A.REPORTSTO = B.EMPLOYEEID;

--Section 7.6

SELECT *
FROM INVOICELINE A
JOIN INVOICE B ON A.INVOICEID = B.INVOICEID
JOIN CUSTOMER C ON B.CUSTOMERID = C.CUSTOMERID
JOIN EMPLOYEE D ON C.SUPPORTREPID = D.EMPLOYEEID
JOIN TRACK E ON A.TRACKID = E.TRACKID
JOIN GENRE F ON E.GENREID = F.GENREID
JOIN MEDIATYPE G ON E.MEDIATYPEID = G.MEDIATYPEID
JOIN PLAYLISTTRACK H ON E.TRACKID = H.TRACKID
JOIN PLAYLIST I ON H.PLAYLISTID = I.PLAYLISTID
JOIN ALBUM J ON E.ALBUMID = J.ALBUMID
JOIN ARTIST K ON J.ARTISTID = K.ARTISTID;

