USE [master]
GO
/****** Object:  Database [bookstore_datn_2022]    Script Date: 26/12/2022 10:11:52 AM ******/
CREATE DATABASE [bookstore_datn_2022]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'bookstore_datn_2022', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.HNTEXPRESS\MSSQL\DATA\bookstore_datn_2022.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'bookstore_datn_2022_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.HNTEXPRESS\MSSQL\DATA\bookstore_datn_2022_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [bookstore_datn_2022] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [bookstore_datn_2022].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [bookstore_datn_2022] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET ARITHABORT OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [bookstore_datn_2022] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [bookstore_datn_2022] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET  ENABLE_BROKER 
GO
ALTER DATABASE [bookstore_datn_2022] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [bookstore_datn_2022] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET RECOVERY FULL 
GO
ALTER DATABASE [bookstore_datn_2022] SET  MULTI_USER 
GO
ALTER DATABASE [bookstore_datn_2022] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [bookstore_datn_2022] SET DB_CHAINING OFF 
GO
ALTER DATABASE [bookstore_datn_2022] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [bookstore_datn_2022] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [bookstore_datn_2022] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [bookstore_datn_2022] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [bookstore_datn_2022] SET QUERY_STORE = OFF
GO
USE [bookstore_datn_2022]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](500) NOT NULL,
	[slug] [varchar](255) NOT NULL,
	[isDeleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[orderId] [bigint] NULL,
	[productId] [bigint] NULL,
	[price] [decimal](12, 3) NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[userId] [bigint] NULL,
	[address] [nvarchar](255) NOT NULL,
	[phone] [varchar](11) NOT NULL,
	[shippingFee] [decimal](12, 3) NOT NULL,
	[totalPrice] [decimal](12, 3) NOT NULL,
	[orderStatus] [nvarchar](255) NOT NULL,
	[createdDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_images]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_images](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[productId] [bigint] NULL,
	[imgName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_reviews]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_reviews](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[productId] [bigint] NULL,
	[description] [ntext] NULL,
	[userId] [bigint] NULL,
	[createdDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_types]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_types](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[slug] [nchar](50) NOT NULL,
	[isDeleted] [bit] NOT NULL,
 CONSTRAINT [PK_product_types] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[categoryId] [bigint] NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](12, 3) NOT NULL,
	[imgName] [varchar](255) NOT NULL,
	[description] [ntext] NOT NULL,
	[discount] [int] NULL,
	[slug] [varchar](255) NOT NULL,
	[isDeleted] [bit] NOT NULL,
	[productTypeId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](255) NULL,
	[fullname] [nvarchar](255) NULL,
	[hashPassword] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[imgUrl] [varchar](255) NULL,
	[createdDate] [datetime2](7) NULL,
	[roleId] [bigint] NULL,
	[isEnabled] [bit] NULL,
	[resetPasswordToken] [varchar](255) NULL,
	[verificationCode] [varchar](255) NULL,
	[authProvider] [varchar](255) NULL,
	[isDeleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([id], [name], [slug], [isDeleted]) VALUES (1, N'Sách Mới', N'sach-moi', 0)
INSERT [dbo].[categories] ([id], [name], [slug], [isDeleted]) VALUES (2, N'Sách Tái Bản', N'sach-tai-ban', 0)
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
SET IDENTITY_INSERT [dbo].[order_details] ON 

INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10005, 10015, 1, CAST(50000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10006, 10015, 2, CAST(36000.000 AS Decimal(12, 3)), 2)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10007, 10016, 16, CAST(58500.000 AS Decimal(12, 3)), 2)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10008, 10016, 18, CAST(60000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10009, 10016, 3, CAST(60000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10010, 10016, 19, CAST(60000.000 AS Decimal(12, 3)), 3)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10011, 10016, 8, CAST(25000.000 AS Decimal(12, 3)), 2)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10012, 10016, 12, CAST(35000.000 AS Decimal(12, 3)), 2)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10013, 10017, 2, CAST(36000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10014, 10017, 7, CAST(28000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10015, 10018, 19, CAST(60000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10016, 10018, 15, CAST(30000.000 AS Decimal(12, 3)), 1)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10017, 10019, 19, CAST(60000.000 AS Decimal(12, 3)), 2)
INSERT [dbo].[order_details] ([id], [orderId], [productId], [price], [quantity]) VALUES (10018, 10020, 17, CAST(55000.000 AS Decimal(12, 3)), 1)
SET IDENTITY_INSERT [dbo].[order_details] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([id], [userId], [address], [phone], [shippingFee], [totalPrice], [orderStatus], [createdDate]) VALUES (10015, 3, N'Tân Phú, HCM', N'0123234235', CAST(30000.000 AS Decimal(12, 3)), CAST(152000.000 AS Decimal(12, 3)), N'CHUA_XAC_NHAN', CAST(N'2022-11-17T12:14:23.357' AS DateTime))
INSERT [dbo].[orders] ([id], [userId], [address], [phone], [shippingFee], [totalPrice], [orderStatus], [createdDate]) VALUES (10016, 3, N'Tân Bình, HCM', N'0123234235', CAST(0.000 AS Decimal(12, 3)), CAST(537000.000 AS Decimal(12, 3)), N'DANG_GIAO_HANG', CAST(N'2022-11-17T12:15:43.517' AS DateTime))
INSERT [dbo].[orders] ([id], [userId], [address], [phone], [shippingFee], [totalPrice], [orderStatus], [createdDate]) VALUES (10017, 3, N'Hóc Môn, HCM', N'0123234235', CAST(30000.000 AS Decimal(12, 3)), CAST(94000.000 AS Decimal(12, 3)), N'DA_XAC_NHAN', CAST(N'2022-10-17T14:45:11.663' AS DateTime))
INSERT [dbo].[orders] ([id], [userId], [address], [phone], [shippingFee], [totalPrice], [orderStatus], [createdDate]) VALUES (10018, 3, N'Quận 2, HCM', N'0123234235', CAST(30000.000 AS Decimal(12, 3)), CAST(120000.000 AS Decimal(12, 3)), N'DA_GIAO_HANG', CAST(N'2022-10-17T14:45:26.143' AS DateTime))
INSERT [dbo].[orders] ([id], [userId], [address], [phone], [shippingFee], [totalPrice], [orderStatus], [createdDate]) VALUES (10019, 3, N'Quận 8, HCM', N'0123234235', CAST(30000.000 AS Decimal(12, 3)), CAST(150000.000 AS Decimal(12, 3)), N'DA_XAC_NHAN', CAST(N'2022-10-17T14:45:41.323' AS DateTime))
INSERT [dbo].[orders] ([id], [userId], [address], [phone], [shippingFee], [totalPrice], [orderStatus], [createdDate]) VALUES (10020, 5, N'0578HCM', N'0963995943', CAST(30000.000 AS Decimal(12, 3)), CAST(85000.000 AS Decimal(12, 3)), N'CHUA_XAC_NHAN', CAST(N'2022-12-12T10:19:50.303' AS DateTime))
SET IDENTITY_INSERT [dbo].[orders] OFF
GO
SET IDENTITY_INSERT [dbo].[product_images] ON 

INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (2, 1, N'kinhte3.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (3, 1, N'kinhte4.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (4, 2, N'kinhte5.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (5, 2, N'kinhte6.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (6, 3, N'kynangsong4.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (7, 3, N'kynangsong3.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (8, 4, N'kynangsong2.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (9, 4, N'kynangsong5.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (10, 5, N'kynangsong2.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (11, 5, N'kynangsong7.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (12, 6, N'ngoaingu2.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (13, 6, N'ngoaingu3.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (14, 7, N'ngoaingu6.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (15, 7, N'ngoaingu1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (16, 8, N'tamlinh3.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (17, 8, N'tamlinh4.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (18, 9, N'tamlinh5.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (19, 9, N'tamlinh6.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (20, 10, N'tamly1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (21, 10, N'tamly3.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (22, 11, N'thamkhao1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (23, 11, N'thamkhao2.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (24, 12, N'thamkhao5.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (25, 12, N'thamkhao6.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (26, 13, N'thieunhi1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (27, 13, N'thieunhi2.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (28, 14, N'thieunhi3.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (29, 14, N'thieunhi4.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (30, 15, N'vanhoadulich1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (31, 15, N'vanhoadulich4.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (32, 16, N'vanhoadulich5.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (33, 16, N'vanhoadulich6.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (34, 17, N'vanhoc1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (35, 17, N'vanhoc4.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (36, 18, N'vanhoc5.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (37, 18, N'vanhoc.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (38, 19, N'manga1.jpg')
INSERT [dbo].[product_images] ([id], [productId], [imgName]) VALUES (39, 23, N'manga5.jpg')
SET IDENTITY_INSERT [dbo].[product_images] OFF
GO
SET IDENTITY_INSERT [dbo].[product_reviews] ON 

INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (1, 1, N'Sách hay', 1, CAST(N'2022-11-04T17:30:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (2, 1, N'Sách ý nghĩa', 2, CAST(N'2022-11-01T08:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (3, 2, N'Tôi chưa từng đọc qua cuốn này', 3, CAST(N'2022-11-04T20:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (4, 2, N'Giá bao nhiêu vậy', 4, CAST(N'2022-09-20T12:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (5, 3, N'Cảm ơn nhé', 5, CAST(N'2022-11-04T08:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (6, 3, N'Nhân viên nhiệt tình', 6, CAST(N'2022-10-06T07:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (7, 4, N'Sách hay', 7, CAST(N'2022-11-06T08:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (8, 4, N'sach đóng gói cẩn thận', 8, CAST(N'2022-11-07T09:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (9, 5, N'địa chỉ ở đâu ạ', 9, CAST(N'2022-11-07T12:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (10, 5, N'tin chuẩn chưa anh', 10, CAST(N'2022-11-07T11:30:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (11, 6, N'here we gooo', 11, CAST(N'2022-09-08T12:30:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (12, 6, N'Cơm ngon hợp giá tiền', 12, CAST(N'2022-11-08T17:30:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (13, 7, N'Bánh ngon dữ thần mèn đét ơi, mai mốt ủng hộ tiếp', 13, CAST(N'2022-08-08T08:52:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (14, 7, N'Bánh ít gất ngon 50 cho quán luôn', 14, CAST(N'2022-11-08T08:36:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (15, 8, N'Sách đẹp, nội dung hay', 15, CAST(N'2022-11-09T19:10:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (16, 8, N'cái đèn trên tầng bị hư rồi', 16, CAST(N'2022-10-29T20:07:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (17, 9, N'alo, alo', 17, CAST(N'2022-11-09T21:10:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (18, 9, N'lễ tân chán quá ad ơi', 18, CAST(N'2022-10-29T23:56:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (19, 10, N'Sách đẹp, nội dung hay', 19, CAST(N'2022-11-10T06:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (20, 10, N'Giá bao nhiêu vậy', 20, CAST(N'2022-11-10T21:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (21, 11, N'tôi muốn gặp giám đốc', 1, CAST(N'2022-09-02T08:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (22, 11, N'Tuyệt vời ông mặt trời', 2, CAST(N'2022-11-10T09:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (23, 12, N'Sách ý nghĩa', 3, CAST(N'2022-11-10T10:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (24, 12, N'Làm sao để order', 4, CAST(N'2022-08-11T08:03:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (25, 13, N'Trời mưa rồi', 5, CAST(N'2022-11-11T08:35:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (26, 13, N'xin chào', 6, CAST(N'2022-10-21T09:56:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (27, 14, N'xin giá bạn ơi', 7, CAST(N'2022-11-12T08:45:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (28, 14, N'xin chào', 8, CAST(N'2022-10-17T11:55:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (29, 15, N'xin địa chỉ', 9, CAST(N'2022-11-14T22:16:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (30, 15, N'hahahaha', 10, CAST(N'2022-11-16T15:25:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (31, 16, N'có sách nấu ăn ko', 11, CAST(N'2022-08-15T12:35:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (32, 16, N'xin chào', 12, CAST(N'2022-11-17T13:55:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (33, 17, N'rep inb', 13, CAST(N'2022-10-14T17:55:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (34, 17, N'chào admin', 14, CAST(N'2022-11-18T18:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (35, 18, N'có truyện tranh ko ạ', 15, CAST(N'2022-11-19T18:30:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (36, 18, N'xin chào', 16, CAST(N'2022-09-21T20:00:00.000' AS DateTime))
INSERT [dbo].[product_reviews] ([id], [productId], [description], [userId], [createdDate]) VALUES (37, 19, N'tôi muốn mua sách', 17, CAST(N'2022-10-22T08:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[product_reviews] OFF
GO
SET IDENTITY_INSERT [dbo].[product_types] ON 

INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (1, N'Sách kinh tế', N'sach-kinh-te                                      ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (2, N'Sách kỹ năng sống', N'sach-ky-nang-song                                 ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (3, N'Sách ngoại ngữ', N'sach-ngoai-ngu                                    ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (4, N'Sách tâm linh', N'sach-tam-linh                                     ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (5, N'Sách tâm lý', N'sach-tam-ly                                       ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (6, N'Sách tham khảo', N'sach-tham-khao                                    ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (7, N'Sách thiếu nhi', N'sach-thieu-nhi                                    ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (8, N'Sách văn hóa du lich', N'sach-van-hoa-du-lich                              ', 0)
INSERT [dbo].[product_types] ([id], [name], [slug], [isDeleted]) VALUES (9, N'Sách văn học', N'sach-van-hoc                                      ', 0)
SET IDENTITY_INSERT [dbo].[product_types] OFF
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (1, N'Để không có tiền vẫn tạo ra tiền', 1, 49, CAST(50000.000 AS Decimal(12, 3)), N'kinhte1.jpg', N'Tác giả: Robert T Kiyosaki', NULL, N'de-khong-co-tien-van-tao-ra-tien', 0, 1)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (2, N'Bí mật tư duy triệu phú', 2, 30, CAST(90000.000 AS Decimal(12, 3)), N'kinhte2.jpg', N'Tác giả: T.Harv Eker', 20, N'bi-mat-tu-duy-trieu-phu', 0, 1)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (3, N'Đắc nhân tâm', 2, 22, CAST(60000.000 AS Decimal(12, 3)), N'kynangsong2.jpg', N'Tác giả: Dale Carnegie', 0, N'dac-nhan-tam', 0, 2)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (4, N'Trên đường băng', 1, 20, CAST(45000.000 AS Decimal(12, 3)), N'kynangsong1.jpg', N'Tác giả: Tony Buoi Sang', 0, N'tren-duong-bang', 0, 2)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (5, N'Dám nghĩ lớn', 2, 14, CAST(45000.000 AS Decimal(12, 3)), N'kynangsong6.jpg', N'Tác giả: David J.Schwartz', 0, N'dam-nghi-lon', 0, 2)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (6, N'Giải thích ngữ pháp tiếng Anh', 1, 35, CAST(55000.000 AS Decimal(12, 3)), N'ngoaingu5.jpg', N'Tác giả: Mai Lan Huong, Hà Thanh Uyên', 0, N'giai-thich-ngu-phap-tieng-anh', 0, 3)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (7, N'30 chủ đề từ vựng', 2, 14, CAST(85000.000 AS Decimal(12, 3)), N'ngoaingu4.jpg', N'Tác giả: Trang Anh', 20, N'30-chu-de-tu-vung', 0, 3)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (8, N'Biểu tượng tâm linh', 1, 20, CAST(60000.000 AS Decimal(12, 3)), N'tamlinh1.jpg', N'Tác giả: Melanie Barnum', 0, N'bieu-tuong-tam-linh', 0, 4)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (9, N'Ai Cập huyền bí', 1, 15, CAST(55000.000 AS Decimal(12, 3)), N'tamlinh2.jpg', N'Tác giả: Paul Brunton', 0, N'ai-cap-huyen-bi', 0, 4)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (10, N'Đọc vị vật kỳ ai', 2, 26, CAST(60000.000 AS Decimal(12, 3)), N'tamly2.jpg', N'Tác giả: David J.Lieberman', 0, N'doc-vi-bat-ki-ai', 0, 5)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (11, N'500 bài tập vật lý', 2, 23, CAST(50000.000 AS Decimal(12, 3)), N'thamkhao4.jpg', N'Tác giả: ThS Phan Hoàng Van', 0, N'500-bai-tap-vat-ly', 0, 6)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (12, N'Hóa học 9', 1, 58, CAST(70000.000 AS Decimal(12, 3)), N'thamkhao3.jpg', N'Tác giả: Lê Xuân Trọng', 0, N'hoa-hoc-9', 0, 6)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (13, N'Hoàng tử bé', 1, 55, CAST(45000.000 AS Decimal(12, 3)), N'thieunhi3.jpg', N'Tác giả: Antoine de Saint-Exupéry', 50, N'hoang-tu-be', 0, 7)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (14, N'Bách khoa khủng long', 1, 55, CAST(45000.000 AS Decimal(12, 3)), N'thieunhi5.jpg', N'Tác giả: Dougal Dixon', 50, N'bach-khoa-khung-long', 0, 7)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (15, N'Du lịch Việt Nam', 2, 34, CAST(80000.000 AS Decimal(12, 3)), N'vanhoadulich2.jpg', N'Tác giả: Võ Van Thành, Phan Huy Xu', 0, N'du-lich-viet-nam', 0, 8)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (16, N'Du lịch, Du khảo', 2, 28, CAST(65000.000 AS Decimal(12, 3)), N'vanhoadulich3.jpg', N' Tác giả: Võ Van Thành', 10, N'du-lich-du-khao', 0, 8)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (17, N'Nhà giả kim', 1, 21, CAST(55000.000 AS Decimal(12, 3)), N'vanhoc2.jpg', N'Tác giả:  Paulo Coelho', 50, N'nha-gia-kim', 0, 9)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (18, N'Cây cam ngọt của tôi', 1, 14, CAST(60000.000 AS Decimal(12, 3)), N'vanhoc3.jpg', N'Tác giả: José Mauro de Vasconcelos', 0, N'cay-cam-ngot-cua-toi', 0, 9)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (19, N'Naruto', 1, 24, CAST(70000.000 AS Decimal(12, 3)), N'manga2.jpg', N'Tác giả: Kishimoto Masashi', 0, N'naruto', 0, 7)
INSERT [dbo].[products] ([id], [name], [categoryId], [quantity], [price], [imgName], [description], [discount], [slug], [isDeleted], [productTypeId]) VALUES (23, N'Người nam châm', 2, 12, CAST(60000.000 AS Decimal(12, 3)), N'vanhoc6.jpg', N'Tác giả: Jack Canfield và D.D.Watkins', 0, N'nguoi-nam-cham', 0, 9)
SET IDENTITY_INSERT [dbo].[products] OFF
GO
SET IDENTITY_INSERT [dbo].[roles] ON 

INSERT [dbo].[roles] ([id], [name]) VALUES (1, N'admin')
INSERT [dbo].[roles] ([id], [name]) VALUES (2, N'user')
SET IDENTITY_INSERT [dbo].[roles] OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (1, N'admin', N'Admin', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'bookstore_datn_2022@dacsanmientrung.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 1, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (2, N'vunv', N'Võ Nguyên Vũ', N'$2a$12$ckJiloePfDCZthh39RlKE.oHjIm0aM9BJ63JTlhV46xDpxQ/fNMhG', N'vuvnps14942@fpt.edu.vn', N'vuvn.jpg', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 1, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (3, N'binhpt', N'Phạm Thanh Bình', N'$2a$12$ckJiloePfDCZthh39RlKE.oHjIm0aM9BJ63JTlhV46xDpxQ/fNMhG', N'binhpt@gmail.com', N'binhnt.jpg', CAST(N'2022-11-01T16:43:24.5866667' AS DateTime2), 1, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (4, N'chanhvm', N'Võ Minh Chánh', N'$2a$12$ckJiloePfDCZthh39RlKE.oHjIm0aM9BJ63JTlhV46xDpxQ/fNMhG', N'tchanhvm@gmail.com', N'chanhvm.jpg', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 1, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (5, N'cuongnt', N'Nguyễn Tấn Cương', N'$2a$10$/XHTgs1tDaV34WXKZwahEOnVspvnz2PMS3ylFZZeVFjYobNyuMec6', N'cuongntps17382@fpt.edu.vn', N'cuongnt.png', CAST(N'2022-11-01T16:43:24.5866667' AS DateTime2), 1, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (6, N'haihq', N'Hàn Quốc Hải', N'$2a$12$ckJiloePfDCZthh39RlKE.oHjIm0aM9BJ63JTlhV46xDpxQ/fNMhG', N'haihq@gmail.com', N'haihq.jpg', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 1, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (7, N'ducpv', N'Phan Văn Đức', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'lducpv@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (8, N'phuongnc', N'Nguyễn Công Phượng', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'phuongnc@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (9, N'dungbt', N'Bùi Tiến Dũng', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'dungbt@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 1)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (10, N'lamdv', N'Đặng Văn Lâm', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'lamdv@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (11, N'hoangnt', N'Nguyễn Trọng Hoàng', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'datpm@gamil.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (12, N'toannv', N'Nguyễn Văn Toàn', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'vult@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (13, N'vinhlc', N'Lê Công Vinh', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'quannm@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (14, N'quyetnv', N'Nguyễn Văn Quyết', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'chitc@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (15, N'sondh', N'Dương Hồng Sơn', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'phatnq@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (16, N'epvt', N'Phan Văn Tài Em', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'anpm@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (17, N'duclh', N'Lê Huỳnh Đức', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'binhnk@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (18, N'haudv', N'Đoàn Văn Hậu', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'khanhld@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (19, N'haiqn', N'Quế Ngọc Hải', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'phanm@gmail.com', N'', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
INSERT [dbo].[users] ([id], [username], [fullname], [hashPassword], [email], [imgUrl], [createdDate], [roleId], [isEnabled], [resetPasswordToken], [verificationCode], [authProvider], [isDeleted]) VALUES (20, N'taiht', N'Hồ Tấn Tài', N'$2a$10$7YIj5SGLAAev58xzTj6y3Owj/0e9ZVBZy9Hoc0nXxeysm8wovQUY6', N'nhatlm@gmail.com', N'8f8d04cf.jpg', CAST(N'2022-09-01T16:43:24.5866667' AS DateTime2), 2, 1, NULL, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__products__32DD1E4C88513F9E]    Script Date: 26/12/2022 10:11:52 AM ******/
ALTER TABLE [dbo].[products] ADD UNIQUE NONCLUSTERED 
(
	[slug] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__roles__72E12F1BD1B8E88F]    Script Date: 26/12/2022 10:11:52 AM ******/
ALTER TABLE [dbo].[roles] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__users__AB6E6164548D2440]    Script Date: 26/12/2022 10:11:52 AM ******/
ALTER TABLE [dbo].[users] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__users__F3DBC572ADF63183]    Script Date: 26/12/2022 10:11:52 AM ******/
ALTER TABLE [dbo].[users] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[categories] ADD  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[product_types] ADD  CONSTRAINT [DF_Product_types_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (getdate()) FOR [createdDate]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [isEnabled]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([orderId])
REFERENCES [dbo].[orders] ([id])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([productId])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[product_images]  WITH CHECK ADD FOREIGN KEY([productId])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[product_reviews]  WITH CHECK ADD  CONSTRAINT [FKhonsga9x61myhhlhbfhd29wk9] FOREIGN KEY([productId])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[product_reviews] CHECK CONSTRAINT [FKhonsga9x61myhhlhbfhd29wk9]
GO
ALTER TABLE [dbo].[product_reviews]  WITH CHECK ADD  CONSTRAINT [FKsn23ja8kbk6akk0627k24xy09] FOREIGN KEY([userId])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[product_reviews] CHECK CONSTRAINT [FKsn23ja8kbk6akk0627k24xy09]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[categories] ([id])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([productTypeId])
REFERENCES [dbo].[product_types] ([id])
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK91mjuf61b7rhfjnjkucfc0omf] FOREIGN KEY([roleId])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK91mjuf61b7rhfjnjkucfc0omf]
GO
/****** Object:  StoredProcedure [dbo].[sp_getTotalOrderPerMonth]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_getTotalOrderPerMonth]
(
	@month varchar(2),
	@year varchar(4)
)
AS BEGIN
	DECLARE @result varchar(20)
	SET @result = (SELECT
						COUNT(id) AS 'totalOrder'
					FROM
						orders
					WHERE MONTH(createdDate) = @month
					AND YEAR(createdDate) = @year)
	IF @result IS NULL BEGIN SET @result = '0' END
	SELECT @result
END
GO
/****** Object:  StoredProcedure [dbo].[sp_getTotalPricePerMonth]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_getTotalPricePerMonth]
(
	@month varchar(2),
	@year varchar(4)
)
AS BEGIN
	DECLARE @result varchar(20)
	SET @result = (SELECT
						SUM(totalPrice) AS 'totalPrice'
					FROM
						orders
					WHERE MONTH(createdDate) = @month
					AND YEAR(createdDate) = @year)
	IF @result IS NULL BEGIN SET @result = '0' END
	SELECT @result
END
GO
/****** Object:  StoredProcedure [dbo].[sp_getTotalUserPerMonth]    Script Date: 26/12/2022 10:11:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_getTotalUserPerMonth]
(
	@month varchar(2),
	@year varchar(4)
)
AS BEGIN
	DECLARE @result varchar(20)
	SET @result = (SELECT
						COUNT(id) AS 'totalUser'
					FROM
						users
					WHERE MONTH(createdDate) = @month
					AND YEAR(createdDate) = @year)
	IF @result IS NULL BEGIN SET @result = '0' END
	SELECT @result
END
GO
USE [master]
GO
ALTER DATABASE [bookstore_datn_2022] SET  READ_WRITE 
GO
