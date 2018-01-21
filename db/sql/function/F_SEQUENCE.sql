# ************************************************************
# Description: 系统流水号生成方法.
# User: kevin
# Date: 2018-01-04
# ************************************************************

DELIMITER //

DROP FUNCTION IF EXISTS F_SEQUENCE //
CREATE FUNCTION F_SEQUENCE() RETURNS VARCHAR(24)
BEGIN
    DECLARE MAXINT INT DEFAULT 2147483647;
    DECLARE SEQ INT;

    SELECT ID INTO SEQ FROM SEQUENCE;

    IF SEQ >= MAXINT THEN
        UPDATE SEQUENCE SET ID = 0;
    ELSE
    	UPDATE SEQUENCE SET ID = ID + 1;
    END IF;

    RETURN CONCAT(DATE_FORMAT(NOW(), '%Y%m%d%H%i%S'), LPAD(SEQ, 10, '0'));

END;//

DELIMITER ;

