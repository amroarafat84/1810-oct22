/*
2.1 SELECT

A) SELECT * FROM EMPLOYEE;
B) SELECT * FROM  EMPLOYEE WHERE LASTNAME = 'King';
C) SELECT * FROM  EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
*/

/*
2.2 ORDER BY

A) SELECT * FROM ALBUM ORDER BY TITLE DESC;
B) SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;
*/

/* 
2.3 INSERT INTO

A) INSERT INTO Track (TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) VALUES (3504, 'For Those About To Rock (We Salute You)', 1, 1, 1, 'Angus Young, Malcolm Young, Brian Johnson', 343719, 11170334, 0.99);
INSERT INTO Track (TrackId, Name, AlbumId, MediaTypeId, GenreId, Milliseconds, Bytes, UnitPrice) VALUES (3505, 'Balls to the Wall', 2, 2, 1, 342562, 5510424, 0.99);
B) INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (9, 'King', 'Robert', 'IT Staff', 6, TO_DATE('1970-5-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2004-1-2 00:00:00','yyyy-mm-dd hh24:mi:ss'), '590 Columbia Boulevard West', 'Lethbridge', 'AB', 'Canada', 'T1K 5N8', '+1 (403) 456-9986', '+1 (403) 456-8485', 'robert@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10, 'Callahan', 'Laura', 'IT Staff', 6, TO_DATE('1968-1-9 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2004-3-4 00:00:00','yyyy-mm-dd hh24:mi:ss'), '923 7 ST NW', 'Lethbridge', 'AB', 'Canada', 'T1H 1Y8', '+1 (403) 467-3351', '+1 (403) 467-8772', 'laura@chinookcorp.com');
C) INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) VALUES (60, 'Lu�s', 'Gon�alves', 'Embraer - Empresa Brasileira de Aeron�utica S.A.', 'Av. Brigadeiro Faria Lima, 2170', 'S�o Jos� dos Campos', 'SP', 'Brazil', '12227-000', '+55 (12) 3923-5555', '+55 (12) 3923-5566', 'luisg@embraer.com.br', 3);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (61, 'Leonie', 'K�hler', 'Theodor-Heuss-Stra�e 34', 'Stuttgart', 'Germany', '70174', '+49 0711 2842222', 'leonekohler@surfeu.de', 5);
*/

/*
2.4 UPDATE

A) UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
B) UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival'; 
*/

/*
2.5 LIKE

A) SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
*/

/*
2.6 BETWEEN

A) SELECT * FROM INVOICE WHERE TOTAL > 15 AND TOTAL < 30;
B) SELECT * FROM EMPLOYEE WHERE HIREDATE > '01-JUN-03' AND HIREDATE < '01-MAR-04';

*/

/*
2.7 DELETE

A) DELETE FROM INVOICELINE WHERE INVOICEID IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID IN ( SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'));
DELETE FROM INVOICE WHERE CUSTOMERID IN ( SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'; 
*/

/*
3.1 System Defined Functions

A)CREATE OR REPLACE FUNCTION GETTIME
RETURN CHAR
AS CURRENTTIME CHAR(8);
BEGIN
    SELECT TO_CHAR(sysdate,'HH12:MI AM') INTO CURRENTTIME FROM DUAL;
    RETURN(CURRENTTIME);
END;
/
B) CREATE OR REPLACE FUNCTION GETMEDIALENGTH(MEDIATYPEIDTARGET IN NUMBER)
RETURN NUMBER
AS MEDIATYPELENGTH NUMBER;
BEGIN
    SELECT LENGTH(NAME) INTO MEDIATYPELENGTH FROM MEDIATYPE WHERE MEDIATYPEID = MEDIATYPEIDTARGET;
    RETURN(MEDIATYPELENGTH);
END;
/
*/

/*
3.2 System Defined Aggregate Functions 

A) CREATE OR REPLACE FUNCTION AVGINVOICETOTAL
RETURN NUMBER
AS AVGTOTAL NUMBER(6,2);
BEGIN
    SELECT AVG(TOTAL) INTO AVGTOTAL FROM INVOICE;
    RETURN(AVGTOTAL);
END;
/
B) CREATE OR REPLACE FUNCTION GETMOSTEXPENSIVETRACKID
RETURN NUMBER
AS TRACKIDRETURN NUMBER;
BEGIN
    SELECT TRACKID INTO TRACKIDRETURN FROM (SELECT * FROM TRACK ORDER BY UNITPRICE DESC) WHERE ROWNUM = 1;
    RETURN(TRACKIDRETURN);
END;
/
*/

/*
3.3 User Defined Functions

A) CREATE OR REPLACE FUNCTION AVGINVOICELINETOTAL
RETURN NUMBER
AS AVGTOTAL NUMBER(6,2);
BEGIN
    SELECT AVG(UNITPRICE) INTO AVGTOTAL FROM INVOICELINE;
    RETURN(AVGTOTAL);
END;
/
*/

/*
3.4 User Defined Table Valued Functions
A) CREATE OR REPLACE FUNCTION GETEMPLYEESBORNAFTER1968
RETURN SYS_REFCURSOR
AS my_cursor SYS_REFCURSOR;
BEGIN
    OPEN my_cursor FOR SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= '01-JAN-1969';
    RETURN my_cursor;
END;
/
*/

/*
4.1 Basic Stored Procedure 

A) CREATE OR REPLACE PROCEDURE GETEMPLOYEENAMES(cursor_for_out OUT SYS_REFCURSOR )
AS
BEGIN   
OPEN cursor_for_out FOR SELECT FIRSTNAME,LASTNAME FROM EMPLOYEE; 
END;
/
*/

/*
4.2 Stored Procedure Input Parameters 

A) CREATE OR REPLACE PROCEDURE UPDATEEMPLOYEE
(E_ID IN NUMBER, PN IN VARCHAR)
AS
BEGIN   
    UPDATE EMPLOYEE SET PHONE = PN WHERE EMPLOYEEID = E_ID;
    COMMIT;
END;
/
B) CREATE OR REPLACE PROCEDURE GETMANAGER
(E_ID IN NUMBER, M_ID OUT VARCHAR)
AS
BEGIN   
    SELECT REPORTSTO INTO M_ID FROM EMPLOYEE WHERE EMPLOYEEID = E_ID;
END;
/
*/

/*
4.3  Stored Procedure Output Parameters

A) CREATE OR REPLACE PROCEDURE GETCUSTOMERDATA
(CID IN NUMBER, FN OUT VARCHAR2, LN OUT VARCHAR2, CMP OUT VARCHAR2)
AS
BEGIN   
    SELECT FIRSTNAME,LASTNAME,COMPANY INTO FN,LN,CMP
    FROM CUSTOMER WHERE CUSTOMERID = CID;
END;
/
*/

/*
5.0 Transactions

A) create or replace PROCEDURE DELETEINVOICE
(INID IN NUMBER) AS
BEGIN
  DELETE FROM INVOICELINE WHERE INVOICEID = INID;
  DELETE FROM INVOICE WHERE INVOICEID = INID;
  COMMIT;
END;
/
B) CREATE OR REPLACE PROCEDURE ADDCUSTOMER2
(CID IN NUMBER,
 FN IN VARCHAR2,
 LN IN VARCHAR2,
 CEM IN VARCHAR2) AS
BEGIN
  INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES (CID, FN, LN, NULL, NULL,NULL,NULL, NULL, NULL, NULL, NULL, CEM, NULL);
  COMMIT;
END;
/ 
*/

/*
6.1 AFTER/FOR
A) CREATE OR REPLACE TRIGGER BI_EMPLOYEE
BEFORE INSERT ON EMPLOYEE 
FOR EACH ROW
BEGIN 
    DBMS_OUTPUT.PUT('INSERTING ROW INTO EMPLOYEE TABLE');
END;
/
B) CREATE OR REPLACE TRIGGER AU_ALBUM
AFTER UPDATE ON ALBUM 
FOR EACH ROW
BEGIN 
    DBMS_OUTPUT.PUT('UPDATED ROW IN ALBUM TABLE');
END;
/
C) CREATE OR REPLACE TRIGGER AD_CUSTOMER
AFTER DELETE ON CUSTOMER 
FOR EACH ROW
BEGIN 
    DBMS_OUTPUT.PUT('DELETED ROW IN CUSTOMER TABLE');
END;
/
*/

/*
7.1 INNER

A) SELECT CUSTOMER.FIRSTNAME,CUSTOMER.LASTNAME, INVOICE.INVOICEID FROM CUSTOMER
JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
*/

/*
7.2 OUTER

A) SELECT CUSTOMER.CUSTOMERID,CUSTOMER.FIRSTNAME,CUSTOMER.LASTNAME,INVOICE.INVOICEID,INVOICE.TOTAL 
FROM CUSTOMER
LEFT JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
*/

/*
7.3 RIGHT

A)SELECT alb.TITLE, art.NAME FROM ALBUM alb
RIGHT JOIN ARTIST art ON art.ARTISTID = alb.ARTISTID;
*/

/*
7.4 CROSS

A) SELECT alb.TITLE, art.NAME FROM ALBUM alb, ARTIST art
ORDER BY art.NAME;
*/

/*
7.5 SELF

A) SELECT e1.FIRSTNAME,e1.LASTNAME,e2.FIRSTNAME,e2.LASTNAME FROM EMPLOYEE e1
LEFT JOIN EMPLOYEE e2 ON e1.REPORTSTO = e2.EMPLOYEEID;
*/

/*
7.6 Complicated Join assignment 

A) SELECT 
INVOICELINE.INVOICELINEID,
INVOICELINE.INVOICEID,
INVOICE.BILLINGADDRESS,
CUSTOMER.FIRSTNAME,
EMPLOYEE.FIRSTNAME,
TRACK.NAME,
GENRE.NAME,
MEDIATYPE.NAME,
ALBUM.TITLE,
ARTIST.NAME,
PLAYLISTTRACK.PLAYLISTID
FROM INVOICELINE
JOIN INVOICE ON INVOICELINE.INVOICEID = INVOICE.INVOICEID
JOIN CUSTOMER ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID
JOIN EMPLOYEE ON CUSTOMER.SUPPORTREPID = EMPLOYEE.EMPLOYEEID
JOIN TRACK ON INVOICELINE.TRACKID = TRACK.TRACKID
JOIN GENRE ON TRACK.GENREID = GENRE.GENREID
JOIN MEDIATYPE ON TRACK.MEDIATYPEID = MEDIATYPE.MEDIATYPEID
JOIN ALBUM ON TRACK.ALBUMID = ALBUM.ALBUMID
JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID
JOIN PLAYLISTTRACK ON TRACK.TRACKID = PLAYLISTTRACK.TRACKID;
*/

/*
9.0 Administration 


*/
