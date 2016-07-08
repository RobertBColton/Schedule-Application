CREATE DATABASE IF NOT EXISTS schedule;
USE schedule;

/*classes*/
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `course id` int(64) NOT NULL,
  `course name` text NOT NULL,
  `course abbreviation` text NOT NULL,
  PRIMARY KEY (`course id`)
);

/*meetings*/
DROP TABLE IF EXISTS `meetings`;
CREATE TABLE `meetings` (
  `meeting id` int(64) NOT NULL,
  `start time` text NOT NULL,
  `end time` text NOT NULL,
  `M` bool null,
  `T` bool null,
  `W` bool null,
  `TR` bool null,
  `F` bool null,
  PRIMARY KEY (`meeting id`)
);

/*classmeetings*/
DROP TABLE IF EXISTS `classmeetings`;
CREATE TABLE `classmeetings` (
  `meeting id` int(64) NOT NULL,
  `course id` int(64) NOT NULL,
  FOREIGN KEY (`meeting id`) REFERENCES meetings(`meeting id`),
  FOREIGN KEY (`course id`) REFERENCES classes(`course id`),
  PRIMARY KEY (`meeting id`, `course id`)
);

/*instructors*/
DROP TABLE IF EXISTS `instructors`;
CREATE TABLE `instructors` (
  `user id` int(64) NOT NULL,
  `name` text NOT NULL,
  `discipline` text NOT NULL,
  PRIMARY KEY (`user id`)
);