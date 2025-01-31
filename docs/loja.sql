-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema loja
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema loja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loja` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `loja` ;

-- -----------------------------------------------------
-- Table `loja`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `pedido_data_hora` DATETIME(6) NULL DEFAULT NULL,
  `pedido_descricao` VARCHAR(255) NULL DEFAULT NULL,
  `produto_status` ENUM('CANCELADO', 'EM_PROCESSAMENTO', 'PROCESSADO') NULL DEFAULT NULL,
  `pedido_valor` DECIMAL(38,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pedido`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `loja`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `produto_data_hora_saida` DATETIME(6) NULL DEFAULT NULL,
  `produto_descricao` VARCHAR(255) NULL DEFAULT NULL,
  `quantidade_estoque` INT NULL DEFAULT NULL,
  `produto_valor` DECIMAL(38,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_produto`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `loja`.`pedido_tem_produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`pedido_tem_produtos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NULL DEFAULT NULL,
  `valor_produto` DECIMAL(38,2) NULL DEFAULT NULL,
  `pedido_id` INT NULL DEFAULT NULL,
  `produto_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK63egh955r309cdsxmqxh0vyiv` (`pedido_id` ASC) VISIBLE,
  INDEX `FKrormxon75twu0svesdrf5leo6` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `FK63egh955r309cdsxmqxh0vyiv`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `loja`.`pedido` (`id_pedido`),
  CONSTRAINT `FKrormxon75twu0svesdrf5leo6`
    FOREIGN KEY (`produto_id`)
    REFERENCES `loja`.`produto` (`id_produto`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `loja`.`status_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`status_pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pedido_descricao` DATETIME(6) NULL DEFAULT NULL,
  `produto_status` ENUM('CANCELADO', 'EM_PROCESSAMENTO', 'PROCESSADO') NULL DEFAULT NULL,
  `pedido_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKgc8woqm13th0s2qr61olnr72x` (`pedido_id` ASC) VISIBLE,
  CONSTRAINT `FKgc8woqm13th0s2qr61olnr72x`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `loja`.`pedido` (`id_pedido`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `loja`.`vendedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja`.`vendedor` (
  `id_vendedor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `setor` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_vendedor`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
