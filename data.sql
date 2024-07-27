-- Tạo cơ sở dữ liệu J6Lab7
CREATE DATABASE J6Lab7;
GO

-- Sử dụng cơ sở dữ liệu J6Lab7
USE J6Lab7;
GO

-- Tạo bảng roles
CREATE TABLE roles (
    id varchar(10) PRIMARY KEY,
    Name varchar(20)
);

-- Chèn dữ liệu vào bảng roles
INSERT INTO roles (id, Name)
VALUES 
    ('ADMIN', 'Administrators'),
    ('GUEST', 'Guest'),
    ('USER', 'Users');
GO

-- Tạo bảng accounts
CREATE TABLE accounts (
    Username varchar(10) PRIMARY KEY,
    Password varchar(20),
    Fullname nvarchar(50),
    Email varchar(50)
);

-- Chèn dữ liệu vào bảng accounts
INSERT INTO accounts (Username, Password, Fullname, Email)
VALUES
    ('user1', '123', N'Đỗ Mỹ Uyên', 'uyendm@gmail.com'),
    ('user2', '123', N'Phạm Ngọc Hân', 'hanpn@gmail.com'),
    ('user3', '123', N'Huỳnh Chánh Trung', 'trunghc@gmail.com');
GO

-- Tạo bảng authorities
CREATE TABLE authorities (
    id int PRIMARY KEY,
    Username varchar(10),
    Roleid varchar(10),
    FOREIGN KEY (Username) REFERENCES accounts(Username),
    FOREIGN KEY (Roleid) REFERENCES roles(id)
);

-- Chèn dữ liệu vào bảng authorities
INSERT INTO authorities (id, Username, Roleid)
VALUES
    (1, 'user2', 'USER'),
    (2, 'user2', 'GUEST'),
    (3, 'user3', 'ADMIN'),
    (4, 'user3', 'USER'),
    (5, 'user3', 'GUEST'),
    (6, 'user1', 'GUEST');
GO
