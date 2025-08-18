

INSERT INTO ims.supplier (supplier_name, supplier_address, supplier_contact) VALUES
('Zara', 'Calle de Hermosilla, 112, Madrid, Spain', '+34 912 345 678'),
('H&M', 'Mäster Samuelsgatan 58, Stockholm, Sweden', '+46 8 123 456 789'),
('Nike', 'One Bowerman Drive, Beaverton, Oregon, USA', '+1 503 123 4567'),
('Adidas', 'Adi-Dassler-Straße 1, Herzogenaurach, Germany', '+49 9132 84 0'),
('Uniqlo', '1-7-1, Marunouchi, Chiyoda, Tokyo, Japan', '+81 3-1234-5678'),
('Levis', '1155 Battery St, San Francisco, CA, USA', '+1 415 123 4567'),
('Ralph Lauren', '650 Madison Ave, New York, NY, USA', '+1 212 555 1234'),
('Gap', '2 Folsom Street, San Francisco, CA, USA', '+1 800 123 4567'),
('Tommy Hilfiger', '16th Floor, 40 West 57th Street, New York, NY, USA', '+1 212 555 7890'),
('Calvin Klein', '205 W 39th St, New York, NY, USA', '+1 212 555 4321');



INSERT INTO ims.products (category, created_at, description, image_file_name, name, price, stock_quantity, supplier_id)
VALUES
('Clothing', NOW(), 'Stylish and comfortable shirt for all occasions.', 'shirt1.jpg', 'Basic T-Shirt', 6396.80, 100, 1), -- Zara (19.99 * 320)
('Clothing', NOW(), 'Sleek design with a modern fit.', 'shirt2.jpg', 'Slim Fit Shirt', 9596.80, 150, 2), -- H&M (29.99 * 320)
('Footwear', NOW(), 'Perfect for running and training.', 'shoe1.jpg', 'Running Shoes', 25596.80, 200, 3), -- Nike (79.99 * 320)
('Footwear', NOW(), 'High-performance soccer shoes.', 'shoe2.jpg', 'Soccer Cleats', 31996.80, 120, 4), -- Adidas (99.99 * 320)
('Clothing', NOW(), 'Casual jacket for cooler weather.', 'jacket1.jpg', 'Lightweight Jacket', 15996.80, 80, 5), -- Uniqlo (49.99 * 320)
('Clothing', NOW(), 'Classic denim jeans with a timeless fit.', 'jeans1.jpg', 'Denim Jeans', 19196.80, 50, 6), -- Levi\'s (59.99 * 320)
('Clothing', NOW(), 'Luxurious polo shirt with a perfect fit.', 'polo1.jpg', 'Polo Shirt', 25596.80, 60, 7), -- Ralph Lauren (79.99 * 320)
('Clothing', NOW(), 'Casual sweater for colder days.', 'sweater1.jpg', 'Crew Neck Sweater', 12796.80, 75, 8), -- Gap (39.99 * 320)
('Clothing', NOW(), 'Classic chinos with a modern cut.', 'chino1.jpg', 'Slim Fit Chinos', 15996.80, 90, 9), -- Tommy Hilfiger (49.99 * 320)
('Clothing', NOW(), 'Comfortable and stylish hoodie.', 'hoodie1.jpg', 'Hooded Sweatshirt', 15996.80, 110, 10), -- Calvin Klein (49.99 * 320)
('Footwear', NOW(), 'Fashionable sneakers for everyday wear.', 'sneaker1.jpg', 'Fashion Sneakers', 19196.80, 120, 1), -- Zara (59.99 * 320)
('Clothing', NOW(), 'Trendy dress for evening events.', 'dress1.jpg', 'Cocktail Dress', 28796.80, 40, 2), -- H&M (89.99 * 320)
('Footwear', NOW(), 'Comfortable sandals for summer days.', 'sandals1.jpg', 'Leather Sandals', 12796.80, 150, 3), -- Nike (39.99 * 320)
('Clothing', NOW(), 'Classic white shirt with a perfect fit.', 'shirt3.jpg', 'White Dress Shirt', 12796.80, 100, 4), -- Adidas (39.99 * 320)
('Clothing', NOW(), 'Stylish hoodie with a minimalist design.', 'hoodie2.jpg', 'Minimalist Hoodie', 19196.80, 50, 5), -- Uniqlo (59.99 * 320)
('Footwear', NOW(), 'Stylish boots for colder weather.', 'boots1.jpg', 'Leather Boots', 31996.80, 80, 6), -- Levi\'s (99.99 * 320)
('Clothing', NOW(), 'Casual t-shirt with a cool design.', 'tshirt1.jpg', 'Graphic T-Shirt', 8311.20, 110, 7), -- Ralph Lauren (25.99 * 320)
('Footwear', NOW(), 'Sporty shoes with enhanced cushioning.', 'sportshoes1.jpg', 'Running Sneakers', 28796.80, 130, 8), -- Gap (89.99 * 320)
('Clothing', NOW(), 'Warm knit sweater for winter days.', 'sweater2.jpg', 'Knit Sweater', 19196.80, 95, 9), -- Tommy Hilfiger (59.99 * 320)
('Clothing', NOW(), 'Chic blazer for professional and casual settings.', 'blazer1.jpg', 'Blazer Jacket', 41596.80, 40, 10), -- Calvin Klein (129.99 * 320)
('Footwear', NOW(), 'Comfortable flip-flops for summer.', 'flipflops1.jpg', 'Flip Flops', 6396.80, 200, 1), -- Zara (19.99 * 320)
('Clothing', NOW(), 'Casual button-up shirt with a relaxed fit.', 'shirt4.jpg', 'Button-Up Shirt', 11117.60, 100, 2), -- H&M (34.99 * 320)
('Footwear', NOW(), 'Stylish dress shoes for formal occasions.', 'dressshoes1.jpg', 'Formal Dress Shoes', 38319.20, 50, 3), -- Nike (119.99 * 320)
('Clothing', NOW(), 'Trendy denim jacket for a cool look.', 'denimjacket1.jpg', 'Denim Jacket', 25596.80, 70, 4), -- Adidas (79.99 * 320)
('Clothing', NOW(), 'Soft cotton t-shirt with a fun design.', 'tshirt2.jpg', 'Graphic Tee', 9596.80, 120, 5), -- Uniqlo (29.99 * 320)
('Footwear', NOW(), 'High-performance training shoes.', 'trainers1.jpg', 'Training Shoes', 28796.80, 160, 6); -- Levi\'s (89.99 * 320)



