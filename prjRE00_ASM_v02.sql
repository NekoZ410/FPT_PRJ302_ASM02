-- Intitialize
USE master
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'prjRE00_ASM')
BEGIN
	ALTER DATABASE prjRE00_ASM SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE prjRE00_ASM SET ONLINE;
	DROP DATABASE prjRE00_ASM;
END
GO

CREATE DATABASE prjRE00_ASM;
GO

USE prjRE00_ASM
GO

-- Create tables
-- 1: Users
CREATE TABLE Users(
UserID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
RealName NVARCHAR(100) NOT NULL,
UserName NVARCHAR(50) NOT NULL UNIQUE,
[Password] NVARCHAR(50) NOT NULL,
[Role] NVARCHAR(20) NOT NULL, 
Email NVARCHAR(200) NOT NULL CHECK (Email LIKE '%@%.%') UNIQUE,
CreateTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

-- 2: Categories
CREATE TABLE Categories(
CategoryID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
CategoryName NVARCHAR(200) NOT NULL,
[Description] NVARCHAR(3000)
);
GO

-- 3: Products
CREATE TABLE Products(
ProductID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
ProductName NVARCHAR(200) NOT NULL,
CategoryID INT FOREIGN KEY REFERENCES Categories(CategoryID) ON DELETE CASCADE,
UnitsInStock INT NOT NULL,
UnitPrice FLOAT NOT NULL,
Discount INT DEFAULT 0,
[Image] NVARCHAR(1000) NOT NULL
);
GO

-- 4: Carts
CREATE TABLE Carts(
UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
CartID INT NOT NULL,
ProductID INT NOT NULL FOREIGN KEY REFERENCES Products(ProductID) ON DELETE CASCADE,
Quantity INT,
CONSTRAINT pk_Carts PRIMARY KEY (UserID, CartID)
);
GO

CREATE TRIGGER HandleInsertInCart
ON Carts
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- 1. Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng của người dùng
    UPDATE c
    SET c.Quantity = c.Quantity + i.Quantity
    FROM Carts c
    INNER JOIN inserted i
        ON c.UserID = i.UserID
        AND c.ProductID = i.ProductID;

    -- 2. Nếu sản phẩm chưa có trong giỏ hàng, thêm bản ghi mới với CartID tự động tăng
    INSERT INTO Carts (UserID, CartID, ProductID, Quantity)
    SELECT 
        i.UserID,
        ISNULL(
            (SELECT MAX(c.CartID) FROM Carts c WHERE c.UserID = i.UserID), 
            0
        ) + 1 AS NewCartID,
        i.ProductID,
        i.Quantity
    FROM inserted i
    WHERE NOT EXISTS (
        SELECT 1
        FROM Carts c
        WHERE c.UserID = i.UserID
        AND c.ProductID = i.ProductID
    );
END;
GO

-- 5: Orders
CREATE TABLE Orders(
UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
OrderID INT NOT NULL,
ProductID INT NOT NULL FOREIGN KEY REFERENCES Products(ProductID) ON DELETE CASCADE, --
Quantity INT,
OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_Orders PRIMARY KEY (UserID, OrderID)
);
GO

CREATE TRIGGER InsertInOrderUpdateQuantity
ON Orders
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- 1. Update existing records if the product already exists in the user's cart
    UPDATE o
    SET o.Quantity = o.Quantity + i.Quantity
    FROM Orders o
    INNER JOIN inserted i
        ON o.UserID = i.UserID
        AND o.ProductID = i.ProductID;

    -- 2. Insert new records if the product does not exist in the user's cart
    INSERT INTO Orders (UserID, OrderID, ProductID, Quantity)
    SELECT 
        i.UserID,
        ISNULL(
            (SELECT MAX(o.OrderID) FROM Orders o WHERE o.UserID = i.UserID), 
            0
        ) + 1 AS NewCartID,
        i.ProductID,
        i.Quantity
    FROM inserted i
    WHERE NOT EXISTS (
        SELECT 1
        FROM Orders o
        WHERE o.UserID = i.UserID
        AND o.ProductID = i.ProductID
    );

    -- 3. Update UnitsInStock in Products based on the new quantity added to Orders
    UPDATE Products
    SET UnitsInStock = UnitsInStock - i.Quantity
    FROM Products p
    INNER JOIN inserted i ON p.ProductID = i.ProductID;
END
GO

-- 6: Purchased
CREATE TABLE Purchased(
UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
PurchasedID INT NOT NULL,
ProductID INT NOT NULL FOREIGN KEY REFERENCES Products(ProductID) ON DELETE CASCADE, --
Quantity INT,
PurchasedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
RequiredDate DATETIME DEFAULT DATEADD(DAY, 7, CURRENT_TIMESTAMP),
ShippedDate DATETIME,
CONSTRAINT pk_Purchased PRIMARY KEY (UserID, PurchasedID, ProductID)
);
GO

CREATE TRIGGER InsertInPurchased
ON Purchased
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;
    -- 2. Insert new records if the product does not exist in the user's cart
    INSERT INTO Purchased (UserID, PurchasedID, ProductID, Quantity, ShippedDate)
    SELECT 
        i.UserID,
        i.PurchasedID,
        i.ProductID,
        i.Quantity,
        CASE (ABS(CHECKSUM(NEWID())) % 3)
            WHEN 0 THEN NULL
            WHEN 1 THEN DATEADD(DAY, 7, CURRENT_TIMESTAMP)
            WHEN 2 THEN DATEADD(DAY, 10, CURRENT_TIMESTAMP)
        END AS ShippedDate
    FROM inserted i    
END
GO

-- 7: Payments
CREATE TABLE Payments(
UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
PaymentID INT NOT NULL,
PaymentMethod NVARCHAR(50),
[Address] NVARCHAR(500),
PaymentDate DATETIME DEFAULT CURRENT_TIMESTAMP,
Price INT DEFAULT NULL,
);
GO

CREATE TRIGGER HandleInsertInPayment
ON Payments
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;
    
    -- 1. Nếu sản phẩm chưa có trong giỏ hàng, thêm bản ghi mới với PaymentID tự động tăng
    INSERT INTO Payments (UserID, PaymentID, PaymentMethod, [Address], Price)
    SELECT 
        i.UserID,
        ISNULL(
            (SELECT MAX(p.PaymentID) FROM Payments p WHERE p.UserID = i.UserID), 
            0
        ) + 1 AS NewPaymentID,
        i.PaymentMethod,
		i.Address,
        i.Price
    FROM inserted i
    
END;
GO

-- Data samples
insert into Users (RealName, UserName, [Password], [Role], Email) values 
-- Admin
('Admin1', 'admin1', '12345', 'admin', 'admin1@gmail.com'),
('Admin2', 'admin2', '12345', 'admin', 'admin2@gmail.com'),
-- User
('User1', 'user1', '12345', 'user', 'user1@gmail.com'),
('User2', 'user2', '12345', 'user', 'user2@gmail.com')

insert into Categories(CategoryName, [Description]) values 
('PC', 'Desktop computer, typically with a keyboard and mouse, for stationary use.'),
('Laptop', 'Portable computer with a built-in keyboard and display.'),
('Tablet', 'Mobile device with a touchscreen display, designed for portability and touch-based interaction.'),
('Speaker', 'Electronic device that converts electrical signals into sound waves.'),
('Networking', 'Hardware components that facilitate communication and data transfer between devices on a network.'),
('Storage', 'Hardware component used to store and retrieve data in a long time.'),
('Memory', 'Hardware component that stores data and instructions for a computer or other electronic device.'),
('Battery', 'Stores chemical energy and converts it into electrical energy.')

insert into Products(ProductName, CategoryID, UnitsInStock, UnitPrice, Discount, [Image]) values 
-- PC
('PC 01', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://product.hstatic.net/1000203080/product/aturos-rk3588-2_a2d9808292cd44be932480d36b0c9b07_master.jpg'),
('PC 02', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://hoangnamcomputer.com/wp-content/uploads/2021/01/bo-may-tinh-dong-bo-dell-optiplex-390-core-i5-2400-man-hinh-qua-tang-hang-nhap-khau-p265.png'),
('PC 03', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIDIJYQeomwMruEZlhheKgkkY2_Q0xGISa5g&s'),
('PC 04', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://m.media-amazon.com/images/I/81JlCSDZ3AL.jpg'),
('PC 05', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://maytinhsocbay.com/wp-content/uploads/2023/06/pc-workstations-may-tram-cau-hinh-khung.jpg'),
-- Laptop
('Laptop 01', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://cdn.mos.cms.futurecdn.net/Cjx3Ekyzx8oXrtKx3n9w3A-970-80.jpg.webp'),
('Laptop 02', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://hoangnamcomputer.com/wp-content/uploads/2021/01/bo-may-tinh-dong-bo-dell-optiplex-390-core-i5-2400-man-hinh-qua-tang-hang-nhap-khau-p265.png'),
('Laptop 03', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://cdn.tgdd.vn/Products/Images/44/316941/msi-gaming-gf63-thin-12uc-i7-887vn-glr-thumb-600x600.jpg'),
('Laptop 04', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://tiki.vn/blog/wp-content/uploads/2023/11/laptop-mini.jpeg'),
('Laptop 05', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://laptopkysu.vn/wp-content/uploads/2021/01/Laptop-Dell-Precision-M4800-avt-1.jpg'),
-- Tablet
('Tablet 01', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://product.hstatic.net/1000370129/product/galaxy_tab_a8_gray_digiphone_6b84a57429b04a06befdb36f70d6c67d.jpg'),
('Tablet 02', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://cdn.mediamart.vn/images/product/apple-ipad-109-inch-wi-fi-256gb---blue_a30145d1.jpg'),
('Tablet 03', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://dienthoaixachtay.com.vn/public/upload/images/hinhsanpham/huawei-mediapad-m3-84-99-31461571654560.jpg'),
-- Speaker
('Speaker 01', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://images-na.ssl-images-amazon.com/images/I/81FzSswwCVL.jpg'),
-- Networking
('Networking 01', 5, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGUeyxm1GI62g8q7u9-VveaDPRDwEZnU-W_g&s'),
-- Storage
('Storage 01', 6, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://static.tandoanh.vn/wp-content/uploads/2023/07/SSD-Samsung-870-Qvo-8TB-H01.jpg.webp'),
-- Memory
('Memory 01', 7, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://anphat.com.vn/media/product/48637_ram_kingston_fury_beast_rgb_64gb__2x32gb__ddr4_3200mhz.jpg'),
-- Battery
('Battery 01', 8, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 10000, 0) * 10000, ROUND(RAND() * 50, 0), 'https://product.hstatic.net/1000370129/product/galaxy_tab_a8_gray_digiphone_6b84a57429b04a06befdb36f70d6c67d.jpg')