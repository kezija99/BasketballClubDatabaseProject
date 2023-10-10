CREATE DATABASE  IF NOT EXISTS `basketballClubDb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `basketballClubDb`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: basketballClubDb
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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('root','root');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `countryId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`countryId`),
  UNIQUE KEY `Naziv_UNIQUE` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `cityId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `countryId` int DEFAULT NULL,
  PRIMARY KEY (`cityId`),
  KEY `fk_CITY_COUNTRY1_idx` (`countryId`),
  CONSTRAINT `fk_CITY_COUNTRY1` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hall` (
  `hallId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Capacity` int NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hallId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'Stark arena',20000,'Humska'),(2,'Pionir',8000,'Novi BeoCITY'),(3,'Stozice',10000,'Ljubljana'),(4,'hall Laktasi',5000,'Laktasi');
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `Nacionality` varchar(25) NOT NULL,
  `personId` int NOT NULL,
  `Height` smallint NOT NULL,
  `Weight` double NOT NULL,
  `JerseyNumber` int NOT NULL,
  `Active` bit(1) NOT NULL,
  `Exist` bit(1) DEFAULT NULL,
  PRIMARY KEY (`personId`),
  CONSTRAINT `fk_PLAYER_PERSON` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES ('Australian',2,199,91,22,_binary '',_binary '');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_plays`
--

DROP TABLE IF EXISTS `player_plays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_plays` (
  `personId` int NOT NULL,
  `positionId` int NOT NULL,
  PRIMARY KEY (`personId`,`positionId`),
  KEY `fk_PLAYER_has_POSITION_POSITION1_idx` (`positionId`),
  KEY `fk_PLAYER_has_POSITION_PLAYER1_idx` (`personId`),
  CONSTRAINT `fk_PLAYER_has_POSITION_PLAYER1` FOREIGN KEY (`personId`) REFERENCES `player` (`personId`),
  CONSTRAINT `fk_PLAYER_has_POSITION_POSITION1` FOREIGN KEY (`positionId`) REFERENCES `position` (`positionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_plays`
--

LOCK TABLES `player_plays` WRITE;
/*!40000 ALTER TABLE `player_plays` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_plays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `clubId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `hallId` int NOT NULL,
  PRIMARY KEY (`clubId`),
  UNIQUE KEY `Naziv_UNIQUE` (`Name`),
  KEY `fk_CLUB_HALL1_idx` (`hallId`),
  CONSTRAINT `fk_CLUB_HALL1` FOREIGN KEY (`hallId`) REFERENCES `hall` (`hallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_registered_for_game`
--

DROP TABLE IF EXISTS `player_registered_for_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_registered_for_game` (
  `gameId` int NOT NULL,
  `Points` int DEFAULT NULL,
  `Assists` int DEFAULT NULL,
  `Rebounds` int DEFAULT NULL,
  `Steals` int DEFAULT NULL,
  `Blocks` int DEFAULT NULL,
  `MinutesPlayed` double DEFAULT NULL,
  `Rating` int DEFAULT NULL,
  `personId` int NOT NULL,
  PRIMARY KEY (`gameId`,`personId`),
  KEY `fk_PLAYER_REGISTERED_FOR_GAME_PLAYER1_idx` (`personId`),
  CONSTRAINT `fk_PLAYER_REGISTERED_FOR_GAME_PLAYER1` FOREIGN KEY (`personId`) REFERENCES `player` (`personId`),
  CONSTRAINT `fk_KOSARKAS_REGISTROVAN_ZA_UTAKMICU_GAME1` FOREIGN KEY (`gameId`) REFERENCES `game` (`gameId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_registered_for_game`
--

LOCK TABLES `player_registered_for_game` WRITE;
/*!40000 ALTER TABLE `player_registered_for_game` DISABLE KEYS */;
INSERT INTO `player_registered_for_game` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `player_registered_for_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `personId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `CityOfBirth` int DEFAULT NULL,
  PRIMARY KEY (`personId`),
  KEY `fk_PERSON_CITY1_idx` (`CityOfBirth`),
  CONSTRAINT `fk_PERSON_CITY1` FOREIGN KEY (`CityOfBirth`) REFERENCES `city` (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (2,'Dante','Exum','2004-07-01',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_from_administration`
--

DROP TABLE IF EXISTS `person_from_administration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_from_administration` (
  `personId` int NOT NULL,
  `Position` varchar(100) NOT NULL,
  PRIMARY KEY (`personId`),
  CONSTRAINT `fk_PERSON_FROM_ADMINISTRATION_PERSON1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_from_administration`
--

LOCK TABLES `person_from_administration` WRITE;
/*!40000 ALTER TABLE `person_from_administration` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_from_administration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `positionId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`positionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `companyId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `cityId` int NOT NULL,
  PRIMARY KEY (`companyId`),
  UNIQUE KEY `Naziv_UNIQUE` (`Name`),
  KEY `fk_COMPANY_CITY1_idx` (`cityId`),
  CONSTRAINT `fk_COMPANY_CITY1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oponent`
--

DROP TABLE IF EXISTS `oponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oponent` (
  `oponentId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(155) NOT NULL,
  `hallId` int NOT NULL,
  `Exist` bit(1) DEFAULT NULL,
  PRIMARY KEY (`oponentId`),
  KEY `fk_OPONENT_HALL1_idx` (`hallId`),
  CONSTRAINT `fk_OPONENT_HALL1` FOREIGN KEY (`hallId`) REFERENCES `hall` (`hallId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oponent`
--

LOCK TABLES `oponent` WRITE;
/*!40000 ALTER TABLE `oponent` DISABLE KEYS */;
INSERT INTO `oponent` VALUES (1,'crvena zvezda',2,_binary '');
/*!40000 ALTER TABLE `oponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `SeasonYear` date NOT NULL,
  `TablePosition` int NOT NULL,
  `seasonId` int NOT NULL,
  `competitionId` int NOT NULL,
  PRIMARY KEY (`seasonId`,`competitionId`),
  CONSTRAINT `fk_RESULT_SEASON_AND_COMPETITION1` FOREIGN KEY (`seasonId`, `competitionId`) REFERENCES `season_and_competition` (`seasonId`, `competitionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season`
--

DROP TABLE IF EXISTS `season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `season` (
  `seasonId` int NOT NULL AUTO_INCREMENT,
  `SeasonMark` varchar(45) NOT NULL,
  PRIMARY KEY (`seasonId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season`
--

LOCK TABLES `season` WRITE;
/*!40000 ALTER TABLE `season` DISABLE KEYS */;
INSERT INTO `season` VALUES (1,'2021/2022'),(2,'2022/2023');
/*!40000 ALTER TABLE `season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season_and_competition`
--

DROP TABLE IF EXISTS `season_and_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `season_and_competition` (
  `seasonId` int NOT NULL,
  `competitionId` int NOT NULL,
  PRIMARY KEY (`seasonId`,`competitionId`),
  KEY `fk_SEASON_has_COMPETITION_COMPETITION1_idx` (`competitionId`),
  KEY `fk_SEASON_has_COMPETITION_SEASON1_idx` (`seasonId`),
  CONSTRAINT `fk_SEASON_has_COMPETITION_SEASON1` FOREIGN KEY (`seasonId`) REFERENCES `season` (`seasonId`),
  CONSTRAINT `fk_SEASON_has_COMPETITION_COMPETITION1` FOREIGN KEY (`competitionId`) REFERENCES `competition` (`competitionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season_and_competition`
--

LOCK TABLES `season_and_competition` WRITE;
/*!40000 ALTER TABLE `season_and_competition` DISABLE KEYS */;
INSERT INTO `season_and_competition` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4);
/*!40000 ALTER TABLE `season_and_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship_contract`
--

DROP TABLE IF EXISTS `sponsorship_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsorship_contract` (
  `sponsorshipContractId` int NOT NULL AUTO_INCREMENT,
  `Value` int NOT NULL,
  `DateFrom` date NOT NULL,
  `DateTo` date NOT NULL,
  `companyId` int NOT NULL,
  PRIMARY KEY (`sponsorshipContractId`),
  KEY `fk_SPONSORSHIP_CONTRACT_COMPANY1_idx` (`companyId`),
  CONSTRAINT `fk_SPONSORSHIP_CONTRACT_COMPANY1` FOREIGN KEY (`companyId`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship_contract`
--

LOCK TABLES `sponsorship_contract` WRITE;
/*!40000 ALTER TABLE `sponsorship_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsorship_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profession_worker`
--

DROP TABLE IF EXISTS `profession_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profession_worker` (
  `PositionName` varchar(25) NOT NULL,
  `personId` int NOT NULL,
  `CareerStartDate` date DEFAULT NULL,
  PRIMARY KEY (`personId`),
  CONSTRAINT `fk_PROFESSION_WORKER_PERSON1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profession_worker`
--

LOCK TABLES `profession_worker` WRITE;
/*!40000 ALTER TABLE `profession_worker` DISABLE KEYS */;
/*!40000 ALTER TABLE `profession_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competition` (
  `competitionId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`competitionId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,'Euroleague'),(2,'Eurocup'),(3,'ABA League'),(4,'Superleague');
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `contractId` int NOT NULL AUTO_INCREMENT,
  `Value` int NOT NULL,
  `personId` int NOT NULL,
  `DateFrom` date NOT NULL,
  `DateTo` date NOT NULL,
  PRIMARY KEY (`contractId`),
  KEY `fk_CONTRACT_PERSON1_idx` (`personId`),
  CONSTRAINT `fk_CONTRACT_PERSON1` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `gameId` int NOT NULL AUTO_INCREMENT,
  `GameDate` date NOT NULL,
  `ClubPoints` int DEFAULT NULL,
  `OponentPoints` int DEFAULT NULL,
  `Home` bit(1) NOT NULL,
  `oponentId` int NOT NULL,
  `seasonId` int NOT NULL,
  `competitionId` int NOT NULL,
  `hallId` int NOT NULL,
  `Exist` bit(1) DEFAULT NULL,
  `Score` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gameId`),
  UNIQUE KEY `GameDate_UNIQUE` (`GameDate`),
  KEY `fk_GAME_OPONENT1_idx` (`oponentId`),
  KEY `fk_GAME_SEASON_HAS_COMPETITION1_idx` (`seasonId`,`competitionId`),
  KEY `fk_GAME_HALL1_idx` (`hallId`),
  CONSTRAINT `fk_GAME_HALL1` FOREIGN KEY (`hallId`) REFERENCES `hall` (`hallId`),
  CONSTRAINT `fk_GAME_OPONENT1` FOREIGN KEY (`oponentId`) REFERENCES `oponent` (`oponentId`),
  CONSTRAINT `fk_GAME_SEASON_HAS_COMPETITION1` FOREIGN KEY (`seasonId`, `competitionId`) REFERENCES `season_and_competition` (`seasonId`, `competitionId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'2023-07-01',123,100,_binary '',1,2,3,1,_binary '','Pobjeda');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01 21:16:49