/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.0 		*/
/*  Created On : 06-5月-2018 10:40:45 				*/
/*  DBMS       : MySql 						*/
/* ---------------------------------------------------- */

SET FOREIGN_KEY_CHECKS=0

/* Create Tables */

CREATE TABLE `depart`
(
	`id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT '主键',
	`depart_id` VARCHAR(50) NOT NULL COMMENT '部门id',
	`parent_id` VARCHAR(50) NOT NULL COMMENT '父部门id',
	`depart_name` VARCHAR(200) COMMENT '部门名称',
	CONSTRAINT `PK_depart` PRIMARY KEY (`id`)
) COMMENT='部门表'
;

SET FOREIGN_KEY_CHECKS=1
