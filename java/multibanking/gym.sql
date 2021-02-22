-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2020 at 08:34 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gym`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `health`
--

CREATE TABLE `health` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `health`
--

INSERT INTO `health` (`id`, `title`, `description`, `date`) VALUES
(1, 'zd', 'zd', '2020-02-15 11:42:10'),
(2, 'dsf', 'dsfdsfsdf', '2020-02-15 11:43:03'),
(3, 'sf', 'sdf', '2020-02-15 12:42:49'),
(4, 'as', 'asd', '2020-02-15 12:43:58');

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `id` int(11) NOT NULL,
  `material_name` text NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`id`, `material_name`, `quantity`, `amount`) VALUES
(1, 'sdfs', 1, 1),
(2, 'asd', 2, 23);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `mobile` text NOT NULL,
  `email` text NOT NULL,
  `age` int(11) NOT NULL,
  `gender` text NOT NULL,
  `height` text NOT NULL,
  `weight` text NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `firstname`, `lastname`, `mobile`, `email`, `age`, `gender`, `height`, `weight`, `address`) VALUES
(1, 'firs', 'last', 'mobil', 'ema', 12, 'gen', 'heig', 'wei', 'add'),
(2, 'jhjk', 'kjh', 'kjh', 'kjhk', 879, 'Male', 'kljlk', 'lkj', 'lkj');

-- --------------------------------------------------------

--
-- Table structure for table `punch`
--

CREATE TABLE `punch` (
  `id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `punch_in` datetime DEFAULT current_timestamp(),
  `punch_out` datetime DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `punch`
--

INSERT INTO `punch` (`id`, `member_id`, `punch_in`, `punch_out`, `date`) VALUES
(7, 1, '2020-02-15 19:20:55', '2020-02-15 19:32:09', '2020-02-15 14:01:55'),
(8, 1, '2020-02-15 19:34:28', '2020-02-15 19:34:31', '2020-02-15 14:04:28'),
(9, 2, '2020-02-15 19:34:41', '2020-02-15 19:34:47', '2020-02-15 14:04:41'),
(10, 1, '2020-02-15 20:14:16', '2020-02-15 20:14:19', '2020-02-15 14:44:16'),
(11, 2, '2020-02-15 20:14:23', '2020-02-15 20:14:27', '2020-02-15 14:44:23'),
(12, 1, '2020-02-15 20:14:29', '2020-02-15 20:14:34', '2020-02-15 14:44:29');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `material_name` text NOT NULL,
  `provider` text NOT NULL,
  `amount` int(11) NOT NULL,
  `issue` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `material_name`, `provider`, `amount`, `issue`, `date`) VALUES
(1, 'sdf', 'sdf', 12, 'sfsf', '2020-02-15 10:19:08'),
(2, 'Thumbils', 'asd', 12, 'sdfsd', '2020-02-15 10:20:54'),
(3, 'asd', 'fg', 12, 'dfs', '2020-02-15 10:24:22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `health`
--
ALTER TABLE `health`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `punch`
--
ALTER TABLE `punch`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `health`
--
ALTER TABLE `health`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `punch`
--
ALTER TABLE `punch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
