create user 'professora'@'%' identified by 'celeusa1969';
create user 'professora'@'localhost' identified by 'celeusa1969';

grant insert, delete, update, select, REFERENCES on extpj.aluno to 'professora'@'%';
grant alter, insert, delete, update, select, REFERENCES on extpj.pagamento to 'professora'@'%';
grant insert, delete, update, select, REFERENCES on extpj.responsavel to 'professora'@'%';

GRANT EXECUTE ON PROCEDURE extpj.reset_autoincrement TO 'professora'@'localhost';
GRANT EXECUTE ON PROCEDURE extpj.reset_autoincrement TO 'professora'@'%';