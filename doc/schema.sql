drop table if exists tbl_admin;
create table tbl_admin(
	id int(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	username varchar(255) NOT NULL COMMENT '用户名',
	password varchar(255) COMMENT '密码',
	create_time bigint(20) DEFAULT NULL COMMENT '创建时间',
	status int(2) DEFAULT 1 COMMENT '状态,1:启用;0:停用',
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into tbl_admin(username,password) values('admin','a123456');