CREATE TRIGGER SetDefaultValours
    ON Users
    AFTER INSERT, UPDATE
                      AS
BEGIN
UPDATE Users
SET IsBanned = ISNULL(IsBanned, 0)
WHERE Id IN (SELECT Id FROM inserted);

UPDATE Users
SET Rol = ISNULL(Rol,'User')
WHERE Id IN (SELECT Id From inserted);

END;
--El trigger lo he tenido que poner debido a que aunque haya puesto un default, cuando no haya valor
--java en el momento de insertar los datos,  si no encuentra valores y como esta nullable pues le pone null
--que ya es un valor aceptado por la base de datos,