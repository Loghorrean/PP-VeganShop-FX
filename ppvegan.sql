-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: vegan_for_pp
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(50) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `city_name` (`city_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (4,'Екатеринбург'),(2,'Москва'),(3,'Омск'),(1,'Санкт-Петербург');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `couriers`
--

DROP TABLE IF EXISTS `couriers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `couriers` (
  `courier_id` int NOT NULL AUTO_INCREMENT,
  `courier_firstname` varchar(45) NOT NULL,
  `courier_lastname` varchar(45) NOT NULL,
  `courier_phone` varchar(28) NOT NULL,
  `verify_code` varchar(100) NOT NULL,
  PRIMARY KEY (`courier_id`),
  UNIQUE KEY `courier_phone` (`courier_phone`),
  UNIQUE KEY `courier_phone_2` (`courier_phone`),
  UNIQUE KEY `courier_phone_3` (`courier_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `couriers`
--

LOCK TABLES `couriers` WRITE;
/*!40000 ALTER TABLE `couriers` DISABLE KEYS */;
/*!40000 ALTER TABLE `couriers` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp866 */ ;
/*!50003 SET character_set_results = cp866 */ ;
/*!50003 SET collation_connection  = cp866_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `courier_validate` BEFORE INSERT ON `couriers` FOR EACH ROW BEGIN 
DECLARE msg varchar(150);
IF new.courier_firstname NOT REGEXP "^[�-�-�]+$" THEN 
SET msg = concat("��� ����� ������ ᮤ�ঠ�� ⮫쪮 ���᪨� ᨬ����, � �� ����� ", new.courier_firstname);
SIGNAL SQLSTATE '45000' set message_text = msg;
END IF;
IF LENGTH(new.courier_firstname) >= 20 THEN 
SET new.courier_firstname = concat(substr(new.courier_firstname, 1, 19), "...");
END IF;
IF new.courier_lastname NOT REGEXP "^[�-�-�]+$" THEN 
SET msg = CONCAT("������� ����� ������ ᮤ�ঠ�� ⮫쪮 ���᪨� ᨬ����, � �� ����� ", new.courier_lastname);
SIGNAL SQLSTATE '45000' set message_text = msg;
END IF;
IF LENGTH(new.courier_lastname) >= 20 THEN 
SET new.courier_lastname = concat(substr(new.courier_lastname, 1, 19), "...");
END IF;
IF new.courier_phone NOT REGEXP "^(\+)?(\d[-]?){9,13}(\d)$" THEN 
SIGNAL SQLSTATE '45000' SET message_text = "�������� ⥫�䮭 �� ᮮ⢥����� �ଠ��";
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `custom_dishes`
--

DROP TABLE IF EXISTS `custom_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_dishes` (
  `dish_id` int NOT NULL AUTO_INCREMENT,
  `template_id` int NOT NULL,
  `dish_name` varchar(45) NOT NULL,
  `dish_recipe` varchar(45) NOT NULL,
  `created_by_user` int NOT NULL,
  PRIMARY KEY (`dish_id`),
  KEY `fk_Dishes (Custom)_Dish_template1_idx` (`template_id`),
  KEY `created_by_user` (`created_by_user`),
  CONSTRAINT `custom_dishes->dish_template` FOREIGN KEY (`template_id`) REFERENCES `dish_templates` (`template_id`),
  CONSTRAINT `custom_dishes_ibfk_1` FOREIGN KEY (`created_by_user`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_dishes`
--

LOCK TABLES `custom_dishes` WRITE;
/*!40000 ALTER TABLE `custom_dishes` DISABLE KEYS */;
/*!40000 ALTER TABLE `custom_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_templates`
--

DROP TABLE IF EXISTS `dish_templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_templates` (
  `template_id` int NOT NULL AUTO_INCREMENT,
  `template_name` varchar(45) NOT NULL,
  `template_description` varchar(100) NOT NULL,
  PRIMARY KEY (`template_id`),
  UNIQUE KEY `template_name` (`template_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_templates`
--

LOCK TABLES `dish_templates` WRITE;
/*!40000 ALTER TABLE `dish_templates` DISABLE KEYS */;
INSERT INTO `dish_templates` VALUES (1,'Супы','Описание супа'),(2,'Каши','Описание каши'),(3,'Салаты','Описание салатов'),(4,'Пицца','Описание пицц'),(5,'Пироги','Описание пирогов'),(6,'Закуски','Описание закусок'),(7,'Десерты','Описание десертов'),(8,'Напитки','Описание напитков'),(9,'Роллы','Описание роллов'),(10,'Гарниры','Описание гарниров');
/*!40000 ALTER TABLE `dish_templates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctrine_migration_versions`
--

LOCK TABLES `doctrine_migration_versions` WRITE;
/*!40000 ALTER TABLE `doctrine_migration_versions` DISABLE KEYS */;
INSERT INTO `doctrine_migration_versions` VALUES ('DoctrineMigrations\\Version20201110174643','2020-11-10 18:48:13',939),('DoctrineMigrations\\Version20201110175623','2020-11-10 18:58:23',416);
/*!40000 ALTER TABLE `doctrine_migration_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_dishes`
--

DROP TABLE IF EXISTS `general_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `general_dishes` (
  `dish_id` int NOT NULL,
  `dish_name` varchar(100) NOT NULL,
  `dish_description` varchar(255) NOT NULL,
  `time_to_cook` int NOT NULL,
  `dish_rating` int DEFAULT NULL,
  PRIMARY KEY (`dish_id`),
  UNIQUE KEY `dish_name_UNIQUE` (`dish_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_dishes`
--

LOCK TABLES `general_dishes` WRITE;
/*!40000 ALTER TABLE `general_dishes` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdish_custom`
--

DROP TABLE IF EXISTS `orderdish_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdish_custom` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `dish_amount` float NOT NULL,
  `dish_id` int NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_OrderDish (Custom)_Dishes (Custom)1_idx` (`dish_id`),
  KEY `fk_OrderDish (Custom)_Orders1_idx` (`order_id`),
  CONSTRAINT `fk_OrderDish (Custom)_Dishes (Custom)1` FOREIGN KEY (`dish_id`) REFERENCES `custom_dishes` (`dish_id`),
  CONSTRAINT `fk_OrderDish (Custom)_Orders1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdish_custom`
--

LOCK TABLES `orderdish_custom` WRITE;
/*!40000 ALTER TABLE `orderdish_custom` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdish_custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdish_general`
--

DROP TABLE IF EXISTS `orderdish_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdish_general` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `dish_amount` float NOT NULL,
  `dish_id` int NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_OrderDish (General)_Dishes (General)1_idx` (`dish_id`),
  KEY `fk_OrderDish (General)_Orders1_idx` (`order_id`),
  CONSTRAINT `fk_OrderDish (General)_Dishes (General)1` FOREIGN KEY (`dish_id`) REFERENCES `general_dishes` (`dish_id`),
  CONSTRAINT `fk_OrderDish (General)_Orders1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdish_general`
--

LOCK TABLES `orderdish_general` WRITE;
/*!40000 ALTER TABLE `orderdish_general` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdish_general` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_date` datetime(2) NOT NULL,
  `order_price` int NOT NULL,
  `order_phone` varchar(10) NOT NULL,
  `order_city` int NOT NULL,
  `order_street` varchar(100) NOT NULL,
  `order_flat` int NOT NULL,
  `order_comment` varchar(100) DEFAULT NULL,
  `order_confirmation` tinyint(1) NOT NULL DEFAULT '0',
  `delivery_confirmation` tinyint(1) NOT NULL DEFAULT '0',
  `payment_type` int NOT NULL,
  `courier_id` int NOT NULL,
  `courier_rating` int DEFAULT NULL,
  `food_rating` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_Orders_Users1_idx` (`user_id`),
  KEY `fk_Orders_Payment_types1_idx` (`payment_type`),
  KEY `fk_Orders_Couriers1_idx` (`courier_id`),
  KEY `order_city` (`order_city`),
  CONSTRAINT `fk_Orders_Payment_types1` FOREIGN KEY (`payment_type`) REFERENCES `payment_types` (`paument_type_id`),
  CONSTRAINT `fk_Orders_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_types`
--

DROP TABLE IF EXISTS `payment_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_types` (
  `paument_type_id` int NOT NULL AUTO_INCREMENT,
  `payment_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`paument_type_id`),
  UNIQUE KEY `payment_type_name` (`payment_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_types`
--

LOCK TABLES `payment_types` WRITE;
/*!40000 ALTER TABLE `payment_types` DISABLE KEYS */;
INSERT INTO `payment_types` VALUES (2,'Банковская карта'),(1,'Наличные'),(3,'Электронные деньги');
/*!40000 ALTER TABLE `payment_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `category_description` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'Фрукты','Описание фруктов'),(2,'Овощи','Описание овощей'),(3,'Бакалея','Описание бакалеи'),(4,'Ягоды','Описание ягод'),(5,'Орехи','Описание орехов'),(6,'Выпечка','Описание выпечки'),(7,'Молочные продукты','Описание молочных продуктов'),(8,'Морепродукты','Описание морепродуктов'),(9,'Специи','Описание специй'),(10,'Соусы','Описание соусов'),(11,'Вкусности','Описание вкусностей');
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_description` varchar(255) NOT NULL,
  `product_amount` float NOT NULL,
  `product_price` int NOT NULL,
  `number_of_calories` int NOT NULL,
  `is_allergic` tinyint(1) NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_name` (`product_name`),
  KEY `fk_Products_Product_categories1_idx` (`category_id`),
  CONSTRAINT `fk_Products_Product_categories1` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_in_custom_dishes`
--

DROP TABLE IF EXISTS `products_in_custom_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_in_custom_dishes` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `product_amount` int NOT NULL,
  `product_recipe` varchar(255) NOT NULL,
  `product_id` int NOT NULL,
  `dish_id` int NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_Products_in_dishes (Customl)_Products1_idx` (`product_id`),
  KEY `fk_Products_in_dishes (Customl)_Dishes (Custom)1_idx` (`dish_id`),
  CONSTRAINT `products_in_custom_dishes->custom_dishes` FOREIGN KEY (`dish_id`) REFERENCES `custom_dishes` (`dish_id`),
  CONSTRAINT `products_in_custom_dishes->products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_in_custom_dishes`
--

LOCK TABLES `products_in_custom_dishes` WRITE;
/*!40000 ALTER TABLE `products_in_custom_dishes` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_in_custom_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_in_general_dishes`
--

DROP TABLE IF EXISTS `products_in_general_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_in_general_dishes` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `dish_id` int NOT NULL,
  `product_id` int NOT NULL,
  `product_amount` float NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_Products_in_dishes (General)_Dishes (General)1_idx` (`dish_id`),
  KEY `fk_Products_in_dishes (General)_Products1_idx` (`product_id`),
  CONSTRAINT `products_in_general_dishes->general_dishes` FOREIGN KEY (`dish_id`) REFERENCES `general_dishes` (`dish_id`),
  CONSTRAINT `products_in_general_dishes->products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_in_general_dishes`
--

LOCK TABLES `products_in_general_dishes` WRITE;
/*!40000 ALTER TABLE `products_in_general_dishes` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_in_general_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_in_template`
--

DROP TABLE IF EXISTS `products_in_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_in_template` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `template_id` int NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_Products_in_template_Product_categories1_idx` (`category_id`),
  KEY `fk_Products_in_template_Dish_template1_idx` (`template_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`category_id`),
  CONSTRAINT `fk_Products_in_template_Dish_template1` FOREIGN KEY (`template_id`) REFERENCES `dish_templates` (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_in_template`
--

LOCK TABLES `products_in_template` WRITE;
/*!40000 ALTER TABLE `products_in_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_in_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews_general`
--

DROP TABLE IF EXISTS `reviews_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews_general` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `review_content` varchar(255) NOT NULL,
  `review_date` datetime(2) NOT NULL,
  `is_approved` tinyint(1) NOT NULL,
  `review_stars` int NOT NULL DEFAULT '0',
  `dish_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `fk_Reviews (General)_Dishes (General)1_idx` (`dish_id`),
  KEY `fk_Reviews (General)_Users1_idx` (`user_id`),
  CONSTRAINT `fk_Reviews (General)_Dishes (General)1` FOREIGN KEY (`dish_id`) REFERENCES `general_dishes` (`dish_id`),
  CONSTRAINT `fk_Reviews (General)_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews_general`
--

LOCK TABLES `reviews_general` WRITE;
/*!40000 ALTER TABLE `reviews_general` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews_general` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(2,'Customer'),(3,'Courier');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `user_phone` varchar(28) DEFAULT NULL,
  `user_city` int DEFAULT NULL,
  `user_street` varchar(100) DEFAULT NULL,
  `user_house` int DEFAULT NULL,
  `user_flat` int DEFAULT NULL,
  `user_salt` varchar(45) NOT NULL,
  `date_of_reg` datetime(2) NOT NULL,
  `system_review` varchar(100) DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `user_phone` (`user_phone`),
  KEY `user_city` (`user_city`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Loghorrean','loghorrean74@gmail.com','1234',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QwErTy','2020-11-17 22:07:07.00',NULL,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp866 */ ;
/*!50003 SET character_set_results = cp866 */ ;
/*!50003 SET collation_connection  = cp866_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `regDate` BEFORE INSERT ON `users` FOR EACH ROW SET NEW.date_of_reg = NOW() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-18 12:20:18
