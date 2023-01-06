CREATE DATABASE  IF NOT EXISTS `smartportables` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `smartportables`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: smartportables
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customerorders`
--

DROP TABLE IF EXISTS `customerorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerorders` (
  `OrderId` int NOT NULL,
  `userName` varchar(40) NOT NULL,
  `orderName` varchar(40) NOT NULL,
  `orderPrice` double DEFAULT NULL,
  `userAddress` varchar(100) DEFAULT NULL,
  `creditCardNo` varchar(40) DEFAULT NULL,
  `inStoreOrDelivery` varchar(20) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `dateOrdered` varchar(30) DEFAULT NULL,
  `shipDate` varchar(30) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `quantity` varchar(20) DEFAULT NULL,
  `storeId` varchar(10) DEFAULT NULL,
  `storeAddress` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`OrderId`,`userName`,`orderName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerorders`
--

LOCK TABLES `customerorders` WRITE;
/*!40000 ALTER TABLE `customerorders` DISABLE KEYS */;
INSERT INTO `customerorders` VALUES (1,'rishit','Amazon Halo',49.99,'Apt 202, 3001 South King Drive, Chicago, Illinois','12345','inStore','60616','Mon Oct 17 23:23:12 CDT 2022','Thu Oct 20 23:23:12 CDT 2022','fitnessWatch','2','BESTBUY','47.W,Division.St,Chicago,IL'),(1,'rishit','Motorola Watch 100',249.99,'Apt 202, 3001 South King Drive, Chicago, Illinois','12345','inStore','60616','Mon Oct 17 23:23:12 CDT 2022','Thu Oct 20 23:23:12 CDT 2022','smartWatch','2','BESTBUY','47.W,Division.St,Chicago,IL'),(2,'coolman','Samsung Galaxy Buds 2',149.99,'1111 N, California','123456665','homeDelivery','824578','Mon Oct 17 23:24:39 CDT 2022','Thu Oct 20 23:24:39 CDT 2022','headphone','1','JEWEL','7122.W,40th.St,Stickney,IL'),(2,'jashwanth','Amazon Halo',49.99,'312N, Milwaukee, Wisconsin','777898766789','homeDelivery','898976','Mon Oct 18 23:41:11 CDT 2022','Thu Oct 20 23:41:11 CDT 2022','fitnessWatch','1','WALLMART','3473,S.Martin.Luther.King.Dr,Chicago,IL'),(3,'hotman','Meta Quest 2',449.99,'Clearlake,Texas','12345','homeDelivery','546346','Mon Oct 18 23:26:32 CDT 2022','Thu Oct 20 23:26:32 CDT 2022','virtualReality','1','WALLMART','3473,S.Martin.Luther.King.Dr,Chicago,IL'),(3,'hotman','Tractive GPS',29.99,'Clearlake,Texas','898798709765','inStore','453234','Mon Oct 20 23:28:34 CDT 2022','Thu Oct 20 23:28:34 CDT 2022','petTracker','1','WALLMART','3473,S.Martin.Luther.King.Dr,Chicago,IL'),(4,'rishit','Motorola Watch 100',249.99,'2951 South King Drive, Chicago','898798709765','inStore','777876','Thu Oct 22 02:02:26 CDT 2022','Sun Oct 30 02:02:26 CDT 2022','smartWatch','1','BESTBUY','47.W,Division.St,Chicago,IL'),(5,'john','Amazon Echo Studio',99.99,'432 S Wacker, Masachusets','777898766789','homeDelivery','90909','Thu Oct 27 19:24:02 CDT 2022','Sun Oct 30 19:24:02 CDT 2022','voiceAssistant','1','TJMAX','1758.W,WABANSIA.AVE,CHICAGO,IL'),(6,'john','Lenovo Ideapad',799.99,'Boston, Masachusets','123456','homeDelivery','89890','Thu Oct 27 19:25:20 CDT 2022','Sun Oct 30 19:25:20 CDT 2022','laptop','1','MACYS','4700.S,Halsted.St,Chicago,IL'),(7,'ashish','Sony WH 1000 XM5',349.99,'3001 South King Drive','1234','homeDelivery','89799','Thu Oct 27 21:21:08 CDT 2022','Sun Oct 30 21:21:08 CDT 2022','headphone','1','JEWEL','7122.W,40th.St,Stickney,IL');
/*!40000 ALTER TABLE `customerorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_accessories`
--

DROP TABLE IF EXISTS `product_accessories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_accessories` (
  `productName` varchar(30) DEFAULT NULL,
  `accessoriesName` varchar(30) DEFAULT NULL,
  KEY `productName` (`productName`),
  KEY `accessoriesName` (`accessoriesName`),
  CONSTRAINT `product_accessories_ibfk_1` FOREIGN KEY (`productName`) REFERENCES `productdetails` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `product_accessories_ibfk_2` FOREIGN KEY (`accessoriesName`) REFERENCES `productdetails` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_accessories`
--

LOCK TABLES `product_accessories` WRITE;
/*!40000 ALTER TABLE `product_accessories` DISABLE KEYS */;
INSERT INTO `product_accessories` VALUES ('fitbit_inspire2','band_replacement'),('fitbit_inspire2','charging_cable'),('amazon_halo','band_replacement'),('amazon_halo','charging_cable'),('fitbit_charge5','band_replacement'),('fitbit_charge5','charging_cable'),('amazfit_band_5','band_replacement'),('amazfit_band_5','charging_cable'),('fitbit_ace3','band_replacement'),('fitbit_ace3','charging_cable'),('moto_100','band_replacement'),('moto_100','charging_cable'),('moto_360','band_replacement'),('moto_360','charging_cable'),('apl_se','band_replacement'),('apl_se','charging_cable'),('glx_4','band_replacement'),('glx_4','charging_cable'),('glx_5','band_replacement'),('glx_5','charging_cable'),('apl_8','band_replacement'),('apl_8','charging_cable'),('sam_level','anker_powerbank'),('sam_level','charging_cable'),('sam_level','carrying_case'),('sam_buds_2','anker_powerbank'),('sam_buds_2','charging_cable'),('sam_buds_2','carrying_case'),('sony_wf1000xm4','anker_powerbank'),('sony_wf1000xm4','charging_cable'),('sony_wf1000xm4','carrying_case'),('apl_airpods_max','apl_magsafe'),('apl_airpods_max','charging_cable'),('apl_airpods_max','carrying_case'),('sony_wh1000xm5','anker_powerbank'),('sony_wh1000xm5','charging_cable'),('sony_wh1000xm5','carrying_case'),('apl_airpods_pro','apl_magsafe'),('apl_airpods_pro','charging_cable'),('apl_airpods_pro','carrying_case'),('logitech_chorus','insignia_grip_kit_meta'),('logitech_chorus','insignia_battery_meta'),('logitech_chorus','charging_cable'),('logitech_chorus','carrying_case'),('hp_reverb_g2','insignia_grip_kit_meta'),('hp_reverb_g2','insignia_battery_meta'),('hp_reverb_g2','charging_cable'),('hp_reverb_g2','carrying_case'),('sam_hmd_odyssey','insignia_grip_kit_meta'),('sam_hmd_odyssey','insignia_battery_meta'),('sam_hmd_odyssey','charging_cable'),('sam_hmd_odyssey','carrying_case'),('meta_quest2','insignia_grip_kit_meta'),('meta_quest2','insignia_battery_meta'),('meta_quest2','charging_cable'),('meta_quest2','carrying_case'),('hp_windows','insignia_grip_kit_meta'),('hp_windows','insignia_battery_meta'),('hp_windows','charging_cable'),('hp_windows','carrying_case'),('tractive_gps','pet_collar'),('tractive_gps','charging_cable'),('tile_pro','pet_collar'),('tile_pro','charging_cable'),('tile_mate','pet_collar'),('tile_mate','charging_cable'),('apl_airtag','pet_collar'),('apl_airtag','apl_magsafe'),('apl_airtag','charging_cable'),('cube','pet_collar'),('cube','charging_cable'),('sam_a53','anker_powerbank'),('sam_a53','charging_cable'),('sam_a53','backcover'),('apl_iphone11','apl_magsafe'),('apl_iphone11','charging_cable'),('apl_iphone11','backcover'),('apl_iphone14pro','apl_magsafe'),('apl_iphone14pro','charging_cable'),('apl_iphone14pro','backcover'),('moto_g_stylus','anker_powerbank'),('moto_g_stylus','charging_cable'),('moto_g_stylus','backcover'),('sam_s22_ultra','anker_powerbank'),('sam_s22_ultra','charging_cable'),('sam_s22_ultra','backcover'),('google_pixel6','anker_powerbank'),('google_pixel6','charging_cable'),('google_pixel6','backcover'),('sam_galaxybook','charger'),('sam_galaxybook','wireless_keyboard_mouse'),('apl_macbook_pro_m1','charger'),('apl_macbook_pro_m1','wireless_keyboard_mouse'),('apl_macbook_air_m2','charger'),('apl_macbook_air_m2','wireless_keyboard_mouse'),('lenovo_flex','charger'),('lenovo_flex','wireless_keyboard_mouse'),('lenovo_ideapad','charger'),('lenovo_ideapad','wireless_keyboard_mouse'),('sam_galaxybook_pro','charger'),('sam_galaxybook_pro','wireless_keyboard_mouse'),('google_nest_audio','charging_cable'),('apl_homepod_mini','charging_cable'),('google_nest_hub7','charging_cable'),('amazon_echo_gen4','amazon_echo_wallmount'),('amazon_echo_gen4','charging_cable');
/*!40000 ALTER TABLE `product_accessories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productdetails`
--

DROP TABLE IF EXISTS `productdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productdetails` (
  `ProductType` varchar(20) DEFAULT NULL,
  `Id` varchar(30) NOT NULL,
  `productName` varchar(40) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productImage` varchar(40) DEFAULT NULL,
  `productManufacturer` varchar(40) DEFAULT NULL,
  `productCondition` varchar(40) DEFAULT NULL,
  `productDiscount` double DEFAULT NULL,
  `available` int DEFAULT NULL,
  `onSale` tinyint(1) DEFAULT NULL,
  `manufacturerRebate` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetails`
--

LOCK TABLES `productdetails` WRITE;
/*!40000 ALTER TABLE `productdetails` DISABLE KEYS */;
INSERT INTO `productdetails` VALUES ('fitnessWatches','amazfit_band_5','amazfit_band_5',39.99,'amazfit_band5.jpg','Amazfit','New',10,42,0,0),('voiceAssistants','amazon_echo_gen4','Amazon Echo 4th Gen',49.99,'amazon_echo_gen4.jpg','Amazon','New',10,23,0,0),('voiceAssistants','amazon_echo_studio','Amazon Echo Studio',99.99,'amazon_echo_studio.jpg','Amazon','New',10,21,0,0),('accessories','amazon_echo_wallmount','Amazon Echo Compatible Wallmount',9.99,'amazon_echo_wallmount.jpg','Amazon','New',10,43,0,0),('fitnessWatches','amazon_halo','Amazon Halo',49.99,'amazon_halo.jpg','Amazon','New',10,23,0,0),('accessories','anker_powerbank','Anker Powerbank',39.99,'anker_powerbank.jpg','Anker','New',20,33,0,1),('smartWatches','apl_8','Apple Watch 8',399.99,'apl_8.jpg','Apple','New',5,53,1,0),('headphones','apl_airpods_max','Apple Airpods Max',599.99,'apl_airpods_max.jpg','Apple','New',10,55,1,0),('headphones','apl_airpods_pro','Apple Airpods Pro',199.99,'apl_airpods_pro.jpg','Apple','New',10,58,1,0),('petTrackers','apl_airtag','Apple Airtag',29.99,'apl_airtag.jpg','Apple','New',15,58,1,1),('voiceAssistants','apl_homepod_mini','Apple Homepod Mini',69.99,'apl_homepod_mini.jpg','Apple','New',5,47,0,0),('phones','apl_iphone11','apl_iphone11',699.99,'apl_iphone11.jpg','Apple','New',10,47,0,0),('phones','apl_iphone14pro','apl_iphone14pro',189.99,'apl_iphone14pro.jpg','Apple','Used',10,35,0,0),('laptops','apl_macbook_air_m2','Apple Macbook Air M2',1299.99,'apl_macbook_air_m2.jpg','Apple','New',15,75,1,1),('laptops','apl_macbook_pro_m1','Apple Macbook Pro M1',1999.99,'apl_macbook_pro_m1.jpg','Apple','Used',15,36,0,1),('accessories','apl_magsafe','Apple Magsafe',39.99,'apl_magsafe.jpg','Apple','New',20,46,0,1),('smartWatches','apl_se','Apple Watch SE',249.99,'apl_se.jpg','Apple','New',5,46,0,0),('accessories','backcover','Mobile Case',19.99,'backcover.jpg','Spiderman','New',5,46,0,0),('accessories','band_replacement','Band Replacement',19.99,'band_replacement.jpg','Spiderman','New',50,64,1,1),('accessories','carrying_case','Carrying Case',14.99,'carrying_case.jpg','Spiderman','New',30,46,0,1),('accessories','charger','Charger',24.99,'charger.jpg','Spiderman','New',40,64,1,1),('accessories','charging_cable','charging_cable',9.99,'charging_cable.jpg','Spiderman','New',20,46,0,1),('petTrackers','cube','Cube Pet Tracker GPS',29.99,'cube.jpg','Cube','New',15,53,1,1),('fitnessWatches','fitbit_ace3','Fitbit Ace 3',44.99,'fitbit_ace3.jpg','Fitbit','New',10,35,0,0),('fitnessWatches','fitbit_charge5','Fitbit Charge 5',44.99,'fitbit_charge5.jpg','Fitbit','New',10,36,0,0),('fitnessWatches','fitbit_inspire2','Fitbit Inspire 2',49.99,'fitbit_inspire2.jpg','Fitbit','New',10,63,1,0),('smartWatches','glx_4','Samsung Galaxy Watch 4',249.99,'glx_4.jpg','Samsung','New',5,36,0,0),('smartWatches','glx_5','Samsung Galaxy Watch 5',349.99,'glx_5.jpg','Samsung','New',5,63,1,0),('voiceAssistants','google_nest_audio','Google Nest Audio',99.99,'google_nest_audio.jpg','Google','New',5,35,0,0),('voiceAssistants','google_nest_hub7','Logitech Wireless Keyboard Mouse',49.99,'wireless_keyboard_mouse.jpg','Logitech','New',20,53,1,1),('phones','google_pixel6','google_pixel6',499.99,'google_pixel6.jpg','Google','New',10,53,1,0),('vrMap','hp_reverb_g2','HP Reverb G2',399.99,'hp_reverb_g2.jpg','HP','New',10,46,0,0),('vrMap','hp_windows','HP Windows Virtual Reality',299.99,'hp_windows.jpg','HP','New',10,75,1,0),('accessories','insignia_battery_meta','Insignia Battery for Virtual Headset',29.99,'insignia_battery_meta.jpg','Insignia','New',20,63,1,1),('accessories','insignia_grip_kit_meta','Insignia Grip Kit for Virtual Headset',39.99,'insignia_grip_kit_meta.jpg','Insignia','New',20,35,0,1),('laptops','lenovo_flex','Lenovo Flex',599.99,'lenovo_flex.jpg','Lenovo','New',15,53,1,1),('laptops','lenovo_ideapad','Lenovo Ideapad',799.99,'lenovo_ideapad.jpg','Lenovo','New',15,54,1,1),('vrMap','logitech_chorus','Logitech Chorus',349.99,'logitech_chorus.jpg','Logitech','New',10,53,1,0),('vrMap','meta_quest2','Meta Quest 2',449.99,'meta_quest2.jpg','Meta','New',10,45,0,0),('smartWatches','moto_100','Motorola Watch 100',249.99,'moto_100.jpg','Motorola','New',5,35,0,0),('smartWatches','moto_360','Motorola Watch 360',349.99,'moto_360.jpg','Motorola','New',5,53,1,0),('phones','moto_g_stylus','moto_g_stylus',499.99,'moto_g_stylus.jpg','Motorola','New',10,53,1,0),('accessories','pet_collar','Pet Collar for GPS',9.99,'pet_collar.jpg','Microsoft','New',10,35,0,0),('phones','sam_a53','sam_a53',399.99,'sam_a53.jpg','Samsung','New',10,53,1,0),('headphones','sam_buds_2','Samsung Galaxy Buds 2',149.99,'sam_buds_2.jpg','Samsung','New',10,75,1,0),('laptops','sam_galaxybook','Samsung Galaxybook',1299.99,'sam_galaxybook.jpg','Samsung','New',10,53,1,0),('laptops','sam_galaxybook_pro','Samsung Galaxybook Pro',1799.99,'sam_galaxybook_pro.jpg','Samsung','New',10,57,1,0),('vrMap','sam_hmd_odyssey','Samsung HMD Odyssey',349.99,'sam_hmd_odyssey.jpg','Samsung','New',10,75,1,0),('headphones','sam_level','Samsung Level Headphones',99.99,'sam_level.jpg','Samsung','New',10,57,1,0),('phones','sam_s22_ultra','sam_s22_ultra',999.99,'sam_s22_ultra.jpg','Samsung','New',10,75,1,0),('headphones','sony_wf1000xm4','Sony WF 1000 XM4',249.99,'sony_wf1000xm4.jpg','Sony','New',10,35,0,0),('headphones','sony_wh1000xm5','Sony WH 1000 XM5',349.99,'sony_wh1000xm5.jpg','Sony','New',10,53,1,0),('petTrackers','tile_mate','Tile Mate GPS',29.99,'tile_mate.jpg','Tile','New',15,53,1,1),('petTrackers','tile_pro','Tile Pro',39.99,'tile_pro.jpg','Tile','New',15,35,0,1),('petTrackers','tractive_gps','Tractive GPS',29.99,'tractive_gps.jpg','Tractive','New',15,24,0,1),('accessories','wireless_keyboard_mouse','Logitech Wireless Keyboard Mouse',49.99,'wireless_keyboard_mouse.jpg','Logitech','New',20,24,0,1);
/*!40000 ALTER TABLE `productdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) DEFAULT NULL,
  `repassword` varchar(40) DEFAULT NULL,
  `usertype` varchar(40) DEFAULT NULL,
  `userAddress` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('ashish','ash123','ash123','retailer','IllinoisTech'),('chris','cbreeze','cbreeze','customer','Madison,Wisconsin'),('coolman','cooldude','cooldude','customer','1111 N, California'),('gopi','go123','go123','manager','IllinoisTech'),('hotman','hotdude','hotdude','customer','Clearlake,Texas'),('jashwanth','jash123','jash123','customer','12 Salt Island Rd,(MA)'),('john','jostar','jostar','customer','mccormick,IL'),('krishna','krish','krish','customer','5764 Curnie, Ohio(OH)'),('pavan','paw123','paw123','customer','Clearlake,Texas'),('raghav','ragha','ragha','customer','8641 Wood St, Michigan(MI)'),('rishit','123','123','customer','3001 S King Drive'),('ritvik','ritvik123','ritvik123','customer','Chicago,IL'),('waterman','lakewater','lakewater','customer','4243 N,state st,Idaho');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` int NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `pincode` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'WALLMART','3473,S.Martin.Luther.King.Dr,Chicago,IL',60616),(2,'BESTBUY','47.W,Division.St,Chicago,IL',60610),(3,'SEVENELEVEN','917.W,Washington.Blvd,Chicago,IL',60607),(4,'WALGREENS','501.W,Roosevelt.Rd,Chicago,IL',60607),(5,'TARGET','2340.W,Madison.St,Chicago,IL',60612),(6,'GOGROCER','101.N,Upper.Wacker.Dr.Suite.102,Chicago,IL',60606),(7,'MACYS','4700.S,Halsted.St,Chicago,IL',60609),(8,'JEWEL','7122.W,40th.St,Stickney,IL',60402),(9,'BURLINGTON','516.N,OGDEN.AVE,MAILROOM,CHICAGO,IL',60642),(10,'TJMAX','1758.W,WABANSIA.AVE,CHICAGO,IL',60622);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-27 21:51:13
