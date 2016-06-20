CREATE DATABASE IF NOT EXISTS schedule;
USE schedule;

/*instructors*/
DROP TABLE IF EXISTS `instructor`;
CREATE TABLE `instructor` (
  `instructor id` int(64) NOT NULL,
  `first name` text NOT NULL,
  `last name` text NOT NULL,
  PRIMARY KEY (`instructor id`)
);

/*classes*/
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `course id` int(64) NOT NULL,
  `course name` text NOT NULL,
  `course abbreviation` text NOT NULL,
  `start time` time NOT NULL,
  `end time` time NOT NULL,
  `M` bool null,
  `T` bool null,
  `W` bool null,
  `TR` bool null,
  `F` bool null,
  PRIMARY KEY (`course id`)
);

/*schedules*/
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules` (
  `schedule id` int(64) NULL,
  `instructor id` int(64)NULL,
  `course #1 id` int(64) NULL,
  `course #2 id` int(64) NULL,
  `course #3 id` int(64) NULL,
  `course #4 id` int(64) NULL,
  `course #5 id` int(64) NULL,
  `course #6 id` int(64) NULL,
  PRIMARY KEY(`schedule id`)
);