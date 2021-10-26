SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `appuser` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `application_user_role` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `characters` (
  `id` bigint(20) NOT NULL,
  `image` mediumblob NOT NULL,
  `name` varchar(100) NOT NULL,
  `age` int(11) NOT NULL,
  `weight` double NOT NULL,
  `history` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `confirmation_token` (
  `id` bigint(20) NOT NULL,
  `token` varchar(255) NOT NULL,
  `crated_at` datetime NOT NULL,
  `expires_at` datetime NOT NULL,
  `confirmed_at` datetime DEFAULT NULL,
  `app_user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `genres` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `relations_characters_series_films` (
  `id` bigint(20) NOT NULL,
  `id_character` bigint(20) NOT NULL,
  `id_series_and_films` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `series_and_films` (
  `id` bigint(20) NOT NULL,
  `image` mediumblob NOT NULL,
  `title` varchar(100) NOT NULL,
  `creation_date` date NOT NULL,
  `review` int(11) NOT NULL,
  `id_genre` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `appuser`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `characters`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `confirmation_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `app_user_id` (`app_user_id`);

ALTER TABLE `genres`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `relations_characters_series_films`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCharacter` (`id_character`),
  ADD KEY `idSeries` (`id_series_and_films`);

ALTER TABLE `series_and_films`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idGenres` (`id_genre`);


ALTER TABLE `appuser`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

ALTER TABLE `characters`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `confirmation_token`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

ALTER TABLE `genres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `relations_characters_series_films`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

ALTER TABLE `series_and_films`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;


ALTER TABLE `confirmation_token`
  ADD CONSTRAINT `app_user_id` FOREIGN KEY (`app_user_id`) REFERENCES `appuser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `relations_characters_series_films`
  ADD CONSTRAINT `idCharacter` FOREIGN KEY (`id_character`) REFERENCES `characters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idSeries` FOREIGN KEY (`id_series_and_films`) REFERENCES `series_and_films` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `series_and_films`
  ADD CONSTRAINT `idGenres` FOREIGN KEY (`id_genre`) REFERENCES `genres` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
