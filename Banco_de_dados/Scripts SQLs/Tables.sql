create database extpj 
	default character set utf8mb4 
	default collate utf8mb4_general_ci;

create table if not exists extpj.responsavel(
	id_responsavel int not null auto_increment,
	nome varchar(90) not null,
	sexo enum('m','f') not null,
	dt_nascimento date not null,
	telefone varchar(14) null,
	primary key (id_responsavel)
);


create table if not exists extpj.aluno(
	id_responsavel int not null,
	id_alunoreferente int not null,
	nome varchar(90) not null,
	sexo enum('m','f') not null,
	dt_nascimento date default null,
	primary key (id_alunoreferente, id_responsavel),
	constraint fk_responsavel 
	foreign key (id_responsavel) references responsavel(id_responsavel)
	on delete cascade
);

create table if not exists extpj.pagamento(
	id_pagamento int not null auto_increment,
	id_responsavel int not null,
	id_alunoreferente int not null,
	valor_mensal decimal(5,2) not null,
	data_pagamento date not null,
	primary key (id_pagamento),
	constraint fk_pagamento_responsavel
	foreign key (id_responsavel) references responsavel (id_responsavel) 
	on delete cascade,
	constraint fk_pagamento_aluno
	foreign key (id_alunoreferente, id_responsavel) references aluno (id_alunoreferente, id_responsavel)
	on delete cascade
);

#FUNÇÃO DE RESET DO AUTO_INCREMENT DE ID DA TABELA.
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
