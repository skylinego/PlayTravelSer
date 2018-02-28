# --- First database schema

# --- !Ups

CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `client` (
  `id` int(10) unsigned NOT NULL,
  `secret` varchar(100) DEFAULT NULL,
  `token` char(80) NOT NULL,
  `expiration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) DEFAULT NULL,
  `MiddleName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(120) NOT NULL,
  `password` char(80) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '0',
  `isPending` tinyint(1) NOT NULL DEFAULT '0',
  `isResetPassword` tinyint(1) NOT NULL DEFAULT '0',
  `roleID` int(10) unsigned NOT NULL DEFAULT '1',
  `activatedAt` datetime DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (roleID) REFERENCES role(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=389 DEFAULT CHARSET=utf8;

CREATE TABLE `usertoken` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accesstoken` char(80) NOT NULL,
  `refreshtoken` char(80) NOT NULL,
  `expiration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
}  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `currency` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `currency` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `continent` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `continent` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `country` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `codeISO3` char(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `capital` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `countryCode` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `languageCodes` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zipCodeFormat` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zipCodePattern` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `continentID` int(1) unsigned NOT NULL DEFAULT '0',
  `currencyID` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (continentID) REFERENCES continent(id) ON DELETE CASCADE,
  FOREIGN KEY (currencyID) REFERENCES currency(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `poi`;
CREATE TABLE `poi1` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `zip` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `country` char(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `countryID` int(10) unsigned NOT NULL DEFAULT '0',
  `continentID` int(1) unsigned NOT NULL DEFAULT '0',
  `location` point DEFAULT NULL,
  `isCustomized` tinyint(1) NOT NULL DEFAULT '0',
  `rank`  float DEFAULT '0.0',
  ``
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (continentID) REFERENCES continent(id) ON DELETE CASCADE,
  FOREIGN KEY (countryID) REFERENCES country(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

# --- drop table if exists roles;

# --- drop table if exists computer;

SET REFERENTIAL_INTEGRITY TRUE;




