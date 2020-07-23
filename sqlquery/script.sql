use master
go
create database JAVAWEB_TsunamiDB
go
use JAVAWEB_TsunamiDB
go
create table Account
(
	id int identity primary key,
	username varchar(100),
	password varchar(100),
	name nvarchar(100),
	avatar varchar(100),
	mail varchar(100),
	phone varchar(20),
	gender bit,
	dob date,
	unique(username),
)
go
create table Shop
(
	id int identity primary key,
	accID int references Account(id),
	brand nvarchar(100),
	description nvarchar(4000),
	address nvarchar(100),
	phone varchar(20),
	facebook varchar(100),
	insta varchar(100),
	rating int,
	unique(brand),
)
go
create table Category
(
	id int primary key,
	name nvarchar(100),
	unique(name),
)
go
create table Brand
(
	id int identity primary key,
	categoryID int references Category(id),
	name nvarchar(100),
	unique(categoryID, name),
)
go
create table Classify
(
	id int identity primary key,
	name nvarchar(100),
)
go
create table Item
(
	id int identity primary key,
	value nvarchar(100),
	classifyID int references Classify(id),
	img varchar(100),
	unique(classifyID, value),
)
go
create table ProductImages
(
	id int identity primary key,
	imgCover varchar(100),
	img1 varchar(100),
	img2 varchar(100),
	img3 varchar(100),
	img4 varchar(100),
	img5 varchar(100),
)
go
create table ProductGroup
(
	id int identity primary key,
	name nvarchar(100),
	shopID int references Shop(id),
	categoryID int references Category(id),
	brandID int references Brand(id),
	description nvarchar(500),
	imagesID int references ProductImages(id),
	classify1ID int references Classify(id),
	classify2ID int references Classify(id),
	cost money,
	sale money,
	rating int,
	state nvarchar(100),
	unique(shopID, name),
)
go
create table Product
(
	id int identity primary key,
	groupID int references ProductGroup(id),
	item1ID int references Item(id),
	item2ID int references Item(id),
	price money,
	quantity int,
	unique(groupID, item1ID, item2ID),
)
go
create table BankAcc
(
	id int identity primary key,
	accID int references Account(id),
	holder nvarchar(500),
	number nvarchar(20),
	expiration date,
	CVV int,
	bank nvarchar(1000),
	unique(accID, number),
)
go
create table Address
(
	id int identity primary key,
	accID int references Account(id),
	fullname nvarchar(500),
	phone varchar(20),
	provincial nvarchar(100),
	district nvarchar(100),
	commune nvarchar(100),
	apartment nvarchar(100),
)
go 
create table [Order]
(
	id int identity primary key,
	accID int references Account(id),
	productID int references Product(id),
	date date,
	quantity int,
	state nvarchar(1000),
	cardID int references BankAcc(id),
	addressID int references Address(id),
	img varchar(100),
)
go
insert into Account(username,password) 
	values('tsunami','tsunami')
GO
declare @adminid int
set @adminid = (select id from Account where username = 'tsunami')
insert into Shop(accid, brand, description, address, phone, facebook, insta, rating)
	values (@adminid, 'Tsunami', 
	'The more you seek security, the less of it you have. But the more you seek opportunity, the more likely it is that you will achieve the security that you desire.', 
	'Mercury', '0836554688', '', '', 0)
GO
insert into Category(id, name)
	values 
		(1, 'Dress'), 
		(2, 'Book'), 
		(3, 'Computer'), 
		(4, 'Phone'), 
		(5, 'Food'), 
		(6, 'Decor'), 
		(7, 'Cosmetic'), 
		(8, 'Houseware'), 
		(9, 'Footware'), 
		(10, 'Accessories'),
GO
insert into Brand(categoryID, name)
	values
		(10, 'FLORALPUNK'),
		(10, 'HERACCESSORIES'),
		(10, 'SOL SISTA'),
		(10, 'FUHEATHER'),
		(10, 'CHOKER CLUB'),
		(10, 'MIGNONNE INC.'),
		(10, 'SUGAR PUNK'),
		(10, N'XÍU XIU'),
		(10, 'SHIMMER'),
		(10, 'DDREAMER'),
		(10, 'MAISON SILVER'),
		(10, 'AMETHYST STUDIO'),
		(10, 'TISUMY ACCESSORIES'),
		(10, 'JENIOR'),
		(10, 'OAKWILL GOODS'),
		(10, 'JEELOOK'),
		(10, 'THE DEAD BIRD'),
		(10, 'GYPSYBYSHALALA'),
		(10, 'UNTAMED JEWELRY'),
		(2, N'Nhà xuất bản Kim Đồng'),
		(2, N'Nhà xuất bản Trẻ'),
		(2, N'Nhà xuất bản Tổng hợp thành phố Hồ Chí Minh'),
		(2, N'Nhà xuất bản chính trị quốc gia sự thật'),
		(2, N'Nhà xuất bản giáo dục'),
		(2, N'Nhà xuất bản Hội Nhà văn'),
		(2, N'Nhà xuất bản Đại học Oxford'),
		(2, N'Nhà xuất bản Đại học Cambridge'),
		(2, N'Nhà xuất bản Đại học California'),
		(2, N'Nhà Xuất bản Đại học Hawaii'),
		(2, 'The MIT Press'),
		(2, 'Penguin Random House'),
		(3, N'Apple'),
		(3, N'Dell'),
		(3, N'Asus'),
		(3, N'HP'),
		(3, N'Lenovo'),
		(3, N'MSI'),
		(3, N'ThinkPad'),
		(7, N'M.A.C'),
		(7, N'L’Oreal'),
		(7, N'Lakmé'),
		(7, N'Maybelline'),
		(7, N'NYX'),
		(7, N'Bobbi Brown'),
		(7, N'Colorbar'),
		(7, N'L.A. Girl'),
		(7, N'Avon'),
		(7, N'The Body Shop'),
		(7, N'Clinique'),
		(7, N'Revlon'),
		(7, N'PAC'),
		(7, N'Makeup Revolution'),
		(7, N'theBalm Cosmetics'),
		(6, N'Nội thất Nhà Xinh'),
		(6, N'Handyman Decor'),
		(6, N'Peony Home'),
		(6, N'Home Decor'),
		(6, N'NOTH Garden'),
		(6, N'La Foret'),
		(6, N'Sugin Home'),
		(6, N'Luxstore'),
		(6, N'Riri Home'),
		(6, N'LIIQ Touch'),
		(1, N'DOLCE & GABBANA'),
		(1, N'ARMANI'),
		(1, N'VERSACE'),
		(1, N'GUCCI'),
		(1, N'BURBERRY'),
		(1, N'HERMÈS'),
		(1, N'DIOR'),
		(1, N'PRADA'),
		(1, N'CHANEL'),
		(1, N'LOUIS VUITTON'),
		(1, N'BOTTEGA VENETA'),
		(5, N'McDonalds'),
		(5, N'Starbucks'),
		(5, N'Subway'),
		(5, N'KFC'),
		(5, N'Burger King'),
		(5, N'Pizza Hut'),
		(5, N'Dunkin’s Donuts'),
		(5, N'Domino’s Pizza'),
		(5, N'Coffee Bean'),
		(5, N'Gong Cha'),
		(5, N'Bobapop'),
		(5, N'Phúc Long'),
		(5, N'R&B'),
		(5, N'Tocotoco'),
		(5, N'Royal tea'),
		(8, N'Premium Housewares'),
		(8, N'Sapa Housewares'),
		(8, N'Acumen Hourseware'),
		(8, N'Chi Lai'),
		(8, N'Phố Xinh'),
		(8, N'Nhà Xinh'),
		(8, N'Hoàng Anh Gia Lai'),
		(8, N'Mia Home'),
		(9, N'Nike'),
		(9, N'Adidas'),
		(9, N'New Balance'),
		(9, N'ASICS'),
		(9, N'PUMA'),
		(9, N'Skechers'),
		(9, N'Fila'),
		(9, N'Bata'),
		(4, N'Apple'),
		(4, N'Samsung'),
		(4, N'Oppo'),
		(4, N'Huawei'),
		(4, N'Xiaomi'),
		(4, N'NOKIA'),
		(4, N'BPhone')
		



