-- MySQL Script generated by MySQL Workbench
-- Fri Dec  8 16:36:47 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `project` ;

-- -----------------------------------------------------
-- Table `project`.`TRAN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`TRAN` ;

CREATE TABLE IF NOT EXISTS `project`.`TRAN` (
  `tran_id` INT auto_increment NOT NULL,
  `tran_no` INT NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `departuretime` VARCHAR(45) NOT NULL,
  `arrivaltime` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`tran_id`))
ENGINE = InnoDB;

-- 設置 AUTO_INCREMENT = 101
alter table TRAN auto_increment = 101;


-- -----------------------------------------------------
-- Table `project`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`USER` ;

CREATE TABLE IF NOT EXISTS `project`.`USER` (
  `user_id` INT auto_increment NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_phone` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;

-- 設置 AUTO_INCREMENT = 501
alter table USER auto_increment = 501;

-- -----------------------------------------------------
-- Table `project`.`TICKET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`TICKET` ;

CREATE TABLE IF NOT EXISTS `project`.`TICKET` (
  `ticket_id` INT auto_increment  NOT NULL,
  `user_id` INT NOT NULL,
  `tran_id` INT NOT NULL,
  `car_no` INT NOT NULL,
  `site_no` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `state` INT NOT NULL,
  PRIMARY KEY (`ticket_id`, `user_id`, `tran_id`),
  INDEX `fk_tran_id_idx` (`tran_id` ASC) VISIBLE,
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_tran_id`
    FOREIGN KEY (`tran_id`)
    REFERENCES `project`.`TRAN` (`tran_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `project`.`USER` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- 設置 AUTO_INCREMENT = 301
alter table TICKET auto_increment = 301;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
