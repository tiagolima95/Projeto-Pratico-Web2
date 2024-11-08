DROP DATABASE IF EXISTS projetoweb_db;

CREATE DATABASE projetoweb_db;

USE projetoweb_db;

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,	
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,	
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(500),
    quantidade_estoque INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    categoria_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)
);

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,	
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE Venda (
	id INT PRIMARY KEY AUTO_INCREMENT,
    data DATETIME DEFAULT CURRENT_TIMESTAMP,
    cliente_id INT,
    funcionario_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE ItemVenda (
    id INT PRIMARY KEY AUTO_INCREMENT,
    venda_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES Venda(id),
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

INSERT INTO Categoria (nome) VALUES
('Camisetas'),
('Calças'),
('Vestidos'),
('Jaquetas'),
('Sapatos');

INSERT INTO Produto (nome, descricao, quantidade_estoque, preco, categoria_id) VALUES
('Camiseta Básica', 'Camiseta de algodão com gola redonda', 50, 29.99, 1),
('Camiseta Estampada', 'Camiseta com estampa de gráfico moderno', 30, 39.99, 1),
('Camiseta Polo', 'Camiseta polo com botão e gola estruturada', 40, 59.99, 1),
('Camiseta Manga Longa', 'Camiseta de manga longa, ideal para o inverno', 25, 49.99, 1),
('Regata', 'Camiseta sem mangas, ideal para dias quentes', 60, 19.99, 1),
('Jeans Slim', 'Calça jeans skinny com lycra', 45, 99.99, 2),
('Jeans Reto', 'Calça jeans modelo reto com tecido confortável', 35, 89.99, 2),
('Calça de Sarja', 'Calça de sarja leve, ideal para o trabalho', 50, 79.99, 2),
('Bermuda Jeans', 'Bermuda jeans com 5 bolsos e acabamento desfiado', 60, 69.99, 2),
('Shorts de Cós Alto', 'Shorts femininos com cós alto e confortável', 40, 49.99, 2),
('Vestido Floral', 'Vestido midi com estampa floral e mangas curtas', 30, 129.99, 3),
('Vestido de Festa', 'Vestido longo para ocasiões formais', 20, 199.99, 3),
('Vestido Casual', 'Vestido casual, ideal para o dia a dia', 50, 89.99, 3),
('Vestido Tubo', 'Vestido justo com detalhes em renda', 15, 159.99, 3),
('Vestido de Noiva', 'Vestido de noiva elegante, com detalhes em pérolas', 5, 999.99, 3),
('Jaqueta de Couro', 'Jaqueta de couro estilo motociclista', 10, 249.99, 4),
('Jaqueta Jeans', 'Jaqueta de jeans estilo retrô, com botões dourados', 20, 169.99, 4),
('Casaco de Lã', 'Casaco longo de lã, ideal para o inverno', 25, 299.99, 4),
('Blazer Feminino', 'Blazer feminino com corte moderno e ajustado', 30, 179.99, 4),
('Jaqueta Parka', 'Jaqueta parka com capô, para proteção contra o frio extremo', 15, 219.99, 4),
('Tênis Casual', 'Tênis confortável para o dia a dia, estilo casual', 60, 89.99, 5),
('Tênis Esportivo', 'Tênis esportivo para atividades físicas e corridas', 50, 149.99, 5),
('Sapato Social Masculino', 'Sapato social de couro para ocasiões formais', 20, 179.99, 5),
('Bota Feminina', 'Bota feminina de cano curto com salto grosso', 30, 159.99, 5),
('Sapatilha', 'Sapatilha feminina de bico fino e confortável', 40, 69.99, 5);

INSERT INTO Cliente (nome, email, telefone) VALUES ('João Silva', 'joao.silva@email.com', '(11) 22222-3333');
INSERT INTO Cliente (nome, email, telefone) VALUES ('Maria Oliveira', 'maria.oliveira@email.com', '(22) 33333-4444');
