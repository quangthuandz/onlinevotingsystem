-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinevotingsystem
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Salt` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Dob` date NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Job` varchar(45) NOT NULL,
  `IsAdmin` tinyint NOT NULL,
  `IsActive` tinyint NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Storing Account details of admin and user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','8WJGYOQe7IO7ORS2BXBUW+79ElPR4TQfhh126l1j1I8=','7H9RGPC7Du4djNjlw0fd8g==','da doi','admin','admin@gmail.com','0903219899','Hoa Lac','2000-12-31','female','Other',1,1),(9,'khanh','s7/J2+rwY+UTbIdtPN2FnGQMQkK/OR4L4rTGvffzU34=','uz+9QtCBxTa0zCSGwZ8XGA==','Đã đổi','Hoàng','hoangnhatkhanh0708@gmail.com','0849070800','Times City','2002-08-07','female','Other',0,1),(20,'thuan','Dm/vtL3eE4mGMAaj+fQojL1kLOjYu6YetnptDdj8TOU=','T7qZi9hqL5TrGBxDhkoIYg==','quang','thuan','quangthuan210103@gmail.com','0904930588','HP','2023-05-31','Male','Developer',0,1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaign` (
  `CampaignId` int NOT NULL AUTO_INCREMENT,
  `CampaignName` varchar(45) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `CampaignDescription` varchar(300) NOT NULL,
  `CampaignImg` varchar(500) NOT NULL,
  `CreatedBy` int DEFAULT NULL,
  `VotingRuleId` int DEFAULT NULL,
  `isPublic` tinyint DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CampaignId`),
  KEY `Host_idx` (`CreatedBy`),
  KEY `RuleId_idx` (`VotingRuleId`),
  CONSTRAINT `HostId` FOREIGN KEY (`CreatedBy`) REFERENCES `account` (`UserId`),
  CONSTRAINT `RuleId` FOREIGN KEY (`VotingRuleId`) REFERENCES `votingrule` (`VotingRuleId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES (4,'Honda or Yamaha','2023-05-15 00:00:00','2023-07-31 00:00:00','Enter the choice between 2 famous motorbike of Japan.','images\\hvsy.jpg',9,2,1,'123',''),(7,'Python vs Java','2023-05-15 00:00:00','2023-05-15 00:00:00','ok','images\\hvsy.jpg',1,NULL,1,NULL,''),(11,'Who will win?','2023-06-25 19:01:26','2023-06-28 20:01:25','Choose the winner of the competition','images\\hvsy.jpg',9,2,0,'123',''),(12,'Người ấy là ai?','2023-07-17 09:16:09','2023-07-19 10:16:08','Chọn 1 người bất kỳ','images\\nala.jpg',9,2,1,NULL,''),(13,'Best Campaign','2023-07-17 09:16:09','2023-07-21 10:16:08','Which is the best?','images\\nala.jpg',9,1,0,'123','In Progress'),(14,'Best Major','2023-07-17 13:25:14','2023-07-19 15:25:13','Which is the best major?','images/vote.jpg',9,1,0,'123',NULL);
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaignaccount`
--

DROP TABLE IF EXISTS `campaignaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaignaccount` (
  `CampaignAccountId` int NOT NULL AUTO_INCREMENT,
  `CampaignId` int NOT NULL,
  `AccountId` int NOT NULL,
  `IsHost` tinyint NOT NULL DEFAULT '0',
  `VotingResult` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CampaignAccountId`),
  KEY `CampaignIs_idx` (`CampaignId`),
  KEY `AccountId_idx` (`AccountId`),
  CONSTRAINT `AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`UserId`),
  CONSTRAINT `CampaignAccountId` FOREIGN KEY (`CampaignId`) REFERENCES `campaign` (`CampaignId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This is table to store AccountId to a corresponding Campaign (User joined Campaign)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaignaccount`
--

LOCK TABLES `campaignaccount` WRITE;
/*!40000 ALTER TABLE `campaignaccount` DISABLE KEYS */;
INSERT INTO `campaignaccount` VALUES (1,4,9,0,NULL),(2,7,9,0,NULL),(3,7,1,0,NULL),(4,4,1,0,NULL),(5,11,9,0,NULL),(6,11,9,1,NULL),(7,12,9,1,NULL),(8,12,1,0,NULL),(9,12,9,1,NULL),(10,14,9,0,NULL),(11,14,9,1,NULL);
/*!40000 ALTER TABLE `campaignaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `CommentId` int NOT NULL AUTO_INCREMENT,
  `AccountId` int NOT NULL,
  `ThreadId` int NOT NULL,
  `Content` varchar(1024) NOT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  `ParentCommentId` int DEFAULT NULL,
  PRIMARY KEY (`CommentId`),
  KEY `CommentAccount_idx` (`AccountId`),
  KEY `CommentThread_idx` (`ThreadId`),
  CONSTRAINT `CommentAccount` FOREIGN KEY (`AccountId`) REFERENCES `campaignaccount` (`AccountId`),
  CONSTRAINT `CommentThread` FOREIGN KEY (`ThreadId`) REFERENCES `thread` (`ThreadId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This table stores both comments and replies to other comments';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,9,23,'hello','2023-06-26 00:14:55',1),(2,9,22,'ẩy ồi','2023-07-02 10:37:31',1),(3,9,20,'quá chất','2023-07-02 20:57:01',NULL),(4,9,20,'hay','2023-07-02 20:58:03',NULL),(5,9,20,'q','2023-07-02 21:45:00',3),(6,9,20,'nhờn','2023-07-02 21:51:41',3),(7,9,20,'oke chưa','2023-07-02 21:51:51',3),(8,9,20,'tại sao nhỉ','2023-07-02 21:52:57',4),(9,9,23,'hay nhỉ','2023-07-02 21:55:40',NULL),(10,9,23,'waeee','2023-07-02 21:55:46',9),(11,9,23,'vô lý','2023-07-02 21:56:33',9),(12,9,23,'Java là chân ái','2023-07-02 21:58:39',NULL),(13,9,22,'chuẩn đấy','2023-07-03 08:09:51',NULL),(14,9,39,'đẹp rồi','2023-07-03 09:22:55',NULL),(15,9,39,'quá chất','2023-07-03 09:23:03',14),(16,9,39,'ghê chưa','2023-07-03 09:23:11',NULL),(17,9,20,'nicer','2023-07-16 20:06:43',3),(18,9,9,'oke','2023-07-16 22:04:44',NULL),(19,9,40,'được chưa','2023-07-16 22:17:25',NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `LikeId` int NOT NULL AUTO_INCREMENT,
  `AccountId` int NOT NULL,
  `ThreadId` int NOT NULL,
  `CommentId` int DEFAULT NULL,
  `LikeType` int NOT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`LikeId`),
  KEY `LikeAccount_idx` (`AccountId`),
  KEY `LikeThread_idx` (`ThreadId`),
  CONSTRAINT `LikeAccount` FOREIGN KEY (`AccountId`) REFERENCES `campaignaccount` (`AccountId`),
  CONSTRAINT `LikeThread` FOREIGN KEY (`ThreadId`) REFERENCES `thread` (`ThreadId`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (102,1,21,NULL,1,NULL),(105,1,23,NULL,0,NULL),(109,1,26,NULL,0,NULL),(110,9,26,NULL,0,NULL),(124,9,23,NULL,1,NULL),(125,9,22,NULL,1,NULL),(127,9,20,17,1,NULL),(128,9,20,7,0,NULL),(131,9,21,NULL,1,NULL),(134,9,1,NULL,1,NULL),(135,9,20,NULL,0,NULL),(136,9,40,NULL,1,NULL);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificaitonId` int NOT NULL AUTO_INCREMENT,
  `receiverId` int NOT NULL,
  `content` varchar(2048) NOT NULL,
  `postId` int DEFAULT NULL,
  `commentId` int DEFAULT NULL,
  `senderId` int DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `isRead` tinyint DEFAULT '0',
  PRIMARY KEY (`notificaitonId`),
  KEY `receiverId` (`receiverId`),
  KEY `postId` (`postId`),
  KEY `commentId` (`commentId`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`receiverId`) REFERENCES `campaignaccount` (`AccountId`),
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`postId`) REFERENCES `thread` (`ThreadId`),
  CONSTRAINT `notification_ibfk_3` FOREIGN KEY (`commentId`) REFERENCES `comment` (`CommentId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,1,' liked your thread ',1,NULL,9,'2023-07-16 15:08:23',0),(2,9,'Added you to Best Major',NULL,NULL,9,'2023-07-17 06:25:04',1);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `OtpId` int NOT NULL AUTO_INCREMENT,
  `Otp` varchar(10) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  PRIMARY KEY (`OtpId`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES (1,'123456','khanhhn.hoang@gmail.com','2023-05-13 02:03:29'),(2,'864430','khanhhn.hoang@gmail.com','2023-05-16 14:39:12'),(3,'894412','khanhhn.hoang@gmail.com','2023-05-16 14:53:49'),(4,'893651','khanhhn.hoang@gmail.com','2023-05-16 15:35:00'),(5,'887425','khanhhn.hoang@gmail.com','2023-05-16 15:37:00'),(6,'870790','khanhhn.hoang@gmail.com','2023-05-16 15:39:31'),(7,'463023','khanhhn.hoang@gmail.com','2023-05-16 15:41:06'),(8,'881968','khanhhn.hoang@gmail.com','2023-05-16 16:00:27'),(9,'169281','khanhhn.hoang@gmail.com','2023-05-16 16:07:00'),(10,'615774','khanhhn.hoang@gmail.com','2023-05-16 16:09:56'),(11,'736163','khanhhn.hoang@gmail.com','2023-05-16 19:50:45'),(12,'224570','khanhhn.hoang@gmail.com','2023-05-16 19:53:44'),(13,'668570','khanhhn.hoang@gmail.com','2023-05-16 20:42:56'),(14,'439548','ducanh041103@gmail.com','2023-05-17 10:21:12'),(15,'794345','khanhhn.hoang@gmail.com','2023-05-17 10:24:03'),(16,'498487','khanhhn.hoang@gmail.com','2023-05-17 10:26:31'),(17,'484130','khanhhn.hoang@gmail.com','2023-05-19 12:30:49'),(18,'354861','khanhhn.hoang@gmail.com','2023-05-19 12:35:16'),(19,'533864','khanhhn.hoang@gmail.com','2023-05-19 12:35:50'),(20,'189231','khanhhn.hoang@gmail.com','2023-05-19 12:38:27'),(21,'331026','khanhhn.hoang@gmail.com','2023-05-19 12:39:14'),(22,'205424','khanhhn.hoang@gmail.com','2023-05-19 12:40:27'),(23,'569655','khanhhn.hoang@gmail.com','2023-05-19 12:40:52'),(24,'560538','khanhhn.hoang@gmail.com','2023-05-19 12:46:24'),(25,'079545','khanhhn.hoang@gmail.com','2023-05-19 12:47:04'),(26,'320844','khanhhn.hoang@gmail.com','2023-05-19 12:47:08'),(27,'411001','khanhhn.hoang@gmail.com','2023-05-22 07:31:49'),(28,'442470','khanhhn.hoang@gmail.com','2023-05-26 00:00:09'),(29,'946142','khanhhn.hoang@gmail.com','2023-05-26 12:06:34'),(30,'734619','khanhhn.hoang@gmail.com','2023-05-26 12:12:15'),(31,'350179','khanhhn.hoang@gmail.com','2023-05-26 12:14:04'),(32,'578816','khanhhn.hoang@gmail.com','2023-05-26 12:16:49'),(33,'478534','khanhhn.hoang@gmail.com','2023-05-26 12:20:12'),(34,'403791','khanhhn.hoang@gmail.com','2023-05-26 12:26:06'),(35,'224322','khanhhn.hoang@gmail.com','2023-05-26 12:27:42'),(36,'569622','khanhhn.hoang@gmail.com','2023-05-26 12:29:48'),(37,'743181','khanhhn.hoang@gmail.com','2023-05-26 23:46:34'),(38,'037011','khanhhn.hoang@gmail.com','2023-05-26 23:48:55'),(39,'086164','dimitri@gmail.com','2023-05-29 14:58:26'),(40,'943372','hoangnhatkhanh0708@gmail.com','2023-06-01 11:12:24'),(41,'196885','kid.from.past@gmail.com','2023-06-01 13:08:32'),(42,'008694','hoangnhatkhanh0708@gmail.com','2023-06-02 14:34:07'),(43,'670099','hoangnhatkhanh0708@gmail.com','2023-06-02 15:20:51'),(44,'526309','hoangnhatkhanh0708@gmail.com','2023-06-02 15:24:09'),(45,'330796','hoangnhatkhanh0708@gmail.com','2023-06-02 15:27:09'),(46,'725096','hoangnhatkhanh0708@gmail.com','2023-06-02 15:27:23'),(47,'256705','hoangnhatkhanh0708@gmail.com','2023-06-04 22:32:01'),(48,'575983','hoangnhatkhanh0708@gmail.com','2023-06-04 22:35:19'),(49,'068968','khanhhn.hoang@gmail.com','2023-06-04 22:37:06'),(50,'611330','khanhhn.hoang@gmail.com','2023-06-05 08:42:08'),(51,'878276','khanhhn.hoang@gmail.com','2023-06-05 08:57:43'),(52,'119803','quanthuan210103@gmail.com','2023-06-12 14:15:03'),(53,'842295','quanthuan210103@gmail.com','2023-06-12 14:16:01'),(54,'053306','quanthuan210103@gmail.com','2023-06-12 14:18:20'),(55,'805347','quanthuan210103@gmail.com','2023-06-12 14:19:45'),(56,'758468','quangthuan210103@gmail.com','2023-06-12 14:20:29'),(57,'123944','quangthuan210103@gmail.com','2023-06-12 14:21:32'),(58,'526976','khanhhn.hoang@gmail.com','2023-06-23 21:32:58'),(59,'785755','hoangnhatkhanh0708@gmail.com','2023-06-23 21:33:24'),(60,'862517','hoangnhatkhanh0708@gmail.com','2023-06-23 21:36:53'),(61,'003741','hoangnhatkhanh0708@gmail.com','2023-06-23 21:41:49'),(62,'124613','khanhhn.hoang@gmail.com','2023-06-23 21:42:20');
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinned`
--

DROP TABLE IF EXISTS `pinned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pinned` (
  `CampaignId` int NOT NULL,
  `UserId` int NOT NULL,
  `isPinned` tinyint NOT NULL,
  PRIMARY KEY (`CampaignId`,`UserId`),
  KEY `User Id` (`UserId`),
  CONSTRAINT `Campaign Id` FOREIGN KEY (`CampaignId`) REFERENCES `campaign` (`CampaignId`),
  CONSTRAINT `User Id` FOREIGN KEY (`UserId`) REFERENCES `account` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pinned`
--

LOCK TABLES `pinned` WRITE;
/*!40000 ALTER TABLE `pinned` DISABLE KEYS */;
INSERT INTO `pinned` VALUES (4,9,1),(12,9,1);
/*!40000 ALTER TABLE `pinned` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thread`
--

DROP TABLE IF EXISTS `thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thread` (
  `ThreadId` int NOT NULL AUTO_INCREMENT,
  `AccountId` int NOT NULL,
  `CampaignId` int NOT NULL,
  `Title` varchar(250) DEFAULT NULL,
  `Content` varchar(2048) NOT NULL,
  `CreatedAt` datetime NOT NULL,
  `ViewCount` int NOT NULL DEFAULT '0',
  `IsPinned` tinyint NOT NULL DEFAULT '0',
  `IsDeleted` tinyint NOT NULL DEFAULT '0',
  `ImagePath` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ThreadId`),
  KEY `ThreadAccount_idx` (`AccountId`),
  KEY `ThreadCampaign_idx` (`CampaignId`),
  CONSTRAINT `ThreadAccount` FOREIGN KEY (`AccountId`) REFERENCES `campaignaccount` (`AccountId`),
  CONSTRAINT `ThreadCampaign` FOREIGN KEY (`CampaignId`) REFERENCES `campaign` (`CampaignId`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thread`
--

LOCK TABLES `thread` WRITE;
/*!40000 ALTER TABLE `thread` DISABLE KEYS */;
INSERT INTO `thread` VALUES (1,1,4,'Hello','Xin chào','2023-06-23 00:00:00',100,0,0,'/img'),(2,9,4,'Hi','Hi','2023-06-23 00:00:00',99,0,0,'/img'),(9,9,4,NULL,'ngu vl ','2023-06-24 10:18:29',0,0,0,NULL),(20,9,4,NULL,'test\r\n','2023-06-25 20:30:36',0,0,0,NULL),(21,9,7,NULL,'Python dễ sống hơn','2023-06-25 20:32:29',0,0,0,NULL),(22,9,12,NULL,'thằng Hanh xứng đáng đấy','2023-06-25 20:55:35',0,0,0,NULL),(23,1,7,NULL,'Java code thích hơn','2023-06-25 23:57:46',0,0,0,NULL),(26,1,7,NULL,'Hello\r\n','2023-06-26 09:54:35',0,0,1,NULL),(29,9,12,NULL,'mới nè','2023-07-03 09:03:14',0,0,0,NULL),(30,9,12,NULL,'bài thứ 3','2023-07-03 09:06:51',0,0,0,NULL),(31,9,12,NULL,'bài thứ 4','2023-07-03 09:06:56',0,0,0,NULL),(32,9,12,NULL,'bài thứ 5','2023-07-03 09:07:02',0,0,0,NULL),(33,9,12,NULL,'sang trang mới đi','2023-07-03 09:07:08',0,0,0,NULL),(34,9,12,NULL,'bài thứ 7 rồi','2023-07-03 09:07:22',0,0,0,NULL),(35,9,12,NULL,'nữa nè','2023-07-03 09:18:34',0,0,0,NULL),(36,9,12,NULL,'even more\r\n','2023-07-03 09:18:41',0,0,0,NULL),(37,9,12,NULL,'mãi không lên trang 3','2023-07-03 09:18:47',0,0,0,NULL),(38,9,12,NULL,'lên chưa','2023-07-03 09:18:51',0,0,0,NULL),(39,9,12,NULL,'yê','2023-07-03 09:18:55',0,0,0,NULL),(40,9,4,'Lỗi','xin chào\r\n','2023-07-16 22:17:20',0,1,0,NULL),(41,9,12,'Lỗi','**** you','2023-07-17 10:05:56',0,1,0,NULL),(42,9,12,'Lỗi','hello\r\n','2023-07-17 10:06:18',0,1,0,NULL);
/*!40000 ALTER TABLE `thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vote` (
  `VoteId` int NOT NULL AUTO_INCREMENT,
  `VoterId` int NOT NULL,
  `VotingObjectId` int NOT NULL,
  `Campaign` int NOT NULL,
  `Point` int NOT NULL,
  PRIMARY KEY (`VoteId`),
  KEY `VoteFor_idx` (`VotingObjectId`),
  KEY `VoteBy_idx` (`VoterId`),
  KEY `CampaignId_idx` (`Campaign`),
  CONSTRAINT `Campaign` FOREIGN KEY (`Campaign`) REFERENCES `campaign` (`CampaignId`),
  CONSTRAINT `VoterId` FOREIGN KEY (`VoterId`) REFERENCES `campaignaccount` (`AccountId`),
  CONSTRAINT `VotingObjectId` FOREIGN KEY (`VotingObjectId`) REFERENCES `votingobject` (`VotingObjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (15,9,1,4,1),(16,9,1,4,2),(17,9,6,12,6),(18,9,3,12,5),(19,9,5,12,4),(20,9,4,12,3),(21,9,7,12,2),(22,9,8,12,1),(23,9,9,13,1);
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votingobject`
--

DROP TABLE IF EXISTS `votingobject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `votingobject` (
  `VotingObjectId` int NOT NULL AUTO_INCREMENT,
  `VotingObjectName` varchar(45) NOT NULL,
  `VotingObjectType` varchar(45) NOT NULL,
  `CampaignId` int NOT NULL,
  `ImgPath` varchar(200) DEFAULT NULL,
  `Question` varchar(2048) DEFAULT NULL,
  `Description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`VotingObjectId`),
  KEY `CampaignId_idx` (`CampaignId`),
  CONSTRAINT `CampaignId` FOREIGN KEY (`CampaignId`) REFERENCES `campaign` (`CampaignId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votingobject`
--

LOCK TABLES `votingobject` WRITE;
/*!40000 ALTER TABLE `votingobject` DISABLE KEYS */;
INSERT INTO `votingobject` VALUES (1,'Honda','Brand',4,'images/hondapng.png',NULL,'Honda Civic'),(2,'Yamaha','Brand',4,'images/yamaha.png',NULL,'Yamaha Exciter'),(3,'Hanh','1',12,'1687700059614.jpg','Ai nhận QBV','Hanh'),(4,'Vinh','1',12,'1687700072912.jpg','Ai nhận QBV','Vinh'),(5,'Đức Anh','1',12,'1687700084312.jpg','Ai nhận QBV','Đức Anh'),(6,'Khánh','1',12,'1687700101093.jpg','Ai nhận QBV','Khánh'),(7,'Toàn','1',12,'1687700109920.png','Ai nhận QBV','Toàn'),(8,'Thuận ','1',12,'1687700119427.jpg','Ai nhận QBV','Thuận'),(9,'Yeah sure','2',13,'images/1687712433621.jpg','Không học IT nữa nhé?','Cút thôi'),(10,'Nope','2',13,'images/1687712445159.png','Không học IT nữa nhé?','Cố lên anh bạn'),(11,'Erling Haaland','1',12,'images/1689560050359.jpg','Ai nhận QBV','ST'),(12,'Lionel Messi','1',12,'images/1689560069312.jpg','Ai nhận QBV','PSG'),(13,'Kevin De Bruyne','1',12,'images/1689560117193.jpg','Ai nhận QBV','MCI'),(14,'Computer Science','2',14,'images/1689575065234.jpg','Hay nhất?','nice'),(15,'Business Admin','2',14,'images/1689575082172.jpg','Hay nhất?','nicer');
/*!40000 ALTER TABLE `votingobject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votingrule`
--

DROP TABLE IF EXISTS `votingrule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `votingrule` (
  `VotingRuleId` int NOT NULL AUTO_INCREMENT,
  `VotingRuleName` varchar(45) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`VotingRuleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votingrule`
--

LOCK TABLES `votingrule` WRITE;
/*!40000 ALTER TABLE `votingrule` DISABLE KEYS */;
INSERT INTO `votingrule` VALUES (1,'Normal','Select one - Best win'),(2,'Ranking','Rank all - Best win');
/*!40000 ALTER TABLE `votingrule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-17 13:26:17
