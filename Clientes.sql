Create table Cliente
(
	id_cliente varchar(10) NOT NULL,
	clave varchar(10) NOT NULL,
	CONSTRAINT "Cliente_pkey" PRIMARY KEY (id_cliente)
);

Create table SerAmigo(
	id_cliente1 varchar(10) NOT NULL,
	id_cliente2 varchar(10) NOT NULL,
	CONSTRAINT "Amigo_pkey" PRIMARY KEY (id_cliente1, id_cliente2),
	CONSTRAINT "Amigo1_fkey" FOREIGN KEY (id_cliente1)
      		REFERENCES Cliente (id_cliente) MATCH SIMPLE
      		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT "Amigo2_fkey" FOREIGN KEY (id_cliente2)
      		REFERENCES Cliente (id_cliente) MATCH SIMPLE
      		ON UPDATE CASCADE ON DELETE CASCADE
);

Create table Solicitude( --cliente1 envía solicitude a cliente2
	id_cliente1 varchar(10) NOT NULL,
	id_cliente2 varchar(10) NOT NULL,
        fecha timestamp,
	CONSTRAINT "Solicitude_pkey" PRIMARY KEY (id_cliente1, id_cliente2),
	
	CONSTRAINT "Solicitude1_fkey" FOREIGN KEY (id_cliente1)
      		REFERENCES Cliente (id_cliente) MATCH SIMPLE
      		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT "Solicitude2_fkey" FOREIGN KEY (id_cliente2)
      		REFERENCES Cliente (id_cliente) MATCH SIMPLE
      		ON UPDATE CASCADE ON DELETE CASCADE
);

insert into cliente values('antonio', 'ola');
insert into cliente values('pablo', 'ola');
insert into cliente values('pepe', 'ola');


insert into SerAmigo values('antonio', 'pablo');
insert into SerAmigo values('antonio', 'pepe');
insert into SerAmigo values('pepe', 'pablo');
