CREATE TABLE public.origin (
	id uuid NOT NULL,
	integration_id varchar(50) NOT NULL,
	description varchar NOT NULL,
	created_at date NOT NULL,
	update_at date NOT NULL,
	CONSTRAINT origin_pk PRIMARY KEY (id),
	CONSTRAINT origin_integration_id_un UNIQUE (integration_id)
);