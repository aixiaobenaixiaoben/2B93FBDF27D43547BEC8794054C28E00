# ************************************************************
# Description: 用户基本信息表.
# User: kevin
# Date: 2018-01-06
# ************************************************************

DROP TABLE IF EXISTS SYUSRINF;
CREATE TABLE SYUSRINF (
  SUISEQCOD varchar(32) NOT NULL DEFAULT '' COMMENT '流水号',
  SUIUSRNAM varchar(32) DEFAULT '' COMMENT '用户名',
  SUIPASWRD varchar(32) DEFAULT '' COMMENT 'MD5密码',
  SUIMOBILE varchar(32) DEFAULT '' COMMENT '手机号码',
  SUIVERSON timestamp NULL DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (SUISEQCOD)
) COMMENT='用户基本信息表';


