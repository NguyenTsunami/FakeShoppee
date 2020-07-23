
select * from ProductGroup


select id, price, quantity from Product where groupID = 1 and item1ID = 1 and item2ID = 1


select * from Shop where id = 1
select * from Category where id = 1
select * from Brand where categoryID = 1

select * from [Order]
select * from Product
select * from ProductImages
select * from Item
select * from Address


select ProductGroup.name, * from Item
join ProductGroup
on ProductGroup.imagesID = ProductImages.id

select groupID, Item.id, Item.img
from Product
join Item
on Product.item1ID = Item.id

select * from BankAcc where id = 1









