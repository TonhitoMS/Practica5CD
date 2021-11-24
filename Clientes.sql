Create table Cliente
(
	id_cliente varchar(10) NOT NULL,
	clave varchar(10) NOT NULL,
	CONSTRAINT "Cliente_pkey" PRIMARY KEY (id_cliente)
);

Create table SerAmigo(
	id_cliente1 varchar(10) NOT NULL,
	id_cliente2 varchar(10) NOT NULL,
	CONSTRAINT "Amigo_pkey" PRIMARY KEY (id_cliente1, id_cliente2)
);
