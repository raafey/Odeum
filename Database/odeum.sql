-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2019 at 09:43 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `odeum`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `link` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `link`) VALUES
(1, 'https://www.gamespot.com/articles/new-avengers-endgame-trailer-showcases-dangerous-m/1100-6466177/'),
(3, 'https://wdwnt.com/2019/04/funtime-with-toy-story-4-coming-to-tokyo-disney-resort-june-14th-september-1st/'),
(5, 'https://www.theverge.com/2019/4/3/18294009/captain-marvel-cinematic-universe-billion-dollar-box-office'),
(7, 'https://www.theverge.com/2018/11/28/18115201/spider-man-into-the-verse-movie-review-miles-morales');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `eventId` int(11) UNSIGNED NOT NULL,
  `name` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `rating` varchar(45) NOT NULL,
  `feedback` varchar(500) DEFAULT NULL,
  `synopsis` varchar(500) NOT NULL,
  `trailerUrl` varchar(150) NOT NULL,
  `cast` varchar(150) NOT NULL,
  `theaterId` int(11) NOT NULL,
  `imageURL` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`eventId`, `name`, `genre`, `rating`, `feedback`, `synopsis`, `trailerUrl`, `cast`, `theaterId`, `imageURL`) VALUES
(4, 'Toy Story (1995)', 'Animated', '8.3', '', 'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy\'s room.', 'https://www.youtube.com/watch?v=KYz2wyBy3kc', 'Tom Hanks,Tim Allen,Annie Potts,Joan Cusack,Blake Clarke,Wallace Shawn', 1, 'https://media.apnarm.net.au/media/images/2014/02/12/toystory2-t9yqcxmr2f9radfrmh2_fct458x345x2_ct300x300.jpg'),
(5, 'Spider-Man: Into the Spider-Verse', 'Animated', '8.6', '', 'Miles Morales is a New York teen struggling with school, friends and, on top of that, being the new Spider-Man. When he comes across Peter Parker, the erstwhile saviour of New York, in the multiverse, Miles must train to become the new protector of his city.', 'https://www.youtube.com/watch?v=g4Hbz2jLxvQ', ' Shameik Moore, Jake Johnson, Hailee Steinfeld, Mahershala Ali, Brian Tyree Henry, Lily Tomlin, Luna Lauren Velez, John Mulaney, Kimiko Glenn, Nicolas', 1, 'http://cdn02.cdn.justjared.com/wp-content/uploads/headlines/2018/12/spider-man-box-office.jpg'),
(6, 'Gladiator', 'Historical', '8.5', '', 'Set in Roman times, the story of a once-powerful general forced to become a common gladiator.', 'https://www.youtube.com/watch?v=owK1qxDselE', 'Russel Crowe, Connie Nelsen, Ralf Moller, Oliver Reed', 1, 'https://images-na.ssl-images-amazon.com/images/I/51cV7a82q%2BL._AC_UL300_SR300,300_.jpg'),
(8, 'Logan', 'Action', '8.1', '', 'In the near future, a weary Logan (Hugh Jackman) cares for an ailing Professor X (Patrick Stewart) at a remote outpost on the Mexican border. His plan to hide from the outside world gets upended when he meets a young mutant (Dafne Keen) who is very much like him. Logan must now protect the girl and battle the dark forces that want to capture her.', 'https://www.youtube.com/watch?v=Div0iP65aZo', 'Hugh Jackman, Patrick Stewart, Dafne Keen, Boyd Holbrook, Stephen Merchent', 1, 'https://joneeplayingthepoint.files.wordpress.com/2017/03/img_1748.jpg?w=https://joneeplayingthepoint.files.wordpress.com/2017/03/img_1748.jpg?w=600'),
(9, 'Captain Marvel (2019)', 'Superhero', '7.2', '', 'Carol Danvers becomes one of the universe\'s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.', 'https://www.youtube.com/watch?v=0LHxvxdRnYc', 'Brie Larson, Samuel L. Jackson, Ben Mendelsohn, Jude Law, Annette Bening, Lashana Lynch', 3, 'http://t1.gstatic.com/images?q=tbn:ANd9GcQ1bDkDLq-_bteASakhnC1XYwlkErFuqcof7KMhFpRwVhCTh1Vo'),
(10, 'Avengers: Endgame (2019)', 'Superhero', 'TBR', '', 'After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos\' actions and restore order to the universe.', 'https://www.youtube.com/watch?v=TcMBFSGVi1c', 'Chris Evans, Brie Larson, Robert Downey Jr., Scarlett Johansson, Chris Hemsworth, Mark Ruffalo, Josh Brolin', 2, 'http://t2.gstatic.com/images?q=tbn:ANd9GcQA_-tL18_rj9zEcjN6n41NEaJm-kRNF9UeOtvksZ4z_OW6jRA9'),
(11, 'Shazam (2019)', 'Superhero', '7.6', '', 'We all have a superhero inside us, it just takes a bit of magic to bring it out. In Billy Batson\'s case, by shouting out one word - SHAZAM. - this streetwise fourteen-year-old foster kid can turn into the grown-up superhero Shazam.', 'https://www.youtube.com/watch?v=go6GEIrcvFY', 'Zachary Levi, Mark Strong, Asher Angel, Jack Dylan Grazer, Adam Brody', 2, 'http://t1.gstatic.com/images?q=tbn:ANd9GcSw9GNAiU9MLuZKlWADfeGXnN38l20s3-WrwUHa37T0CUSve0oM'),
(12, 'Mission: Impossible - Fallout', 'Action', '7.8', '', 'Ethan Hunt and his IMF team, along with some familiar allies, race against time after a mission gone wrong.', 'https://www.youtube.com/watch?v=XiHiW4N7-bo', 'Tom Cruise, Simon Pegg, Henry Cavill, Ving Rhames, Rebecca Ferguson, Sean Harris', 3, 'http://t1.gstatic.com/images?q=tbn:ANd9GcTDuzrnxIkh11AqI-6PrU9Qrycml22OhFHM9UwGmlkxCsPctLTr'),
(13, 'How to Train Your Dragon: The Hidden World', 'Animated', '7.7', '', 'When Hiccup discovers Toothless isn\'t the only Night Fury, he must seek \"The Hidden World\", a secret Dragon Utopia before a hired tyrant named Grimmel finds it first.', 'https://www.youtube.com/watch?v=qLTDtbYmdWM', 'Jay Baruchel, America Ferrera, F. Murray Abraham, Cate Blanchett, Gerard Butler, Craig Ferguson', 1, 'http://t0.gstatic.com/images?q=tbn:ANd9GcRG-sq7rWKAhBWDyJHSfuq0FnG-crHR-O9yikfBnEf6Em-cRIv9'),
(14, 'The Lego Movie 2: The Second Part', 'Animated', '6.9', '', 'It\'s been five years since everything was awesome and the citizens are facing a huge new threat: Lego Duplo invaders from outer space, wrecking everything faster than they can rebuild.', 'https://www.youtube.com/watch?v=cksYkEzUa7k', 'Chris Pratt, Elizabeth Banks, Will Arnett, Tiffany Haddish, Stephanie Beatriz, Alison Brie, Nick Offerman', 2, 'http://t3.gstatic.com/images?q=tbn:ANd9GcRrr0S4KPQBWgRovRnBvE52JE7CXGp1Ox8x4gx8vhkI5m560-SK');

-- --------------------------------------------------------

--
-- Table structure for table `hall`
--

CREATE TABLE `hall` (
  `hallId` int(11) UNSIGNED NOT NULL,
  `name` varchar(45) NOT NULL,
  `theaterId` int(11) NOT NULL,
  `isFree` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hall`
--

INSERT INTO `hall` (`hallId`, `name`, `theaterId`, `isFree`) VALUES
(1, 'Hall-1', 1, 'T'),
(2, 'Hall-2', 1, 'T'),
(3, 'Hall-3', 1, 'T'),
(4, 'Hall-4', 1, 'T'),
(5, 'Hall-1', 2, 'T'),
(6, 'Hall-1', 3, 'T'),
(7, 'Hall-2', 3, 'T'),
(8, 'Hall-1', 4, 'T'),
(9, 'Hall-2', 4, 'T'),
(10, 'Hall-3', 4, 'T'),
(11, 'Hall-1', 5, 'T'),
(12, 'Hall-2', 5, 'T'),
(13, 'Hall-1', 6, 'T'),
(14, 'Hall-1', 8, 'T'),
(15, 'Hall-2', 8, 'T'),
(16, 'Hall-1', 9, 'T');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `question` varchar(600) NOT NULL,
  `opt_a` varchar(300) NOT NULL,
  `opt_b` varchar(300) NOT NULL,
  `opt_c` varchar(300) DEFAULT NULL,
  `opt_d` varchar(300) DEFAULT NULL,
  `answer` varchar(6) NOT NULL,
  `trivia_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question`, `opt_a`, `opt_b`, `opt_c`, `opt_d`, `answer`, `trivia_id`) VALUES
(1, ' What is the name of Jon\'s direwolf?', 'Ghost', 'Grey-Wind', 'Mr. Buttons', 'Drogon', 'A', 7),
(2, 'In the first episode, King Robert Baratheon says \"In my dreams, I kill him every night.\" To whom is the King referring to?', 'Rhaegar Targaryen', 'Tywen Lannister', '', '', 'A', 7),
(3, 'How many fingertips did Stannis Baratheon chop off of Davos\' hand(s)?', '3', '2', '4', '1', 'C', 7),
(4, 'What is the name of the continent in which most of the action in Game of Thrones takes place?', 'Essos', 'Westeros', 'Shire', 'Tamriel', 'B', 7),
(5, 'What noble house is Catelyn Stark from?', 'House Targareyn', 'House Tully', 'House Baratheon', 'House Tarly ', 'B', 7),
(6, 'In what year did the Avengers make their comic book debut?', '1963', '1989', '1947', '1999', 'A', 8),
(7, 'Who did Matthew Murdock go to the Raft to see in \"New Avengers\" #1?', 'The Sentry', 'Karl Peaterson', 'Foggy Nelson', 'Frank Castle', 'A', 8),
(8, 'What is Captain America\'s real name?', 'Tony Stark', 'Steven Strange', 'Nick Fury', 'Steve Rogers', 'D', 8),
(9, 'Who constructed the Vision?', 'Tony Stark', 'Ultron', 'S.H.I.E.L.D', '', 'B', 8),
(10, 'What is Wolverine\'s real name?', 'Charles Xavier', 'Scott Summers', 'James Howlett', 'Bruce Wayne', 'C', 8),
(11, 'Who was Hawkeye married to?', 'Black Widow', 'Karren Page', 'Jessica Jones', 'Mockingbird', 'D', 8),
(12, 'One of Iron Man\'s teammates is known as Black Widow. What is her real name?', 'Natasha Romanov', 'Natasha Romonsiky', '', '', 'A', 8),
(13, 'What villain from a previous movie returns in \"Avengers: Infinity War\"?', 'Thanos', 'Vulture', 'Red Skull', 'Killmonger', 'C', 8),
(14, 'Who is Nick Fury shown calling before he is erased in Infinity War?', 'Captain America', 'Captain Marvel', 'Dr. Strange', 'Tony Stark', 'B', 8),
(15, 'What was the space stone originally called?', 'The Blue Source', 'The Portal Generator', 'The Ultimate Weapon', 'The Tessaract', 'D', 8);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `schId` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `eventId` int(11) UNSIGNED NOT NULL,
  `hallId` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schId`, `date`, `time`, `Price`, `eventId`, `hallId`) VALUES
(17, '2019-03-08', '24:00', 400, 8, 1),
(18, '2019-03-05', '07:00', 800, 4, 4),
(20, '2019-03-04', '07:00', 200, 6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `seating_plan`
--

CREATE TABLE `seating_plan` (
  `seatId` int(11) UNSIGNED NOT NULL,
  `row` int(10) NOT NULL,
  `col` varchar(2) NOT NULL,
  `schId` int(11) NOT NULL,
  `isBooked` varchar(2) NOT NULL,
  `userId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seating_plan`
--

INSERT INTO `seating_plan` (`seatId`, `row`, `col`, `schId`, `isBooked`, `userId`) VALUES
(2, 1, 'A', 17, 'T', 1),
(3, 1, 'B', 17, 'F', NULL),
(4, 1, 'C', 17, 'F', NULL),
(5, 1, 'D', 17, 'T', 3),
(6, 1, 'E', 17, 'F', NULL),
(7, 1, 'F', 17, 'F', NULL),
(8, 1, 'G', 17, 'F', NULL),
(9, 1, 'H', 17, 'F', NULL),
(10, 1, 'I', 17, 'F', NULL),
(11, 1, 'J', 17, 'F', NULL),
(12, 1, 'K', 17, 'F', NULL),
(13, 1, 'L', 17, 'T', 2),
(14, 1, 'M', 17, 'F', NULL),
(15, 1, 'N', 17, 'F', NULL),
(16, 2, 'A', 17, 'F', NULL),
(17, 2, 'B', 17, 'F', NULL),
(18, 2, 'C', 17, 'T', 2),
(19, 2, 'D', 17, 'F', NULL),
(20, 2, 'E', 17, 'F', NULL),
(21, 2, 'F', 17, 'F', NULL),
(22, 2, 'G', 17, 'F', NULL),
(23, 2, 'H', 17, 'F', NULL),
(24, 2, 'I', 17, 'F', NULL),
(25, 2, 'J', 17, 'F', NULL),
(26, 2, 'K', 17, 'F', NULL),
(27, 2, 'L', 17, 'F', NULL),
(28, 2, 'M', 17, 'F', NULL),
(29, 2, 'N', 17, 'F', NULL),
(30, 3, 'A', 17, 'F', NULL),
(31, 3, 'B', 17, 'F', NULL),
(32, 3, 'C', 17, 'F', NULL),
(33, 3, 'D', 17, 'F', NULL),
(34, 3, 'E', 17, 'F', NULL),
(35, 3, 'F', 17, 'T', 3),
(36, 3, 'G', 17, 'F', NULL),
(37, 3, 'H', 17, 'F', NULL),
(38, 3, 'I', 17, 'F', NULL),
(39, 3, 'J', 17, 'F', NULL),
(40, 3, 'K', 17, 'F', NULL),
(41, 3, 'L', 17, 'F', NULL),
(42, 3, 'M', 17, 'F', NULL),
(43, 3, 'N', 17, 'F', NULL),
(44, 4, 'A', 17, 'F', NULL),
(45, 4, 'B', 17, 'F', NULL),
(46, 4, 'C', 17, 'F', NULL),
(47, 4, 'D', 17, 'F', NULL),
(48, 4, 'E', 17, 'F', NULL),
(49, 4, 'F', 17, 'F', NULL),
(50, 4, 'G', 17, 'F', NULL),
(51, 4, 'H', 17, 'F', NULL),
(52, 4, 'I', 17, 'F', NULL),
(53, 4, 'J', 17, 'F', NULL),
(54, 4, 'K', 17, 'F', NULL),
(55, 4, 'L', 17, 'F', NULL),
(56, 4, 'M', 17, 'F', NULL),
(57, 4, 'N', 17, 'F', NULL),
(58, 5, 'A', 17, 'F', NULL),
(59, 5, 'B', 17, 'F', NULL),
(60, 5, 'C', 17, 'F', NULL),
(61, 5, 'D', 17, 'F', NULL),
(62, 5, 'E', 17, 'F', NULL),
(63, 5, 'F', 17, 'F', NULL),
(64, 5, 'G', 17, 'F', NULL),
(65, 5, 'H', 17, 'F', NULL),
(66, 5, 'I', 17, 'F', NULL),
(67, 5, 'J', 17, 'F', NULL),
(68, 5, 'K', 17, 'F', NULL),
(69, 5, 'L', 17, 'F', NULL),
(70, 5, 'M', 17, 'F', NULL),
(71, 5, 'N', 17, 'F', NULL),
(72, 6, 'A', 17, 'F', NULL),
(73, 6, 'B', 17, 'F', NULL),
(74, 6, 'C', 17, 'F', NULL),
(75, 6, 'D', 17, 'T', 2),
(76, 6, 'E', 17, 'T', 4),
(77, 6, 'F', 17, 'F', NULL),
(78, 6, 'G', 17, 'T', 4),
(79, 6, 'H', 17, 'F', NULL),
(80, 6, 'I', 17, 'F', NULL),
(81, 6, 'J', 17, 'F', NULL),
(82, 6, 'K', 17, 'F', NULL),
(83, 6, 'L', 17, 'F', NULL),
(84, 6, 'M', 17, 'F', NULL),
(85, 6, 'N', 17, 'F', NULL),
(86, 7, 'A', 17, 'F', NULL),
(87, 7, 'B', 17, 'F', NULL),
(88, 7, 'C', 17, 'F', NULL),
(89, 7, 'D', 17, 'F', NULL),
(90, 7, 'E', 17, 'F', NULL),
(91, 7, 'F', 17, 'F', NULL),
(92, 7, 'G', 17, 'F', NULL),
(93, 7, 'H', 17, 'T', 2),
(94, 7, 'I', 17, 'F', NULL),
(95, 7, 'J', 17, 'F', NULL),
(96, 7, 'K', 17, 'F', NULL),
(97, 7, 'L', 17, 'F', NULL),
(98, 7, 'M', 17, 'F', NULL),
(99, 7, 'N', 17, 'F', NULL),
(100, 1, 'A', 18, 'T', 2),
(101, 1, 'B', 18, 'F', NULL),
(102, 1, 'C', 18, 'F', NULL),
(103, 1, 'D', 18, 'F', NULL),
(104, 1, 'E', 18, 'F', NULL),
(105, 1, 'F', 18, 'F', NULL),
(106, 1, 'G', 18, 'F', NULL),
(107, 1, 'H', 18, 'F', NULL),
(108, 1, 'I', 18, 'F', NULL),
(109, 1, 'J', 18, 'F', NULL),
(110, 1, 'K', 18, 'F', NULL),
(111, 1, 'L', 18, 'T', 1),
(112, 1, 'M', 18, 'F', NULL),
(113, 1, 'N', 18, 'F', NULL),
(114, 2, 'A', 18, 'F', NULL),
(115, 2, 'B', 18, 'T', 4),
(116, 2, 'C', 18, 'F', NULL),
(117, 2, 'D', 18, 'T', 3),
(118, 2, 'E', 18, 'F', NULL),
(119, 2, 'F', 18, 'F', NULL),
(120, 2, 'G', 18, 'F', NULL),
(121, 2, 'H', 18, 'F', NULL),
(122, 2, 'I', 18, 'F', NULL),
(123, 2, 'J', 18, 'F', NULL),
(124, 2, 'K', 18, 'F', NULL),
(125, 2, 'L', 18, 'F', NULL),
(126, 2, 'M', 18, 'F', NULL),
(127, 2, 'N', 18, 'F', NULL),
(128, 3, 'A', 18, 'F', NULL),
(129, 3, 'B', 18, 'F', NULL),
(130, 3, 'C', 18, 'F', NULL),
(131, 3, 'D', 18, 'F', NULL),
(132, 3, 'E', 18, 'F', NULL),
(133, 3, 'F', 18, 'F', NULL),
(134, 3, 'G', 18, 'F', NULL),
(135, 3, 'H', 18, 'F', NULL),
(136, 3, 'I', 18, 'F', NULL),
(137, 3, 'J', 18, 'F', NULL),
(138, 3, 'K', 18, 'F', NULL),
(139, 3, 'L', 18, 'F', NULL),
(140, 3, 'M', 18, 'F', NULL),
(141, 3, 'N', 18, 'F', NULL),
(142, 4, 'A', 18, 'F', NULL),
(143, 4, 'B', 18, 'F', NULL),
(144, 4, 'C', 18, 'F', NULL),
(145, 4, 'D', 18, 'F', NULL),
(146, 4, 'E', 18, 'F', NULL),
(147, 4, 'F', 18, 'F', NULL),
(148, 4, 'G', 18, 'F', NULL),
(149, 4, 'H', 18, 'F', NULL),
(150, 4, 'I', 18, 'F', NULL),
(151, 4, 'J', 18, 'F', NULL),
(152, 4, 'K', 18, 'F', NULL),
(153, 4, 'L', 18, 'F', NULL),
(154, 4, 'M', 18, 'F', NULL),
(155, 4, 'N', 18, 'F', NULL),
(156, 5, 'A', 18, 'F', NULL),
(157, 5, 'B', 18, 'F', NULL),
(158, 5, 'C', 18, 'F', NULL),
(159, 5, 'D', 18, 'F', NULL),
(160, 5, 'E', 18, 'T', 4),
(161, 5, 'F', 18, 'F', NULL),
(162, 5, 'G', 18, 'F', NULL),
(163, 5, 'H', 18, 'F', NULL),
(164, 5, 'I', 18, 'F', NULL),
(165, 5, 'J', 18, 'F', NULL),
(166, 5, 'K', 18, 'F', NULL),
(167, 5, 'L', 18, 'F', NULL),
(168, 5, 'M', 18, 'F', NULL),
(169, 5, 'N', 18, 'F', NULL),
(170, 6, 'A', 18, 'F', NULL),
(171, 6, 'B', 18, 'F', NULL),
(172, 6, 'C', 18, 'F', NULL),
(173, 6, 'D', 18, 'F', NULL),
(174, 6, 'E', 18, 'F', NULL),
(175, 6, 'F', 18, 'F', NULL),
(176, 6, 'G', 18, 'F', NULL),
(177, 6, 'H', 18, 'F', NULL),
(178, 6, 'I', 18, 'F', NULL),
(179, 6, 'J', 18, 'F', NULL),
(180, 6, 'K', 18, 'F', NULL),
(181, 6, 'L', 18, 'F', NULL),
(182, 6, 'M', 18, 'F', NULL),
(183, 6, 'N', 18, 'F', NULL),
(184, 7, 'A', 18, 'F', NULL),
(185, 7, 'B', 18, 'F', NULL),
(186, 7, 'C', 18, 'F', NULL),
(187, 7, 'D', 18, 'F', NULL),
(188, 7, 'E', 18, 'F', NULL),
(189, 7, 'F', 18, 'F', NULL),
(190, 7, 'G', 18, 'F', NULL),
(191, 7, 'H', 18, 'F', NULL),
(192, 7, 'I', 18, 'F', NULL),
(193, 7, 'J', 18, 'F', NULL),
(194, 7, 'K', 18, 'F', NULL),
(195, 7, 'L', 18, 'F', NULL),
(196, 7, 'M', 18, 'F', NULL),
(197, 7, 'N', 18, 'F', NULL),
(198, 1, 'A', 20, 'F', NULL),
(199, 1, 'B', 20, 'F', NULL),
(200, 1, 'C', 20, 'F', NULL),
(201, 1, 'D', 20, 'F', NULL),
(202, 1, 'E', 20, 'F', NULL),
(203, 1, 'F', 20, 'F', NULL),
(204, 1, 'G', 20, 'F', NULL),
(205, 1, 'H', 20, 'F', NULL),
(206, 1, 'I', 20, 'F', NULL),
(207, 1, 'J', 20, 'F', NULL),
(208, 1, 'K', 20, 'F', NULL),
(209, 1, 'L', 20, 'F', NULL),
(210, 1, 'M', 20, 'F', NULL),
(211, 1, 'N', 20, 'F', NULL),
(212, 2, 'A', 20, 'F', NULL),
(213, 2, 'B', 20, 'F', NULL),
(214, 2, 'C', 20, 'F', NULL),
(215, 2, 'D', 20, 'F', NULL),
(216, 2, 'E', 20, 'F', NULL),
(217, 2, 'F', 20, 'F', NULL),
(218, 2, 'G', 20, 'F', NULL),
(219, 2, 'H', 20, 'F', NULL),
(220, 2, 'I', 20, 'F', NULL),
(221, 2, 'J', 20, 'F', NULL),
(222, 2, 'K', 20, 'F', NULL),
(223, 2, 'L', 20, 'F', NULL),
(224, 2, 'M', 20, 'F', NULL),
(225, 2, 'N', 20, 'F', NULL),
(226, 3, 'A', 20, 'F', NULL),
(227, 3, 'B', 20, 'F', NULL),
(228, 3, 'C', 20, 'F', NULL),
(229, 3, 'D', 20, 'F', NULL),
(230, 3, 'E', 20, 'F', NULL),
(231, 3, 'F', 20, 'F', NULL),
(232, 3, 'G', 20, 'F', NULL),
(233, 3, 'H', 20, 'F', NULL),
(234, 3, 'I', 20, 'F', NULL),
(235, 3, 'J', 20, 'F', NULL),
(236, 3, 'K', 20, 'F', NULL),
(237, 3, 'L', 20, 'F', NULL),
(238, 3, 'M', 20, 'F', NULL),
(239, 3, 'N', 20, 'F', NULL),
(240, 4, 'A', 20, 'F', NULL),
(241, 4, 'B', 20, 'F', NULL),
(242, 4, 'C', 20, 'F', NULL),
(243, 4, 'D', 20, 'F', NULL),
(244, 4, 'E', 20, 'F', NULL),
(245, 4, 'F', 20, 'F', NULL),
(246, 4, 'G', 20, 'F', NULL),
(247, 4, 'H', 20, 'F', NULL),
(248, 4, 'I', 20, 'F', NULL),
(249, 4, 'J', 20, 'F', NULL),
(250, 4, 'K', 20, 'F', NULL),
(251, 4, 'L', 20, 'F', NULL),
(252, 4, 'M', 20, 'F', NULL),
(253, 4, 'N', 20, 'F', NULL),
(254, 5, 'A', 20, 'F', NULL),
(255, 5, 'B', 20, 'F', NULL),
(256, 5, 'C', 20, 'F', NULL),
(257, 5, 'D', 20, 'F', NULL),
(258, 5, 'E', 20, 'F', NULL),
(259, 5, 'F', 20, 'F', NULL),
(260, 5, 'G', 20, 'F', NULL),
(261, 5, 'H', 20, 'F', NULL),
(262, 5, 'I', 20, 'F', NULL),
(263, 5, 'J', 20, 'F', NULL),
(264, 5, 'K', 20, 'F', NULL),
(265, 5, 'L', 20, 'F', NULL),
(266, 5, 'M', 20, 'F', NULL),
(267, 5, 'N', 20, 'F', NULL),
(268, 6, 'A', 20, 'F', NULL),
(269, 6, 'B', 20, 'F', NULL),
(270, 6, 'C', 20, 'F', NULL),
(271, 6, 'D', 20, 'F', NULL),
(272, 6, 'E', 20, 'F', NULL),
(273, 6, 'F', 20, 'F', NULL),
(274, 6, 'G', 20, 'F', NULL),
(275, 6, 'H', 20, 'F', NULL),
(276, 6, 'I', 20, 'F', NULL),
(277, 6, 'J', 20, 'F', NULL),
(278, 6, 'K', 20, 'F', NULL),
(279, 6, 'L', 20, 'F', NULL),
(280, 6, 'M', 20, 'F', NULL),
(281, 6, 'N', 20, 'F', NULL),
(282, 7, 'A', 20, 'F', NULL),
(283, 7, 'B', 20, 'F', NULL),
(284, 7, 'C', 20, 'F', NULL),
(285, 7, 'D', 20, 'F', NULL),
(286, 7, 'E', 20, 'F', NULL),
(287, 7, 'F', 20, 'F', NULL),
(288, 7, 'G', 20, 'F', NULL),
(289, 7, 'H', 20, 'T', 2),
(290, 7, 'I', 20, 'F', NULL),
(291, 7, 'J', 20, 'F', NULL),
(292, 7, 'K', 20, 'F', NULL),
(293, 7, 'L', 20, 'F', NULL),
(294, 7, 'M', 20, 'F', NULL),
(295, 7, 'N', 20, 'F', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `theater`
--

CREATE TABLE `theater` (
  `theaterId` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `halls` int(11) NOT NULL,
  `address` varchar(150) NOT NULL,
  `city` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `theater`
--

INSERT INTO `theater` (`theaterId`, `name`, `halls`, `address`, `city`, `contact`, `username`, `password`) VALUES
(1, 'Cineplex', 4, 'F-8', 'Islamabad', '051223232', 'cent_cin', '1234'),
(2, 'Arena', 1, 'Bahria', 'Islamabad', '051222222', 'arena_12', '2323'),
(3, 'Cinegold Plex', 2, 'Street 256, Alpha Colony', 'Rawalpindi', '0312344321', 'cine_gold', 'c123'),
(4, 'Mega Zone Plex', 3, 'Street 12, D-12', 'Islamabad', '051443123', 'meg_plex', 'abc123'),
(5, 'The Centrum', 2, 'G-8 Markaz', 'Islamabad', '0513455432', 'cent_2345', '12345'),
(6, 'Alpha Cinema', 1, 'I-11 Markaz', 'Islamabad', '0512323456', 'alpha_23', '123'),
(8, 'The Patheon', 2, 'Defense, Phase II', 'Karachi', '0214343432', 'pat_12', 'abcd'),
(9, 'Islamabad Cinema', 1, 'I-10 Markaz', 'Islamabad', '04312345', 'isb_cin', '3333');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `ticketId` int(11) UNSIGNED NOT NULL,
  `seatId` int(11) UNSIGNED NOT NULL,
  `eventId` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`ticketId`, `seatId`, `eventId`) VALUES
(1, 160, 4),
(2, 117, 4),
(3, 115, 4),
(4, 111, 4),
(5, 100, 4),
(6, 75, 8),
(7, 78, 8),
(8, 35, 8),
(9, 13, 8),
(10, 5, 8),
(11, 76, 8),
(12, 18, 8),
(14, 289, 6),
(15, 93, 8),
(17, 2, 8);

-- --------------------------------------------------------

--
-- Table structure for table `trivia`
--

CREATE TABLE `trivia` (
  `trivia_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trivia`
--

INSERT INTO `trivia` (`trivia_id`, `name`) VALUES
(8, 'Avengers Trivia'),
(7, 'Game of Thrones Trivia');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `name` varchar(85) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `username`, `name`, `email`, `address`, `contact`, `dateOfBirth`, `password`) VALUES
(1, 'alpha', 'Alpha dude', 'alpha@alpha.com', 'A Chad house', '02456789', '1999-12-15', '1234'),
(2, 'malik_12', 'Zain Malik', 'malik@gmail.com', 'House:90, Steet:54, G-9, Islamabad', '0900786011', '2002-03-12', 'abcd123'),
(3, 'franics_k_10', 'Franics Kennedy', 'francisk@gmail.com', 'House:123, Street:293, I-11, Islamabad', '03335334234', '1980-04-12', 'haha123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`eventId`),
  ADD UNIQUE KEY `eventId_UNIQUE` (`eventId`),
  ADD KEY `theaterId` (`theaterId`);

--
-- Indexes for table `hall`
--
ALTER TABLE `hall`
  ADD PRIMARY KEY (`hallId`),
  ADD UNIQUE KEY `hallId_UNIQUE` (`hallId`),
  ADD KEY `theaterId` (`theaterId`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `trivia_id` (`trivia_id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD UNIQUE KEY `schId_UNIQUE` (`schId`),
  ADD KEY `eventId` (`eventId`),
  ADD KEY `hallId` (`hallId`);

--
-- Indexes for table `seating_plan`
--
ALTER TABLE `seating_plan`
  ADD PRIMARY KEY (`seatId`),
  ADD UNIQUE KEY `seatId_UNIQUE` (`seatId`),
  ADD KEY `schId` (`schId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `theater`
--
ALTER TABLE `theater`
  ADD PRIMARY KEY (`theaterId`),
  ADD UNIQUE KEY `idTheater_UNIQUE` (`theaterId`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticketId`),
  ADD UNIQUE KEY `ticketId` (`ticketId`),
  ADD KEY `ticket_ibfk_1` (`seatId`),
  ADD KEY `ticket_ibfk_2` (`eventId`);

--
-- Indexes for table `trivia`
--
ALTER TABLE `trivia`
  ADD PRIMARY KEY (`trivia_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `userId_UNIQUE` (`userId`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `eventId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `hall`
--
ALTER TABLE `hall`
  MODIFY `hallId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `schId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `seating_plan`
--
ALTER TABLE `seating_plan`
  MODIFY `seatId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=296;

--
-- AUTO_INCREMENT for table `theater`
--
ALTER TABLE `theater`
  MODIFY `theaterId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticketId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `trivia`
--
ALTER TABLE `trivia`
  MODIFY `trivia_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`theaterId`) REFERENCES `theater` (`theaterId`);

--
-- Constraints for table `hall`
--
ALTER TABLE `hall`
  ADD CONSTRAINT `hall_ibfk_1` FOREIGN KEY (`theaterId`) REFERENCES `theater` (`theaterId`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`trivia_id`) REFERENCES `trivia` (`trivia_id`);

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`eventId`) REFERENCES `event` (`eventId`),
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`hallId`) REFERENCES `hall` (`hallId`);

--
-- Constraints for table `seating_plan`
--
ALTER TABLE `seating_plan`
  ADD CONSTRAINT `seating_plan_ibfk_1` FOREIGN KEY (`schId`) REFERENCES `schedule` (`schId`),
  ADD CONSTRAINT `seating_plan_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`seatId`) REFERENCES `seating_plan` (`seatId`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`eventId`) REFERENCES `event` (`eventId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
