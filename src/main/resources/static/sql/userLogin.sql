--Creacion de usuario para la conexion en la base de datos
CREATE LOGIN angelsanchez WITH PASSWORD = N'sY£1Q_PT=9i3';
CREATE USER angelsanchez FOR LOGIN angelsanchez;
ALTER ROLE db_owner ADD MEMBER angelsanchez; -- me doy control