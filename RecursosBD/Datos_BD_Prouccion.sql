USE ERP_PRoduccion;

-- ===========================================
-- INSERTAR ROLES
-- ===========================================
INSERT INTO Rol (Nombre_Rol, Descripcion) VALUES
('Admin', 'Administrador con acceso completo'),
('Analista_Produccion', 'Usuario que rinde produccion y consumo'),
('Jefatura', 'Usuario que valida los cierres'),
('Control_Finanzas', 'Usuario que realiza el cierre de costos');
-- ===========================================
-- INSERTAR USUARIOS
-- (Clave simulada, en real debería ir hasheada)
-- ===========================================
INSERT INTO Usuario (Nombre_Completo, Usuario, Clave, Email, Telefono, RolID) VALUES
('Carlos Pérez', 'cperez', '1234', 'cperez@empresa.com', '987654321', 1), -- Admin
('Laura Gómez', 'lgomez', '1234', 'lgomez@empresa.com', '987654322', 2), -- analista
('Juan Torres', 'jtorres', '1234', 'jtorres@empresa.com', '987654323', 3), -- jefatura
('María Ruiz', 'mruiz', '1234', 'mruiz@empresa.com', '987654324', 4); -- finanzas

-- ===========================================
-- INSERTAR VISTAS
-- ===========================================
INSERT INTO Vistas (Nombre_Vista, Descripcion, Ruta) VALUES
('Mantenimiento Usuarios', 'Módulo para mantenimiento de Usuarios', '/usuarios/listar'),
('Modulo Vistas', 'Administración de cierres', '/manejo_vistas/listarVistas'),
('Modulo Roles', 'Administración de cierres', '/manejo_vistas/listarRoles'),
('Modulo Asignacion de Vistas', 'Administración de cierres', '/manejo_vistas/listarRolVista'),
('Modulo Produccion', 'Módulo para la Gestion de Produccion', '/usuarios/listar'),
('Modulo Validacion', 'Módulo para la validacion de cierres', '/usuarios/listar'),
('Modulo Finanzas', 'Administración de cierres', '/usuarios/listar');


-- ===========================================
-- ASIGNAR VISTAS A ROLES
-- ===========================================
INSERT INTO Rol_Vista (RolID, VistaID, Permisos) VALUES
(1, 1, 'Admin'),   -- Admin tiene dashboard admin
(1, 2, 'Admin'),   -- Admin ve todo
(1, 3, 'Admin'),
(1, 4, 'Admin'),
(2, 5, 'Admin'),
(3, 6, 'Escritura'), -- Reportador puede crear reportes
(4, 7, 'Escritura');

-- ==========================================================
-- MAESTRA DE PRODUCTOS
-- ==========================================================
INSERT INTO Maestra_Productos (Producto, Presentacion, Descripcion)
VALUES 
('Dog Premium Adulto', '25 kg', 'Alimento premium para perro adulto'),
('Dog Cachorro Plus', '20 kg', 'Alimento balanceado para cachorro'),
('Cat Supreme Adulto', '15 kg', 'Alimento completo para gato adulto'),
('Cat Light Senior', '10 kg', 'Fórmula ligera para gatos mayores');

-- ==========================================================
-- MAESTRA DE INSUMOS
-- ==========================================================
INSERT INTO Maestra_Insumos (Insumo, Presentacion, Descripcion, Costo)
VALUES
('Harina de maíz', '50 kg', 'Fuente de carbohidratos', 45.50),
('Harina de pollo', '25 kg', 'Proteína animal', 120.00),
('Aceite de pescado', '20 L', 'Grasa esencial Omega 3', 80.00),
('Premezcla vitamínica', '10 kg', 'Aporte de vitaminas y minerales', 150.00),
('Harina de arroz', '50 kg', 'Carbohidrato alternativo', 60.00),
('Colorante natural', '5 kg', 'Pigmento natural para croquetas', 40.00);

-- ==========================================================
-- RECETAS ENCABEZADO
-- ==========================================================
INSERT INTO Receta_Encabezado (ProductoID, Cantidad_Batch)
VALUES 
(1, 1000), -- Dog Premium Adulto
(2, 800),  -- Dog Cachorro Plus
(3, 700),  -- Cat Supreme Adulto
(4, 600);  -- Cat Light Senior

-- ==========================================================
-- RECETA DETALLE (Dog Premium Adulto)
-- ==========================================================
INSERT INTO Receta_Detalle (RecetaID, InsumoID, Cantidad_Planificada, Descripcion)
VALUES
(1, 1, 400, 'Base de maíz'),
(1, 2, 350, 'Proteína animal'),
(1, 3, 100, 'Grasa esencial'),
(1, 4, 50, 'Vitaminas y minerales'),
(1, 6, 10, 'Colorante natural');

-- ==========================================================
-- RECETA DETALLE (Dog Cachorro Plus)
-- ==========================================================
INSERT INTO Receta_Detalle (RecetaID, InsumoID, Cantidad_Planificada, Descripcion)
VALUES
(2, 1, 300, 'Carbohidratos'),
(2, 2, 400, 'Proteína principal'),
(2, 3, 50, 'Grasa esencial'),
(2, 4, 40, 'Vitaminas'),
(2, 6, 10, 'Colorante natural');

-- ==========================================================
-- RECETA DETALLE (Cat Supreme Adulto)
-- ==========================================================
INSERT INTO Receta_Detalle (RecetaID, InsumoID, Cantidad_Planificada, Descripcion)
VALUES
(3, 5, 300, 'Harina base'),
(3, 2, 300, 'Proteína animal'),
(3, 3, 50, 'Aceite de pescado'),
(3, 4, 40, 'Vitaminas'),
(3, 6, 10, 'Colorante natural');

-- ==========================================================
-- RECETA DETALLE (Cat Light Senior)
-- ==========================================================
INSERT INTO Receta_Detalle (RecetaID, InsumoID, Cantidad_Planificada, Descripcion)
VALUES
(4, 5, 350, 'Harina ligera'),
(4, 2, 200, 'Proteína baja'),
(4, 3, 30, 'Grasa controlada'),
(4, 4, 20, 'Vitaminas'),
(4, 6, 5, 'Colorante natural');

-- ==========================================================
-- ORDEN DE FABRICACIÓN
-- ==========================================================
INSERT INTO Orden_Fabricacion 
(Codigo_Orden, ProductoID, Cantidad_Planificada, Cantidad_Fabricada, Estado, Rendimiento, Usuario_Creacion_ID)
VALUES
(20000, 1, 1000, 980, 'Cerrada', 98.0, 2),   -- Laura Gómez crea
(20001, 2, 800, 810, 'Cerrada', 101.3, 2),
(20002, 3, 700, 650, 'Cerrada', 92.8, 2),
(20003, 4, 600, 605, 'Cerrada', 100.8, 2);

-- ==========================================================
-- DETALLE ORDEN DE FABRICACIÓN (Dog Premium Adulto)
-- ==========================================================
INSERT INTO Detalle_Orden_Fabricacion (OrdenID, InsumoID, Cantidad_Planificada, Cantidad_Consumida, Costo, UsuarioID)
VALUES
(1, 1, 400, 395, 45.50, 2),
(1, 2, 350, 352, 120.00, 2),
(1, 3, 100, 99, 80.00, 2),
(1, 4, 50, 49, 150.00, 2),
(1, 6, 10, 10, 40.00, 2);

-- ==========================================================
-- VALIDACIÓN DE CIERRES (solo cuando el rendimiento es bajo o alto)
-- ==========================================================
INSERT INTO Validacion_Cierres (OrdenID, Descargo_Rendimiento, UsuarioJefaturaID)
VALUES
(3, 'Rendimiento bajo debido a humedad alta en insumo principal.', 3);

-- ==========================================================
-- CIERRE FINANCIERO
-- ==========================================================
INSERT INTO Cierre_Financiero (OrdenID, Rendimiento, Costo_Tonelada, UsuarioFinanzasID)
VALUES
(1, 98.0, 950.00, 4),
(2, 101.3, 970.00, 4),
(3, 92.8, 1020.00, 4),
(4, 100.8, 940.00, 4);
