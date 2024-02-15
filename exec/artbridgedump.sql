-- MySQL dump 10.13  Distrib 8.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: artbridge
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `artist_seq` bigint NOT NULL AUTO_INCREMENT,
  `artist_id` varchar(30) NOT NULL,
  `artist_name` varchar(30) NOT NULL,
  `artist_pwd` varchar(255) NOT NULL COMMENT '회원가입 시 입력하는 비밀번호',
  `artist_nickname` varchar(30) NOT NULL COMMENT '회원가입 시 입력하는 필명(pen name)',
  `artist_email` varchar(50) NOT NULL COMMENT '회원가입 시 입력하는 이메일',
  `artist_contact` varchar(11) NOT NULL COMMENT '회원가입 시 입력하는 연락처(전화번호)',
  `artist_point` bigint NOT NULL DEFAULT '0',
  `artist_history` varchar(300) DEFAULT NULL,
  `artist_isdeleted` tinyint(1) NOT NULL DEFAULT '0',
  `artist_deleted_date` datetime DEFAULT NULL,
  `artist_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`artist_seq`),
  UNIQUE KEY `artist_id` (`artist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'sssss','퍄퍄퍄','111111','라라','ssafy@naver.com','01011112222',0,'할수 있어',0,NULL,'2024-01-29 04:54:33'),(2,'hhh','김퍄퍄','111111','fsd','hhh@naver.com','01011112222',0,'할수 있어',0,NULL,'2024-01-29 04:54:33'),(3,'123','123','123123','123','123@naver.com','11111111111',0,'할수 있어',0,NULL,'2024-01-29 04:54:33'),(4,'555','55','555555','5555','55@naevr.com','55',0,'할수 있어',0,NULL,'2024-01-29 04:54:33'),(5,'ssafy','ssafy','ssafy1','ssafy','ssafy@naver.com','01012345678',0,'할수 있어',0,NULL,'2024-01-29 04:54:33'),(86,'arin99','김아린','ssafy1234','상남자','arin@naver.com','01011111111',0,'할수 있어',0,NULL,'2024-01-29 04:54:33'),(87,'dfdff0','김아린','123456','쌉가능','aring@gmail.com','1234567',0,'할 수 있어',0,NULL,'2024-02-07 16:07:46'),(88,'dfdffdd0','김아dd린','12345dd6','쌉가능','aring@gmail.com','1234567',0,'할 수 있어',0,NULL,'2024-02-07 16:09:26'),(89,'john_doe','John Doe','password123','JD','john.doe@example.com','1234567890',0,'Some history about the artist',0,NULL,'2024-02-07 16:56:28'),(90,'john_doe3','John Doe','password123','JD','john.doe@example.com','1234567890',0,'Some history about the artist',0,NULL,'2024-02-07 16:56:35'),(128,'hong','홍길동','password','hong','hong@example.com','01012345678',0,NULL,0,NULL,'2024-02-14 02:01:24'),(129,'alexander930828','이동훈','1234','안녕','alexander930828@gmail.com','01022369308',0,'alexander930828@gmail.com',0,NULL,'2024-02-15 10:47:09'),(130,'alexander','이작가','secret123','조선제일붓','alexander930828@kakao.com','01022369308',0,NULL,0,NULL,'2024-02-16 00:58:07'),(131,'test','이동훈','test123','test','test@test.com','01012345678',0,'test@test.com',0,NULL,'2024-02-15 16:10:45'),(132,'ddd','d','dddddd','dd','test@test.com','01012345678',0,'test@test.com',0,NULL,'2024-02-15 16:24:57'),(133,'dddd','ddd','dddddd','ddd','test@test.com','01012345678',0,'test@test.com',0,NULL,'2024-02-15 16:25:44');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_homepage_comment`
--

DROP TABLE IF EXISTS `artist_homepage_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_homepage_comment` (
  `artist_homepage_comment_seq` bigint NOT NULL AUTO_INCREMENT,
  `artist_seq` bigint DEFAULT NULL,
  `member_seq` bigint DEFAULT NULL,
  `artist_homepage_comment_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `artist_homepage_comment_isdeleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '삭제 여부',
  `artist_homepage_comment_deleted_date` datetime DEFAULT NULL COMMENT '삭제 일시',
  `artist_homepage_comment_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`artist_homepage_comment_seq`),
  KEY `FK3plpc9s0ueshxmecbwtm8s3dy` (`artist_seq`),
  KEY `FKpbyc2sffe16xq2u4skoamfvwm` (`member_seq`),
  CONSTRAINT `FK3plpc9s0ueshxmecbwtm8s3dy` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`artist_seq`),
  CONSTRAINT `FKpbyc2sffe16xq2u4skoamfvwm` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_homepage_comment`
--

LOCK TABLES `artist_homepage_comment` WRITE;
/*!40000 ALTER TABLE `artist_homepage_comment` DISABLE KEYS */;
INSERT INTO `artist_homepage_comment` VALUES (87,88,1,'2024-02-15 11:38:57',0,NULL,NULL),(88,88,1,'2024-02-15 13:04:20',0,NULL,'This is a test comment.'),(89,86,51,'2024-02-15 04:52:00',0,NULL,NULL),(90,86,51,'2024-02-15 04:54:33',0,NULL,NULL),(91,86,51,'2024-02-15 04:54:34',0,NULL,NULL),(92,86,51,'2024-02-15 04:55:01',0,NULL,NULL),(93,86,51,'2024-02-15 04:55:21',0,NULL,'d'),(94,86,51,'2024-02-15 04:56:04',0,NULL,'4534634634'),(95,86,51,'2024-02-15 04:59:16',0,NULL,'ㅇ'),(96,86,51,'2024-02-15 05:01:58',0,NULL,'ㅇ'),(97,86,51,'2024-02-15 05:02:07',0,NULL,'ㅇ'),(98,86,51,'2024-02-15 05:02:20',0,NULL,'ㅇ'),(99,86,51,'2024-02-15 05:02:28',0,NULL,'ㅇ'),(100,86,51,'2024-02-15 05:07:53',0,NULL,'ㅇ'),(101,88,1,'2024-02-15 14:20:39',0,NULL,'This is a test comment.'),(102,88,1,'2024-02-15 14:22:04',0,NULL,'This is a test comment.'),(103,86,51,'2024-02-15 05:23:18',0,NULL,'d'),(104,88,1,'2024-02-15 14:23:36',0,NULL,'This is a test comment.'),(105,86,51,'2024-02-15 05:24:22',0,NULL,'ㅇㅇ'),(106,88,1,'2024-02-15 05:29:46',0,NULL,'This is a test comment.'),(107,88,1,'2024-02-15 05:36:39',0,NULL,'This is a test comment.'),(108,88,1,'2024-02-15 14:37:47',0,NULL,'This is a test comment.');
/*!40000 ALTER TABLE `artist_homepage_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_mention`
--

DROP TABLE IF EXISTS `artist_mention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_mention` (
  `artist_mention_seq` bigint NOT NULL AUTO_INCREMENT,
  `artist_seq` bigint DEFAULT NULL,
  `artist_mention_content` varchar(255) NOT NULL,
  `artist_mention_subject` varchar(255) NOT NULL,
  `artist_mention_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `artist_mention_isremoved` tinyint(1) NOT NULL DEFAULT '0' COMMENT '삭제 여부',
  `artist_mention_deleted_date` datetime DEFAULT NULL COMMENT '삭제 일시',
  `artist_mention_isdeleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`artist_mention_seq`),
  KEY `FK8dp9tgimfbkbo17jqmgabcsiq` (`artist_seq`),
  CONSTRAINT `FK8dp9tgimfbkbo17jqmgabcsiq` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`artist_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_mention`
--

LOCK TABLES `artist_mention` WRITE;
/*!40000 ALTER TABLE `artist_mention` DISABLE KEYS */;
INSERT INTO `artist_mention` VALUES (143,3,'죽는다.','빌드를 못하는 자는','2024-02-13 17:31:36',0,NULL,_binary '\0'),(144,3,'죽는다.','빌드를 못하는 자는','2024-02-13 17:35:02',0,NULL,_binary '\0'),(145,3,'죽는다.','빌드를 못하는 자는','2024-02-13 08:43:49',0,NULL,_binary '\0'),(158,86,'안녕하세요~ddd','안녕하세요~d','2024-02-15 02:24:11',0,NULL,_binary '\0'),(170,86,'d','d','2024-02-15 05:34:24',0,NULL,_binary '\0'),(171,86,'가고싶어 ㅠ','집','2024-02-15 06:27:48',0,NULL,_binary '\0');
/*!40000 ALTER TABLE `artist_mention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_mention_comment`
--

DROP TABLE IF EXISTS `artist_mention_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_mention_comment` (
  `artist_mention_comment_seq` bigint NOT NULL AUTO_INCREMENT,
  `member_seq` bigint DEFAULT NULL,
  `artist_mention_seq` bigint NOT NULL,
  `artist_mention_comment_content` varchar(255) NOT NULL,
  `artist_mention_comment_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `artist_mention_comment_isdeleted` tinyint(1) NOT NULL DEFAULT '0',
  `artist_mention_comment_deleted_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`artist_mention_comment_seq`),
  KEY `FKga3hb92ykto36w75whsyc982e` (`artist_mention_seq`),
  KEY `FKm84s9ahb35hy7o06nedsj52x2` (`member_seq`),
  CONSTRAINT `FKga3hb92ykto36w75whsyc982e` FOREIGN KEY (`artist_mention_seq`) REFERENCES `artist_mention` (`artist_mention_seq`),
  CONSTRAINT `FKm84s9ahb35hy7o06nedsj52x2` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_mention_comment`
--

LOCK TABLES `artist_mention_comment` WRITE;
/*!40000 ALTER TABLE `artist_mention_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `artist_mention_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction` (
  `auction_seq` int NOT NULL AUTO_INCREMENT,
  `item_seq` int NOT NULL COMMENT '자동 부여되는 작품 seq 번호',
  `auction_scheduled_time` datetime NOT NULL COMMENT '경매가 이후에 진행될 예정 시각(최소 일주일)',
  `auction_status` int NOT NULL COMMENT '경매 상태(ENUM, 취소, 유찰, 낙찰, 진행중)',
  `auction_start_point` int NOT NULL COMMENT '경매 작품 시작가',
  `auction_ask_point` int NOT NULL DEFAULT '10' COMMENT '경매 호가 퍼센트',
  `auction_sell_point` bigint DEFAULT NULL COMMENT '경매 진행 이후 최종 낙찰 가격',
  `auction_winner` int DEFAULT NULL COMMENT '낙찰자의 seq 번호',
  `auction_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '경매가 처음 등록된 시간',
  `auction_canceled_date` datetime DEFAULT NULL COMMENT '경매가 취소됐을 경우의 취소된 시간',
  `auction_ismiscarried` tinyint(1) NOT NULL DEFAULT '0',
  `auction_win_date` datetime DEFAULT NULL,
  `auction_session_id` varchar(12) DEFAULT NULL,
  `auction_miscarried_date` datetime DEFAULT NULL,
  PRIMARY KEY (`auction_seq`),
  KEY `FKsgfl8fk06dglk7hxxf8t6pi89` (`item_seq`),
  CONSTRAINT `FKsgfl8fk06dglk7hxxf8t6pi89` FOREIGN KEY (`item_seq`) REFERENCES `item` (`item_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (152,232,'2024-02-15 15:00:00',1,100000,5000,NULL,NULL,'2024-02-15 08:00:00',NULL,0,NULL,NULL,NULL),(153,232,'2024-02-15 15:00:00',1,100000,5000,NULL,NULL,'2024-02-15 08:28:12',NULL,0,NULL,'7yw-bs8-fwj',NULL),(154,232,'2024-02-15 15:00:00',1,100000,5000,NULL,NULL,'2024-02-15 10:55:58',NULL,0,NULL,'em0-q55-4f1',NULL);
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction_like`
--

DROP TABLE IF EXISTS `auction_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction_like` (
  `member_seq` bigint NOT NULL,
  `auction_seq` int NOT NULL COMMENT '경매 개시될 경우 자동 부여되는 ID',
  KEY `FKaolpvwo3so2bouwgf30fgp919` (`auction_seq`),
  KEY `FKh8jx75g9ja84ukl5e6bvmdw24` (`member_seq`),
  CONSTRAINT `FKaolpvwo3so2bouwgf30fgp919` FOREIGN KEY (`auction_seq`) REFERENCES `auction` (`auction_seq`),
  CONSTRAINT `FKh8jx75g9ja84ukl5e6bvmdw24` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_like`
--

LOCK TABLES `auction_like` WRITE;
/*!40000 ALTER TABLE `auction_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `auction_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction_point_detail`
--

DROP TABLE IF EXISTS `auction_point_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction_point_detail` (
  `auction_point_detail_seq` int NOT NULL AUTO_INCREMENT,
  `auction_seq` int NOT NULL COMMENT '경매 개시될 경우 자동 부여되는 ID',
  `member_seq` bigint DEFAULT NULL,
  `auction_point_detail_point` int NOT NULL,
  `auction_point_detail_iswin` tinyint(1) NOT NULL DEFAULT '0',
  `auction_point_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`auction_point_detail_seq`),
  KEY `FKndsosvjed3v96y3y308ya21ka` (`auction_seq`),
  KEY `FKmhviasjxaycueaux04itlrtqd` (`member_seq`),
  CONSTRAINT `FKmhviasjxaycueaux04itlrtqd` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`),
  CONSTRAINT `FKndsosvjed3v96y3y308ya21ka` FOREIGN KEY (`auction_seq`) REFERENCES `auction` (`auction_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_point_detail`
--

LOCK TABLES `auction_point_detail` WRITE;
/*!40000 ALTER TABLE `auction_point_detail` DISABLE KEYS */;
INSERT INTO `auction_point_detail` VALUES (110,152,1,100,0,'2024-02-16 12:34:56'),(111,152,82,100,0,'2024-02-15 00:00:00'),(112,152,82,100,0,'2024-02-15 00:00:00'),(113,152,1,100,0,'2024-02-15 00:00:00'),(114,152,1,100,0,'2024-02-15 00:00:00'),(115,152,1,100000,0,'2024-02-16 01:30:31'),(116,152,1,5100000,0,'2024-02-16 01:30:35');
/*!40000 ALTER TABLE `auction_point_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commission` (
  `commission_detail_seq` bigint NOT NULL AUTO_INCREMENT,
  `artist_seq` int NOT NULL COMMENT '회원가입 시 자동 부여되는 seq',
  `commission_detail_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수수료 이력이 발생한 시간',
  `commission_detail_point` bigint NOT NULL DEFAULT '0' COMMENT '차감된 수수료 포인트',
  `commission_detail_category` int NOT NULL,
  `commission_detail_table_seq` int NOT NULL,
  PRIMARY KEY (`commission_detail_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_seq` int NOT NULL AUTO_INCREMENT,
  `artist_seq` bigint NOT NULL,
  `item_name` varchar(100) NOT NULL COMMENT '작품 명',
  `item_width` int NOT NULL,
  `item_height` int NOT NULL,
  `item_like` int NOT NULL DEFAULT '0' COMMENT '좋아요 개수',
  `item_sell_price` bigint DEFAULT NULL COMMENT '판매상품일 경우의 판매가',
  `item_is_sold` tinyint(1) NOT NULL DEFAULT '0' COMMENT '판매 여부',
  `item_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작품이 동록된 시간',
  `item_explain` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_seq`),
  KEY `FKk9we93yneqa7fxftx0ut53fj8` (`artist_seq`),
  CONSTRAINT `FKk9we93yneqa7fxftx0ut53fj8` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`artist_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (232,87,'해바라기',92,73,0,230000,0,'2024-02-14 16:30:28','《해바라기》는 빈센트 반 고흐가 그린 정물화이다. 이 그림은 두 가지 버전이 있는데, 첫 번째는 1887년 파리에서 그린 바닥에 놓여있는 해바라기이며, 두 번째는 1년 뒤 아를에서 그린 꽃병에 담긴 해바라기이다.'),(234,87,'작품이에요',45,78,0,100000,0,'2024-02-15 08:46:49','아주 좋은 작품입니다'),(235,87,'붐붐붐',43,75,0,25000,0,'2024-02-15 08:47:17','작가의 작품입니다'),(236,87,'ETA',23,35,0,1223350,0,'2024-02-15 08:47:44','뉴진스의 작품 ETA입니다.'),(237,87,'나나나',100,80,0,500000,0,'2024-02-15 08:48:09','나나나라는 작품입니다.'),(238,87,'굳나잇',150,80,0,152000,0,'2024-02-15 08:48:40','잘자요 여러분'),(239,87,'2페이지작품입니다',23,56,0,56000,0,'2024-02-15 08:49:03','2페이지작품입니다');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kakaopay_answer`
--

DROP TABLE IF EXISTS `kakaopay_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kakaopay_answer` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `next_redirect_app_url` int DEFAULT NULL,
  `next_redirect_mobile_url` varchar(255) DEFAULT NULL COMMENT '카카오톡 모바일 웹 결제 페이지',
  `next_redirect_pc_url` varchar(255) DEFAULT NULL COMMENT '카카오톡 PC 웹 결제 페이지',
  `android_app_scheme` varchar(255) DEFAULT NULL COMMENT '카카오페이 결제 화면으로 이동하는 Android 앱 스킴',
  `ios_app_scheme` varchar(255) DEFAULT NULL COMMENT '카카오페이 결제 화면으로 이동하는 iOS 앱 스킴',
  `created_at` datetime NOT NULL COMMENT '결제 준비 요청 시간',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kakaopay_answer`
--

LOCK TABLES `kakaopay_answer` WRITE;
/*!40000 ALTER TABLE `kakaopay_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `kakaopay_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kakaopay_request`
--

DROP TABLE IF EXISTS `kakaopay_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kakaopay_request` (
  `kakaopay_request_seq` int NOT NULL,
  `cid` varchar(10) NOT NULL,
  `partner_order_id` varchar(100) NOT NULL,
  `partner_user_id` varchar(100) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL DEFAULT '1',
  `total_amount` int NOT NULL,
  `tax_free_amount` int DEFAULT NULL,
  `approval_url` varchar(255) DEFAULT NULL COMMENT '결제 성공 시 redirect url',
  `cancel_url` varchar(255) DEFAULT NULL COMMENT '결제 취소 시 redirect url',
  `fail_url` varchar(255) DEFAULT NULL COMMENT '결제 실패 시 redirect url',
  PRIMARY KEY (`kakaopay_request_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kakaopay_request`
--

LOCK TABLES `kakaopay_request` WRITE;
/*!40000 ALTER TABLE `kakaopay_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `kakaopay_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meeting` (
  `meeting_seq` int NOT NULL AUTO_INCREMENT,
  `meeting_session_id` varchar(100) NOT NULL,
  `auction_seq` int DEFAULT NULL,
  PRIMARY KEY (`meeting_seq`),
  KEY `FKgi7rl0u72h5akc0vd5s8b785m` (`auction_seq`),
  CONSTRAINT `FKgi7rl0u72h5akc0vd5s8b785m` FOREIGN KEY (`auction_seq`) REFERENCES `auction` (`auction_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeting`
--

LOCK TABLES `meeting` WRITE;
/*!40000 ALTER TABLE `meeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_seq` bigint NOT NULL AUTO_INCREMENT,
  `member_id` varchar(30) NOT NULL,
  `member_name` varchar(30) NOT NULL COMMENT '회원가입 시 입력하는 이름',
  `member_pwd` varchar(255) NOT NULL COMMENT '회원가입 시 입력하는 비밀번호',
  `member_nickname` varchar(30) NOT NULL COMMENT '회원가입 시 입력하는 닉네임',
  `member_email` varchar(50) NOT NULL COMMENT '회원가입 시 입력하는 이메일',
  `member_contact` varchar(11) NOT NULL COMMENT '회원가입 시 입력하는 연락처(전화번호)',
  `member_point` bigint NOT NULL DEFAULT '0',
  `member_isdeleted` tinyint(1) NOT NULL DEFAULT '0',
  `member_deleted_date` datetime DEFAULT NULL,
  `member_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_artist` bit(1) DEFAULT NULL,
  PRIMARY KEY (`member_seq`),
  UNIQUE KEY `member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'hhh','히히','111111','jjjj','hhh@naver.com','01099999999',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(3,'ggg','ggg','111111','으아','ggg@naver.com','01099998888',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(4,'123','123','123123','123','123@naver.com','12312341234',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(5,'1234','1234','123123','123','123@naver.com','12312341234',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(6,'444','444','444444','444','44@naver.com','12312341234',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(7,'ddd','퍄퍄퍄','111111','ggggg','ff@naver.com','01033334444',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(8,'666','666','666666','666','66@naver.com','666',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(51,'test','이윤정','111111','test','test@naver.com','01012345678',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(80,'daram2','이다람','ssafy1234','다람쥐','daram@naver.com','01012341234',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(81,'wonyoung','장원영','ssafy1234','슈퍼스타','won0@gmail.com','01012341234',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(82,'hihihi','졸려','','','','',0,0,NULL,'2024-01-29 04:54:33',_binary '\0'),(83,'alexander930828','황보동훈','123123','조선제일검','alexander2@kakao.com','01022369308',0,0,NULL,'2024-01-29 04:54:33',_binary '\0');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_auction_bidding`
--

DROP TABLE IF EXISTS `member_auction_bidding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_auction_bidding` (
  `member_auction_bidding_seq` bigint NOT NULL AUTO_INCREMENT,
  `member_seq` bigint DEFAULT NULL,
  `auction_seq` int NOT NULL COMMENT '경매 개시될 경우 자동 부여되는 ID',
  `member_auction_bidding_deposit` int NOT NULL COMMENT '입찰 신청 시 차감되는 보증금',
  `member_auction_bidding_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `member_auction_bidding_deleted_date` datetime DEFAULT NULL,
  `member_auction_bidding_isdeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`member_auction_bidding_seq`),
  KEY `FK4ghdgco5f6qlhtep9bxtbl88p` (`auction_seq`),
  KEY `FK3bd59vfadi0athdx7v0eyo8ng` (`member_seq`),
  CONSTRAINT `FK3bd59vfadi0athdx7v0eyo8ng` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`),
  CONSTRAINT `FK4ghdgco5f6qlhtep9bxtbl88p` FOREIGN KEY (`auction_seq`) REFERENCES `auction` (`auction_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_auction_bidding`
--

LOCK TABLES `member_auction_bidding` WRITE;
/*!40000 ALTER TABLE `member_auction_bidding` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_auction_bidding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_seq` bigint NOT NULL AUTO_INCREMENT,
  `member_seq` bigint DEFAULT NULL,
  `artist_seq` bigint DEFAULT NULL,
  `item_seq` int NOT NULL COMMENT '자동 부여되는 작품 seq 번호',
  `auction_seq` int DEFAULT NULL COMMENT '경매 개시될 경우 자동 부여되는 ID',
  `sorting_code_detail_seq` int NOT NULL,
  `order_detail_totalpoint` bigint NOT NULL,
  `order_detail_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_detail_address` varchar(255) NOT NULL,
  `order_detail_phonenumber` varchar(11) NOT NULL,
  `order_detail_delivery_point` int NOT NULL DEFAULT '0',
  `order_detail_contract` varchar(1000) DEFAULT NULL,
  `order_detail_commission` int NOT NULL,
  `order_detail_isauction` tinyint(1) NOT NULL DEFAULT '0',
  `order_detail_iscanceled` tinyint(1) NOT NULL DEFAULT '0',
  `order_detail_canceled_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_detail_seq`),
  KEY `FKi56gnvjnotj5c66t57pdkbw67` (`artist_seq`),
  KEY `FK1eixjy4fttuokq8p0j9neur6o` (`auction_seq`),
  KEY `FKep3m0i84t50fdf3sw8kd49mgb` (`item_seq`),
  KEY `FKeatb5b1fv0yu5hvnv5dh24hdd` (`member_seq`),
  CONSTRAINT `FK1eixjy4fttuokq8p0j9neur6o` FOREIGN KEY (`auction_seq`) REFERENCES `auction` (`auction_seq`),
  CONSTRAINT `FKeatb5b1fv0yu5hvnv5dh24hdd` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`),
  CONSTRAINT `FKep3m0i84t50fdf3sw8kd49mgb` FOREIGN KEY (`item_seq`) REFERENCES `item` (`item_seq`),
  CONSTRAINT `FKi56gnvjnotj5c66t57pdkbw67` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`artist_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point` (
  `point_detail_seq` bigint NOT NULL AUTO_INCREMENT,
  `point_detail_id` int NOT NULL,
  `point_detail_isartist` tinyint(1) NOT NULL,
  `point_detail_trade_point` bigint NOT NULL,
  `point_detail_trade_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `point_detail_trade_category` int NOT NULL COMMENT 'ENUM(판매, 경매, deposit, 충전, 환전, 작가받는포인트)',
  `point_detail_trade_table_seq` int NOT NULL,
  PRIMARY KEY (`point_detail_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

LOCK TABLES `point` WRITE;
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
/*!40000 ALTER TABLE `point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_seq` int NOT NULL AUTO_INCREMENT,
  `member_seq` bigint DEFAULT NULL,
  `review_content` varchar(255) NOT NULL,
  `review_visit` int NOT NULL,
  `review_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `review_title` varchar(200) DEFAULT NULL,
  `artist_seq` bigint DEFAULT NULL,
  `item_seq` int DEFAULT NULL,
  PRIMARY KEY (`review_seq`),
  KEY `FKoppeupt7ee22ix7rwfobbdwd7` (`member_seq`),
  KEY `FKm3xk65ps0eqe0a00b2hxewr14` (`artist_seq`),
  CONSTRAINT `FKm3xk65ps0eqe0a00b2hxewr14` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`artist_seq`),
  CONSTRAINT `FKoppeupt7ee22ix7rwfobbdwd7` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_comment`
--

DROP TABLE IF EXISTS `review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_comment` (
  `review_comment_seq` bigint NOT NULL AUTO_INCREMENT,
  `member_seq` bigint DEFAULT NULL,
  `review_seq` int NOT NULL,
  `review_comment_content` varchar(255) NOT NULL,
  `review_comment_created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_comment_seq`),
  KEY `FKdrs9mgo431781d8cdetd8stgs` (`member_seq`),
  KEY `FKm19ii5ds9e74npcec3o4976r` (`review_seq`),
  CONSTRAINT `FKdrs9mgo431781d8cdetd8stgs` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`),
  CONSTRAINT `FKm19ii5ds9e74npcec3o4976r` FOREIGN KEY (`review_seq`) REFERENCES `review` (`review_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_comment`
--

LOCK TABLES `review_comment` WRITE;
/*!40000 ALTER TABLE `review_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_like`
--

DROP TABLE IF EXISTS `sale_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_like` (
  `item_seq` int NOT NULL,
  `member_seq` bigint NOT NULL,
  KEY `FK1jli68qc9372h11i4w9gj3543` (`member_seq`),
  KEY `FK7v4pjd8pjdcewr51cf3552an0` (`item_seq`),
  CONSTRAINT `FK1jli68qc9372h11i4w9gj3543` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`),
  CONSTRAINT `FK7v4pjd8pjdcewr51cf3552an0` FOREIGN KEY (`item_seq`) REFERENCES `item` (`item_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_like`
--

LOCK TABLES `sale_like` WRITE;
/*!40000 ALTER TABLE `sale_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_file`
--

DROP TABLE IF EXISTS `upload_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload_file` (
  `upload_file_seq` int NOT NULL AUTO_INCREMENT,
  `upload_file_addr` varchar(255) NOT NULL COMMENT '서버에 업로드하며 받아오는 url',
  `upload_file_name` varchar(100) NOT NULL COMMENT '사용자에게 보여줄 때 항상 업로드회원명_업로드파일카테고리 로 변경하여 보여줌',
  `upload_file_code` int NOT NULL COMMENT '작품 사진, 프로필 사진, 판매 사진, 포트폴리오 등 모든 연관된 ENUM',
  `upload_file_table_seq` bigint NOT NULL COMMENT '테이블 마다의 seq번호로 접근',
  `upload_file_order` tinyint DEFAULT NULL COMMENT '사진 순서',
  PRIMARY KEY (`upload_file_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_file`
--

LOCK TABLES `upload_file` WRITE;
/*!40000 ALTER TABLE `upload_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `upload_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish`
--

DROP TABLE IF EXISTS `wish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish` (
  `wish_seq` int NOT NULL AUTO_INCREMENT,
  `member_seq` bigint DEFAULT NULL,
  `item_seq` int NOT NULL,
  PRIMARY KEY (`wish_seq`),
  KEY `FKbg92crfv38g2kgchs8uxd8klu` (`item_seq`),
  KEY `FK889im0ec2ishrvgjf39qn16fe` (`member_seq`),
  CONSTRAINT `FK889im0ec2ishrvgjf39qn16fe` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`),
  CONSTRAINT `FKbg92crfv38g2kgchs8uxd8klu` FOREIGN KEY (`item_seq`) REFERENCES `item` (`item_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish`
--

LOCK TABLES `wish` WRITE;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
/*!40000 ALTER TABLE `wish` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-15 16:44:39
