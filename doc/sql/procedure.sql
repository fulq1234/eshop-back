DELIMITER
DROP PROCEDURE IF EXISTS proc_goods;
DELIMITER
CREATE PROCEDURE proc_goods()
BEGIN
	DECLARE v1 int;
	SET v1 = 0;
	while v1 < 10 do 
		SET v1 = v1 + 1;
		insert into tbl_goods(admin_id,name) values(1,concat('name',v1));
	end while;
	
END;

call proc_goods();