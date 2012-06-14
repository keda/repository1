--<ScriptOptions statementTerminator=";"/>

ALTER TABLE tao_tracermessage DROP PRIMARY KEY;

DROP TABLE tao_tracermessage;

CREATE TABLE tao_tracermessage (
	id BIGINT NOT NULL AUTO_INCREMENT,
	info_name VARCHAR(200),
	info_url VARCHAR(500),
	info_date VARCHAR(30),
	client_ip VARCHAR(15),
	client_port INT,
	timestamp BIGINT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

