USE [master]
GO
/****** Object:  Database [BookStore]    Script Date: 6/9/2023 12:20:55 AM ******/
CREATE DATABASE [BookStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookStore', FILENAME = N'/var/opt/mssql/data/BookStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BookStore_log', FILENAME = N'/var/opt/mssql/data/BookStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [BookStore] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BookStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookStore] SET RECOVERY FULL 
GO
ALTER DATABASE [BookStore] SET  MULTI_USER 
GO
ALTER DATABASE [BookStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BookStore] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BookStore] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookStore', N'ON'
GO
ALTER DATABASE [BookStore] SET QUERY_STORE = OFF
GO
USE [BookStore]
GO
/****** Object:  Table [dbo].[BookGenres]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookGenres](
	[genreID] [int] NOT NULL,
	[genreName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[genreID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[bookID] [int] NOT NULL,
	[bookTitle] [varchar](255) NULL,
	[bookPrice] [decimal](10, 2) NULL,
	[formatID] [int] NULL,
	[genreID] [int] NULL,
	[publisherID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transactions]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transactions](
	[transactionID] [int] NOT NULL,
	[customerID] [int] NULL,
	[quantity] [int] NULL,
	[transactionPurchaseAmount] [decimal](10, 2) NULL,
	[transactionDATE] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[transactionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books_Transactions]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books_Transactions](
	[bookID] [int] NULL,
	[transactionID] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[TopGenre]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[TopGenre] AS (
    SELECT genreName, (SELECT MAX(quantity) FROM Transactions
 WHERE transactionDATE BETWEEN '2023-05-01' AND '2023-05-31') AS 'Quantity'
FROM BookGenres
WHERE genreID = 
(SELECT genreID FROM Books WHERE bookID = 
(SELECT bookID FROM Books_Transactions WHERE transactionID = 
(SELECT transactionID FROM Transactions WHERE quantity = 
(SELECT MAX(quantity) FROM Transactions WHERE transactionDATE BETWEEN '2023-05-01' AND '2023-05-31'))))
);
GO
/****** Object:  Table [dbo].[Authors]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authors](
	[authorID] [int] NOT NULL,
	[authorFirstName] [varchar](255) NULL,
	[authorLastName] [varchar](255) NULL,
	[authorEmail] [varchar](255) NULL,
	[publisherID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[authorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authors_Books]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authors_Books](
	[authorID] [int] NULL,
	[bookID] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[NumBooksAuthor]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[NumBooksAuthor] AS (
    SELECT COUNT(DISTINCT bookID) AS 'NumBooks' FROM Authors_Books WHERE authorID = (SELECT authorID From Authors WHERE authorFirstName = 'John' AND authorLastName = 'Smith')
)
GO
/****** Object:  View [dbo].[GenreOfBook]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[GenreOfBook] AS (
    SELECT genreName FROM BookGenres WHERE genreID = (SELECT genreID FROM Books WHERE BookTitle = 'Book 1')
);
GO
/****** Object:  Table [dbo].[BookFormats]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookFormats](
	[formatID] [int] NOT NULL,
	[formatName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[formatID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[customerID] [int] NOT NULL,
	[customerFirstName] [varchar](255) NULL,
	[customerLastName] [varchar](255) NULL,
	[customerEmail] [varchar](255) NULL,
	[customerPhone] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[customerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Publisher]    Script Date: 6/9/2023 12:20:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Publisher](
	[publisherID] [int] NOT NULL,
	[publisherName] [varchar](255) NULL,
	[publisherEmail] [varchar](255) NULL,
	[publisherStreetAddress] [varchar](255) NULL,
	[publisherCity] [varchar](255) NULL,
	[publisherState] [varchar](255) NULL,
	[publisherCountry] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[publisherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Authors]  WITH CHECK ADD FOREIGN KEY([publisherID])
REFERENCES [dbo].[Publisher] ([publisherID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Authors_Books]  WITH CHECK ADD FOREIGN KEY([authorID])
REFERENCES [dbo].[Authors] ([authorID])
GO
ALTER TABLE [dbo].[Authors_Books]  WITH CHECK ADD FOREIGN KEY([bookID])
REFERENCES [dbo].[Books] ([bookID])
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([formatID])
REFERENCES [dbo].[BookFormats] ([formatID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([genreID])
REFERENCES [dbo].[BookGenres] ([genreID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([publisherID])
REFERENCES [dbo].[Publisher] ([publisherID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Books_Transactions]  WITH CHECK ADD FOREIGN KEY([bookID])
REFERENCES [dbo].[Books] ([bookID])
GO
ALTER TABLE [dbo].[Books_Transactions]  WITH CHECK ADD FOREIGN KEY([transactionID])
REFERENCES [dbo].[Transactions] ([transactionID])
GO
ALTER TABLE [dbo].[Transactions]  WITH CHECK ADD FOREIGN KEY([customerID])
REFERENCES [dbo].[Customers] ([customerID])
ON DELETE CASCADE
GO
USE [master]
GO
ALTER DATABASE [BookStore] SET  READ_WRITE 
GO
