# order-out-springboot

# to initialize db


INSERT INTO PRODUCT (ID, NAME, PICTURE_URL, PRICE) VALUES ('1', '7aleva Jebneh', 'assets/images/img-jebneh.jpg', '0.41');
INSERT INTO PRODUCT (ID, NAME, PICTURE_URL, PRICE) VALUES ('2', '7aleva Ba6a6a', 'assets/images/img-ba6a6a.jpg', '0.36');
INSERT INTO PRODUCT (ID, NAME, PICTURE_URL, PRICE) VALUES ('3', '7aleva La7meh', 'assets/images/img-la7meh.jpg', '0.42');
INSERT INTO PRODUCT (ID, NAME, PICTURE_URL, PRICE) VALUES ('4', 'Zateij', 'assets/images/img-zateij.jpg', '0.20');
INSERT INTO PRODUCT (ID, NAME, PICTURE_URL, PRICE) VALUES ('5', 'Laqum', 'assets/images/img-laqum.jpg', '0.18');

insert into configuration (property, value) values ('order_deadline', '2019/04/24 04:00 pm');

update product set PICTURE_URL='assets/images/img-jebneh.jpg' where id='1';
update product set PICTURE_URL='assets/images/img-ba6a6a.jpg' where id='2';
update product set PICTURE_URL='assets/images/img-la7meh.jpg' where id='3';
update product set PICTURE_URL='assets/images/img-zateij.jpg' where id='4';
update product set PICTURE_URL='assets/images/img-laqum.jpg' where id='5';
