USE [master]
GO
/****** Object:  Database [LibreLibrary]    Script Date: 2024/1/28 下午 11:42:27 ******/
CREATE DATABASE [LibreLibrary]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LibreLibrary', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\LibreLibrary.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LibreLibrary_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\LibreLibrary_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [LibreLibrary] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LibreLibrary].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LibreLibrary] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LibreLibrary] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LibreLibrary] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LibreLibrary] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LibreLibrary] SET ARITHABORT OFF 
GO
ALTER DATABASE [LibreLibrary] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LibreLibrary] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LibreLibrary] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LibreLibrary] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LibreLibrary] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LibreLibrary] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LibreLibrary] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LibreLibrary] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LibreLibrary] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LibreLibrary] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LibreLibrary] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LibreLibrary] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LibreLibrary] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LibreLibrary] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LibreLibrary] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LibreLibrary] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LibreLibrary] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LibreLibrary] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LibreLibrary] SET  MULTI_USER 
GO
ALTER DATABASE [LibreLibrary] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LibreLibrary] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LibreLibrary] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LibreLibrary] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LibreLibrary] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LibreLibrary] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [LibreLibrary] SET QUERY_STORE = OFF
GO
USE [LibreLibrary]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 2024/1/28 下午 11:42:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[isbn] [varchar](20) NOT NULL,
	[author] [nvarchar](30) NOT NULL,
	[introduction] [nvarchar](255) NULL,
	[name] [nvarchar](60) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[isbn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BorrowingRecord]    Script Date: 2024/1/28 下午 11:42:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BorrowingRecord](
	[recordId] [int] IDENTITY(1,1) NOT NULL,
	[borrowingTime] [datetime2](7) NOT NULL,
	[inventoryId] [int] NOT NULL,
	[returnTime] [datetime2](7) NULL,
	[userId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[recordId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inventory]    Script Date: 2024/1/28 下午 11:42:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inventory](
	[inventoryId] [int] IDENTITY(1,1) NOT NULL,
	[isbn] [varchar](20) NOT NULL,
	[status] [nvarchar](25) NOT NULL,
	[storeTime] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[inventoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LibUser]    Script Date: 2024/1/28 下午 11:42:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibUser](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[lastLoginTime] [datetime2](7) NULL,
	[password] [varchar](255) NOT NULL,
	[phoneNumber] [varchar](25) NOT NULL,
	[registrationTime] [datetime2](7) NOT NULL,
	[userName] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9786263350717', N'劉潤', N'事實是最不容易產生爭議的客觀存在嗎？或許，我們對事實的瞭解，還不夠全面。或許，我們是被表象或經驗欺騙、迷惑，導致看不透事情的本質。唯有透過「底層邏輯+環境變數」，才能讓你在千變萬化的世界中，認清所有真相！', N'底層邏輯：看清這個世界的底牌')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9786263550537', N'Morgan Housel', N'世界上有賺很多錢卻破產的人，也有賺很少錢卻有辦法捐大錢做慈善的人。為什麼？因為理財結果與運氣和風險有關，而且不受才智與個人努力影響，更重要的是，與其了解許多理財專業知識，適當的言行舉止更加關鍵。這就是致富心態，這是現今社會不可或缺的軟實力。', N'致富心態：關於財富、貪婪與幸福的20堂理財課')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9786263554573', N'Eric Yu', N'此書並非勵志型的心靈雞湯書，而是具體可行的實踐手冊。在每一章的最後都有「行動清單」，指導讀者們一步一步從實作練習中累積經驗。在執行這些練習的過程中，實則已經踏上了自我實現的道路。', N'內在成就：成為你真正想成為的人')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9789576587399', N'Robert de Board', N'心理諮商創舉，取材自英國童話《柳林中的風聲》完美結合心理學的經典之作，授權10個語言版本，自1997年起長銷20多年。', N'蛤蟆先生去看心理師')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9789861339047', N'井田千秋', N'日本Amazon 800則五星好評推薦，上市後迅速熱銷10萬冊！溫暖、療癒、驚喜不斷，畫出那個或許也屬於你的，小小空間！構圖豐富有層次，房間格局布置考究，每一頁畫面都像百寶箱，承載了溫暖與幸福。', N'戀家的人︰五個獨居女子的溫暖家居生活')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9789861755267', N'James Clear', N'每天都進步1%，一年後，你會進步37倍；每天都退步1%，一年後，你會弱化到趨近於0！你的一點小改變、一個好習慣，將會產生複利效應，如滾雪球般，為你帶來豐碩的人生成果！一出版立刻風行全球，企業界、運動界、教育界、瘦身界都在用的習慣養成實作指南！', N'原子習慣：細微改變帶來巨大成就的實證法則')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9789862017050', N'Robert C. Martin', N'本書的原文書名為Clean Code: A Handbook of Agile Software Craftsmanship，根據作者的說法，無瑕的程式碼為Jolt得獎著作敏捷軟體開發：原則、樣式及實務的前傳。', N'無瑕的程式碼：敏捷軟體開發技巧守則')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9789862355848', N'Walter Isaacson', N'他的一生，讓世界活成了現在的樣貌。賈伯斯傳作者華特．艾薩克森另一經典巨作。美國之父班傑明．富蘭克林──迄今最完整、最權威傳記', N'班傑明．富蘭克林：美國心靈的原型')
INSERT [dbo].[Book] ([isbn], [author], [introduction], [name]) VALUES (N'9789869820141', N'金宇澄', N'2018年台北國際書展書展大獎首獎得主金宇澄震撼文壇之作。胡晴舫、柯裕棻、張惠菁、馬家輝、傅月庵、黃麗群、黃崇凱、駱以軍推薦，王家衛即將改編電影，為繼《花樣年華》、《2046》後的第三部曲。', N'繁花')
GO
SET IDENTITY_INSERT [dbo].[BorrowingRecord] ON 

INSERT [dbo].[BorrowingRecord] ([recordId], [borrowingTime], [inventoryId], [returnTime], [userId]) VALUES (1, CAST(N'2024-01-28T20:16:25.3466667' AS DateTime2), 1, CAST(N'2024-01-28T22:29:02.3933333' AS DateTime2), 1)
INSERT [dbo].[BorrowingRecord] ([recordId], [borrowingTime], [inventoryId], [returnTime], [userId]) VALUES (4, CAST(N'2024-01-28T22:27:03.3433333' AS DateTime2), 39, CAST(N'2024-01-28T22:29:02.3933333' AS DateTime2), 1)
INSERT [dbo].[BorrowingRecord] ([recordId], [borrowingTime], [inventoryId], [returnTime], [userId]) VALUES (5, CAST(N'2024-01-28T22:51:56.6700000' AS DateTime2), 1, NULL, 1)
SET IDENTITY_INSERT [dbo].[BorrowingRecord] OFF
GO
SET IDENTITY_INSERT [dbo].[Inventory] ON 

INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (1, N'9789862355848', N'出借中', CAST(N'2020-03-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (2, N'9789862355848', N'出借中', CAST(N'2020-03-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (3, N'9789862355848', N'整理中(歸還後未入庫)', CAST(N'2020-03-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (4, N'9789862355848', N'遺失', CAST(N'2020-03-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (5, N'9789862355848', N'損毀', CAST(N'2020-03-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (6, N'9789862355848', N'廢棄', CAST(N'2020-03-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (7, N'9789862017050', N'在庫', CAST(N'2021-05-18T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (8, N'9789862017050', N'在庫', CAST(N'2021-05-18T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (9, N'9789862017050', N'在庫', CAST(N'2021-05-18T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (10, N'9789862017050', N'出借中', CAST(N'2021-05-18T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (11, N'9789862017050', N'整理中(歸還後未入庫)', CAST(N'2021-05-18T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (12, N'9786263350717', N'在庫', CAST(N'2020-01-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (13, N'9786263350717', N'在庫', CAST(N'2020-01-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (14, N'9786263350717', N'在庫', CAST(N'2020-01-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (15, N'9786263350717', N'出借中', CAST(N'2020-01-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (16, N'9786263350717', N'出借中', CAST(N'2020-01-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (17, N'9789869820141', N'在庫', CAST(N'2020-05-07T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (18, N'9789869820141', N'在庫', CAST(N'2020-05-07T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (19, N'9789869820141', N'在庫', CAST(N'2020-05-07T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (20, N'9789861339047', N'出借中', CAST(N'2020-05-08T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (21, N'9789861339047', N'出借中', CAST(N'2020-05-08T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (22, N'9789861339047', N'出借中', CAST(N'2020-05-08T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (23, N'9789861339047', N'出借中', CAST(N'2020-05-08T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (24, N'9789861755267', N'在庫', CAST(N'2020-02-05T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (25, N'9789861755267', N'在庫', CAST(N'2020-02-05T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (26, N'9789861755267', N'整理中(歸還後未入庫)', CAST(N'2020-02-05T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (27, N'9789861755267', N'整理中(歸還後未入庫)', CAST(N'2020-02-05T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (28, N'9789861755267', N'整理中(歸還後未入庫)', CAST(N'2020-02-05T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (29, N'9789576587399', N'出借中', CAST(N'2021-06-02T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (30, N'9789576587399', N'出借中', CAST(N'2021-06-02T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (31, N'9789576587399', N'出借中', CAST(N'2021-06-02T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (32, N'9789576587399', N'在庫', CAST(N'2021-06-02T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (33, N'9789576587399', N'在庫', CAST(N'2021-06-02T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (34, N'9789576587399', N'整理中(歸還後未入庫)', CAST(N'2021-06-02T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (35, N'9786263554573', N'在庫', CAST(N'2021-06-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (36, N'9786263554573', N'在庫', CAST(N'2021-06-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (37, N'9786263554573', N'在庫', CAST(N'2021-06-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (38, N'9786263554573', N'在庫', CAST(N'2021-06-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (39, N'9786263550537', N'在庫', CAST(N'2021-08-09T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (40, N'9786263550537', N'在庫', CAST(N'2021-08-09T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (41, N'9786263550537', N'在庫', CAST(N'2021-08-09T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (42, N'9786263550537', N'遺失', CAST(N'2021-08-09T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[Inventory] ([inventoryId], [isbn], [status], [storeTime]) VALUES (43, N'9786263550537', N'出借中', CAST(N'2021-08-09T00:00:00.0000000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[Inventory] OFF
GO
SET IDENTITY_INSERT [dbo].[LibUser] ON 

INSERT [dbo].[LibUser] ([userId], [lastLoginTime], [password], [phoneNumber], [registrationTime], [userName]) VALUES (1, CAST(N'2024-01-28T00:00:00.0000000' AS DateTime2), N'$2a$10$FLWVHAtiiv9qyF7QFSyGGOslHU7UbgZWHg5KMBM6jAUJUuRYSA0pm', N'0933567899', CAST(N'2024-01-28T00:00:00.0000000' AS DateTime2), N'Stark')
INSERT [dbo].[LibUser] ([userId], [lastLoginTime], [password], [phoneNumber], [registrationTime], [userName]) VALUES (2, CAST(N'2024-01-28T00:00:00.0000000' AS DateTime2), N'$2a$10$QoHF3ZoYsLa8LOfuFEqh.uE4.f.KVhcDaBuOmsTKdj8TzFzdIQSxq', N'0937567899', CAST(N'2024-01-28T00:00:00.0000000' AS DateTime2), N'Zack')
SET IDENTITY_INSERT [dbo].[LibUser] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ida5lhkqojykoqtb8s48bx71j]    Script Date: 2024/1/28 下午 11:42:28 ******/
ALTER TABLE [dbo].[LibUser] ADD  CONSTRAINT [UK_ida5lhkqojykoqtb8s48bx71j] UNIQUE NONCLUSTERED 
(
	[phoneNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BorrowingRecord]  WITH CHECK ADD  CONSTRAINT [FKoqdnqmc8o7s93nidc97b7cs6e] FOREIGN KEY([inventoryId])
REFERENCES [dbo].[Inventory] ([inventoryId])
GO
ALTER TABLE [dbo].[BorrowingRecord] CHECK CONSTRAINT [FKoqdnqmc8o7s93nidc97b7cs6e]
GO
ALTER TABLE [dbo].[BorrowingRecord]  WITH CHECK ADD  CONSTRAINT [FKtls6e7j50sg4cesna5ytvtm0u] FOREIGN KEY([userId])
REFERENCES [dbo].[LibUser] ([userId])
GO
ALTER TABLE [dbo].[BorrowingRecord] CHECK CONSTRAINT [FKtls6e7j50sg4cesna5ytvtm0u]
GO
ALTER TABLE [dbo].[Inventory]  WITH CHECK ADD  CONSTRAINT [FKpiccijt7cc8imti3ey2pliurh] FOREIGN KEY([isbn])
REFERENCES [dbo].[Book] ([isbn])
GO
ALTER TABLE [dbo].[Inventory] CHECK CONSTRAINT [FKpiccijt7cc8imti3ey2pliurh]
GO
/****** Object:  StoredProcedure [dbo].[INSERT_BORROWING_RECORD_BORROW]    Script Date: 2024/1/28 下午 11:42:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[INSERT_BORROWING_RECORD_BORROW]
	@userId INT,
	@inventoryId INT

AS BEGIN 

   BEGIN TRY
      BEGIN TRANSACTION;
      
      -- Step 1: Insert BorrowingRecord
      DECLARE @borrowingTime DATETIME2 = GETDATE();
      DECLARE @returnTime DATETIME2 = NULL;  

      INSERT INTO BorrowingRecord (userId, inventoryId, borrowingTime, returnTime)
      VALUES (@userId, @inventoryId, @borrowingTime, @returnTime);

      -- Step 2: Update Inventory status (if needed)
      UPDATE Inventory
		SET status = '出借中'
	    WHERE inventoryId= @inventoryId;
      
      COMMIT;
   END TRY
   BEGIN CATCH
      ROLLBACK;  
      
      THROW;
   END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[UPDATE_BORROWING_RECORD_RETURN]    Script Date: 2024/1/28 下午 11:42:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[UPDATE_BORROWING_RECORD_RETURN]
	@userId INT,
	@inventoryId INT

AS BEGIN 

   BEGIN TRY
      BEGIN TRANSACTION;
      
      -- Step 1: Update BorrowingRecord
      DECLARE @returnTime DATETIME2 =  GETDATE();

      UPDATE BorrowingRecord
      SET returnTime = @returnTime
	  WHERE userId=@userId;

      -- Step 2: Update Inventory status 
      UPDATE Inventory
		SET status = '在庫'
	    WHERE inventoryId= @inventoryId;
      
      COMMIT;
   END TRY
   BEGIN CATCH
      ROLLBACK;  
      
      THROW;
   END CATCH
END
GO
USE [master]
GO
ALTER DATABASE [LibreLibrary] SET  READ_WRITE 
GO
