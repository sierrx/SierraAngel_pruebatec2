-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-01-2025 a las 17:23:24
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `turnero`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudadano`
--

CREATE TABLE `ciudadano` (
  `id` bigint(20) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TELEFONO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `ciudadano`
--

INSERT INTO `ciudadano` (`id`, `APELLIDO`, `NOMBRE`, `TELEFONO`) VALUES
(1, 'Sierra', 'Angel', '7721541110'),
(2, 'Sierra', 'Angel', '7721541110'),
(3, 'Cortez', 'Luis', '871289182'),
(4, 'Cortez', 'Luis', '871289182'),
(5, 'Hernandez', 'Juan', '123377828872'),
(6, 'Alvarez', 'Miriam', '9029897837'),
(7, 'Marquez', 'Aldair', '287389212123'),
(8, 'Juarez', 'Miguel', '9029897837');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `id` bigint(20) NOT NULL,
  `ACTIVO` tinyint(1) DEFAULT 0,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TEMA` varchar(255) DEFAULT NULL,
  `id_ciudadano` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id`, `ACTIVO`, `DESCRIPCION`, `FECHA`, `STATUS`, `TEMA`, `id_ciudadano`) VALUES
(1, 0, 'No hay agua hace 2 semanas', '2025-01-23', 'En espera', 'Agua', 2),
(2, 0, 'No hay agua hace 2 semanas', '2025-01-23', 'En espera', 'Agua', 1),
(3, 0, 'se me fue la luz', '2025-01-23', 'En espera', 'luz', 3),
(4, 1, 'se me fue la luz', '2025-01-23', 'Atendido', 'luz', 4),
(5, 1, 'hay baches en toda la colonia', '2025-01-22', 'En espera', 'pavimentar calle', 5),
(6, 1, 'Estallo un transformador y necesita ser cambiado', '2025-01-31', 'En espera', 'Falla Electrica', 6),
(7, 1, 'No hay agua hace 3 semanas', '2025-02-07', 'En espera', 'Agua', 7),
(8, 1, 'No sirve el alumbrado publico de la calle Puerto Madero', '2025-02-05', 'En espera', 'Falla Electrica', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID`, `EMAIL`, `PASSWORD`) VALUES
(2, 'angelaldair@gmail.com', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Turno_id_ciudadano` (`id_ciudadano`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `FK_Turno_id_ciudadano` FOREIGN KEY (`id_ciudadano`) REFERENCES `ciudadano` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
