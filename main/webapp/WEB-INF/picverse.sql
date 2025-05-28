-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2025 at 07:40 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `picverse`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(10) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `post_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `comment`, `post_id`, `user_id`) VALUES
(1, 'om', 2, 1),
(2, 'cute', 6, 1),
(3, 'Nice Selfie', 7, 3),
(4, 'enjoy your vaccation', 7, 2),
(5, 'The goat', 9, 1),
(6, 'Omo....very cute !!!', 24, 8),
(7, 'Wow!!!', 28, 8),
(8, 'wow', 37, 7),
(9, 'lalala', 42, 7),
(10, 'wow', 42, 7),
(11, 'cute', 42, 7),
(12, 'beautiful', 43, 7),
(13, 'Big fan', 42, 6),
(14, 'Hiii...', 41, 6),
(15, 'Lovelies...', 40, 6),
(16, 'Memories...', 38, 6),
(17, 'suiiiiii', 35, 7),
(18, 'What are you looking at...?🙄', 36, 6),
(19, 'Brunooo...', 34, 6),
(20, 'nice', 20, 13),
(21, 'wow', 45, 13),
(22, 'nice', 17, 13),
(23, 'Beautiful !!!', 5, 11),
(24, 'Cute.', 40, 11),
(25, 'Lovely...❤', 14, 15),
(26, 'Blinks Forever', 42, 8),
(27, 'Love the view', 1, 15),
(28, 'Beautiful !!!', 6, 8),
(29, 'cute', 13, 13),
(30, 'Aesthetic.', 1, 17),
(31, 'Wooow...😘', 43, 15),
(32, 'wow ', 46, 2),
(33, 'wow', 64, 16),
(34, 'dream', 65, 15),
(35, 'nice view', 61, 16),
(36, 'meow', 59, 16),
(37, 'cool', 48, 19),
(38, 'where is tension ??', 47, 14),
(40, 'Oeyyy...Swag...', 48, 15),
(41, 'wow', 48, 19),
(42, '😎', 48, 19),
(43, 'Cooll...bro', 48, 15),
(46, '👀', 48, 19),
(47, 'nice view', 54, 1),
(48, 'My bros', 48, 1),
(49, 'in my delusion', 47, 1),
(50, 'just go with the flow', 29, 14),
(51, 'Necessity', 67, 8),
(53, 'meow', 73, 20);

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone_number` bigint(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `name`, `phone_number`, `email`, `subject`, `message`) VALUES
(1, 'Simran Dangol', 9875654322, 'simran@gmail.com', 'picverse member', 'hello everyone'),
(2, 'Satish chad', 9810658495, 'cca.santoshsingh@gmail.com', 'qwertyuiop', 'qwertyuiop'),
(3, 'Durga parsai', 9810658495, 'cca.santoshsingh@gmail.com', 'Lower candicate', 'need vote'),
(4, 'Gore Gore', 9876543210, 'gore@gmail.com', 'need brighting cream', 'due to uv ray');

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE `follow` (
  `id` int(10) NOT NULL,
  `follower_id` int(10) NOT NULL,
  `following_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `follow`
--

INSERT INTO `follow` (`id`, `follower_id`, `following_id`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 2, 3),
(4, 1, 3),
(5, 1, 2),
(6, 4, 1),
(7, 4, 3),
(8, 5, 2),
(9, 10, 2),
(10, 10, 1),
(11, 2, 8),
(12, 2, 10),
(13, 2, 5),
(14, 2, 9),
(15, 7, 1),
(16, 7, 2),
(18, 7, 6),
(19, 7, 5),
(20, 7, 9),
(21, 11, 2),
(22, 13, 1),
(23, 13, 6),
(24, 13, 7),
(25, 11, 6),
(26, 13, 9),
(27, 14, 1),
(28, 12, 11),
(29, 14, 5),
(30, 13, 2),
(31, 12, 5),
(32, 14, 9),
(33, 12, 9),
(34, 14, 10),
(35, 12, 10),
(36, 14, 12),
(37, 11, 8),
(38, 14, 7),
(39, 12, 1),
(40, 14, 2),
(41, 8, 11),
(42, 8, 3),
(43, 15, 1),
(44, 15, 5),
(45, 15, 6),
(46, 15, 7),
(47, 17, 2),
(48, 16, 9),
(49, 16, 12),
(50, 17, 5),
(51, 2, 14);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(10) NOT NULL,
  `caption` varchar(50) NOT NULL,
  `image` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_id` int(10) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `caption`, `image`, `created_at`, `user_id`) VALUES
(1, 'view', 'a.png', '2025-05-21 05:31:26', 2),
(2, 'peace', 'daniil-silantev--cY_x-urW6s-unsplash.jpg', '2025-05-20 04:31:45', 1),
(4, 'Wow what a day', 'pauline-loroy-U3aF7hgUSrk-unsplash.jpg', '2025-04-15 00:31:12', 3),
(5, 'Classic Restaurant ', 'd.png', '2025-05-22 04:34:21', 2),
(6, 'Good morning', 'bee.jpg', '2025-05-11 01:34:32', 3),
(7, 'Hi friends', 'img.webp', '2025-05-22 03:34:52', 1),
(8, 'This is amazing', 'caleb-fisher-pgUbpDLJh3E-unsplash.jpg', '2025-05-22 04:15:42', 4),
(9, 'suii\r\n', 'ronaldo.jpg', '2025-05-22 04:37:42', 1),
(10, 'Wow what a day!!!', 'jozsef-szabo-9Et4S5q1otY-unsplash.jpg', '2025-04-20 12:37:47', 4),
(11, 'This is a beautiful city', 'simona-sergi-QiT5YEAt6Is-unsplash.jpg', '2025-04-22 16:38:17', 4),
(12, 'indoor plants', 'brooke-balentine-ak0AO_HWGeU-unsplash.jpg', '2025-05-21 16:39:13', 2),
(13, 'cute dog', 'bernese-mountain-dog-royalty-free-image-1581013857.avif', '2025-05-01 02:46:05', 7),
(14, 'Beach + Friends perfect combination', 'e.png', '2025-05-22 11:46:46', 5),
(15, 'Love Drawing...🧡', 'Sketch.jpeg', '2025-05-22 04:48:38', 6),
(16, 'Beautiful flower', 'elijah-pilchard-zpr6Cvps_MI-unsplash.jpg', '2025-05-19 08:47:54', 5),
(17, 'My Obsession..', 'Paint.jpeg', '2025-05-18 07:48:16', 6),
(18, 'Painting of a Scenary', 'Scenary Painting.jpeg', '2025-04-18 10:48:42', 6),
(19, 'Loved this view', 'zhiyuan-sun-8Atw-ciSzHw-unsplash.jpg', '2025-05-12 04:45:08', 5),
(20, 'Let\'s sketch a girl...', 'Girl Drawing.jpeg', '2025-05-21 20:49:14', 6),
(21, 'fun', 'doremon.jpg', '2025-05-22 04:49:24', 7),
(22, 'good morning..\r\n', 'download (1).jpeg', '2025-05-11 06:51:44', 7),
(23, 'wuhuu', 'befunky-collagery5rwdeqwefdwad-16-1654258234.jpg', '2025-05-13 04:49:56', 7),
(24, 'Hachii 🐶', 'download (2).jpeg', '2025-05-18 13:47:22', 7),
(25, 'Music is Love. ', 'simon-noh-0rmby-3OTeI-unsplash.jpg', '2025-05-16 17:50:30', 8),
(26, 'Me and My Obsession...❤💛', 'Girl Painting.jpg', '2025-05-15 04:27:42', 6),
(27, 'meow 🐱', 'download (3).jpeg', '2025-05-17 04:15:09', 7),
(28, 'Let\'s turn nature into art...', 'Nature and Girl.jpg', '2025-05-19 12:51:16', 6),
(29, 'Guitar', 'f.png', '2025-05-22 04:51:44', 10),
(30, 'runnn', 'download (4).jpeg', '2025-05-16 04:51:54', 7),
(31, 'Sunset', 'marek-piwnicki-ZJAHyorvToM-unsplash.jpg', '2025-05-22 04:52:06', 10),
(32, '🐶', 'download.jpeg', '2025-05-22 04:52:13', 7),
(33, 'Cutie pieeee', 'Drawing1.jpeg', '2025-05-22 04:52:28', 6),
(34, 'brunooo❤️', 'bru.JPG', '2025-05-19 04:52:45', 7),
(35, 'Always on top', 'images.jpeg', '2025-05-19 04:52:47', 9),
(36, '', 'cat.avif', '2025-05-20 04:52:55', 7),
(37, 'Utilizing Free Time...', 'Canvas Painting.jpeg', '2025-05-20 04:53:03', 6),
(38, 'Doraemon ', '2.jpg', '2025-05-20 04:53:07', 10),
(39, 'own it', 'EJcFYUHWwAEcuma.jpg', '2025-05-20 12:53:18', 9),
(40, 'ahhh🥱', 'IMG_8379.JPG', '2025-05-22 13:53:24', 7),
(41, '', 'dfdade2bc59127c4e1c3e9ccd5b28d4f07ad7b06.jpg', '2025-05-10 14:53:57', 9),
(42, 'BlackPink Forever !!!', '16b29y7udzt91.jpg', '2025-05-20 19:54:36', 11),
(43, 'Lonely...😥', 'Painting Walking Girl.jpg', '2025-05-20 21:55:39', 6),
(44, 'Music Therapy', 'alphacolor-66JMudIjDTw-unsplash.jpg', '2025-05-21 08:58:47', 11),
(45, '', 'dogukan-sahin-pc3DmZEMkKY-unsplash.jpg', '2025-05-21 08:58:49', 12),
(46, 'Peace\r\n', 'toni-tan-y28C0M9Mabg-unsplash.jpg', '2025-05-21 08:59:36', 12),
(47, 'Tension', 'javier-miranda-6Pou6SeJ6cY-unsplash.jpg', '2025-05-21 09:00:13', 12),
(48, 'Hi guys\r\n', 'img.webp', '2025-05-21 20:01:54', 12),
(49, 'Coffee ', 'D5.png', '2025-05-21 22:02:12', 14),
(50, 'cute dog', 'D1.png', '2025-05-22 02:02:35', 14),
(51, 'Ice cream chillin\', chillin\', ice cream chillin\'', 'D6.png', '2025-05-22 03:03:49', 14),
(52, 'Is this me...?', 'Boy1.jpg', '2025-05-22 03:03:56', 15),
(53, 'Book', 'Books.png', '2025-05-22 03:50:07', 14),
(54, 'great', '1.jpeg', '2025-05-22 04:10:15', 13),
(55, '', '2.jpeg', '2025-05-22 04:29:42', 13),
(56, 'Sunny Day...', 'Boy2.jpg', '2025-05-22 04:34:48', 15),
(57, 'preety\r\n', '3.jpeg', '2025-05-22 04:45:51', 13),
(58, 'wow', '4.jpeg', '2025-05-22 04:47:59', 13),
(59, 'Tired ', 'D3.png', '2025-05-22 04:50:03', 14),
(60, 'view', '5.jpeg', '2025-05-22 04:55:12', 13),
(61, 'beautiful', '6.jpeg', '2025-05-22 04:57:26', 13),
(62, 'Jay nepal ', 'images (1).jpeg', '2025-05-22 04:59:36', 16),
(63, 'Tasty !!!', 'lama-roscu-Wpg3Qm0zaGk-unsplash.jpg', '2025-05-22 05:00:36', 17),
(64, 'good evening', '7.jpg', '2025-05-22 05:05:40', 13),
(65, 'lalalala', '8.jpeg', '2025-05-22 05:05:57', 13),
(67, 'morning cup of coffee', '9.jpeg', '2025-05-22 05:06:11', 13),
(70, 'meow shock', 'images (4).jpeg', '2025-05-22 05:34:26', 20),
(71, 'innocent me', 'images (5).jpeg', '2025-05-22 05:34:43', 20),
(72, 'Bros', 'img.webp', '2025-05-22 05:34:57', 20),
(73, 'wowow', 'images (6).jpeg', '2025-05-22 05:38:15', 20);

-- --------------------------------------------------------

--
-- Table structure for table `post_like`
--

CREATE TABLE `post_like` (
  `id` int(10) NOT NULL,
  `post_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `is_liked` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post_like`
--

INSERT INTO `post_like` (`id`, `post_id`, `user_id`, `is_liked`) VALUES
(2, 2, 2, 1),
(4, 2, 1, 1),
(5, 6, 3, 1),
(6, 6, 2, 1),
(7, 5, 3, 1),
(8, 4, 3, 1),
(9, 2, 3, 1),
(10, 7, 1, 1),
(11, 6, 1, 1),
(12, 7, 3, 1),
(13, 7, 4, 1),
(14, 6, 4, 1),
(15, 7, 2, 1),
(16, 8, 1, 1),
(17, 9, 1, 1),
(20, 10, 4, 1),
(21, 11, 4, 1),
(22, 9, 4, 1),
(23, 8, 4, 1),
(24, 5, 4, 1),
(25, 10, 1, 1),
(26, 1, 1, 1),
(27, 12, 5, 1),
(28, 9, 5, 1),
(29, 12, 7, 1),
(30, 11, 7, 1),
(31, 10, 7, 1),
(32, 9, 7, 1),
(33, 8, 7, 1),
(34, 7, 7, 1),
(35, 6, 7, 1),
(36, 5, 7, 1),
(37, 15, 1, 1),
(38, 15, 5, 1),
(39, 24, 8, 1),
(40, 27, 6, 1),
(41, 25, 6, 1),
(42, 24, 6, 1),
(43, 23, 6, 1),
(44, 22, 6, 1),
(45, 21, 6, 1),
(47, 19, 6, 1),
(48, 28, 8, 1),
(49, 35, 9, 1),
(50, 25, 10, 1),
(51, 39, 7, 1),
(52, 38, 7, 1),
(53, 37, 7, 1),
(54, 35, 7, 1),
(55, 41, 9, 1),
(56, 40, 9, 1),
(57, 33, 7, 1),
(58, 39, 9, 1),
(59, 41, 10, 1),
(60, 31, 7, 1),
(61, 38, 9, 1),
(62, 30, 7, 1),
(63, 40, 10, 1),
(64, 29, 7, 1),
(65, 37, 9, 1),
(66, 28, 7, 1),
(67, 36, 9, 1),
(68, 39, 10, 1),
(69, 26, 7, 1),
(70, 38, 10, 1),
(71, 25, 7, 1),
(72, 37, 10, 1),
(73, 36, 10, 1),
(76, 35, 10, 1),
(77, 20, 7, 1),
(78, 19, 7, 1),
(79, 34, 10, 1),
(80, 18, 7, 1),
(81, 33, 10, 1),
(82, 14, 7, 1),
(83, 32, 10, 1),
(84, 16, 7, 1),
(85, 15, 7, 1),
(86, 31, 10, 1),
(87, 17, 7, 1),
(88, 29, 10, 1),
(89, 42, 2, 1),
(90, 41, 11, 1),
(91, 42, 6, 1),
(92, 40, 7, 1),
(93, 41, 6, 1),
(94, 40, 6, 1),
(95, 39, 6, 1),
(96, 38, 6, 1),
(97, 36, 6, 1),
(98, 35, 6, 1),
(99, 43, 13, 1),
(100, 43, 12, 1),
(101, 42, 13, 1),
(102, 41, 13, 1),
(103, 41, 12, 1),
(104, 40, 13, 1),
(105, 40, 12, 1),
(106, 39, 13, 1),
(107, 39, 12, 1),
(108, 37, 13, 1),
(109, 36, 13, 1),
(110, 34, 6, 1),
(111, 34, 13, 1),
(112, 33, 13, 1),
(113, 32, 13, 1),
(114, 31, 13, 1),
(115, 30, 13, 1),
(116, 29, 13, 1),
(117, 28, 13, 1),
(118, 27, 13, 1),
(119, 26, 13, 1),
(120, 24, 13, 1),
(121, 25, 13, 1),
(122, 23, 13, 1),
(123, 22, 13, 1),
(124, 21, 13, 1),
(125, 32, 6, 1),
(127, 20, 13, 1),
(128, 31, 6, 1),
(129, 30, 6, 1),
(130, 29, 6, 1),
(131, 45, 12, 1),
(132, 45, 13, 1),
(133, 16, 6, 1),
(134, 14, 6, 1),
(135, 38, 13, 1),
(136, 13, 6, 1),
(137, 35, 13, 1),
(138, 11, 6, 1),
(139, 10, 6, 1),
(140, 17, 13, 1),
(141, 9, 6, 1),
(142, 8, 6, 1),
(143, 7, 6, 1),
(144, 6, 6, 1),
(145, 5, 6, 1),
(146, 4, 6, 1),
(147, 2, 6, 1),
(148, 1, 6, 1),
(149, 46, 12, 1),
(150, 47, 12, 1),
(151, 46, 15, 1),
(152, 45, 15, 1),
(153, 44, 15, 1),
(154, 43, 15, 1),
(155, 42, 15, 1),
(156, 41, 15, 1),
(157, 40, 15, 1),
(158, 45, 11, 1),
(159, 39, 15, 1),
(160, 38, 15, 1),
(161, 43, 11, 1),
(162, 37, 15, 1),
(163, 36, 15, 1),
(164, 35, 15, 1),
(165, 40, 11, 1),
(166, 34, 15, 1),
(167, 33, 15, 1),
(168, 32, 15, 1),
(169, 31, 15, 1),
(170, 30, 15, 1),
(171, 29, 15, 1),
(172, 28, 15, 1),
(173, 27, 15, 1),
(174, 26, 15, 1),
(175, 25, 15, 1),
(176, 24, 15, 1),
(177, 23, 15, 1),
(178, 22, 15, 1),
(179, 21, 15, 1),
(180, 20, 15, 1),
(181, 19, 15, 1),
(182, 18, 15, 1),
(183, 17, 15, 1),
(184, 16, 15, 1),
(185, 15, 15, 1),
(186, 44, 8, 1),
(187, 42, 8, 1),
(188, 47, 15, 1),
(190, 48, 12, 1),
(191, 2, 15, 1),
(192, 4, 15, 1),
(193, 5, 15, 1),
(194, 6, 15, 1),
(195, 7, 15, 1),
(196, 8, 15, 1),
(197, 9, 15, 1),
(198, 10, 15, 1),
(199, 11, 15, 1),
(200, 12, 15, 1),
(201, 13, 15, 1),
(202, 14, 15, 1),
(203, 58, 14, 1),
(204, 57, 14, 1),
(205, 56, 14, 1),
(206, 55, 14, 1),
(207, 54, 14, 1),
(208, 52, 14, 1),
(209, 50, 14, 1),
(210, 51, 14, 1),
(211, 48, 14, 1),
(212, 49, 2, 1),
(214, 67, 15, 1),
(215, 67, 17, 1),
(218, 65, 15, 1),
(219, 64, 15, 1),
(220, 63, 15, 1),
(222, 67, 16, 1),
(224, 62, 15, 1),
(225, 65, 16, 1),
(226, 61, 15, 1),
(227, 60, 15, 1),
(228, 59, 15, 1),
(229, 58, 15, 1),
(230, 57, 15, 1),
(232, 55, 15, 1),
(233, 54, 15, 1),
(236, 64, 16, 1),
(237, 63, 16, 1),
(238, 61, 16, 1),
(239, 59, 14, 1),
(241, 59, 16, 1),
(242, 67, 19, 1),
(243, 53, 14, 1),
(245, 49, 14, 1),
(246, 47, 14, 1),
(248, 67, 1, 1),
(250, 56, 1, 1),
(251, 55, 1, 1),
(252, 54, 1, 1),
(253, 7, 19, 1),
(254, 38, 19, 1),
(255, 37, 19, 1),
(256, 36, 19, 1),
(257, 35, 19, 1),
(258, 34, 19, 1),
(259, 33, 19, 1),
(260, 32, 19, 1),
(261, 31, 19, 1),
(262, 30, 19, 1),
(263, 29, 19, 1),
(264, 28, 19, 1),
(265, 27, 19, 1),
(266, 26, 19, 1),
(267, 48, 1, 1),
(268, 25, 19, 1),
(269, 47, 1, 1),
(270, 24, 19, 1),
(271, 23, 19, 1),
(272, 22, 19, 1),
(273, 21, 19, 1),
(274, 20, 19, 1),
(275, 19, 19, 1),
(276, 18, 19, 1),
(277, 17, 19, 1),
(278, 16, 19, 1),
(280, 67, 12, 1),
(282, 44, 12, 1),
(283, 63, 14, 1),
(284, 67, 8, 1),
(285, 62, 14, 1),
(286, 61, 14, 1),
(287, 67, 2, 1),
(288, 65, 2, 1),
(289, 64, 2, 1),
(290, 63, 2, 1),
(292, 72, 20, 1),
(293, 71, 20, 1),
(294, 70, 20, 1),
(295, 67, 20, 1),
(296, 65, 20, 1),
(297, 64, 20, 1),
(298, 63, 20, 1),
(299, 73, 20, 1),
(300, 47, 20, 1),
(301, 48, 20, 1),
(302, 46, 20, 1),
(303, 45, 20, 1),
(304, 44, 20, 1),
(305, 43, 20, 1),
(306, 42, 20, 1),
(307, 41, 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `saved_post`
--

CREATE TABLE `saved_post` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `post_id` int(10) NOT NULL,
  `is_saved` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `saved_post`
--

INSERT INTO `saved_post` (`id`, `user_id`, `post_id`, `is_saved`) VALUES
(1, 2, 2, 1),
(2, 1, 1, 1),
(3, 1, 12, 1),
(4, 9, 38, 1),
(5, 9, 40, 1),
(6, 7, 37, 1),
(7, 7, 42, 1),
(8, 7, 43, 1),
(9, 7, 35, 1),
(10, 13, 20, 1),
(11, 13, 45, 1),
(12, 13, 17, 1),
(13, 15, 43, 1),
(14, 13, 13, 1),
(15, 16, 61, 1),
(16, 16, 59, 1),
(19, 19, 48, 1),
(20, 1, 54, 1),
(21, 1, 48, 1),
(22, 1, 47, 1),
(23, 20, 73, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` bigint(10) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `bio` varchar(512) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT 'logo.png',
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `email`, `phone_number`, `location`, `hobby`, `bio`, `profile_picture`, `password`) VALUES
(1, 'suii satish', 'suii', 'cca.santoshsingh@gmail.com', 0, 'New Baneshwor', 'football suii', 'football', 'Screenshot 2025-05-19 123718.png', 'kfGCm1tt7oATd2LSZZH9gFdJiRYXUY20EAxlt9TBYWatNzYwaCkbfjOIMwRtJEhR'),
(2, 'Anushka Adhikari', 'anushka', 'anushkaadhikari959@gmail.com', 1239484841, '', '', '', 'karsten-winegeart-5TJDnqfqejA-unsplash.jpg', 'zmDGD8M4sf07VdWYqG/36/6G5NJe2jhe1ZlLAY6dt2YzeOT9umEGI8VUrOyAZaY2f/cP'),
(3, 'Demo demo', 'demo', 'demo@gmail.com', 0, '', '', '', 'cat4.png', 'kobXYB9rArEOf/jnduRFrp7RZ6sbwrf6etqVlfTG2xzVeVjALNP4w1vZvdWnGigJ'),
(4, 'First first', 'first', 'first@gmail.com', 0, '', '', '', 'celine-sayuri-tagami-2s6ORaJY6gI-unsplash.jpg', '5D2jqMxa+9DlvYYQLAXzX5ZsUpdDqdpt88FgvAE3dRZ4SIPMDBbIByrJdVdFD4uRzg=='),
(5, 'Anshu Shrestha', 'Anshu', 'anshu@123gmail.com', 0, '', '', '', 'clayton-cardinalli-LpCnRYK6U_k-unsplash.jpg', 'cVY23bbQg2GrDOUyBnr1+2a8K0d7jyXolzba720/6cJ+Vc+5deXp4GnsPJIVkdgIHQ=='),
(6, 'Aakriti Chaudhary', 'aakriti37', 'aakriti@gmail.com', 9806261589, 'Dang', 'Drawing', 'Peace...🥰', 'Aakriti.jpeg', 'WjlOTYaISMPccd0daFC4+vqYUU/WN0UXSLfE1w6y8Pc5yDu4kvcSkFCRSVDQ491wTQTP'),
(7, 'Simran Dangol', 'simran777', 'simran@gmail.com', 9846470646, 'Thamel', 'Hiking', 'no worries😉', 'simran.JPG', '5o9A6s62HydEtcjcSTnj9tJeHCRzNkc52chkO//ZE/G1kB/P4KRAFzw7/LJnaHee4U+679Q='),
(8, 'Jennie Maharjan', 'jennie', 'jennie@gmail.com', 9874512365, 'Korea', 'Singing', 'Hello Girlies..', 'jennie.jpg', 'Zfd+sO7UcoyqB1GSXKlhsa9VJjOT75gf22qdK07qkK8IBxpeWO3lhaKe515GRVex3DU='),
(9, 'cr ronaldo', 'ronaldo', 'Satishchad36@gmail.com', 9810658495, 'portugal', 'football', 'The goat', 'ronaldo.jpg', 'qZ7nAoSVqCCRh4p2PVuyaMkFbKwQVy2oSaNMvQQzWP3JPcEXHSMTL8RflE8XBOALr69G'),
(10, 'Arya Tamang', 'Arya', 'adhikarianushka2020@gmail.com', 0, '', '', '', 'f.png', 'Tq/R8JLUJMzlTiWoj+aA/IXYvo0wrnIBPCmsDpulIfAEmWk6QzkXqL0hSLkLwV+H'),
(11, 'Rose Shahi', 'rose', 'rose@gmail.com', 0, '', '', '', 'images.jpg', 'fBAWXxk3Ayw4clG2hUtew59VVudAn9CVx/48kMvXV204DZPloCR+YCXScizJvAuI'),
(12, 'gore gore', 'gore', 'gore@gmail.com', 0, 'Tomland', 'Stealing food', 'meow meow', 'img.webp', 'gD+Gxd6c7CssPRz1vyFvMtE8fxQry8LTTwNwQ0wrqwRqfZ+PdtqGztYjSgaM2BHq'),
(13, 'Aasra Dangol', 'aasra123', 'aasra@gmail.com', 9865753431, 'Newroad', 'games', 'hello...', 'Screenshot 2025-05-21 at 11.43.50.png', 'mv5qNRLr3/cNyFYLMSyVT5yX0j9MWbeKrU83IDdKIvylIUYn3UFKGQizQfWVyJs8UQx4Sw=='),
(14, 'Reshna Sharma', 'reshna', 'reshna@123gmail.com', 0, '', '', '', 'logo.png', 'WhvQ7gNWfLo1w2f5Uu9jMUSJxlmY4d8+/c/cdp2MIrcT6ODG9o90lqERbOwcIfCWsC0='),
(15, 'Samit Chaudhary', 'samin27', 'samit@gmail.com', 9785462315, 'Dang', 'Playing Games', 'Yes that\'s me..', 'Boy4.jpg', 'BwHf8EtMBG/iLgCA7kw05L1MhrqZuQiKHBqckK0btDnE4CeZNapc+d4dCkUD5RZ2rA=='),
(16, 'Durga parsai', 'durga', 'durga@gmail.com', NULL, NULL, NULL, NULL, 'logo.png', 'kDb5UBL3JuL9HSaHhOxkG73vrtCCUkhsFV2ekbCrmaqqK2GwFtehBkpZQ1b0YkcLhQ=='),
(17, 'Joshua Kim', 'joshua', 'joshua@gmail.com', 0, '', '', '', 'de-an-sun-b57RqS-nQ1c-unsplash.jpg', '+MVUZkfM4eQFVKOW++txyH01p+/mFKooI2UoYZQATBfkJBDoH6j/Wfjbctaw21mm8ZE='),
(18, 'admin admin', 'admin', 'admin@gmail.com', NULL, NULL, NULL, NULL, 'logo.png', 'Xffer5+G9wfexfKQWlvLs+V0IL7fqQnpMJf8biDS0rCpHS6KD3RSIekMcnbb8R2lXQ=='),
(19, 'Ram Sharma', 'ram334', 'ram@gmail.com', NULL, NULL, NULL, NULL, 'logo.png', 'fg5ko5tpnRZ0NJxIDyFOHJIqU08vi/av8iSXFBxgbRWy6GWD920DC68chhjT0ZUl7E8='),
(20, 'meow meow', 'meow', 'meow@gmai.com', 9876543210, 'meowland', 'meow', 'meow', 'images (4).jpeg', 'TGJnq8bOAtNuCYaLz0JT+1XZ2UF/bwgA22mjCTcvOAPhNSYPrmZlfrnAap/++XNJ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `post_id` (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `follower_id` (`follower_id`),
  ADD KEY `following_id` (`following_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`user_id`);

--
-- Indexes for table `post_like`
--
ALTER TABLE `post_like`
  ADD PRIMARY KEY (`id`),
  ADD KEY `posts_id` (`post_id`),
  ADD KEY `users_id` (`user_id`);

--
-- Indexes for table `saved_post`
--
ALTER TABLE `saved_post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `post_save_id` (`post_id`),
  ADD KEY `user_saved_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `post_like`
--
ALTER TABLE `post_like`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=308;

--
-- AUTO_INCREMENT for table `saved_post`
--
ALTER TABLE `saved_post`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follower_id` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `following_id` FOREIGN KEY (`following_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `post_like`
--
ALTER TABLE `post_like`
  ADD CONSTRAINT `posts_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `saved_post`
--
ALTER TABLE `saved_post`
  ADD CONSTRAINT `post_save_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `user_saved_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
