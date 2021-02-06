-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2020 at 07:22 AM
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
-- Database: `vehicleservice`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `branchid` int(11) NOT NULL,
  `servideid` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `date` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id`, `branchid`, `servideid`, `amount`, `date`) VALUES
(23, 2, 18, 2200, '07-01-2018'),
(24, 1, 20, 350, '07-01-2018'),
(17, 2, 12, 250, '07-01-2018'),
(25, 2, 25, 230, '23-02-2019'),
(26, 6, 26, 1500, '23-02-2019'),
(27, 2, 22, 11110, '06-02-2020'),
(28, 2, 30, 4546, '07-02-2020'),
(29, 6, 32, 1111, '08-02-2020'),
(30, 2, 24, 2345, '09-02-2020'),
(31, 8, 34, 956, '22-02-2020');

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `landmark` varchar(100) NOT NULL,
  `pincode` int(11) NOT NULL,
  `address` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`id`, `name`, `landmark`, `pincode`, `address`) VALUES
(1, 'RAJA HONDA SHOW ROOM', 'NEAR SIVAN THEATRE', 641987, 'Anna street,Avinashi Road , Tirupur'),
(2, 'RABA HONDA SHOW ROOM', 'NEAR OLD BUS STAND', 645667, 'Palladam Road, Tirupur'),
(6, 'PALLADAM', 'PALLADAM', 645254, 'PALLADAM'),
(8, 'AVINASHI', 'near by busstand', 642658, 'xxxxxxxxxxxxxxxxxxxxxxxxxxx');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `branch` varchar(60) NOT NULL,
  `vehicle` varchar(30) NOT NULL,
  `serno` varchar(30) NOT NULL,
  `problem` text NOT NULL,
  `address` text NOT NULL,
  `status` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `date` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `mobile`, `name`, `branch`, `vehicle`, `serno`, `problem`, `address`, `status`, `uid`, `date`) VALUES
(24, '7418227833', 'Gokul', '2', 'CAR', 'TN 65 NH 0928', 'change tier', 'tup', 1, 9, '17-01-2018'),
(22, '9952316067', 'Madhan', '2', 'CAR', 'TN BK 0516', 'adkj', 'lkjs', 1, 10, '08-01-2018'),
(20, '9952316067', 'Madhan', '1', 'BIKE', 'TN 009 BH 0562', 'change oil', 'Annur', 1, 10, '07-01-2018'),
(18, '9952316067', 'Madhan', '2', 'CAR', 'AB 0000', 'Change tier', 'Annur', 1, 10, '07-01-2018'),
(12, '7418227833', 'Gokul', '2', 'BIKE', 'TN 39 BK 0516', 'normal service', '70 Anna street kumaranandhapuram', 1, 9, '07-01-2018'),
(25, '7418227833', 'ramya', '2', 'CAR', '7687', 'water service', 'full address', 1, 11, '23-02-2019'),
(26, '7418227833', 'ramya', '6', 'CAR', '46552', 'light', 'address', 1, 11, '23-02-2019'),
(34, '7418227833', 'ramya', '8', 'BIKE', 'TN 36 BJ 0987', 'normal service and water service', 'xxxxxxxxxxxxxxxxxxxxxxx', 1, 11, '22-02-2020'),
(30, '789', 'testttt', 'ad', 'asd', 'asd', 'asd', 'asd', 1, 1, '07-02-2020'),
(32, '9952316067', 'Madhan', '6', 'CAR', '23', 'asd', 'asd', 1, 10, '08-02-2020');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `lastname` text NOT NULL,
  `email` text NOT NULL,
  `age` text NOT NULL,
  `gender` text NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `password` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `FLAG` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `lastname`, `email`, `age`, `gender`, `mobile`, `password`, `address`, `FLAG`) VALUES
(9, 'Praveena', '', '', '', '', '9658965475', 'gokul@94', '70 Anna street kumaranandhapuram Tirupur 641602', 0),
(8, 'Admin', '', '', '', '', 'admin', 'admin', '70 Anna street ,  Kumaranandhapuram , Tirupur 641602', 1),
(10, 'Madhan', '', '', '', '', '9952316067', 'madhan@95', '34 Raja street Annur post, coimbatore dist , 641234', 0),
(11, 'ramya', '', '', '', '', '7418227833', 'ramya', 'coimbatore', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
