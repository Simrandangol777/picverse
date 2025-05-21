-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2025 at 11:06 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
(1, 'Great insights! Really made me think.', 13, 1),
(2, 'Could you explain this part a bit more?', 14, 1),
(3, 'I completely agree with your point.', 16, 1),
(4, 'Nice post! Looking forward to more.', 17, 1),
(5, 'This helped me understand the concept better.', 20, 1),
(6, 'Well explained. Thanks for sharing!', 21, 1),
(7, 'I had the same question. Glad it’s answered.', 23, 1),
(8, 'Interesting perspective, never thought of it like this.', 13, 1),
(9, 'Awesome breakdown of the topic!', 16, 1),
(10, 'Not sure I follow. Can you clarify?', 14, 1),
(11, 'This saved me a lot of time, thanks!', 21, 1),
(12, 'Loved this explanation!', 20, 1),
(13, 'fdsa', 36, 1),
(16, 'fdas', 36, 1),
(18, 'fdsa', 37, 1),
(19, 'this is good can we see more', 37, 1),
(20, 'fadskjl', 37, 1),
(21, 'fdksa', 37, 1),
(22, 'hello', 37, 1),
(23, 'ball', 36, 1),
(24, 'this is a class diagrma', 37, 1),
(25, 'this is a ball', 36, 1),
(26, 'ghj', 38, 1),
(28, 'this is acomment', 40, 1),
(30, 'it is amazing', 39, 1),
(31, 'this is nice', 40, 1),
(32, 'wow', 40, 1),
(33, 'this is a ball', 40, 2),
(34, 'wow', 40, 2),
(35, 'fantastic', 40, 2),
(37, 'nice camera', 39, 2),
(38, 'this is good', 39, 1),
(41, 'wow', 40, 9),
(44, 'cutie pie...', 51, 20),
(45, 'Wooow...😘', 50, 17),
(46, 'Hiii...', 56, 17),
(47, 'Keep going', 67, 14);

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
(1, 'first', 1111111111, 'first@gmail.com', 'testing', 'working or not'),
(2, 'first', 1111111111, 'first@gmail.com', 'testing', 'testing or not'),
(6, 'demo', 2222222222, 'demo@gmail.com', 'Demo', 'This is a demo to check contact is working or not for the second time.'),
(7, 'Aakriti Chaudhary', 1111111110, 'aakriti@gmail.com', 'Nothing', 'Hi');

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
(2, 11, 2),
(3, 11, 1),
(4, 11, 10),
(7, 12, 1);

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
(13, 'this is the first caption', 'Screenshot 2025-05-02 112132.png', '2025-05-21 05:28:22', 1),
(14, 'This is data 2', 'siberian-husky.jpeg', '2025-05-21 05:28:22', 1),
(16, 'This is the iamge', 'cat.jpg', '2025-05-21 05:28:22', 1),
(17, 'this is edited again', 'dog.jpg', '2025-05-21 05:28:22', 1),
(20, 'this is a test', 'background.avif', '2025-05-21 05:28:22', 1),
(21, 'this is caption', 'capital one.png', '2025-05-21 05:28:22', 1),
(23, 'Checking ', 'cat.jpg', '2025-05-21 05:28:22', 1),
(25, 'The cat is just beautiful', 'cats2.jpg', '2025-05-21 05:28:22', 1),
(33, 'cat are beautiful', 'cats2.jpg', '2025-05-21 05:28:22', 1),
(36, 'ndnnsnak', 'intro_ball.gif', '2025-05-21 05:28:22', 1),
(37, 'this is a picture and', 'main.drawio.png', '2025-05-21 05:28:22', 1),
(38, 'This is a good image', 'background3.jpg', '2025-05-21 05:28:22', 4),
(39, 'a new post and edited', 'background2.jpg', '2025-05-21 05:28:22', 1),
(40, 'this is the second', 'background.avif', '2025-05-21 05:28:22', 2),
(46, 'simran', 'download(6).jpg', '2025-05-21 05:28:22', 10),
(47, 'What a good day', 'background3.jpg', '2025-05-21 05:28:22', 1),
(48, 'Let\'s Light Up the World like Nobody else.', 'Girl.png', '2025-05-21 05:28:22', 12),
(49, 'Beautiful Scenary...', 'flower.png', '2025-05-21 05:28:22', 12),
(50, 'Beautiful Sketch❤', 'Sketch.jpeg', '2025-05-21 05:28:22', 14),
(51, 'Cute girl drawing...😍', 'Drawing1.jpeg', '2025-05-21 05:28:22', 14),
(53, 'Hero me...\r\n', 'sinchan1.jpeg', '2025-05-21 05:28:22', 20),
(54, 'Chilling with friends...', 'Sinchan Friends.jpeg', '2025-05-21 05:28:22', 20),
(55, 'My cutie pie...💕😍', 'Siro1.jpeg', '2025-05-21 05:28:22', 20),
(56, 'Love❤', 'Sinchan5.jpeg', '2025-05-21 05:28:22', 20),
(57, 'Me and my siro...', 'Siro.jpeg', '2025-05-21 05:28:22', 20),
(58, 'Mount Fuji...An active volcano.', 'Mt Fuji.jpeg', '2025-05-21 05:28:22', 17),
(59, 'Sunshine...', 'Sunshine.jpeg', '2025-05-21 05:28:22', 17),
(60, 'Lovely Day...', 'Scenary2.jpeg', '2025-05-21 05:28:22', 17),
(61, 'Fitness is in my blood...', 'Fitness.jpeg', '2025-05-21 05:28:22', 19),
(62, 'Gymmm', 'Gym.jpeg', '2025-05-21 05:28:22', 19),
(64, 'Chinese Cuisine...', 'Food3.jpeg', '2025-05-21 05:28:22', 16),
(65, 'Sandwich time..', 'Food5.jpeg', '2025-05-21 05:28:22', 16),
(66, 'Cinnamon Roll', 'Cinnamon Roll1.jpeg', '2025-05-21 05:28:22', 18),
(67, 'My passion❤', 'Piano.jpeg', '2025-05-21 05:28:22', 18);

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
(31, 23, 1, 1),
(32, 17, 1, 1),
(36, 38, 1, 1),
(38, 37, 1, 1),
(39, 36, 1, 1),
(41, 40, 1, 1),
(42, 39, 1, 1),
(43, 46, 10, 1),
(44, 39, 10, 1),
(51, 47, 1, 1),
(52, 46, 1, 1),
(58, 47, 11, 1),
(62, 40, 11, 1),
(63, 39, 11, 1),
(64, 46, 11, 1),
(65, 47, 12, 1),
(66, 48, 12, 1),
(68, 51, 20, 1),
(69, 50, 20, 1),
(70, 57, 17, 1),
(71, 56, 17, 1),
(72, 55, 17, 1),
(73, 54, 17, 1),
(74, 53, 17, 1),
(76, 51, 17, 1),
(77, 50, 17, 1),
(78, 50, 19, 1),
(79, 53, 19, 1),
(80, 51, 19, 1),
(81, 60, 19, 1),
(82, 59, 19, 1),
(83, 58, 19, 1),
(84, 57, 19, 1),
(85, 56, 19, 1),
(86, 55, 19, 1),
(87, 54, 19, 1),
(89, 62, 16, 1),
(90, 61, 16, 1),
(91, 60, 16, 1),
(92, 59, 16, 1),
(93, 58, 16, 1),
(94, 57, 16, 1),
(95, 50, 16, 1),
(96, 51, 16, 1),
(97, 56, 16, 1),
(98, 55, 16, 1),
(99, 54, 16, 1),
(100, 53, 16, 1),
(102, 65, 18, 1),
(103, 64, 18, 1),
(105, 62, 18, 1),
(106, 61, 18, 1),
(107, 60, 18, 1),
(108, 59, 18, 1),
(109, 58, 18, 1),
(110, 57, 18, 1),
(111, 56, 18, 1),
(112, 55, 18, 1),
(113, 54, 18, 1),
(114, 53, 18, 1),
(116, 51, 18, 1),
(117, 50, 18, 1),
(118, 67, 14, 1),
(119, 66, 14, 1),
(120, 65, 14, 1),
(121, 64, 14, 1),
(123, 62, 14, 1),
(124, 61, 14, 1),
(125, 60, 14, 1),
(126, 59, 14, 1),
(127, 58, 14, 1),
(128, 57, 14, 1),
(129, 55, 14, 1),
(130, 56, 14, 1),
(131, 54, 14, 1),
(132, 53, 14, 1);

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
(7, 11, 47, 1),
(8, 11, 46, 1),
(9, 12, 39, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` int(10) DEFAULT NULL,
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
(3, 'admin', 'admin', 'admin@gmail.com', 0, '', '', '', '', 'ogQmxNZEMadpwkJbjKaw0bZYFKwsEeVP3rXTqhe9hiDJLnc1t6PXIWavaKoK+thRwg=='),
(14, 'Aakriti Chaudhary', 'aakriti37', 'aakriti@gmail.com', 1111111111, 'Dang', 'Drawing', 'Peace', 'Aakriti.jpeg', '/C2ZS8lksN0vLiN5CEVqVjarzK8IYTp5QgtBRrT9hrYuRqQdk+OS11LLJzz5NPbn0Ml1'),
(15, 'Nabin Bista', 'nabin2', 'nabin@gmail.com', NULL, NULL, NULL, NULL, 'logo.png', 'cS1eJyKi6ZMaTGQYfKSMUPI/kTPta/X05dgZ7ZOw+Na3HvHMqZzxlyCrCSu6Tu1aLA=='),
(16, 'Anushka Adhikari', 'anushka1', 'anushka@gmail.com', 1111111111, 'Kathmandu', 'Food Blogging', 'Food Lover...', 'Anushka.jpg', 'D0zCqd4UXt+VDp+PBGA4OGCMtjmf7jIW4PPIG5NxvPlnEAqcH+SkDBEZEX0/4QBrYCZN'),
(17, 'Simran Dangol', 'simran7', 'simran@gmail.com', 1111111111, 'Kathmandu', 'Hiking', 'Hi...', 'Simran.jpeg', 'dV8yoY4q8HHpDByJ1fYxxg9UHArk4VoDYjkCX8OpmXG/nk1x/q1+Clz/php8TsnD93g='),
(18, 'Prarthana Shahi', 'prarthana3', 'prarthana@gmail.com', 1111111111, 'Kathmandu', 'Playing Piano', 'Annyong...', 'Prarthana.jpeg', 'A1K18LkRwQteOBN5lDQpALVap2GL3lIKbmvSm+QNBUi1gNl68VPBL+VUzd2ueeH2iHqo2To='),
(19, 'Santosh Singh Chad', 'satish9', 'satish@gmail.com', 1111111111, 'Mahendranagar', 'Fitness', 'Let\'s go to gym...', 'Satish.jpeg', 'elYRD3OFM274XEf2cFpsIGfXZUjbfKK5V2RzwmKPZFXLl+OKbJcl9YueomIfHcreNrQ='),
(20, 'Sinchan Nohara', 'sinchan0', 'sinchan@gmail.com', 1111111111, 'China', 'Chilling', 'I am Sinchan Nohara who is full of fun.', 'Sinchan.jpg', 'gkaT+WcRO/KKHi6lsnHJbQx9Y568BulwrQK2pld4/fR/V0F0WXD0riP/W9MvJNI9RWIk'),
(21, 'Deepak Bhandari', 'deepak6', 'deepak@gmail.com', NULL, NULL, NULL, NULL, 'logo.png', 'NOdeaJKFusvZ5J4xzSWdMLaoJvNlfVGIc9YcTgSae3YgsxRshm0RoTyG9xPnBRLxt18='),
(22, 'Kritika Gurung', 'kritika1', 'kritika@gmail.com', NULL, NULL, NULL, NULL, 'logo.png', 'xzBXGW7HVwvAWdYdSdwmeBpoHCGM+2Ao54zec+8WwZKF4IpMqqbywaNgm+QBk2ktzepa');

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `post_like`
--
ALTER TABLE `post_like`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT for table `saved_post`
--
ALTER TABLE `saved_post`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

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
