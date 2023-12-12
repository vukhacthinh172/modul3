use demo ;
create table Products (
Id int,
productCode int ,
productName varchar(20),
productPrice int ,
productAmount bit ,
productDescription text ,
productStatus text 
);
INSERT INTO Products (Id, productCode, productName, productPrice, productAmount, productDescription, productStatus)
VALUES
  (1, 1001, 'Product A', 50, 1, 'Description A', 'Active'),
  (2, 1002, 'Product B', 75, 0, 'Description B', 'Inactive'),
  (3, 1003, 'Product C', 30, 1, 'Description C', 'Active'),
  (4, 1004, 'Product D', 90, 0, 'Description D', 'Active'),
  (5, 1005, 'Product E', 60, 1, 'Description E', 'Inactive');
create unique index index_product on products (productCode);
create index ix_product on products (productName ,productPrice) ;
explain select *from products where productCode = 'your_product_code';
-- tạo view  
create view view_pro as 
select p. productCode,p.productName, p.productPrice 
from products p ;
-- sửa view  
alter view view_pro as 
select productCode , productAmount 
from products ;
-- xoá view 
 drop view view_pro;
 -- tạo  store procedure 
-- Đặt định giới hạn cho delimiter
DELIMITER //
-- Tạo stored procedure
CREATE PROCEDURE GetAllProducts()
BEGIN
    -- Chọn tất cả thông tin từ bảng Products
    SELECT * FROM products;
END //
-- Khôi phục delimiter về giá trị mặc định (;)
DELIMITER ;
-- Gọi stored procedure
CALL GetAllProducts;
DELIMITER //
CREATE PROCEDURE addProduct(
  IN p_productCode INT,
    IN p_productName VARCHAR(20),
    IN p_productPrice INT,
    IN p_productAmount BIT,
    IN p_productDescription TEXT,
    IN p_productStatus TEXT) 
begin
 INSERT INTO Products (productCode, productName, productPrice, productAmount, productDescription, productStatus)
    VALUES (p_productCode, p_productName, p_productPrice, p_productAmount, p_productDescription, p_productStatus);
end //
DELIMITER ;
CALL addProduct(1006, 'Product F', 40, 1, 'Description F', 'Active');
DELIMITER //
-- Tạo stored procedure sửa thông tin sản phẩm theo ID
CREATE PROCEDURE UpdateProduct(
    IN p_productCode INT,
    IN p_productName VARCHAR(20),
    IN p_productPrice INT,
    IN p_productAmount BIT,
    IN p_productDescription TEXT,
    IN p_productStatus TEXT
)
BEGIN
    -- Kiểm tra xem ID tồn tại trong bảng hay không
    DECLARE idExists INT;
    SELECT COUNT(*) INTO idExists FROM Products WHERE productCode = p_productCode;

    -- Nếu ID tồn tại, thực hiện cập nhật thông tin sản phẩm
    IF idExists > 0 THEN
        UPDATE Products
        SET
            productName = p_productName,
            productPrice = p_productPrice,
            productAmount = p_productAmount,
            productDescription = p_productDescription,
            productStatus = p_productStatus
        WHERE
            productCode = p_productCode;
    ELSE
        -- Nếu ID không tồn tại, bắt lỗi
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Product with the specified ID does not exist.';
    END IF;
END //

DELIMITER ;
CALL UpdateProduct(1001, 'New Name', 60, 1, 'New Description', 'Updated');

