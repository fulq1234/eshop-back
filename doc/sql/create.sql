DROP TABLE IF EXISTS tbl_admin;

CREATE TABLE tbl_admin(
	id int(12) NOT NULL AUTO_INCREMENT,
	username VARCHAR(32) NOT NULL COMMENT '用户名',
	password VARCHAR(32) NOT NULL COMMENT '密码',
	create_time VARCHAR(20) DEFAULT 0 COMMENT '创建时间',
	status int(2) DEFAULT 1 COMMENT '是否启用 0:停用;1:启用;',
	shenhe int(2) DEFAULT 0 COMMENT '当usertype=1时， 0:管理员自己创建; >0时审核 1:未审核;2:审核通过;3:审核不通过;',
	remark VARCHAR(32) DEFAULT '' COMMENT '备注',
	usertype int(2) DEFAULT 0 COMMENT '0:管理员;1:商家',
	PRIMARY KEY (id)
)ENGINE=INNODB COMMENT '管理员表';


insert into tbl_admin(username,password) values('admin','1B88C81D3C8D674B7CC9AC4527B3E061');

/**
 * 商家详情表
 */
DROP TABLE IF EXISTS tbl_sale_account;
CREATE TABLE tbl_sale_account(
	id int(12) NOT NULL AUTO_INCREMENT,
	admin_id int(12) NOT NULL COMMENT '指向tbl_admin的id',
	name VARCHAR(32) NOT  NULL COMMENT '名称',
	mobile VARCHAR(32) NOT NULL COMMENT '手机号',
	address VARCHAR(32) NOT NULL COMMENT '地址',
	idcard VARCHAR(32) NOT NULL COMMENT '身份证号码',
	idimage VARCHAR(32) NOT NULL COMMENT '身份证复印件图片位置',
	PRIMARY KEY(id)
	
)ENGINE=INNODB COMMENT '商家账号其它详细信息';

/**
 * 
 */

