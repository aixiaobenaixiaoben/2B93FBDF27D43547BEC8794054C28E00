DELIMITER //

DROP FUNCTION IF EXISTS F_TEST //
CREATE FUNCTION F_TEST() RETURNS VARCHAR(200)
BEGIN
  RETURN 'hello from intellij IDEA';
END;//

DELIMITER ;