ALTER TABLE db_memorychallengeapp.table_bloque
MODIFY COLUMN fecha_creacion datetime default now();



INSERT INTO db_memorychallengeapp.table_bloqueactivocorto(ID_BLOQUE_AC, tiempo_actual)
VALUES ((SELECT  LAST_INSERT_ID() FROM  db_memorychallengeapp.table_bloque),1000);

INSERT INTO db_memorychallengeapp.table_bloqueactivocorto(ID_BLOQUE_AC, tiempo_actual)
VALUES((SELECT LAST_INSERT_ID(ID_BLOQUE) from db_memorychallengeapp.table_bloque order by LAST_INSERT_ID(ID_BLOQUE) desc limit 1),0);

truncate table  db_memorychallengeapp.table_bloque;
truncate table  db_memorychallengeapp.table_bloqueactivocorto;
truncate table  db_memorychallengeapp.table_bloqueinactivocorto;
truncate table db_memorychallengeapp.table_enunciado;
DELETE FROM db_memorychallengeapp.table_bloque WHERE ID_BLOQUE = 42;

insert into db_memorychallengeapp.table_enunciado ( COD_ENUNCIADO, pregunta, respuesta)
VALUES( 'EJEMPLO', '¿PREGUNTA EJEMPLO? ', 'RESPUESTA EJEMPLO');

DROP TRIGGER db_memorychallengeapp.table_bloque_AFTER_INSERT;

INSERT INTO db_memorychallengeapp.table_bloque(nombre,duracion_total,duracion_inicial,estado)
VALUES ('46ejemplo_de_insercion', 12345,123,1);

UPDATE db_memorychallengeapp.table_bloque
SET estado = 0
WHERE ID_BLOQUE = 44;

DELETE FROM db_memorychallengeapp.table_bloqueactivocorto WHERE ID_BLOQUE_AC =  NEW.ID_BLOQUE_AC;
SELECT * FROM  db_memorychallengeapp.table_bloque;
SELECT * FROM db_memorychallengeapp.table_bloqueactivocorto;
SELECT * FROM db_memorychallengeapp.table_bloqueinactivocorto;
SELECT * FROM  db_memorychallengeapp.table_enunciado;



SELECT   (LAST_INSERT_ID()) FROM  db_memorychallengeapp.table_bloque;


  