ALTER TABLE usuarios ADD activo TINYINT;
UPDATE usuarios SET activo = 1;