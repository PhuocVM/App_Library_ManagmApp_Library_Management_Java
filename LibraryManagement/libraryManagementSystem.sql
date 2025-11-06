-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: librarymanagement
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Nguyễn Nhật Ánh'),(2,'Nam Cao'),(3,'Ngô Tất Tố'),(4,'Tô Hoài'),(5,'Xuân Diệu'),(6,'Nguyễn Du'),(7,'Victor Hugo'),(8,'Lev Tolstoy'),(9,'George Orwell'),(10,'Stephen Hawking'),(11,'Trịnh Xuân Thuận'),(12,'Nguyễn Đình Chiểu'),(13,'Nguyễn Minh Châu'),(14,'Nguyễn Huy Thiệp'),(15,'Nguyễn Mạnh Tuấn'),(16,'Nguyễn Phong'),(17,'Trần Đăng Khoa'),(18,'Nguyễn Trãi'),(19,'Phạm Duy Tốn'),(20,'Nguyễn Bỉnh Khiêm');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookid` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `author` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `subject` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publisher` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isbn` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `edition` int DEFAULT NULL,
  `shelfNo` int DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('31967134','hai','Nam Cao','Chọn','NXB Giáo dục','Công nghệ thông tin','15656',4545,455),('B001','Tôi thấy hoa vàng trên cỏ xanh','Nguyễn Nhật Ánh','Văn học','NXB Trẻ','Văn học Việt Nam','9786042084997',1,1),('B002','Lão Hạc','Nam Cao','Văn học','NXB Văn học','Văn học Việt Nam','9786049539124',2,2),('B003','Tắt đèn','Ngô Tất Tố','Văn học','NXB Văn học','Văn học Việt Nam','9786049632153',1,3),('B004','Dế mèn phiêu lưu ký','Tô Hoài','Thiếu nhi','NXB Kim Đồng','Văn học Việt Nam','9786042118005',5,4),('B005','Truyện Kiều','Nguyễn Du','Thơ ca','NXB Văn học','Văn học Việt Nam','9786049632788',10,5),('B006','Nhật ký trong tù','Hồ Chí Minh','Chính trị','NXB Chính trị quốc gia','Lịch sử Việt Nam','9786045728959',1,6),('B007','Đất rừng phương Nam','Đoàn Giỏi','Văn học','NXB Kim Đồng','Văn học Việt Nam','9786042098390',4,7),('B008','Người lái đò sông Đà','Nguyễn Tuân','Văn học','NXB Văn học','Văn học Việt Nam','9786047789644',2,8),('B009','Chiến tranh và hòa bình','Lev Tolstoy','Tiểu thuyết','NXB Văn học','Văn học nước ngoài','9786047762951',1,9),('B010','Những người khốn khổ','Victor Hugo','Tiểu thuyết','NXB Văn học','Văn học nước ngoài','9786049653486',1,10),('B011','1984','George Orwell','Chính trị','NXB Văn học','Văn học nước ngoài','9786047753386',1,11),('B012','Đắc nhân tâm','Dale Carnegie','Tâm lý học','NXB Tổng hợp TP.HCM','Khoa học xã hội','9786045890519',4,12),('B013','Lược sử thời gian','Stephen Hawking','Vật lý','NXB Khoa học tự nhiên','Khoa học tự nhiên','9786045892186',3,13),('B014','Giai điệu của vũ trụ','Trịnh Xuân Thuận','Vật lý','NXB Tri Thức','Khoa học tự nhiên','9786049228738',2,14),('B015','Hạt giống tâm hồn','Nhiều tác giả','Kỹ năng sống','NXB Trẻ','Giáo dục học','9786041028954',1,15),('B016','Bên kia bầu trời','Nguyễn Ngọc Tư','Truyện ngắn','NXB Trẻ','Văn học Việt Nam','9786042112560',1,16),('B017','Sapiens: Lược sử loài người','Yuval Noah Harari','Lịch sử','NXB Thế Giới','Khoa học xã hội','9786047762877',2,17),('B018','Tư duy nhanh và chậm','Daniel Kahneman','Tâm lý học','NXB Thế Giới','Khoa học xã hội','9786047760156',1,18),('B019','Cà phê cùng Tony','Tony Buổi Sáng','Truyền cảm hứng','NXB Trẻ','Giáo dục học','9786041071227',1,19),('B020','Những ngôi sao xa xôi','Lê Minh Khuê','Truyện ngắn','NXB Kim Đồng','Văn học Việt Nam','9786042097424',1,20),('B021','Lịch sử Việt Nam đại cương','Phan Huy Lê','Lịch sử','NXB Giáo dục','Lịch sử Việt Nam','9786041234001',1,1),('B022','Kinh tế học vi mô','Paul Samuelson','Kinh tế học','NXB Thống kê','Kinh tế học','9786041234002',1,2),('B023','Kinh tế học vĩ mô','Paul Krugman','Kinh tế học','NXB Thống kê','Kinh tế học','9786041234003',1,3),('B024','Giáo trình Công nghệ phần mềm','Nguyễn Văn Vỵ','CNTT','NXB Thông tin và Truyền thông','Công nghệ thông tin','9786041234004',2,4),('B025','Cấu trúc dữ liệu và giải thuật','Lê Minh Hoàng','CNTT','NXB Khoa học tự nhiên','Công nghệ thông tin','9786041234005',1,5),('B026','Giáo trình Hệ điều hành','Trần Đình Quế','CNTT','NXB Giáo dục','Công nghệ thông tin','9786041234006',2,6),('B027','Toán cao cấp A1','Nguyễn Đình Trí','Toán học','NXB Đại học Quốc gia','Toán học','9786041234007',1,7),('B028','Đại số tuyến tính','Nguyễn Thị Mỹ Linh','Toán học','NXB Đại học Quốc gia','Toán học','9786041234008',1,8),('B029','Giải tích 1','Vũ Ngọc Ban','Toán học','NXB Đại học Quốc gia','Toán học','9786041234009',1,9),('B030','Giải tích 2','Vũ Ngọc Ban','Toán học','NXB Đại học Quốc gia','Toán học','9786041234010',1,10),('B031','Hóa đại cương','Trần Quốc Trung','Hóa học','NXB Giáo dục','Hóa học','9786041234011',1,11),('B032','Hóa hữu cơ','Nguyễn Đức Vượng','Hóa học','NXB Giáo dục','Hóa học','9786041234012',1,12),('B033','Sinh học tế bào','Lê Văn Minh','Sinh học','NXB Giáo dục','Sinh học','9786041234013',1,13),('B034','Di truyền học','Phạm Văn Hiệp','Sinh học','NXB Giáo dục','Sinh học','9786041234014',1,14),('B035','Địa lý tự nhiên Việt Nam','Nguyễn Viết Thịnh','Địa lý','NXB Giáo dục','Địa lý','9786041234015',1,15),('B036','Địa lý kinh tế Việt Nam','Trần Hồng Quang','Địa lý','NXB Giáo dục','Địa lý','9786041234016',1,16),('B037','Giáo trình Tâm lý học đại cương','Nguyễn Quang Uẩn','Tâm lý học','NXB Giáo dục','Tâm lý học','9786041234017',1,17),('B038','Giáo trình Luật Hiến pháp','Nguyễn Đăng Dung','Luật học','NXB Chính trị quốc gia','Luật học','9786041234018',1,18),('B039','Giáo trình Luật Dân sự','Trần Văn Biên','Luật học','NXB Chính trị quốc gia','Luật học','9786041234019',1,19),('B040','Triết học Mác - Lênin','Nguyễn Hữu Vui','Triết học','NXB Chính trị quốc gia','Triết học','9786041234020',1,20),('B041','Logic học đại cương','Phạm Văn Đức','Triết học','NXB Chính trị quốc gia','Triết học','9786041234021',1,21),('B042','Xã hội học cơ bản','Trần Hữu Quang','Khoa học xã hội','NXB Khoa học xã hội','Khoa học xã hội','9786041234022',1,22),('B043','Chính trị học đại cương','Nguyễn Văn Huyên','Chính trị học','NXB Chính trị quốc gia','Chính trị học','9786041234023',1,23),('B044','Quản trị học','Nguyễn Minh Kiều','Kinh tế học','NXB Lao động','Kinh tế học','9786041234024',1,24),('B045','Nguyên lý kế toán','Nguyễn Văn Công','Kinh tế học','NXB Thống kê','Kinh tế học','9786041234025',1,25),('B046','Tài chính doanh nghiệp','Trần Ngọc Thơ','Kinh tế học','NXB Thống kê','Kinh tế học','9786041234026',1,26),('B047','Marketing căn bản','Philip Kotler','Kinh tế học','NXB Thống kê','Kinh tế học','9786041234027',1,27),('B048','Quản lý môi trường','Nguyễn Thị Hoa','Môi trường','NXB Khoa học kỹ thuật','Môi trường','9786041234028',1,28),('B049','Sinh thái học','Lê Thị Thanh','Môi trường','NXB Khoa học tự nhiên','Môi trường','9786041234029',1,29),('B050','Giáo trình Giáo dục học','Phạm Viết Vượng','Giáo dục','NXB Giáo dục','Giáo dục học','9786041234030',1,30),('B051','Phương pháp nghiên cứu khoa học','Nguyễn Văn Tuấn','Giáo dục','NXB Giáo dục','Giáo dục học','9786041234031',1,31),('B052','Giáo trình Cấu trúc máy tính','Đặng Minh Tuấn','CNTT','NXB Bách khoa Hà Nội','Công nghệ thông tin','9786041234032',1,32),('B053','Giáo trình Lập trình C cơ bản','Nguyễn Văn Hưng','CNTT','NXB Bách khoa Hà Nội','Công nghệ thông tin','9786041234033',1,33),('B054','Lập trình Java nâng cao','Trần Văn Tấn','CNTT','NXB Thông tin và Truyền thông','Công nghệ thông tin','9786041234034',1,34),('B055','Phân tích thiết kế hệ thống','Phạm Hồng Sơn','CNTT','NXB Bách khoa Hà Nội','Công nghệ thông tin','9786041234035',1,35),('B056','Cơ sở dữ liệu','Nguyễn Thị Lan','CNTT','NXB Khoa học tự nhiên','Công nghệ thông tin','9786041234036',1,36),('B057','Trí tuệ nhân tạo','Đỗ Văn Thành','CNTT','NXB Khoa học tự nhiên','Công nghệ thông tin','9786041234037',1,37),('B058','Mạng máy tính','Trần Minh Quân','CNTT','NXB Khoa học tự nhiên','Công nghệ thông tin','9786041234038',1,38),('B059','An toàn thông tin','Lê Quang Hiếu','CNTT','NXB Bách khoa Hà Nội','Công nghệ thông tin','9786041234039',1,39),('B060','Phân tích dữ liệu với Python','Nguyễn Thành Đạt','CNTT','NXB Trẻ','Công nghệ thông tin','9786041234040',1,40),('B061','Thiết kế Web cơ bản','Lê Văn Hòa','CNTT','NXB Trẻ','Công nghệ thông tin','9786041234041',1,41),('B062','Giáo trình Vật lý đại cương','Nguyễn Văn Hòa','Vật lý','NXB Đại học Quốc gia','Vật lý học','9786041234042',1,42),('B063','Cơ học lượng tử nhập môn','Nguyễn Hữu Phương','Vật lý','NXB Khoa học tự nhiên','Vật lý học','9786041234043',1,43),('B064','Vật lý nhiệt học','Ngô Quốc Tạo','Vật lý','NXB Đại học Quốc gia','Vật lý học','9786041234044',1,44),('B065','Điện từ học','Phạm Minh Chính','Vật lý','NXB Đại học Quốc gia','Vật lý học','9786041234045',1,45),('B066','Giáo trình Cơ học chất lỏng','Nguyễn Văn Tân','Vật lý','NXB Đại học Quốc gia','Vật lý học','9786041234046',1,46),('B067','Thống kê ứng dụng','Phạm Thanh Bình','Toán học','NXB Thống kê','Toán học','9786041234047',1,47),('B068','Xác suất thống kê','Lê Thị Hạnh','Toán học','NXB Thống kê','Toán học','9786041234048',1,48),('B069','Giải tích số','Nguyễn Văn Phong','Toán học','NXB Đại học Quốc gia','Toán học','9786041234049',1,49),('B070','Phương pháp tính','Nguyễn Thị Hằng','Toán học','NXB Đại học Quốc gia','Toán học','9786041234050',1,50),('B071','Giáo trình Mỹ thuật cơ bản','Trần Văn Hải','Nghệ thuật','NXB Mỹ thuật','Nghệ thuật','9786041234051',1,51),('B072','Lịch sử mỹ thuật thế giới','Nguyễn Thị Mai','Nghệ thuật','NXB Mỹ thuật','Nghệ thuật','9786041234052',1,52),('B073','Cơ sở thiết kế đồ họa','Nguyễn Văn Phúc','Nghệ thuật','NXB Mỹ thuật','Nghệ thuật','9786041234053',1,53),('B074','Nhiếp ảnh cơ bản','Trần Quốc Hưng','Nghệ thuật','NXB Mỹ thuật','Nghệ thuật','9786041234054',1,54),('B075','Âm nhạc và cuộc sống','Phạm Thị Minh','Nghệ thuật','NXB Âm nhạc','Nghệ thuật','9786041234055',1,55),('B076','Văn hóa Việt Nam','Trần Quốc Vượng','Văn hóa','NXB Khoa học xã hội','Khoa học xã hội','9786041234056',1,56),('B077','Giáo trình Triết học phương Tây','Trần Đức Thảo','Triết học','NXB Chính trị quốc gia','Triết học','9786041234057',1,57),('B078','Y học cổ truyền Việt Nam','Phạm Ngọc Khánh','Y học','NXB Y học','Y học','9786041234058',1,58),('B079','Giải phẫu người','Nguyễn Văn Đoàn','Y học','NXB Y học','Y học','9786041234059',1,59),('B080','Sinh lý học đại cương','Lê Thị Ngọc','Y học','NXB Y học','Y học','9786041234060',1,60),('B081','Dược lý học cơ bản','Phạm Văn Dũng','Y học','NXB Y học','Y học','9786041234061',1,61),('B082','Ký sinh trùng học','Trần Minh Tâm','Y học','NXB Y học','Y học','9786041234062',1,62),('B083','Vi sinh vật học','Nguyễn Thị Hoa','Y học','NXB Y học','Y học','9786041234063',1,63),('B084','Bệnh học nội khoa','Trần Văn Dũng','Y học','NXB Y học','Y học','9786041234064',1,64),('B085','Bệnh học ngoại khoa','Nguyễn Văn Tài','Y học','NXB Y học','Y học','9786041234065',1,65),('B086','Điều dưỡng cơ bản','Lê Thị Thắm','Y học','NXB Y học','Y học','9786041234066',1,66),('B087','Công nghệ sinh học','Trần Hữu Bình','Sinh học','NXB Khoa học tự nhiên','Sinh học','9786041234067',1,67),('B088','Ứng dụng công nghệ gen','Phạm Văn Khánh','Sinh học','NXB Khoa học tự nhiên','Sinh học','9786041234068',1,68),('B089','Sinh học phân tử','Nguyễn Thị Trang','Sinh học','NXB Khoa học tự nhiên','Sinh học','9786041234069',1,69),('B090','Vật lý y sinh','Nguyễn Văn Hậu','Vật lý','NXB Y học','Vật lý học','9786041234070',1,70),('B091','Môi trường và phát triển bền vững','Phạm Thị Lan','Môi trường','NXB Khoa học tự nhiên','Môi trường','9786041234071',1,71),('B092','Kỹ thuật môi trường','Nguyễn Văn Duy','Môi trường','NXB Khoa học tự nhiên','Môi trường','9786041234072',1,72),('B093','Kinh tế môi trường','Nguyễn Minh Tuấn','Môi trường','NXB Khoa học tự nhiên','Môi trường','9786041234073',1,73),('B094','Chính trị học so sánh','Phạm Quốc Thành','Chính trị học','NXB Chính trị quốc gia','Chính trị học','9786041234074',1,74),('B095','Quan hệ quốc tế hiện đại','Lê Anh Tuấn','Chính trị học','NXB Chính trị quốc gia','Chính trị học','9786041234075',1,75),('B096','Kỹ năng giao tiếp','Trần Thị Hạnh','Tâm lý học','NXB Giáo dục','Tâm lý học','9786041234076',1,76),('B097','Tâm lý học phát triển','Nguyễn Thị Thanh','Tâm lý học','NXB Giáo dục','Tâm lý học','9786041234077',1,77),('B098','Tâm lý học ứng dụng','Phạm Hồng Hà','Tâm lý học','NXB Giáo dục','Tâm lý học','9786041234078',1,78),('B099','Giáo trình Thống kê xã hội','Nguyễn Quốc Khánh','Khoa học xã hội','NXB Thống kê','Khoa học xã hội','9786041234079',1,79),('B100','Giới thiệu văn học Việt Nam hiện đại','Nguyễn Đăng Mạnh','Văn học','NXB Giáo dục','Văn học Việt Nam','9786041234080',1,80);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Văn học Việt Nam'),(2,'Văn học nước ngoài'),(3,'Khoa học tự nhiên'),(4,'Khoa học xã hội'),(5,'Lịch sử Việt Nam'),(6,'Kinh tế học'),(7,'Công nghệ thông tin'),(8,'Giáo dục học'),(9,'Y học'),(10,'Chính trị học'),(11,'Tâm lý học'),(12,'Luật học'),(13,'Nghệ thuật'),(14,'Triết học'),(15,'Môi trường'),(16,'Toán học'),(17,'Vật lý học'),(18,'Hóa học'),(19,'Sinh học'),(20,'Địa lý');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuebooks`
--

DROP TABLE IF EXISTS `issuebooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issuebooks` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bookId` int NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `author` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `issueDate` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dueDate` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `returnStatus` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuebooks`
--

LOCK TABLES `issuebooks` WRITE;
/*!40000 ALTER TABLE `issuebooks` DISABLE KEYS */;
/*!40000 ALTER TABLE `issuebooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `notice` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'NXB Trẻ'),(2,'NXB Giáo dục'),(3,'NXB Kim Đồng'),(4,'NXB Chính trị quốc gia'),(5,'NXB Khoa học tự nhiên'),(6,'NXB Lao Động'),(7,'NXB Văn học'),(8,'NXB Tổng hợp TP.HCM'),(9,'NXB Tri Thức'),(10,'NXB Hội Nhà văn');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `usertype` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('1','Quan tri vien','0900000001','admin@library.com','admin','admin123','admin'),('2','Giao vien','0900000002','giaovien@library.com','giaovien','giaovien123','Faculty'),('3','Sinh vien','0900000003','sinhvien@library.com','sinhvien','sinhvien123','student');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-06  6:57:48
