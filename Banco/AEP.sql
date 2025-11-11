CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL, 
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Locais (
    id_local INT AUTO_INCREMENT PRIMARY KEY,
    cidade VARCHAR(100) NOT NULL,
    distrito VARCHAR(100),
    estado CHAR(2) NOT NULL,
    país VARCHAR(100) NOT NULL DEFAULT 'Brasil'
);

CREATE TABLE Historia (
    id_historia INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    conteudo TEXT NOT NULL,
    data_publicacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT,
    id_local INT,
    
    CONSTRAINT FK_Historia_Usuario
        FOREIGN KEY (id_usuario)
        REFERENCES Usuarios (id_usuario)
        ON DELETE SET NULL,
    
    CONSTRAINT FK_Historia_Local
        FOREIGN KEY (id_local)
        REFERENCES Locais (id_local)
        ON DELETE SET NULL
);

CREATE TABLE Midias (
    id_midia INT AUTO_INCREMENT PRIMARY KEY,
    tipo_midia ENUM ('foto', 'video', 'audio') NOT NULL,
    url_arquivo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    id_historia INT NOT NULL,

    CONSTRAINT FK_Midias_Historia
        FOREIGN KEY (id_historia)
        REFERENCES Historia (id_historia)
        ON DELETE CASCADE
);