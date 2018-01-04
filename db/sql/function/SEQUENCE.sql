# ************************************************************
# Description: 系统流水号生成表.
# User: kevin
# Date: 2018-01-04
# ************************************************************

DROP TABLE IF EXISTS SEQUENCE;
CREATE TABLE SEQUENCE (ID INT NOT NULL) COMMENT='系统流水号生成表';
INSERT INTO SEQUENCE VALUES (0);

