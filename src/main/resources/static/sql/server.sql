CREATE DATABASE JurasickPark;
USE JurasickPark;
--Tablas

--tabla de los recintos de los dinosaurios
CREATE TABLE Enclousures(
                            Id INT PRIMARY KEY IDENTITY(1,1),
                            Name VARCHAR(100) NOT NULL,
                            Description VARCHAR(1000) NOT NULL,
                            Image VARBINARY(MAX) NOT NULL,
                            Cost DECIMAL(10,2) NOT NULL,
);

--tabla de los dinosaurios
CREATE TABLE Dinosaurs(
                          Id INT PRIMARY KEY IDENTITY(1,1),
                          Name VARCHAR(100)  NOT NULL,
                          ScientificName VARCHAR(1000) NOT NULL,
                          Cost DECIMAL(10,2) NOT NULL,
                          Image VARBINARY(MAX) NOT NULL,
                          RequiredRecintoId INT,--cada dinosaurio ira asociado con el id del su recinto (esto lo usario para cuando se compre un recinto, se desbloquen para comprar los dinosuarios de ese recinto
                          CONSTRAINT FK_Enclousures
                              FOREIGN KEY(RequiredRecintoId)
                                  REFERENCES Enclousures(Id));


---tabla de las emergencias, son eventos aleatorios que surgiran al recargar la pagina

CREATE TABLE Emergencies(
                            Id INT PRIMARY KEY IDENTITY(1,1),
                            Name VARCHAR(100) NOT NULL,
                            CoinLost DECIMAL (10,2) NOT NULL,
                            Image VARBINARY(MAX) NOT NULL,
);


--la tabla de los usuarios

CREATE TABLE Users(
                      Id BIGINT PRIMARY KEY IDENTITY(1,1),
                      Email NVARCHAR(100) NOT NULL UNIQUE,
                      Password NVARCHAR(255) NOT NULL,--la contraseña estara cifrada
                      Salt NVARCHAR(255) NOT NULL,--segun he investigado se usa para el hashing, hara que se genere de forma aleatoria para cada usuario
                      Name NVARCHAR(100) NOT NULL,
                      Rol VARCHAR(40) DEFAULT 'User',
                      IsBanned BIT DEFAULT 0,--para cuando banee algun usuario 0 no baneado 1 baneado
                      BanExpirationDate DATETIME NULL, --para cuando termine el baneo (si es temporal)
                      banReason VARCHAR(255) NULL
);


--la tabla para el parke
CREATE TABLE Parks (
                       Id INT PRIMARY KEY IDENTITY(1,1),
                       UserId BIGINT,
                       ParkName VARCHAR(100) NOT NULL,
                       Coins DECIMAL(10,2) NOT NULL,
                       FOREIGN KEY (UserId) REFERENCES Users(Id)
);

CREATE TABLE Dinosaurs_Parks (
                                 ParkId INT,
                                 DinosaurId INT,
                                 PRIMARY KEY (ParkId, DinosaurId),
                                 FOREIGN KEY (ParkId) REFERENCES Parks(Id),
                                 FOREIGN KEY (DinosaurId) REFERENCES Dinosaurs(Id)
);

CREATE TABLE Enclousures_Park (
                                  ParkId INT,
                                  EnclousureId INT,
                                  PRIMARY KEY (ParkId, EnclousureId),
                                  FOREIGN KEY (ParkId) REFERENCES Parks(Id),
                                  FOREIGN KEY (EnclousureId) REFERENCES Enclousures(Id)
);


ALTER TABLE Enclousures
ALTER COLUMN Image VARCHAR(255) NOT NULL;


ALTER TABLE Dinosaurs
ALTER COLUMN Image VARCHAR(255) NOT NULL;


ALTER TABLE Emergencies
ALTER COLUMN Image VARCHAR(255) NOT NULL;

ALTER TABLE Parks
ALTER COLUMN ParkName VARCHAR(100) NULL;


--inserciones de datos

INSERT INTO Enclousures (Name, Description, Image, Cost)
VALUES
    ('Recinto de depredadores',
     'Recinto seguro y fortificado diseñado para dinosaurios carnívoros grandes.',
     '/images/enclosures/recinto-depredadores.webp',
     1000),
    ('Recinto de herbívoros',
     'Un amplio recinto con abundante vegetación para albergar dinosaurios herbívoros.',
     '/images/enclosures/recinto-herbivoros.webp',
     800),
    ('Recinto gigante',
     'Espacio inmenso diseñado para albergar dinosaurios de gran tamaño como Brachiosaurus.',
     '/images/enclosures/recinto-gigante.webp',
     1200),
    ('Recinto volador',
     'Área cerrada donde se pueden alojar dinosaurios voladores como Pteranodon.',
     '/images/enclosures/recinto-volador.webp',
     900),
    ('Recinto acuático',
     'Espacio acuático especializado para dinosaurios marinos como el Mosasaurus.',
     '/images/enclosures/recinto-acuatico.webp',
     1500);

INSERT INTO Dinosaurs (Name, ScientificName, Cost, Image, RequiredRecintoId)
VALUES
    ('Tyrannosaurus Rex', 'Tyrannosaurus rex', 500, 'images/dinosaurs/t-rex.webp', 1),
    ('Velociraptor', 'Velociraptor mongoliensis', 300, 'images/dinosaurs/velociraptor.webp', 1),
    ('Triceratops', 'Triceratops horridus', 400, 'images/dinosaurs/triceratops.webp', 2),
    ('Brachiosaurus', 'Brachiosaurus altithorax', 600, 'images/dinosaurs/brachiosaurus.webp', 3),
    ('Stegosaurus', 'Stegosaurus stenops', 350, 'images/dinosaurs/stegosaurus.webp', 2),
    ('Pteranodon', 'Pteranodon longiceps', 450, 'images/dinosaurs/pteranodon.webp', 4),
    ('Spinosaurus', 'Spinosaurus aegyptiacus', 700, 'images/dinosaurs/spinosaurus.webp', 1),
    ('Ankylosaurus', 'Ankylosaurus magniventris', 400, 'images/dinosaurs/ankylosaurus.webp', 2),
    ('Parasaurolophus', 'Parasaurolophus walkeri', 300, 'images/dinosaurs/parasaurolophus.webp', 2),
    ('Mosasaurus', 'Mosasaurus hoffmannii', 800, 'images/dinosaurs/mosasaurus.webp', 5);

INSERT INTO Emergencies (Name, CoinLost, Image)
VALUES
    ('Fuga de dinosaurios', 300, '/images/emergencies/fuga-dinosaurios.png'),
    ('Tormenta eléctrica', 200, '/images/emergencies/tormenta-electrica.png'),
    ('Enfermedad contagiosa', 400, '/images/emergencies/enfermedad-contagiosa.png');

--Se me olvido agregarle la columna que guardase el motivo de baneo
ALTER TABLE users
    ADD banReason VARCHAR(255) NULL;