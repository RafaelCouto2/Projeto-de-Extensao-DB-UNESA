#FUNÇÃO DE RESET DO AUTO_INCREMENT DE ID DE TABELA.
CREATE DEFINER=`root`@`%` PROCEDURE `extpj`.`reset_autoincrement`(IN tablename varchar(100), IN idname varchar(100))
proc: BEGIN
	
	DECLARE rowCount int default 0;
	DECLARE reset_ varchar(255);

	SET @query = CONCAT('SELECT COUNT(*) INTO @rowCount FROM ', tablename);
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
	IF @rowCount = 0 THEN
	SET @reset_ = CONCAT('ALTER TABLE ', tablename ,' auto_increment = 1;');
	PREPARE stmt FROM @reset_;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
	LEAVE proc;
	END IF;
	
   	SET @get_next_inc = CONCAT('SELECT @next_inc := max(',idname,' ) + 1 FROM ',tablename,';');
    
    PREPARE stmt FROM @get_next_inc;
    EXECUTE stmt;
    SELECT @next_inc AS result;
    DEALLOCATE PREPARE stmt;

    set @alter_statement = concat('ALTER TABLE ', tablename, ' AUTO_INCREMENT = ', @next_inc, ';');
    PREPARE stmt FROM @alter_statement;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END

