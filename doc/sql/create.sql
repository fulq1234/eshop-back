DROP TABLE IF EXISTS tbl_admin;

CREATE TABLE tbl_admin(
	id int(12) NOT NULL AUTO_INCREMENT,
	username VARCHAR(32) NOT NULL COMMENT '用户名',
	password VARCHAR(32) NOT NULL COMMENT '密码',
	create_time VARCHAR(20) DEFAULT 0 COMMENT '创建时间',
	status int(2) DEFAULT 1 COMMENT '是否启用 0:停用;1:启用',	
	PRIMARY KEY (id)
)ENGINE=INNODB COMMENT '管理员表';


insert into tbl_admin(username,password) values('admin','1B88C81D3C8D674B7CC9AC4527B3E061');