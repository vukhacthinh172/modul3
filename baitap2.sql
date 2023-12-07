create table Customer (
cID int primary key auto_increment,
cName varchar(50) ,
cAge int 
);
create table Product (
pID int primary key auto_increment,
pName varchar(50) ,
pPrice  double check (pPrice > 0)   
);
create table Orderdetail (
oId int not null ,
pId int not null ,
odQTY int ,
  foreign key (oId) references `Order`(oId),
  foreign key (pId) references product(pId),
  PRIMARY KEY (oId, pId)
);
create table `Order` (
oId int primary key ,
cId int,
oDate int,
oTotalPrice int,
 foreign key (cId) references customer(cId)
);
INSERT INTO Customer (cName, cAge) 
values
    ('Nguyễn Văn A', 25),
    ('Trần Thị B', 30),
    ('Lê Minh C', 22);
INSERT INTO Product (pName, pPrice) 
values
    ('Sản phẩm A', 50.5),
    ('Sản phẩm B', 30.2),
    ('Sản phẩm C', 45.0);
INSERT INTO `Order` (oId, cId, oDate, oTotalPrice) 
values
    (1, 1, '2023', 150),
    (2, 2, '2023', 80),
    (3, 3, '2023', 120);
INSERT INTO OrderDetail (oId, pId, odQTY) 
values
    (1, 1, '5 '),
    (1, 2, '2 '),
    (2, 3, '10 '),
    (3, 1, '3 ');
