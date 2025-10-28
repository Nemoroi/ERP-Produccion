-- ===========================================
-- CREACIÓN DE BASE DE DATOS
-- ===========================================
CREATE DATABASE IF NOT EXISTS ERP_PRoduccion;
USE ERP_PRoduccion;

-- ===========================================
-- TABLA ROL
-- ===========================================
CREATE TABLE Rol (
    RolID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Rol VARCHAR(50) UNIQUE NOT NULL,
    Descripcion TEXT
);

-- ===========================================
-- TABLA USUARIO
-- ===========================================
CREATE TABLE Usuario (
    UsuarioID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Completo VARCHAR(100) NOT NULL,
    Usuario VARCHAR(50) UNIQUE NOT NULL,
    Clave VARCHAR(255) NOT NULL,         -- guardar hasheada
    Email VARCHAR(100) UNIQUE,
    Telefono VARCHAR(20),
    RolID INT NOT NULL,
    Estado ENUM('Activo','Inactivo') DEFAULT 'Activo',
    Fecha_Registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (RolID) REFERENCES Rol(RolID)
);


-- ===========================================
-- TABLA VISTAS (módulos accesibles)
-- ===========================================
CREATE TABLE Vistas (
    VistaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Vista VARCHAR(100) NOT NULL,
    Descripcion TEXT,
	Ruta TEXT
);

-- ===========================================
-- TABLA DE ASIGNACIÓN DE VISTAS A ROLES
-- ===========================================
CREATE TABLE Rol_Vista (
    Rol_VistaID INT AUTO_INCREMENT PRIMARY KEY,
    RolID INT NOT NULL,
    VistaID INT NOT NULL,
    Permisos ENUM('Lectura','Escritura','Admin') DEFAULT 'Lectura',
    Fecha_Asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (RolID) REFERENCES Rol(RolID),
    FOREIGN KEY (VistaID) REFERENCES Vistas(VistaID),
    UNIQUE (RolID, VistaID) -- evita duplicados
);

-- ==========================================================
-- TABLA MAESTRA DE PRODUCTOS
-- ==========================================================
CREATE TABLE Maestra_Productos (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    Producto VARCHAR(100) NOT NULL,
    Presentacion VARCHAR(50),
    Descripcion TEXT,
    Fecha_Registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================================
-- TABLA MAESTRA DE INSUMOS
-- ==========================================================
CREATE TABLE Maestra_Insumos (
    InsumoID INT AUTO_INCREMENT PRIMARY KEY,
    Insumo VARCHAR(100) NOT NULL,
    Presentacion VARCHAR(50),
    Descripcion TEXT,
    Costo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    Fecha_Registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================================
-- TABLA DE RECETAS (ENCABEZADO)
-- ==========================================================
CREATE TABLE Receta_Encabezado (
    RecetaID INT AUTO_INCREMENT PRIMARY KEY,
    ProductoID INT NOT NULL,
    Cantidad_Batch DECIMAL(10,3) NOT NULL, -- suma total de los insumos
    Fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ProductoID) REFERENCES Maestra_Productos(ProductoID)
);

-- ==========================================================
-- TABLA DE RECETAS (DETALLE)
-- ==========================================================
CREATE TABLE Receta_Detalle (
    DetalleRecetaID INT AUTO_INCREMENT PRIMARY KEY,
    RecetaID INT NOT NULL,
    InsumoID INT NOT NULL,
    Cantidad_Planificada DECIMAL(10,3) NOT NULL,
    Descripcion TEXT,
    FOREIGN KEY (RecetaID) REFERENCES Receta_Encabezado(RecetaID),
    FOREIGN KEY (InsumoID) REFERENCES Maestra_Insumos(InsumoID)
);

-- ==========================================================
-- TABLA ORDEN DE FABRICACION (ENCABEZADO)
-- ==========================================================
CREATE TABLE Orden_Fabricacion (
    OrdenID INT AUTO_INCREMENT PRIMARY KEY,
    Codigo_Orden INT UNIQUE NOT NULL,  -- empieza en 20000
    ProductoID INT NOT NULL,
    Cantidad_Planificada DECIMAL(10,3) NOT NULL,
    Cantidad_Fabricada DECIMAL(10,3) DEFAULT 0.00,
    Estado ENUM('Planificada','En Proceso','Cerrada','Anulada') DEFAULT 'Planificada',
    Rendimiento DECIMAL(6,2) DEFAULT NULL,
    UsuarioCreacionID INT NOT NULL,
    UsuarioCierreID INT DEFAULT NULL,
    Flag_MotivoCierre BOOLEAN DEFAULT FALSE,
    Fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Fecha_Cierre DATETIME DEFAULT NULL,
    FOREIGN KEY (ProductoID) REFERENCES Maestra_Productos(ProductoID),
    FOREIGN KEY (UsuarioCreacionID) REFERENCES Usuario(UsuarioID),
    FOREIGN KEY (UsuarioCierreID) REFERENCES Usuario(UsuarioID)
);

-- Trigger para iniciar los códigos de orden desde 20000
DELIMITER //
CREATE TRIGGER trg_codigo_orden
BEFORE INSERT ON Orden_Fabricacion
FOR EACH ROW
BEGIN
    IF NEW.Codigo_Orden IS NULL THEN
        SET NEW.Codigo_Orden = 20000 + (SELECT IFNULL(MAX(OrdenID),0) FROM Orden_Fabricacion);
    END IF;
END;
//
DELIMITER ;

-- ==========================================================
-- TABLA DETALLE ORDEN DE FABRICACION
-- ==========================================================
CREATE TABLE Detalle_Orden_Fabricacion (
    DetalleOFID INT AUTO_INCREMENT PRIMARY KEY,
    OrdenID INT NOT NULL,
    InsumoID INT NOT NULL,
    Cantidad_Planificada DECIMAL(10,3) NOT NULL,
    Cantidad_Consumida DECIMAL(10,3) DEFAULT 0.00,
    Costo DECIMAL(10,2) DEFAULT 0.00,
    UsuarioID INT NOT NULL,
    FOREIGN KEY (OrdenID) REFERENCES Orden_Fabricacion(OrdenID),
    FOREIGN KEY (InsumoID) REFERENCES Maestra_Insumos(InsumoID),
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(UsuarioID)
);

-- ==========================================================
-- TABLA VALIDACIÓN DE CIERRES (por jefatura)
-- ==========================================================
CREATE TABLE Validacion_Cierres (
    ValidacionID INT AUTO_INCREMENT PRIMARY KEY,
    OrdenID INT NOT NULL,
    Descargo_Rendimiento TEXT NOT NULL,
    UsuarioJefaturaID INT NOT NULL,
    Fecha_Validacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (OrdenID) REFERENCES Orden_Fabricacion(OrdenID),
    FOREIGN KEY (UsuarioJefaturaID) REFERENCES Usuario(UsuarioID)
);

-- ==========================================================
-- TABLA CIERRE FINANCIERO
-- ==========================================================
CREATE TABLE Cierre_Financiero (
    CierreID INT AUTO_INCREMENT PRIMARY KEY,
    OrdenID INT NOT NULL,
    Rendimiento DECIMAL(6,2) NOT NULL,
    Costo_Tonelada DECIMAL(10,2) NOT NULL,
    UsuarioFinanzasID INT NOT NULL,
    Fecha_Cierre TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (OrdenID) REFERENCES Orden_Fabricacion(OrdenID),
    FOREIGN KEY (UsuarioFinanzasID) REFERENCES Usuario(UsuarioID)
);
