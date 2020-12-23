/*
SQLyog - Free MySQL GUI v5.11
Host - 5.5.24-log : Database - bd
*********************************************************************
Server version : 5.5.24-log
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `bd`;

USE `bd`;

/*Table structure for table `atencion` */

DROP TABLE IF EXISTS `atencion`;

CREATE TABLE `atencion` (
  `folio` int(15) NOT NULL AUTO_INCREMENT,
  `paciente` varchar(30) DEFAULT NULL,
  `fecha` varchar(15) DEFAULT NULL,
  `hora` int(4) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `costo` int(50) DEFAULT NULL,
  `dentista` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`folio`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `atencion` */

insert into `atencion` (`folio`,`paciente`,`fecha`,`hora`,`descripcion`,`costo`,`dentista`) values (9,'Daniel Vasquez','01/05/2021',1600,'Extraccion de molar',30000,'Tamara Galarce');
insert into `atencion` (`folio`,`paciente`,`fecha`,`hora`,`descripcion`,`costo`,`dentista`) values (10,'Pablo Vargas','15/02/2021',1500,'Limpieza general',20000,'Jorge Errasuriz');

/*Table structure for table `dentista` */

DROP TABLE IF EXISTS `dentista`;

CREATE TABLE `dentista` (
  `rut` varchar(15) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `edad` int(3) DEFAULT NULL,
  `especialidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dentista` */

insert into `dentista` (`rut`,`nombre`,`edad`,`especialidad`) values ('19096056-8','Bernardo Salazar',43,'Implantología Buco Máxilo Facial');
insert into `dentista` (`rut`,`nombre`,`edad`,`especialidad`) values ('19456789-3','Raul Retalames',35,'Endodoncia');
insert into `dentista` (`rut`,`nombre`,`edad`,`especialidad`) values ('20368654-K','Jorge Errasuriz',40,'Odontopediatría');
insert into `dentista` (`rut`,`nombre`,`edad`,`especialidad`) values ('20423785-4','Javiera Zambrano',28,'Rehabilitacion Oral');
insert into `dentista` (`rut`,`nombre`,`edad`,`especialidad`) values ('20568159-K','Tamara Galarce',37,'Ortodoncia y Ortopedia Dento Máxilo Facial');

/*Table structure for table `paciente` */

DROP TABLE IF EXISTS `paciente`;

CREATE TABLE `paciente` (
  `rut` varchar(15) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `edad` int(3) DEFAULT NULL,
  `celular` int(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `paciente` */

insert into `paciente` (`rut`,`nombre`,`edad`,`celular`,`email`) values ('13456789-5','Pablo Vargas',30,12234556,'pv@gmail.com');
insert into `paciente` (`rut`,`nombre`,`edad`,`celular`,`email`) values ('19093516-7','Daniel Vasquez',25,62445292,'davr@gmail.com');
insert into `paciente` (`rut`,`nombre`,`edad`,`celular`,`email`) values ('19456789-9','Marcelo Gamboa',57,45784589,'mga@gmail.com');
