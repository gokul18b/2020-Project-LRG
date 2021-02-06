-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2020 at 03:31 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ovs`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `candidates`
--

CREATE TABLE `candidates` (
  `userid` int(11) NOT NULL,
  `post` int(11) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `img` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candidates`
--

INSERT INTO `candidates` (`userid`, `post`, `firstname`, `lastname`, `mobile`, `email`, `address`, `img`) VALUES
(3, 1, 'Mythili', 'V', '6598425869', 'mythili@gmail.com', 'XXXXXXXXXXXx,\r\nXXXXXXXXXXXX\r\nXXXXXXXXXXX\r\n', ''),
(11, 2, 'Aamena', 'D', '6369857896', 'aamena@gmail.com', 'XXXXXXXXXXX,\r\nXXXXXXXXXXX,\r\nXXXXXXXX', ''),
(10, 2, 'Kaveena', 'G', '878723873', 'kaveena@gmail.com', 'XXXXXXXX\r\nXXXXXXXXX\r\nXXXXXX', ''),
(14, 1, 'Parkavi', 'P', '4187414287', 'parkavi@gmail.com', 'XXXXXXXXXXX\r\nXXXXXXXXXXX\r\nXXXXXXXXXXX', ''),
(21, 2, 'Priya', 'P', '9852547896', 'priya@gmail.com', 'XXXXXXXX\r\nXXXXXXXXX\r\nXXXXXXXXX', '');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_mobile` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `usertype` int(1) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `mobilenumber` varchar(10) NOT NULL,
  `address` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `usertype`, `email`, `password`, `firstname`, `lastname`, `mobilenumber`, `address`) VALUES
(1, 1, 'admin', 'admin', 'Admin', '.', '7418227833', '&0, Anna street Kumaranandhapuram, Tirupur 641602'),
(17, 0, 'praveena@gmail.com', 'praveena', 'Praveena', 'S', '8965821587', '70 Anna sgtreet kumaranandhapuram tirupur'),
(18, 0, 'Subasri@gmail.com', 'subasri', 'Subasri', 'S', '8745245871', 'XXXXXXXX\r\nXXXXXXXXXXX\r\nXXXXXXXXXX');

-- --------------------------------------------------------

--
-- Table structure for table `voting`
--

CREATE TABLE `voting` (
  `id` int(11) NOT NULL,
  `canid` int(11) NOT NULL,
  `votid` int(11) NOT NULL,
  `postid` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `voting`
--

INSERT INTO `voting` (`id`, `canid`, `votid`, `postid`) VALUES
(18, 3, 17, 1),
(17, 3, 17, 1),
(16, 10, 1, 2),
(15, 14, 1, 1),
(14, 12, 17, 2),
(13, 3, 17, 1),
(19, 10, 1, 2),
(20, 15, 1, 3),
(21, 16, 1, 4),
(22, 16, 17, 4),
(23, 18, 17, 6),
(24, 17, 17, 5),
(25, 14, 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `candidates`
--
ALTER TABLE `candidates`
  ADD PRIMARY KEY (`userid`,`firstname`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`,`email`);

--
-- Indexes for table `voting`
--
ALTER TABLE `voting`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `candidates`
--
ALTER TABLE `candidates`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `voting`
--
ALTER TABLE `voting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
